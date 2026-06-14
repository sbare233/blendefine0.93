package blendefine;

import java.util.HashMap;

public class i18n {
	static int sw=(int) blendefine.screenwidth;
	static int sh=(int) blendefine.screenheight;
	static double suofang=blendefine.suofang;
	String[][] langs = {
		    {"中文",            "zh",   "-中文-"},
		    {"繁中",            "zht",   "-繁中-"},
		    {"日本語",          "ja",   "-日本語-"},
		    {"한국어",          "ko",   "-한국어-"},
		    {"Deutsch",        "de",   "-Deutsch-"},
		    {"English",        "en",   "-English-"},
		    {"Español",        "es",   "-Español-"},
		    {"Français",       "fr",   "-Français-"},
		    {"Português",      "pt",   "-Português-"},
		    {"Русский язык",   "ru",   "-Русский язык-"},
		};
	String[] hunyuanLingyin = {
		    "混元灵印",                 // 中文
		    "混元靈印",                 // 繁中
		    "「混元霊印」",              // 日语
		    "혼원령인",                 // 韩语
		    "Blendefine",   // 德语
		    "Blendefine",       // 英语
		    "Blendefine",  // 西班牙语
		    "Blendefine",   // 法语
		    "Blendefine",   // 葡萄牙语
		    "Блендефайн" // 俄语
		};
	String[] startGame = {
		    "开始游戏",           // 中文
		    "開始遊戲",           // 繁中
		    "ゲーム開始",         // 日语
		    "게임 시작",          // 韩语
		    "Spiel starten",     // 德语
		    "Start Game",        // 英语
		    "Iniciar juego",     // 西班牙语
		    "Commencer le jeu",  // 法语
		    "Iniciar jogo",      // 葡萄牙语
		    "Начать игру"        // 俄语
		};
	String[][] opentip = {
		    // 中文 (zh)
		    {
		        "游戏出现的事物与情节为虚构，请勿模仿，雷同纯属巧合。",
		        "游戏中有一些光效变化，如有不适请停止游戏。",
		        "JavaFX 制作"
		    },
		    // 繁中 (zht)
		    {
		        "遊戲出現的事物與情節為虛構，請勿模仿，雷同純屬巧合。",
		        "遊戲中有一些光效變化，如有不適請停止遊戲。",
		        "JavaFX 製作"
		    },
		    // 日语 (ja)
		    {
		        "ゲーム内の出来事やストーリーはすべて架空のものです。絶対に真似しないでください。実在の人物・団体などと類似していても、それはすべて偶然です。",
		        "ゲームには一部、光の効果が変化するシーンが含まれます。もし気分が悪くなった場合は、ただちにプレイを中止してください。",
		        "JavaFX で作成"
		    },
		    // 韩语 (ko)
		    {
		        "게임에 등장하는 사물과 이야기는 가상입니다. 모방하지 마십시오. 우연의 일치일 뿐입니다.",
		        "게임에는 일부 빛 효과 변화가 있습니다. 불편하시면 게임을 중단하세요.",
		        "JavaFX로 제작"
		    },
		    // 德语 (de)
		    {
		        "Alle im Spiel dargestellten Ereignisse und Handlungen sind frei erfunden. Bitte nicht nachahmen. Ähnlichkeiten mit realen Personen oder Vorfällen sind rein zufällig.",
		        "Das Spiel enthält einige Lichteffekte. Wenn Sie sich unwohl fühlen, brechen Sie das Spiel bitte sofort ab.",
		        "Mit JavaFX erstellt"
		    },
		    // 英语 (en)
		    {
		        "All events and stories depicted in the game are fictional. Do not imitate them. Any resemblance to real persons or incidents is purely coincidental.",
		        "The game contains some light effect changes. If you feel unwell, please stop playing immediately.",
		        "Made With JavaFX"
		    },
		    // 西班牙语 (es)
		    {
		        "Todos los eventos e historias que aparecen en el juego son ficticios. No los imites. Cualquier similitud con la realidad es mera coincidencia.",
		        "El juego incluye algunos cambios de efectos de luz. Si te sientes mal, deja de jugar inmediatamente.",
		        "Hecho con JavaFX"
		    },
		    // 法语 (fr)
		    {
		        "Tous les événements et intrigues présentés dans le jeu sont fictifs. Ne les imitez pas. Toute ressemblance avec des personnes ou des faits réels serait purement fortuite.",
		        "Le jeu contient quelques variations d'effets lumineux. En cas de malaise, cessez immédiatement de jouer.",
		        "Fabriqué avec JavaFX"
		    },
		    // 葡萄牙语 (pt)
		    {
		        "Todos os eventos e histórias do jogo são fictícios. Não os imite. Qualquer semelhança com a realidade é mera coincidência.",
		        "O jogo contém algumas mudanças de efeitos de luz. Se se sentir mal, pare de jogar imediatamente.",
		        "Feito com JavaFX"
		    },
		    // 俄语 (ru)
		    {
		        "Все события и сюжеты, представленные в игре, являются вымышленными. Не подражайте им. Любые совпадения с реальными людьми или событиями случайны.",
		        "В игре присутствуют изменения световых эффектов. Если вы почувствуете недомогание, немедленно прекратите игру.",
		        "Сделано с JavaFX"
		    }
		};

	String[][] fushis = {
		    // 中文（zh）
		    { "抓取", "围转", "释放", "停止", "起始", "循环", "前进", "后退", "复制", "删除" },
		    // 繁中（zht）
		    { "抓取", "圍轉", "釋放", "停止", "起始", "循環", "前進", "後退", "複製", "刪除" },
		    // 日语（ja）
		    { "採石", "旋回", "放出", "中止", "開始", "複製", "ゼロ", "エクストリーム", "コピー", "削除" },
		    // 韩语（ko）
		    { "잡기", "회전", "방출", "정지", "시작", "순환", "전진", "후진", "복사", "삭제" },
		    // 德语（de）
		    { "Abbau","Wirbel","Ausstoßen","Abbrechen","Start","Duplizieren","Null","Extrem","Kopieren","Entfernen" },
		    // 英文（en）
		    { "Quarry", "Whirl", "Emit", "Abort", "Start", "Duplicate", "Zero", "Xtreme", "Copy", "Remove" },
		    // 西班牙语（es）
		    { "Cantera", "Remolino", "Emitir", "Abortar", "Iniciar", "Duplicar", "Cero", "Extremo", "Copiar","Eliminar" },
		    // 法语（fr）
		    { "Carrière","Tourbillon","Émettre","Abandonner","Démarrer","Dupliquer","Zéro","Extrême","Copier","Supprimer"},
		    // 葡萄牙语（pt）
		    { "Pedreira", "Redemoinho", "Emitir", "Abortar", "Iniciar", "Duplicar", "Zero", "Extremo","Copiar","Remover"},
		    // 俄语（ru）
		    { "Карьер","Вихрь","Выпускать","Прервать","Старт","Дублировать","Ноль","Экстрим","Копировать","Удалить" }
		};
	
	String[] jiaochengname = {
		    "回忆",        // 中文
		    "回憶",        // 繁中
		    "回想",        // 日语
		    "회상",        // 韩语
		    "Erinnerung", // 德语
		    "Recall",     // 英语
		    "Recuerdo",   // 西班牙语
		    "Rappel",     // 法语
		    "Recordação", // 葡萄牙语
		    "Воспоминание" // 俄语
		};

	String[] jiaochengh = {
		    "右键拖动？可以查看对应物品帮助",
		    "右鍵拖動？可以查看對應物品幫助",
		    "右クリックでドラッグ？対応するアイテムのヘルプを表示できます",
		    "오른쪽 클릭 드래그? 해당 아이템 도움말을 볼 수 있습니다",
		    "Rechtsklick ziehen? Hilfe zum entsprechenden Gegenstand anzeigen",
		    "Right-click drag? You can view help for the corresponding item",
		    "¿Arrastrar con clic derecho? Puedes ver la ayuda del objeto correspondiente",
		    "Faire glisser avec le bouton droit ? Vous pouvez consulter l'aide de l'objet correspondant",
		    "Arrastar com o botão direito? Você pode ver a ajuda do item correspondente",
		    "Перетаскивание правой кнопкой? Вы можете просмотреть справку по соответствующему предмету"
		};
	
	String[][] jiaochengd = {
		    // 中文 (zh)
		    {
		        "设置面板，左键拖动或者按左键或者按ESC键打开",
		        "混元真经，左键拖动或者按左键打开，选择关卡",
		        "灵印供应器，左键拖动到灵盘上，用龙爪抓取",
		        "灵盒，展示需要合成的灵印设计，右键其中的灵印可以查看合成表",
		        "青龙爪，用来抓取灵印，左键点击底座可以设置初始抓取位置，适用QWE指令",
		        "朱雀炉，用来连接不相生的灵印",
		        "白虎口，销毁六格范围内的灵印",
		        "玄武路，可以用水流运输龙爪，适用ZX指令",
		        "混元器，可以用阴或阳灵印与令一个灵印转化为相生的灵印，",
		        "五脏凝练炉，可以用最多五个灵印，按照合成表中的顺序顺时针放入，合成目标灵印",
		        "六合周天混元器，可以用灵印直接在选定位置转换为相生链上对应位置的灵印",
		        "灵盘，左键或者WASD按键可以拖动，滚轮或者QE按键可以缩放，中键删除，右键拖动框选，C键复制",
		        "虚空镜，左键点击或者按空格键用来暂停时序和启动时序",
		        "时序盘，左键可以上下左右拖动，将指令拖到机器对应颜色的时序条中编入指令集",
		        "云蝠拨，左键可以左右拖动，查看对应时序发生的操作",
		        "提示框，显示当前放置的指令",
		        "Q指令，使龙爪抓取当前格的灵印，或从供应器取出一个灵印",
		        "W指令，左键点击W指令调整龙爪的目标位置和角度",
		        "E指令，使龙爪放下当前抓取的灵印",
		        "A指令，停止机器，适用朱雀炉，混元器，五脏凝练炉，六合周天混元器",
		        "S指令，启动机器，适用范围与A相同",
		        "D指令，从放置位置开始循环运行右键框选的指令",
		        "Z指令，使玄武路朝黑色位置流动，带动水路中的龙爪",
		        "X指令，使玄武路朝白色位置流动",
		        "C指令，复制右键框选过的灵印"
		    },
		    // 繁中 (zht)
		    {
		        "設定面板，左鍵拖動或者按左鍵或者按ESC鍵打開",
		        "混元真經，左鍵拖動或者按左鍵打開，選擇關卡",
		        "靈印供應器，左鍵拖動到靈盤上，用龍爪抓取",
		        "靈盒，展示需要合成的靈印設計，右鍵其中的靈印可以查看合成表",
		        "青龍爪，用來抓取靈印，左鍵點擊底座可以設置初始抓取位置，適用QWE指令",
		        "朱雀爐，用來連接不相生的靈印",
		        "白虎口，銷毀六格範圍內的靈印",
		        "玄武路，可以用水流運輸龍爪，適用ZX指令",
		        "混元器，可以用陰或陽靈印與令一個靈印轉化為相生的靈印，",
		        "五臟凝練爐，可以用最多五個靈印，按照合成表中的順時針放入，合成目標靈印",
		        "六合周天混元器，可以用靈印直接在選定位置轉換為相生鏈上對應位置的靈印",
		        "靈盤，左鍵或者WASD按鍵可以拖動，滾輪或者QE按鍵可以縮放，中鍵刪除，右鍵拖動框選，C鍵複製",
		        "虛空鏡，左鍵點擊或者按空格鍵用來暫停時序和啟動時序",
		        "時序盤，左鍵可以上下左右拖動，將指令拖到機器對應顏色的時序條中編入指令集",
		        "雲蝠撥，左鍵可以左右拖動，查看對應時序發生的操作",
		        "提示框，顯示當前放置的指令",
		        "Q指令，使龍爪抓取當前格的靈印，或從供應器取出一個靈印",
		        "W指令，左鍵點擊W指令調整龍爪的目標位置和角度",
		        "E指令，使龍爪放下當前抓取的靈印",
		        "A指令，停止機器，適用朱雀爐，混元器，五臟凝練爐，六合周天混元器",
		        "S指令，啟動機器，適用範圍與A相同",
		        "D指令，從放置位置開始循環運行右鍵框選的指令",
		        "Z指令，使玄武路朝黑色位置流動，帶動水路中的龍爪",
		        "X指令，使玄武路朝白色位置流動",
		        "C指令，複製右鍵框選過的靈印"
		    },
		    // 日本語 (ja)
		    {
		        "設定パネル：左ドラッグ、左クリック、またはESCキーで開く",
		        "混元真経：左ドラッグまたは左クリックで開き、レベルを選択",
		        "シギル供給器：シギルを左ドラッグで霊盤に移動、龍爪で掴む",
		        "シギルボックス：合成すべきシギルの設計図を表示、右クリックで合成表を表示",
		        "青龍爪：シギルを掴む用。土台を左クリックで初期掴み位置設定、QWE指令対応",
		        "朱雀炉：相生でないシギルを接続",
		        "白虎口：6マス範囲内のシギルを破壊",
		        "玄武路：水流で龍爪を運搬可能、ZX指令対応",
		        "混元器：陰または陽シギルと他のシギルを相生のシギルに変換",
		        "五臓凝練炉：最大5個のシギルを合成表順に時計回りに配置、目標シギルを合成",
		        "六合周天混元器：シギルを選択位置で相生チェーンの対応位置のシギルに直接変換",
		        "霊盤：左クリックまたはWASDキーでドラッグ、ホイールまたはQEキーでズーム、中クリックで削除、右クリックドラッグで範囲選択、Cキーでコピー",
		        "虚鏡：左クリックまたはスペースキーでタイミング一時停止・再開",
		        "タイミング盤：左ドラッグで上下左右に移動、指令を機械の対応色タイミング枠へドラッグして命令セットに登録",
		        "雲蝠撥：左ドラッグで左右に移動、対応タイミングの動作を確認",
		        "ヒント枠：現在配置した指令を表示",
		        "Q指令：龍爪が現在マスのシギルを掴む、または供給器から取り出す",
		        "W指令：左クリックで龍爪の目標位置と角度を調整",
		        "E指令：龍爪が掴んでいるシギルを置く",
		        "A指令：機械停止（朱雀炉、混元器、五臓凝練炉、六合周天混元器に適用）",
		        "S指令：機械起動（Aと同じ範囲）",
		        "D指令：配置位置から右クリックで範囲選択した指令を循環実行",
		        "Z指令：玄武路を黒色位置へ流し、水路中の龍爪を移動",
		        "X指令：玄武路を白色位置へ流す",
		        "C指令：右クリックで範囲選択したシギルを複製"
		    },
		    // 한국어 (ko)
		    {
		        "설정 패널: 왼쪽 드래그 또는 왼쪽 클릭 또는 ESC 키로 열기",
		        "혼원진경: 왼쪽 드래그 또는 왼쪽 클릭으로 열고 레벨 선택",
		        "시길 공급기: 시길을 왼쪽 드래그로 영반 위에 놓고 용발로 집기",
		        "시길 상자: 합성할 시길 설계도 표시, 우클릭으로 합성표 확인",
		        "청룡발: 시길을 집는 도구. 받침대를 왼쪽 클릭으로 초기 집기 위치 설정, QWE 명령어 적용",
		        "주작로: 상생하지 않는 시길 연결",
		        "백호구: 6칸 범위 내 시길 파괴",
		        "현무로: 물길로 용발 운반 가능, ZX 명령어 적용",
		        "혼원기: 음 또는 양 시길과 다른 시길을 상생하는 시길로 변환",
		        "오장응련로: 최대 5개 시길을 합성표 순서대로 시계 방향으로 배치하여 목표 시길 합성",
		        "육합주천혼원기: 시길을 선택 위치에서 상생 체인의 해당 위치 시길로 직접 변환",
		        "영반: 왼쪽 클릭 또는 WASD 키로 드래그, 휠 또는 QE 키로 줌, 가운데 버튼 클릭으로 삭제, 오른쪽 클릭 드래그로 영역 선택, C 키로 복사",
		        "허공경: 왼쪽 클릭 또는 스페이스바로 타이밍 일시정지 및 시작",
		        "타이밍반: 왼쪽 드래그로 상하좌우 이동, 명령어를 기계의 해당 색상 타이밍 슬롯으로 드래그하여 명령어 세트에 등록",
		        "운복발: 왼쪽 드래그로 좌우 이동, 해당 타이밍의 동작 확인",
		        "힌트 상자: 현재 배치된 명령어 표시",
		        "Q 명령어: 용발이 현재 칸의 시길을 집거나 공급기에서 시길 하나를 꺼냄",
		        "W 명령어: 왼쪽 클릭으로 용발의 대상 위치와 각도 조정",
		        "E 명령어: 용발이 집은 시길 내려놓기",
		        "A 명령어: 기계 정지 (주작로, 혼원기, 오장응련로, 육합주천혼원기에 적용)",
		        "S 명령어: 기계 시작 (A와 동일한 범위)",
		        "D 명령어: 배치 위치에서 우클릭으로 범위 선택한 명령어를 순환 실행",
		        "Z 명령어: 현무로가 검은색 위치로 흘러가 수로의 용발을 이동",
		        "X 명령어: 현무로가 흰색 위치로 흐름",
		        "C 명령어: 우클릭으로 범위 선택한 시길 복제"
		    },
		    // Deutsch (de)
		    {
		        "Einstellungen: per Linksdrag, Linksklick oder ESC öffnen",
		        "Hunyuan-Klassiker: per Linksdrag/Linksklick öffnen, Level wählen",
		        "Sigil-Spender: Sigil per Linksdrag auf Sigil-Scheibe ziehen, mit Drachenklaue greifen",
		        "Sigil-Kiste: zeigt zu synthetisierende Sigil-Designs, Rechtsklick auf Sigil zeigt Rezept",
		        "Azur-Drachenklaue: greift Sigil, Linksklick auf Sockel setzt Startposition, QWE-Befehle",
		        "Vermilion-Ofen: verbindet nicht-erzeugende Sigil",
		        "Weißer Tigerrachen: zerstört Sigil in 6er-Radius",
		        "Schwarzschildkröten-Pfad: transportiert Drachenklaue mit Wasserstrom, unterstützt ZX-Befehle",
		        "Hunyuan-Gerät: wandelt Yin- oder Yang-Sigil + anderes Sigil in erzeugendes Sigil um",
		        "Fünf-Eingeweide-Schmelze: bis zu 5 Sigil im Uhrzeigersinn nach Rezept anordnen, Ziel-Sigil synthetisieren",
		        "Liuhe-Zhoutian-Hunyuan: wandelt Sigil direkt in erzeugende Kettensigil an gewählter Position",
		        "Sigil-Scheibe: Mit Linksklick oder den WASD-Tasten ziehen, mit Mausrad oder QE zoomen, Mittelklick löscht, Rechtsklick-Ziehen markiert einen Bereich, C kopiert",
		        "Leerenspiegel: Linksklick oder Leertaste pausiert/stattet Timing",
		        "Timing-Scheibe: Linksdrag bewegt Befehle in farbige Timing-Leisten der Maschine",
		        "Wolkenfledermaus-Regler: Linksdrag zeigt Aktionen des gewählten Timings",
		        "Hinweisfeld: zeigt aktuell platzierten Befehl",
		        "Q-Befehl: Drachenklaue greift Sigil auf aktuellem Feld oder nimmt eines vom Spender",
		        "W-Befehl: Linksklick auf W passt Zielposition und -winkel der Drachenklaue an",
		        "E-Befehl: Drachenklaue legt gegriffenes Sigil ab",
		        "A-Befehl: stoppt Maschine (Vermilion-Ofen, Hunyuan, Fünf-Eingeweide, Liuhe-Zhoutian)",
		        "S-Befehl: startet Maschine (gleiche Geräte wie A)",
		        "D-Befehl: startet Schleife über rechts angeklickte Befehle ab Platzierungsposition",
		        "Z-Befehl: Schwarzschildkröten-Pfad fließt Richtung schwarze Position, bewegt Klaue im Wasserweg",
		        "X-Befehl: Schwarzschildkröten-Pfad fließt Richtung weiße Position",
		        "C-Befehl: kopiert rechts angeklicktes Sigil"
		    },
		    // English (en)
		    {
		        "Settings panel: left-click drag, left-click, or press ESC to open",
		        "Hunyuan Scripture: left-click drag or left-click to open, select level",
		        "Sigil supplier: left-click drag onto Sigil Disk, grab with Dragon Claw",
		        "Sigil Box: shows sigil designs needed for synthesis, right-click a sigil to view recipe",
		        "Azure Dragon Claw: used to grab sigils, left-click base to set initial grab position, uses QWE commands",
		        "Vermilion Furnace: connects non-generating sigils",
		        "White Tiger Maw: destroys sigils within a 6-cell radius",
		        "Black Tortoise Path: transports Dragon Claw via water flow, supports ZX commands",
		        "Hunyuan Device: converts Yin or Yang sigil + another sigil into a generating sigil",
		        "Five Viscera Refining Furnace: place up to 5 sigils clockwise according to recipe, synthesize target sigil",
		        "Liuhe Zhoutian Hunyuan: directly converts a sigil at a selected position into the corresponding sigil on a generating chain",
		        "Sigil Disk: drag with left click or WASD keys, zoom with scroll wheel or QE keys, middle click deletes, right-click drag to select area, C to copy",
		        "Void Mirror: left-click or press Space to pause/unpause timing",
		        "Timing Disk: left-click drag up/down/left/right to drag commands into the machine's matching color timing slots to compile an instruction set",
		        "Cloud Bat Plectrum: left-click drag left/right to view actions occurring at the corresponding timing",
		        "Tooltip box: displays the currently placed command",
		        "Q command: makes Dragon Claw grab the sigil on the current cell, or take one sigil from the supplier",
		        "W command: left-click W command to adjust Dragon Claw's target position and angle",
		        "E command: makes Dragon Claw release the currently held sigil",
		        "A command: stops the machine (applicable to Vermilion Furnace, Hunyuan Device, Five Viscera Furnace, Liuhe Zhoutian Hunyuan)",
		        "S command: starts the machine (same applicable range as A)",
		        "D command: loop-run commands selected by right-click drag, starting from the placement position",
		        "Z command: makes Black Tortoise Path flow toward the black position, moving Dragon Claws in the waterway",
		        "X command: makes Black Tortoise Path flow toward the white position",
		        "C command: copies the sigil selected by right-click drag"
		    },
		    // Español (es)
		    {
		        "Panel ajustes: arrastrar clic izq, clic izq o ESC para abrir",
		        "Escritura Hunyuan: arrastrar clic izq o clic izq para abrir, seleccionar nivel",
		        "Proveedor de sigilos: arrastrar con clic izq al Disco de Sigilos, agarrar con Garra Dragón",
		        "Caja de Sigilos: muestra diseños necesarios, clic der en sigilo para ver receta",
		        "Garra Dragón Azur: agarra sigilos, clic izq en base fija posición inicial, usa comandos QWE",
		        "Horno Vermellón: conecta sigilos no generadores",
		        "Fauces Tigre Blanco: destruye sigilos en radio de 6 celdas",
		        "Camino Tortuga Negra: transporta Garra Dragón con flujo de agua, compatible con comandos ZX",
		        "Dispositivo Hunyuan: convierte sigilo Yin/Yang + otro en sigilo generador",
		        "Horno Refinador de Cinco Vísceras: coloca hasta 5 sigilos en orden horario según receta, sintetiza objetivo",
		        "Hunyuan Liuhe Zhoutian: convierte sigilo directamente en el de la cadena generadora en posición elegida",
		        "Disco de Sigilos: arrastrar con clic izquierdo o teclas WASD, hacer zoom con la rueda o teclas QE, clic medio elimina, arrastrar con clic derecho para seleccionar área, tecla C para copiar",
		        "Espejo Vacío: clic izq o Espacio pausa/reanuda tiempo",
		        "Disco Temporal: arrastrar clic izq mueve comandos a ranuras de color de máquina para compilar",
		        "Plectro Murciélago Nube: arrastrar clic izq muestra acciones del tiempo correspondiente",
		        "Cuadro de ayuda: muestra comando colocado actualmente",
		        "Comando Q: Garra Dragón agarra sigilo en celda actual o toma uno del proveedor",
		        "Comando W: clic izq en W ajusta posición y ángulo objetivo de Garra",
		        "Comando E: Garra suelta sigilo agarrado",
		        "Comando A: para máquina (Horno Vermellón, Hunyuan, Horno Vísceras, Hunyuan Liuhe)",
		        "Comando S: inicia máquina (mismo rango que A)",
		        "Comando D: ejecuta en bucle comandos seleccionados con clic der desde posición de colocación",
		        "Comando Z: Camino Tortuga Negra fluye hacia posición negra, moviendo Garras en el canal de agua",
		        "Comando X: Camino Tortuga Negra fluye hacia posición blanca",
		        "Comando C: copia sigilo seleccionado con clic der"
		    },
		    // Français (fr)
		    {
		        "Panneau paramètres : glisser clic gauche, clic gauche ou ESC pour ouvrir",
		        "Écriture Hunyuan : glisser clic gauche ou clic gauche pour ouvrir, choisir niveau",
		        "Distributeur de sigils : glisser avec clic gauche sur le Disque à Sigils, saisir avec Griffe de Dragon",
		        "Boîte à Sigils : montre les conceptions nécessaires, clic droit sur un sigil pour voir la recette",
		        "Griffe de Dragon Azur : saisit les sigils, clic gauche sur le socle fixe la position initiale, utilise commandes QWE",
		        "Four Vermillon : connecte les sigils non-générateurs",
		        "Gueule de Tigre Blanc : détruit les sigils dans un rayon de 6 cellules",
		        "Chemin Tortue Noire : transporte la Griffe de Dragon par flux d'eau, compatible commandes ZX",
		        "Dispositif Hunyuan : convertit sigil Yin/Yang + un autre en sigil générateur",
		        "Four d'Affinage des Cinq Viscères : placez jusqu'à 5 sigils dans l'ordre horaire selon la recette, synthétisez le sigil cible",
		        "Hunyuan Liuhe Zhoutian : convertit directement un sigil à une position choisie en celui de la chaîne génératrice",
		        "Disque à Sigils : glisser avec clic gauche ou touches WASD, zoomer avec molette ou touches QE, clic central supprime, glisser avec clic droit pour sélectionner une zone, touche C pour copier",
		        "Miroir du Vide : clic gauche ou Espace pour suspendre/reprendre le séquencement",
		        "Disque de Séquencement : glisser clic gauche pour déplacer les commandes dans les fentes couleur de la machine afin de compiler",
		        "Plectre Chauve-souris de Nuage : glisser clic gauche pour voir les actions du séquencement correspondant",
		        "Zone d'infobulle : affiche la commande actuellement posée",
		        "Commande Q : la Griffe de Dragon saisit le sigil dans la cellule courante ou en prend un depuis le distributeur",
		        "Commande W : clic gauche sur W ajuste la position et l'angle cible de la Griffe",
		        "Commande E : la Griffe relâche le sigil saisi",
		        "Commande A : arrête la machine (Four Vermillon, Hunyuan, Four Viscères, Hunyuan Liuhe)",
		        "Commande S : démarre la machine (même périmètre que A)",
		        "Commande D : exécute en boucle les commandes sélectionnées par clic droit en partant de la position posée",
		        "Commande Z : le Chemin Tortue Noire s'écoule vers la position noire, déplaçant les Griffes dans le canal",
		        "Commande X : le Chemin Tortue Noire s'écoule vers la position blanche",
		        "Commande C : copie le sigil sélectionné par clic droit"
		    },
		    // Português (pt)
		    {
		        "Painel definições: arrastar com botão esq, clicar esq ou ESC para abrir",
		        "Escritura Hunyuan: arrastar com botão esq ou clicar esq para abrir, selecionar nível",
		        "Fornecedor de Sigilos: arrastar com botão esq para o Disco de Sigilos, agarrar com Garra de Dragão",
		        "Caixa de Sigilos: mostra designs necessários, clique direito num sigilo para ver receita",
		        "Garra Dragão Azul: agarra sigilos, clique esq na base define posição inicial, usa comandos QWE",
		        "Forno Vermelhão: conecta sigilos não-geradores",
		        "Boca Tigre Branco: destrói sigilos num raio de 6 células",
		        "Caminho Tartaruga Negra: transporta Garra de Dragão com fluxo de água, compatível com comandos ZX",
		        "Dispositivo Hunyuan: converte sigilo Yin/Yang + outro sigilo em sigilo gerador",
		        "Forno Refinador dos Cinco Visceras: coloque até 5 sigilos no sentido horário conforme receita, sintetiza sigilo alvo",
		        "Hunyuan Liuhe Zhoutian: converte diretamente um sigilo na posição selecionada no sigilo correspondente da cadeia geradora",
		        "Disco de Sigilos: arrastar com clique esquerdo ou teclas WASD, ampliar com roda ou teclas QE, clique médio exclui, arrastar com clique direito para selecionar área, tecla C para copiar",
		        "Espelho Vazio: clique esq ou Espaço pausa/retoma o temporização",
		        "Disco de Temporização: arrastar com botão esq move comandos para as ranhuras coloridas da máquina para compilar",
		        "Plectro Morcego Nuvem: arrastar com botão esq para ver ações da temporização correspondente",
		        "Caixa de dica: mostra o comando atualmente colocado",
		        "Comando Q: Garra de Dragão agarra o sigilo na célula atual ou retira um do fornecedor",
		        "Comando W: clique esq no W ajusta posição e ângulo alvo da Garra",
		        "Comando E: Garra solta o sigilo agarrado",
		        "Comando A: para a máquina (Forno Vermelhão, Hunyuan, Forno Visceras, Hunyuan Liuhe)",
		        "Comando S: inicia a máquina (mesmo escopo do A)",
		        "Comando D: executa em loop comandos selecionados com clique direito a partir da posição colocada",
		        "Comando Z: Caminho Tartaruga Negra flui para a posição preta, movendo Garras no canal de água",
		        "Comando X: Caminho Tartaruga Negra flui para a posição branca",
		        "Comando C: copia o sigilo selecionado com clique direito"
		    },
		 // Русский (ru)
		    {
		        "Панель настроек: перетаскивание левой кнопкой, левый клик или ESC для открытия",
		        "Хунь Юань Чжэньцзин: перетащите левой кнопкой или кликните, чтобы открыть, выберите уровень",
		        "Податчик сигилов: перетащите сигил левой кнопкой на Диск сигилов, захватите Когтем Дракона",
		        "Коробка сигилов: показывает чертежи для синтеза, правый клик по сигилу показывает рецепт",
		        "Лазурный Коготь Дракона: захватывает сигилы, левый клик на основании устанавливает начальную позицию, использует команды QWE",
		        "Печь Вермилиона: соединяет несоздающие сигилы, уже соединённые сигилы через один ход контактируют с окружающими",
		        "Пасть Белого Тигра: уничтожает сигилы в радиусе 6 клеток",
		        "Путь Чёрной Черепахи: перевозит Коготь Дракона потоком воды, поддерживает команды ZX",
		        "Устройство Хунь Юань: превращает сигил Инь или Ян + другой сигил в создающий сигил",
		        "Печь Пяти Внутренностей: поместите до 5 сигилов по часовой стрелке согласно рецепту, синтез целевого сигила",
		        "Хунь Юань Люхэ Чжоутянь: напрямую превращает сигил в выбранной позиции в соответствующий сигил на создающей цепочке",
		        "Диск сигилов: перетаскивание левой кнопкой мыши или клавишами WASD, масштабирование колёсиком или клавишами QE, средняя кнопка удаляет, перетаскивание правой кнопкой выделяет область, клавиша C копирует",
		        "Зеркало Пустоты: левый клик или пробел ставит на паузу / возобновляет тайминг",
		        "Диск времени: перетащите левой кнопкой команды в цветные слоты машины для компиляции",
		        "Регулятор Летучей Мыши: перетаскивание левой кнопкой влево/вправо показывает действия в соответствующем такте",
		        "Облако подсказки: показывает текущую размещённую команду",
		        "Команда Q: Коготь Дракона захватывает сигил в текущей клетке или берёт один из податчика",
		        "Команда W: левый клик по W регулирует целевую позицию и угол Когтя",
		        "Команда E: Коготь Дракона отпускает захваченный сигил",
		        "Команда A: останавливает машину (Печь Вермилиона, Устройство Хунь Юань, Печь Пяти Внутренностей, Хунь Юань Люхэ)",
		        "Команда S: запускает машину (те же, что и A)",
		        "Команда D: запускает циклическое выполнение команд, выделенных правой кнопкой, начиная с позиции размещения",
		        "Команда Z: Путь Чёрной Черепахи течёт к чёрной позиции, перемещая Когти в водном канале",
		        "Команда X: Путь Чёрной Черепахи течёт к белой позиции",
		        "Команда C: копирует сигил, выделенный правым кликом"
		    }
		};
	
	int[][] jiaopos0= {
			{sw/2,sh/2,1},
			{sw/2,sh/2,1},
			{(int)(40*suofang),sh-(int)(140*suofang),7},
			{sw-(int)(40*suofang),sh-(int)(140*suofang),3},
			{sw/2-(int)(140*suofang),sh/2,7},
			{sw/2,sh/2,1},
			{(int)(40*suofang),sh/2,7}
	};
	
	String[][] jiaocheng0 = {
		    // 中文 (zh)
		    {
		        "好久没炼制，生疏了啊（请点击此处）",
		        "今天再看一遍十二术吧",
		        "先在书中找到对应记载",
		        "然后打开灵盒摆出灵印设计",
		        "感应到灵印设计连通，星石会闪光",
		        "（点击星石）就可以开始合成灵印了",
		        "（点击左侧的？可以重新查看教程）"
		    },
		    // 繁中 (zht)
		    {
		        "好久沒煉製，生疏了啊（請點擊此處）",
		        "今天再看一遍十二術吧",
		        "先在書中找到對應記載",
		        "然後打開靈盒擺出靈印設計",
		        "感應到靈印設計連通，星石會閃光",
		        "（點擊星石）就可以開始合成靈印了",
		        "（點擊左側的？可以重新查看教程）"
		    },
		    // 日语 (ja)
		    {
		        "長時間練成していなくて、腕が鈍ったな（ここをクリック）",
		        "今日もう一度『十二術』を見てみよう",
		        "まず書物で該当する記載を見つけて",
		        "それから霊箱を開き、霊印デザインを配置する",
		        "霊印デザインが接続されていることを感知すると、星石が光る",
		        "（星石をクリック）で霊印の合成を開始できます",
		        "（左側の？をクリックすると、チュートリアルを再表示できます）"
		    },
		    // 韩语 (ko)
		    {
		        "오랜만에 제련해서 서툴렀네 (여기를 클릭하세요)",
		        "오늘 다시 한번 '십이술'을 보자",
		        "먼저 책에서 해당 기록을 찾아라",
		        "그런 다음 영함을 열고 영인 디자인을 배치하라",
		        "영인 디자인이 연결된 것을 감지하면 성석이 반짝인다",
		        "(성석을 클릭하면) 영인 합성을 시작할 수 있다",
		        "(왼쪽의 ?를 클릭하면 튜토리얼을 다시 볼 수 있다)"
		    },
		    // 德语 (de)
		    {
		        "Lange nicht mehr hergestellt, eingerostet (bitte hier klicken)",
		        "Schau dir heute noch einmal die 'Zwölf Künste' an",
		        "Finde zuerst die entsprechende Aufzeichnung im Buch",
		        "Öffne dann die Geisterbox und lege das Siegel-Design an",
		        "Wenn das Siegel-Design verbunden ist, leuchtet der Sternenstein",
		        "(Klicke auf den Sternenstein) um mit der Synthese des Siegels zu beginnen",
		        "(Klicke auf das ? links, um das Tutorial erneut anzuzeigen)"
		    },
		    // 英语 (en)
		    {
		        "Haven't refined for a long time, I'm out of practice (click here)",
		        "Let's review the 'Twelve Arts' once more today",
		        "First, find the corresponding record in the book",
		        "Then open the spirit box and arrange the sigil design",
		        "When the sigil design is connected, the star stone will flash",
		        "(Click the star stone) to start synthesizing the sigil",
		        "(Click the ? on the left to review the tutorial)"
		    },
		    // 西班牙语 (es)
		    {
		        "Hace mucho que no refino, estoy oxidado (haga clic aquí)",
		        "Repasemos hoy una vez más las 'Doce Artes'",
		        "Primero, encuentra el registro correspondiente en el libro",
		        "Luego abre la caja espiritual y coloca el diseño del sello",
		        "Cuando el diseño del sello esté conectado, la piedra estelar brillará",
		        "(Haga clic en la piedra estelar) para comenzar a sintetizar el sello",
		        "(Haga clic en el ? de la izquierda para volver a ver el tutorial)"
		    },
		    // 法语 (fr)
		    {
		        "Ça fait longtemps que je n'ai pas raffiné, je suis rouillé (cliquez ici)",
		        "Revois aujourd'hui les 'Douze Arts'",
		        "Trouve d'abord la mention correspondante dans le livre",
		        "Ouvre ensuite la boîte spirituelle et dispose le motif du sceau",
		        "Quand le motif du sceau est connecté, la pierre d'étoile scintille",
		        "(Cliquez sur la pierre d'étoile) pour commencer la synthèse du sceau",
		        "(Cliquez sur le ? à gauche pour revoir le tutoriel)"
		    },
		    // 葡萄牙语 (pt)
		    {
		        "Há muito tempo não refino, estou enferrujado (clique aqui)",
		        "Vamos revisar as 'Doze Artes' novamente hoje",
		        "Primeiro, encontre o registro correspondente no livro",
		        "Depois abra a caixa espiritual e disponha o design do selo",
		        "Quando o design do selo estiver conectado, a pedra estelar brilhará",
		        "(Clique na pedra estelar) para começar a sintetizar o selo",
		        "(Clique no ? à esquerda para reexibir o tutorial)"
		    },
		    // 俄语 (ru)
		    {
		        "Давно не занимался очищением, заржавел (нажмите здесь)",
		        "Сегодня ещё раз просмотрим 'Двенадцать искусств'",
		        "Сначала найди соответствующую запись в книге",
		        "Затем открой духовую шкатулку и выложи дизайн печати",
		        "Когда дизайн печати подключён, звездный камень засветится",
		        "(Нажмите на звездный камень), чтобы начать синтез печати",
		        "(Нажмите на ? слева, чтобы снова посмотреть обучение)"
		    }
		};
	int[][] jiaopos1= {{sw/2,sh/2,1}};
	String[][] jiaocheng1 = {
		    // 中文 (zh)
		    {"红色的连接线代表需要使用朱雀炉来连接"},
		    // 繁中 (zht)
		    {"紅色的連接線代表需要使用朱雀爐來連接"},
		    // 日本語 (ja)
		    {"赤い接続線は朱雀炉を使用して接続する必要があることを示します"},
		    // 한국어 (ko)
		    {"빨간색 연결선은 주작로를 사용하여 연결해야 함을 나타냅니다"},
		    // Deutsch (de)
		    {"Die rote Verbindungslinie bedeutet, dass der Vermilion-Ofen zum Verbinden verwendet werden muss"},
		    // English (en)
		    {"The red connection line means you need to use the Vermilion Furnace to connect"},
		    // Español (es)
		    {"La línea de conexión roja significa que necesitas usar el Horno Vermellón para conectar"},
		    // Français (fr)
		    {"La ligne de connexion rouge signifie que vous devez utiliser le Four Vermillon pour connecter"},
		    // Português (pt)
		    {"A linha de conexão vermelha significa que você precisa usar o Forno Vermelhão para conectar"},
		    // Русский (ru)  —— 拉丁转写
		    {"Красная линия соединения означает, что для соединения необходимо использовать печь Vermilion"}
		};
	int[][] jiaopos2= {
			{sw/2,sh/2,1},
			{(int) (sw/2-100*suofang),(int) (sh/2-100*suofang),1},
			{sw/2,sh/2,1},
			};
	String[][] jiaocheng2 = {
		    // 中文 (zh)
		    {
		        "顾客没有指定灵印组合方式，需要自己设计了",
		        "请点击黄色小球拉开抽屉，拖动灵印到灵盒右侧",
		        "右键已存在灵印的相邻格可以选取相生的灵印放置"
		    },
		    // 繁中 (zht)
		    {
		        "顧客沒有指定靈印組合方式，需要自己設計了",
		        "請點擊黃色小球拉開抽屜，拖動靈印到靈盒右側",
		        "右鍵已存在靈印的相鄰格可以選取相生的靈印放置"
		    },
		    // 日本語 (ja)
		    {
		        "お客様はシギルの組み合わせ方法を指定していません。自分で設計する必要があります",
		        "黄色いボールをクリックして引き出しを開き、シギルをシギルボックスの右側にドラッグしてください",
		        "既存のシギルの隣接マスを右クリックすると、相生のシギルを選択して配置できます"
		    },
		    // 한국어 (ko)
		    {
		        "고객이 시길 조합 방식을 지정하지 않았습니다. 직접 설계해야 합니다",
		        "노란 공을 클릭하여 서랍을 열고, 시길을 시길 상자 오른쪽으로 드래그하세요",
		        "기존 시길의 인접 칸을 우클릭하면 상생하는 시길을 선택하여 배치할 수 있습니다"
		    },
		    // Deutsch (de)
		    {
		        "Der Kunde hat keine Sigil-Kombinationsmethode vorgegeben, Sie müssen selbst entwerfen",
		        "Klicken Sie auf die gelbe Kugel, um die Schublade zu öffnen, und ziehen Sie das Sigil auf die rechte Seite der Sigil-Box",
		        "Rechtsklick auf ein angrenzendes Feld eines vorhandenen Sigils ermöglicht die Auswahl eines erzeugenden Sigils zum Platzieren"
		    },
		    // English (en)
		    {
		        "The customer did not specify a sigil combination method, you need to design it yourself",
		        "Click the yellow ball to open the drawer, drag the sigil to the right side of the Sigil Box",
		        "Right-click an adjacent cell to an existing sigil to select a generating sigil to place"
		    },
		    // Español (es)
		    {
		        "El cliente no especificó un método de combinación de sigilos, necesitas diseñarlo tú mismo",
		        "Haz clic en la bola amarilla para abrir el cajón, arrastra el sigilo al lado derecho de la Caja de Sigilos",
		        "Haz clic derecho en una celda adyacente a un sigilo existente para seleccionar un sigilo generador y colocarlo"
		    },
		    // Français (fr)
		    {
		        "Le client n'a pas spécifié de méthode de combinaison de sigils, vous devez la concevoir vous-même",
		        "Cliquez sur la boule jaune pour ouvrir le tiroir, faites glisser le sigil vers le côté droit de la Boîte à Sigils",
		        "Faites un clic droit sur une cellule adjacente à un sigil existant pour sélectionner un sigil générateur à placer"
		    },
		    // Português (pt)
		    {
		        "O cliente não especificou um método de combinação de sigilos, você precisa projetá-lo você mesmo",
		        "Clique na bola amarela para abrir a gaveta, arraste o sigilo para o lado direito da Caixa de Sigilos",
		        "Clique com o botão direito em uma célula adjacente a um sigilo existente para selecionar um sigilo gerador para colocar"
		    },
		    // Русский (ru)  —— 西里尔俄语
		    {
		        "Клиент не указал метод комбинации сигилов, вам нужно разработать его самостоятельно",
		        "Нажмите на жёлтый шарик, чтобы открыть ящик, перетащите сигил в правую сторону Коробки сигилов",
		        "Щёлкните правой кнопкой мыши по соседней ячейке с существующим сигилом, чтобы выбрать порождающий сигил для размещения"
		    }
		};
	int[][][] jiaochengpos= {jiaopos0,jiaopos1,jiaopos2};
	String[][][] jiaocheng= {jiaocheng0,jiaocheng1,jiaocheng2};
	
	String[] clearAllMachines = {
		    "清空所有机器？",                // 中文
		    "清空所有機器？",                // 繁中
		    "すべての機械をクリア？",        // 日语
		    "모든 기계를 지우시겠습니까?",    // 韩语
		    "Alle Maschinen löschen?",       // 德语
		    "Clear all machines?",           // 英语
		    "¿Borrar todas las máquinas?",   // 西班牙语
		    "Effacer toutes les machines?",  // 法语
		    "Limpar todas as máquinas?",     // 葡萄牙语
		    "Очистить все машины?"           // 俄语
		};
	
	String[][] levels = {
		    // 中文 (zh)
		    {"一气", "两仪", "六合", "方胜", "五行", "易", "阴鱼", "阳鱼", "变化", "组合", "放置", "三才"},
		    
		    // 繁中 (zht)
		    {"一氣", "兩儀", "六合", "方勝", "五行", "易", "陰魚", "陽魚", "變化", "組合", "放置", "三才"},
		    
		    // 日本語 (ja)
		    {"一気", "両儀", "六合", "方勝", "五行", "易", "陰魚", "陽魚", "変化", "組合", "配置", "三才"},
		    
		    // 한국어 (ko)
		    {"일기", "양의", "육합", "방승", "오행", "역", "음어", "양어", "변화", "조합", "배치", "삼재"},
		    
		    // Deutsch (de)  —— 长度均≤10
		    {"1 Qi", "2 Yi", "6 He", "Fang", "5 Xi", "Yi", "Yin-F", "Yang-F", "Wandl", "Kombi", "Platz", "3 Cai"},
		    
		    // English (en)
		    {"1 Qi", "2 Yi", "6 He", "Fang", "5 Xi", "Yi", "YinF", "YangF", "Change", "Combo", "Place", "3 Cai"},
		    
		    // Español (es)
		    {"1 Qi", "2 Yi", "6 He", "Fang", "5 Xi", "Yi", "YinP", "YangP", "Cambio", "Combo", "Poner", "3 Cai"},
		    
		    // Français (fr)
		    {"1 Qi", "2 Yi", "6 He", "Fang", "5 Xi", "Yi", "YinP", "YangP", "Chgt", "Combo", "Poser", "3 Cai"},
		    
		    // Português (pt)
		    {"1 Qi", "2 Yi", "6 He", "Fang", "5 Xi", "Yi", "YinP", "YangP", "Mudar", "Combo", "Por", "3 Cai"},
		    
		    // Русский (ru) —— 使用拉丁转写或简写，长度≤10
		    {"1 Qi", "2 Yi", "6 He", "Fang", "5 Xi", "Yi", "YinR", "YangR", "Izmen", "Komb", "Post", "3 Cai"}
		};
		
	String[] moveboxHelp = {
		    "点击空白格或用wasd移动",                   // 中文
		    "點擊空白格或用wasd移動",                   // 繁中
		    "空白セルをクリックまたはwasdで移動",       // 日语
		    "빈칸을 클릭하거나 wasd로 이동",            // 韩语
		    "Klicke leeres Feld oder bewege mit WASD",  // 德语
		    "Click empty cell or move with WASD",       // 英语
		    "Haz clic en celda vacía o muévete con WASD", // 西班牙语
		    "Cliquez sur une cellule vide ou déplacez-vous avec WASD", // 法语
		    "Clique em célula vazia ou mova-se com WASD", // 葡萄牙语
		    "Нажмите на пустую ячейку или двигайтесь WASD" // 俄语
		};
	String[][] moveboxButtonNames = {
		    // 中文 (zh)
		    {"重置", "帮助", "上一关", "下一关", "退出"},
		    // 繁中 (zht)
		    {"重置", "幫助", "上一關", "下一關", "退出"},
		    // 日语 (ja)
		    {"リセット", "ヘルプ", "前の面", "次の面", "戻る"},
		    // 韩语 (ko)
		    {"초기화", "도움", "이전 단계", "다음 단계", "돌아가기"},
		    // 德语 (de)
		    {"Reset", "Hilfe", "Vorherige", "Nächste", "Zurück"},
		    // 英语 (en)
		    {"Reset", "Help", "Previous", "Next", "Back"},
		    // 西班牙语 (es)
		    {"Reiniciar", "Ayuda", "Anterior", "Siguiente", "Volver"},
		    // 法语 (fr)
		    {"Réinitialiser", "Aide", "Précédent", "Suivant", "Retour"},
		    // 葡萄牙语 (pt)
		    {"Reiniciar", "Ajuda", "Anterior", "Próximo", "Voltar"},
		    // 俄语 (ru)
		    {"Сброс", "Помощь", "Предыдущий", "Следующий", "Назад"}
		};
	
	i18n(){
		
	}
	void initext() {
		
	}
}
