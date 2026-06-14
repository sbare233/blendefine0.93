package blendefine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.effect.Bloom;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.util.Duration;

public class define{
	Group g;
	int x;
	int y;
	int upf;
	int face;
	int nowpic;
	int iscf=0;
	int zang;
	String name,key;
	double nowangle;
	guanka gk;
	int upx,upy,leftx,lefty,rightx,righty;
	public GemGroup parentGroup;
	
	define(int x, int y,Group g,String name,String key) {
		this.x=x;
		this.y=y;
		this.g=g;
		this.name=name;
		this.key=key;
		parentGroup= new GemGroup(this);
		parentGroup.nowd=this;
//		parentGroup.calculateArithmeticMean();
	}
	define(int x, int y,Group g,int upf,int face,int iscf,int nowpic,double nowangle,int zang,int upx,int upy,int leftx,int lefty,int rightx,int righty,String name,String key) {
		this.x=x;
		this.y=y;
		this.g=g;
		this.name=name;
		this.key=key;
		this.face=face;
		this.iscf=iscf;
		this.nowangle=nowangle;
		this.nowpic=nowpic;
		this.upf=upf;
		this.zang=zang;
		this.upx=upx;
		this.upy=upy;
		this.leftx=leftx;
		this.lefty=lefty;
		this.rightx=rightx;
		this.righty=righty;
//		double mofx=parentGroup.mofx;
//		double mofy=parentGroup.mofy;
		parentGroup= new GemGroup(this);
//		parentGroup.mofx=mofx;
//		parentGroup.mofy=mofy;
		parentGroup.nowd=this;
//		parentGroup.calculateArithmeticMean();
	}
	public HashMap<String,define> getgengroup() {
		HashMap<String,define> ds=new  HashMap<String,define>();
		parentGroup.gems.forEach(e->{
			ds.put(e.x+","+e.y,e.clone());
		});
		return ds;
	}
	 public define clone(define this) {
		 Group g1=new Group();
		 g.getChildren().iterator().forEachRemaining(e->{
			 ImageView iv=new ImageView(((ImageView) e).getImage());
			 g1.getChildren().add(iv); 
		 });
		 g1.setEffect(g.getEffect());
		 g1.setTranslateX(x*150-23);
	        if((x+y)%2==0) {
	        	g1.setTranslateY(y*260-85);
	        }
	        else {
	        	g1.setTranslateY(y*260);
	        }
	     g1.setRotate(g.getRotate());
		return new define(x,y,g1,upf,face,iscf,nowpic,nowangle,zang,upx,upy,leftx,lefty,rightx,righty, name, key);
	 }
	 public void connect(define other) {
		 	if (other == null) return;
//		    System.out.println("connect: this=" + this.x+","+this.y + " (group=" + (this.parentGroup==null?"null":this.parentGroup.gems.size()) 
//		            + "), other=" + other.x+","+other.y + " (group=" + (other.parentGroup==null?"null":other.parentGroup.gems.size()) + ")");
	        if (this.parentGroup == null && other.parentGroup == null) {
	            // 创建新组
	            GemGroup newGroup = new GemGroup(this);
	            newGroup.addGem(other);
	        } else if (this.parentGroup != null && other.parentGroup == null) {
	            // 添加到现有组
	            this.parentGroup.addGem(other);
	        } else if (this.parentGroup == null && other.parentGroup != null) {
	            // 添加到对方组
	            other.parentGroup.addGem(this);
	        } else {
	            // 合并两个组
	            mergeGroups(this.parentGroup, other.parentGroup);
	        }
//	        System.out.println("After connect, parentGroup size=" + parentGroup.gems.size());
	       
	    }
	 private void mergeGroups(GemGroup g1, GemGroup g2) {
		    // 合并宝石集合
		    for (define gem : g2.gems) {
		        g1.addGem(gem);
		    }

		    // 合并邻接表 (connsxy)
		    for (Map.Entry<String, HashSet<String>> entry : g2.connsxy.entrySet()) {
		        String node = entry.getKey();
		        HashSet<String> neighbors = entry.getValue();
		        g1.connsxy.computeIfAbsent(node, k -> new HashSet<>()).addAll(neighbors);
		    }

		    // 合并线对象 (conns)
		    g1.conns.putAll(g2.conns);

		    // 清空 g2
		    g2.connsxy.clear();
		    g2.conns.clear();
		    g2.gems.clear();
		}

	    
	    void baoshiconnect(){
	    	int[][] dirs=null;
	        if((x+y)%2==0) dirs=new int[][]{ {1,0}, {0,-1}, {-1,0}};
	        else dirs=new int[][]{{0,1}, {1,0}, {-1,0}};	 
	        for (int[] dir : dirs) {
	            int nx = x + dir[0];
	            int ny = y + dir[1];
	            if (nx >= 0 && nx < guanka.MAPWIDTH && ny >= 0 && ny < guanka.MAPHEIGHT) {
	            	if (null!=gk.df[ny][nx] ) {
		                    define neighbor =gk.df[ny][nx];
		                    	
		                    if((null==gk.db[y][x]&&null==gk.db[ny][nx])&&!parentGroup.gems.contains(neighbor)) {
		                    	
		                    	//相生的情况
		                    	Set<String> connections = gk.findcraftgraph.get(neighbor.name);
//		                    	connections.forEach(e->{
//		                    		System.out.print(e+" ");
//		                    	});
//		                    	System.out.println();
		                        //朱雀连接
		                    	if((null!=gk.db0[y][x]&&(gk.db0[y][x].name.contains("灌")||gk.db0[y][x].name.equals("朱雀")&&gk.db0[y][x].openf))||
		                    			(null!=gk.db0[ny][nx]&&(gk.db0[ny][nx].name.contains("灌")||gk.db0[ny][nx].name.equals("朱雀")&&gk.db0[ny][nx].openf))||
		                    			connections != null && connections.contains(name)) {
				                    	connect(neighbor);
//				                    	double ctx=x*150+150,cty=260*y+((x+y)%2==0?87:173),ctnx=nx*150+150,ctny=260*ny+((nx+ny)%2==0?87:173);
			                    		
				                    	// 原有逻辑中的 biggerf 判断可以去掉，改为：
				                    	parentGroup.connsxy.computeIfAbsent(x+","+y, k -> new HashSet<>()).add(nx+","+ny);
				                    	parentGroup.connsxy.computeIfAbsent(nx+","+ny, k -> new HashSet<>()).add(x+","+y);
//				                    		parentGroup.conns.put(x+","+y+","+nx+","+ny,createAnimatedConnectionLine(new Point2D(ctx,cty),new Point2D(ctnx,ctny),Color.WHITE));                
				                    	parentGroup.gems.forEach(e->{
				                    		ImageView bv=(ImageView) e.g.getChildren().get(0);
					                        (bv).setImage(gk.baoshiimagemap.get(e.name+e.face*4));

				                    	});
				                    	Bloom bloom = new Bloom();
				                    
				                    	// 只给当前连接的 two 个宝石添加特效（通过 parentGroup 获取）
				                    	ImageView iv1 = (ImageView) this.g.getChildren().get(0);
				                    	ImageView iv2 = (ImageView) neighbor.g.getChildren().get(0);
				                    	iv1.setEffect(bloom);
				                    	iv2.setEffect(bloom);
				                    	
				                    	Timeline t = new Timeline(
					                    	    new KeyFrame(Duration.seconds(0.4), new KeyValue(bloom.thresholdProperty(), 1))
					                    	);
					                    	t.setOnFinished(e -> {
					                    		bloom.setThreshold(0);
					                    		iv1.setEffect(null);
						                    	iv2.setEffect(null);
					                    	});
				                    	t.play();
				                    }	
		                }
		            }
	            }
	        }
	    }
//	    public void disconnectall() {
//	        if (parentGroup == null) return;
//	        parentGroup.gems.remove(this);
//	        Set<define> remainingGems = new HashSet<>(parentGroup.gems);
//
//	        // 备份并清除连接线视觉效果
//	        Map<String, HashSet<String>> oldConnsxy = new HashMap<>();
//	        for (Map.Entry<String, HashSet<String>> e : parentGroup.connsxy.entrySet()) {
//	            oldConnsxy.put(e.getKey(), new HashSet<>(e.getValue()));
//	        }
//	        if (gk != null && gk.connectpane != null) {
//	            gk.connectpane.getChildren().removeAll(parentGroup.conns.values());
//	        }
//	        parentGroup.connsxy.clear();
//	        parentGroup.conns.clear();
//
//	        if (remainingGems.isEmpty()) {
//	            GemGroup selfGroup = new GemGroup(this);
//	            this.parentGroup = selfGroup;
//	            return;
//	        }
//
//	        // 构建剩余宝石的坐标映射
//	        Map<String, define> coordToGem = new HashMap<>();
//	        for (define gem : remainingGems) {
//	            coordToGem.put(gem.x + "," + gem.y, gem);
//	        }
//
//	        // 从 oldConnsxy 构建邻接表（无向）
//	        Map<String, Set<String>> adj = new HashMap<>();
//	        for (String node : coordToGem.keySet()) {
//	            adj.put(node, new HashSet<>());
//	        }
//	        for (Map.Entry<String, HashSet<String>> entry : oldConnsxy.entrySet()) {
//	            String from = entry.getKey();
//	            for (String to : entry.getValue()) {
//	                if (adj.containsKey(from) && adj.containsKey(to)) {
//	                    adj.get(from).add(to);
//	                    adj.get(to).add(from);
//	                }
//	            }
//	        }
//
//	        // BFS 求连通分量
//	        Set<String> visited = new HashSet<>();
//	        List<Set<define>> components = new ArrayList<>();
//	        for (String coord : adj.keySet()) {
//	            if (!visited.contains(coord)) {
//	                Set<define> comp = new HashSet<>();
//	                Queue<String> queue = new LinkedList<>();
//	                queue.add(coord);
//	                visited.add(coord);
//	                while (!queue.isEmpty()) {
//	                    String cur = queue.poll();
//	                    comp.add(coordToGem.get(cur));
//	                    for (String nb : adj.get(cur)) {
//	                        if (!visited.contains(nb)) {
//	                            visited.add(nb);
//	                            queue.add(nb);
//	                        }
//	                    }
//	                }
//	                components.add(comp);
//	            }
//	        }
//
//	        // 为每个连通分量创建新组，并迁移连接线
//	        for (Set<define> comp : components) {
//	            if (comp.isEmpty()) continue;
//	            define first = comp.iterator().next();
//	            GemGroup newGroup = new GemGroup(first);
//	            for (define gem : comp) {
//	                if (gem != first) {
//	                    newGroup.addGem(gem);
//	                }
//	            }
//	            // 迁移属于该分量的连接线（根据 oldConnsxy 中的邻居关系）
//	            for (Map.Entry<String, HashSet<String>> entry : oldConnsxy.entrySet()) {
//	                String from = entry.getKey();
//	                define fromGem = coordToGem.get(from);
//	                if (fromGem == null || !comp.contains(fromGem)) continue;
//	                for (String to : entry.getValue()) {
//	                    define toGem = coordToGem.get(to);
//	                    if (toGem == null || !comp.contains(toGem)) continue;
//	                    // 避免重复添加同一条边（只处理 from < to 的字典序，或用 visitedEdges）
//	                    if (from.compareTo(to) >= 0) continue;
////	                    Point2D start = getGemCenter(fromGem);
////	                    Point2D end = getGemCenter(toGem);
////	                    Line line = createAnimatedConnectionLine(start, end, Color.WHITE);
//	                    // 存储到新组的 connsxy 和 conns（双向存储）
//	                    newGroup.connsxy.computeIfAbsent(from, k -> new HashSet<>()).add(to);
//	                    newGroup.connsxy.computeIfAbsent(to, k -> new HashSet<>()).add(from);
////	                    newGroup.conns.put(from + "," + to, line);
//	                }
//	            }
//	            // 更新组内宝石的 parentGroup
//	            for (define gem : comp) {
//	                gem.parentGroup = newGroup;
//	            }
//	        }
//
//	        // 为当前宝石自身创建独立组
//	        GemGroup selfGroup = new GemGroup(this);
//	        this.parentGroup = selfGroup;
//	    }

	    /*
	     * now 当前宝石的网格坐标 ct中心宝石的网格坐标
	     */
	    public int[] gridrotate(int nowx,int nowy,int ctx,int cty,int roll) {
	    	double sin=Math.sin(Math.toRadians(roll*60)),cos=Math.cos(Math.toRadians(roll*60));
	    	double offxs=(nowx-ctx)*150;
	    	double offys=(nowy-cty)*260+((nowx-ctx+nowy-cty)%2==0?0:((nowx+nowy)%2==0?-260/3:260/3));	  
	    	double offxs1 = offxs * cos - offys * sin;  //当前宝石与被抓宝石的坐标偏移
	    	double offys1 = offxs * sin + offys * cos;  
	    	int offxgs=(int) Math.floor((offxs1+75)/150.0);
	    	int offygs=(int) Math.floor((offys1+130)/260.0);//当前宝石与被抓宝石的网格偏移
			nowx=nowx+offxgs;nowy=nowy+offygs;
			return new int[]{nowx,nowy};
	    }
		public boolean movebaoshi(define d,int x,int y,int roll) {
			int dx=d.x,dy=d.y;
//			int roll1=roll-d.face;
//			在没有d1.x=nowx,d1.y=nowy时使用
			int roll1=roll;
			int s=d.parentGroup.gems.size();
			define[] ds=d.parentGroup.gems.toArray(new define[s]);
			double[] offxs=new double[s], offys=new double[s];
			double[] offxs1=new double[s], offys1=new double[s];
			int[] offxgs=new int[s], offygs=new int[s];
			int[] nowxs=new int[s], nowys=new int[s];
			HashMap<String,String> movedefinexy=new HashMap<String,String>();
			double sin=Math.sin(Math.toRadians(roll1*60)),cos=Math.cos(Math.toRadians(roll1*60));
			
			for(int i=0;i<s;i++) {
				offxs[i]=(ds[i].x-dx)*150;
				offys[i]=(ds[i].y-dy)*260+((ds[i].x-dx+ds[i].y-dy)%2==0?0:((ds[i].x+ds[i].y)%2==0?-260/3:260/3));	  
				offxs1[i] = offxs[i] * cos - offys[i] * sin;  //当前宝石与被抓宝石的坐标偏移
				offys1[i] = offxs[i] * sin + offys[i] * cos;  
				offxgs[i]=(int) Math.floor((offxs1[i]+75)/150.0);
				offygs[i]=(int) Math.floor((offys1[i]+130)/260.0);//当前宝石与被抓宝石的网格偏移
				nowxs[i]=x+offxgs[i];
				nowys[i]=y+offygs[i];
				if(gk.df[nowys[i]][nowxs[i]]!=null||gk.db[nowys[i]][nowxs[i]]!=null) {
	         		grid gd = new grid(nowxs[i],nowys[i]);
					Polygon t = new Polygon(gd.posx + gd.ptx1, gd.posy + gd.pty1, gd.posx + gd.ptx2,
							gd.posy + gd.pty2, gd.posx + gd.ptx3, gd.posy + gd.pty3);
					t.setFill(Color.color(1, 0, 0,0.7));
					gk.handlepane.getChildren().add(t);//重叠提示
					return false;
				}
				//当存在重叠时无法移动，返回false使longzhua的putbs失败
			}
			for(int i=0;i<s;i++) {
				int nowx=nowxs[i],nowy=nowys[i];
				define d1=ds[i];
				gk.df[nowy][nowx]=d1;
				movedefinexy.put(d1.x+","+d1.y, nowx+","+nowy);
				d1.x=nowx;
				d1.y=nowy;
		        Group g2=d1.g;
		        gk.dfpane.getChildren().add(g2);
		        int toroll=(d1.face+roll)%6;        
		        d1.face=toroll;
		        ArrayList<String> al=gk.dirs;
		        //改变连接的朝向
		        
		        if(al.indexOf(upx+","+upy)!=-1) {
		        	String[] ups=al.get(toroll).split(",");
		        	upx=Integer.parseInt(ups[0]);
		        	upy=Integer.parseInt(ups[1]);
		        }
		        if(al.indexOf(leftx+","+lefty)!=-1) {
		        	String[] lefts=al.get((toroll+2)%6).split(",");
		        	leftx=Integer.parseInt(lefts[0]);
		        	lefty=Integer.parseInt(lefts[1]);
		        }
		        if(al.indexOf(rightx+","+righty)!=-1) {
		        	String[] rights=al.get((toroll+2)%6).split(",");
		        	rightx=Integer.parseInt(rights[0]);
		        	righty=Integer.parseInt(rights[1]);
		        }
		        
		        g2.setTranslateX((nowx)*150-23);
		        
		        d1.nowangle=d1.face*60;
		        if((nowx+nowy)%2==0) {
		        	d1.upf=0;
		        	g2.setTranslateY(nowy*260-87);
		        }
		        else {
		        	d1.upf=1;
		        	g2.setTranslateY(nowy*260);
		        }
		        //设置光影
		        g2.setRotate(toroll*60);
		        int np1=((int) Math.floor((((ds[i].face)*60+375)%360)/15)+24)%24;
	        	if(ds[i].nowpic!=np1) {
	        		ds[i].nowpic=np1;
	        		((ImageView) ds[i].g.getChildren().get(0)).setImage(gk.baoshiimagemap.get(ds[i].name+np1));
	        	}
			}
			
			// 重建连接（原 d.parentGroup.connsxy 是 Map<String, HashSet<String>>）
			HashMap<String, HashSet<String>> tempconnsxy = new HashMap<>(); // 临时存储新坐标下的邻接表

			d.parentGroup.connsxy.forEach((oldKey, neighborSet) -> {
			    // oldKey 格式 "x,y"，neighborSet 是该节点连接的所有邻居坐标字符串
			    String newKey = movedefinexy.get(oldKey);  // 映射后的当前节点坐标
			    if (newKey == null) return; // 防御

			    for (String oldNeighbor : neighborSet) {
			        String newNeighbor = movedefinexy.get(oldNeighbor);
			        if (newNeighbor == null) continue;

			        // 解析坐标用于绘制线条和确定字典序
			        String[] xy1 = newKey.split(",");
			        String[] xy2 = newNeighbor.split(",");
			        int kx = Integer.parseInt(xy1[0]), ky = Integer.parseInt(xy1[1]);
			        int vx = Integer.parseInt(xy2[0]), vy = Integer.parseInt(xy2[1]);

			        // 计算中心点坐标
			        double ctx = kx * 150 + 150;
			        double cty = 260 * ky + ((kx + ky) % 2 == 0 ? 87 : 173);
			        double ctnx = vx * 150 + 150;
			        double ctny = 260 * vy + ((vx + vy) % 2 == 0 ? 87 : 173);

			        // 创建线条
			        //TODO 此处的创建连接是正常的
//			        Line stline = createConnectionLine(new Point2D(ctx, cty), new Point2D(ctnx, ctny), Color.WHITE);
//			        gk.connectpane.getChildren().add(stline);

			        // 以字典序存储双向连接（避免重复边）
			        if (newKey.compareTo(newNeighbor) < 0) {
			            // 存储邻接关系（双向）
			            tempconnsxy.computeIfAbsent(newKey, k -> new HashSet<>()).add(newNeighbor);
			            tempconnsxy.computeIfAbsent(newNeighbor, k -> new HashSet<>()).add(newKey);
			            // 存储线条对象，使用 "小坐标,大坐标" 作为键
//			            parentGroup.conns.put(newKey + "," + newNeighbor, stline);
			        } else if (newKey.compareTo(newNeighbor) > 0) {
			            // 已由字典序小的那一端处理，跳过避免重复
			            continue;
			        } else {
			            // 理论上不会相同，如果相同表示自环，忽略
			        }
			    }
			});

			// 清空旧的邻接表和线条映射，替换为新数据
			d.parentGroup.connsxy.clear();
			d.parentGroup.connsxy.putAll(tempconnsxy);

			baoshiconnect();
			return true;
		}			
}

