package blendefine;

import javafx.animation.Timeline;
import javafx.scene.Group;

public class baihu extends blend{
	public Timeline tl;
	baihu(int x, int y, String name, String key, Group g) {
		super(x, y, name, key, g);
	}
	public void remove(){
		gk.machines.remove(machineindex);
		gk.udpane.getChildren().remove(g);
		tl.stop();
		tl=null;
		gk.db0[y][x]=null;gk.db0[y][x+1]=null;gk.db0[y][x+2]=null;
		gk.db0[y+1][x]=null;gk.db0[y+1][x+1]=null;gk.db0[y+1][x+2]=null;
		gk.gridv[y][x].setOpacity(1);gk.gridv[y][x+1].setOpacity(1);gk.gridv[y][x+2].setOpacity(1);
		gk.gridv[y+1][x].setOpacity(1);gk.gridv[y+1][x+1].setOpacity(1);gk.gridv[y+1][x+2].setOpacity(1);
	}
	public void move(int x1,int y1) {
		gk.db0[y][x]=null;gk.db0[y][x+1]=null;gk.db0[y][x+2]=null;
		gk.db0[y+1][x]=null;gk.db0[y+1][x+1]=null;gk.db0[y+1][x+2]=null;
		gk.gridv[y][x].setOpacity(1);gk.gridv[y][x+1].setOpacity(1);gk.gridv[y][x+2].setOpacity(1);
		gk.gridv[y+1][x].setOpacity(1);gk.gridv[y+1][x+1].setOpacity(1);gk.gridv[y+1][x+2].setOpacity(1);
		
		this.x=x1;this.y=y1;
		g.setTranslateX(150*x);
		g.setTranslateY(260*y);
		gk.gridv[y][x].setOpacity(0);gk.gridv[y][x+1].setOpacity(0);gk.gridv[y][x+2].setOpacity(0);
		gk.gridv[y+1][x].setOpacity(0);gk.gridv[y+1][x+1].setOpacity(0);gk.gridv[y+1][x+2].setOpacity(0);
		gk.db0[y][x]=this;gk.db0[y][x+1]=this;gk.db0[y][x+2]=this;
		gk.db0[y+1][x]=this;gk.db0[y+1][x+1]=this;gk.db0[y+1][x+2]=this;
	}
	
}
