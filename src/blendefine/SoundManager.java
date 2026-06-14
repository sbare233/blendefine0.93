package blendefine;

import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaException;

import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 游戏音效管理器（单例模式）
 * 负责加载、播放和自动回收 AudioClip，支持同时播放同一音效的多个重叠实例。
 * 自动回收策略：
 * - 超过指定空闲时间（默认 2 分钟）未使用的音效会被移除
 * - 缓存总数超过最大容量（默认 100）时，按最后访问时间移除最旧的条目
 */
public final class SoundManager {

    // 单例实例
    private static final SoundManager INSTANCE = new SoundManager();

    // 默认配置
    private static final long DEFAULT_IDLE_TIMEOUT_MS = 2 * 60 * 1000; // 2分钟
    private static final int DEFAULT_MAX_CACHE_SIZE = 100;

    // 缓存结构
    private final ConcurrentHashMap<String, AudioClip> cache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Long> lastAccess = new ConcurrentHashMap<>();

    // 清理任务调度器
    private final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread t = new Thread(r, "SoundManager-Cleaner");
        t.setDaemon(true); // 守护线程，不阻止 JVM 退出
        return t;
    });

    private final long idleTimeoutMs;
    private final int maxCacheSize;

    /**
     * 私有构造器，启动自动清理任务
     * @param idleTimeoutMs 空闲超时时间（毫秒），超过此时间未访问的音效将被移除
     * @param maxCacheSize  最大缓存数量，超出时将按访问时间淘汰最久未使用的条目
     */
    private SoundManager(long idleTimeoutMs, int maxCacheSize) {
        this.idleTimeoutMs = idleTimeoutMs;
        this.maxCacheSize = maxCacheSize;
        startCleaner();
    }

    private SoundManager() {
        this(DEFAULT_IDLE_TIMEOUT_MS, DEFAULT_MAX_CACHE_SIZE);
    }

    /**
     * 获取单例实例
     */
    public static SoundManager getInstance() {
        return INSTANCE;
    }

    // ======================= 公共 API =======================

    /**
     * 预加载音效（如果已存在则不重复加载）
     * @param key  音效标识
     * @param resourcePath 资源路径（相对于类路径的根，例如 "/sounds/explosion.wav"）
     * @throws MediaException 如果加载失败
     */
    public void load(String key, String resourcePath) {
        if (cache.containsKey(key)) {
            touch(key);
            return;
        }
        AudioClip clip = loadClip(resourcePath);
        if (clip != null) {
            cache.put(key, clip);
            touch(key);
            evictIfNeeded();
        }
    }

    /**
     * 播放已加载的音效（使用默认音量、平衡和速率）
     * @param key 音效标识（必须已通过 load 预加载）
     * @throws IllegalArgumentException 如果音效未加载
     */
    public void play(String key) {
        AudioClip clip = getClip(key);
        if (clip != null) {
            clip.play();
            touch(key);
        } else {
            throw new IllegalArgumentException("Sound not loaded: " + key);
        }
    }

    /**
     * 播放音效，如果尚未加载则自动加载（使用默认音量、平衡和速率）
     * @param key          音效标识
     * @param resourcePath 资源路径（自动加载时使用，已加载则忽略）
     * @throws MediaException 如果自动加载失败
     */
    public void play(String key, String resourcePath) {
        if (!cache.containsKey(key)) {
            load(key, resourcePath);
        }
        play(key);
    }

    /**
     * 播放已加载的音效，并指定音量、平衡和速率
     * @param key     音效标识
     * @param volume  音量（0.0 ~ 1.0）
     * @param balance 平衡（-1.0 左声道，1.0 右声道）
     * @param rate    播放速率（0.5 ~ 2.0）
     * @see AudioClip#play(double, double, double, double, int)
     */
    public void play(String key, double volume, double balance, double rate) {
        AudioClip clip = getClip(key);
        if (clip != null) {
            clip.play(volume, balance, rate, 0, 1);
            touch(key);
        } else {
            throw new IllegalArgumentException("Sound not loaded: " + key);
        }
    }

    /**
     * 手动卸载音效，释放资源
     * @param key 音效标识
     */
    public void unload(String key) {
        @SuppressWarnings("unused")
		AudioClip removed = cache.remove(key);
        lastAccess.remove(key);
        // AudioClip 无 dispose 方法，失去强引用后 GC 会自行回收原生资源
    }

    /**
     * 停止并清理所有音效，关闭清理线程（通常在应用退出时调用）
     */
    public void shutdown() {
        cleaner.shutdownNow();
        cache.clear();
        lastAccess.clear();
    }

    // ======================= 内部辅助方法 =======================

    /**
     * 从资源路径加载 AudioClip
     * @param resourcePath 类路径资源（例如 "/sounds/click.wav"）
     * @return AudioClip 实例，失败时抛出 MediaException
     */
    private AudioClip loadClip(String resourcePath) {
        URL url = getClass().getResource(resourcePath);
        if (url == null) {
        	return null;
        }
        try {
            return new AudioClip(url.toString());
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return null;
    }

    /**
     * 获取已缓存的 AudioClip，若不存在则返回 null
     */
    private AudioClip getClip(String key) {
        return cache.get(key);
    }

    /**
     * 更新音效的最后访问时间
     */
    private void touch(String key) {
        lastAccess.put(key, System.currentTimeMillis());
    }

    /**
     * 检查并执行缓存淘汰（超出最大容量时淘汰最久未使用的条目）
     */
    private void evictIfNeeded() {
        if (cache.size() <= maxCacheSize) {
            return;
        }
        // 收集所有条目并按最后访问时间排序
        List<Map.Entry<String, Long>> entries = new ArrayList<>(lastAccess.entrySet());
        entries.sort(Comparator.comparingLong(Map.Entry::getValue));
        int toRemove = cache.size() - maxCacheSize;
        for (int i = 0; i < toRemove && i < entries.size(); i++) {
            String key = entries.get(i).getKey();
            cache.remove(key);
            lastAccess.remove(key);
        }
    }

    /**
     * 启动后台清理任务：定期移除空闲超时的音效
     */
    private void startCleaner() {
        cleaner.scheduleAtFixedRate(() -> {
            long now = System.currentTimeMillis();
            // 遍历快照避免并发修改异常
            List<String> expiredKeys = new ArrayList<>();
            for (Map.Entry<String, Long> entry : lastAccess.entrySet()) {
                if (now - entry.getValue() > idleTimeoutMs) {
                    expiredKeys.add(entry.getKey());
                }
            }
            for (String key : expiredKeys) {
                cache.remove(key);
                lastAccess.remove(key);
            }
        }, idleTimeoutMs, idleTimeoutMs / 2, TimeUnit.MILLISECONDS);
    }
}