package blendefine;

import javafx.scene.Group;

public class grid {
	guanka gk;
	Group g;
	int x;
	int y;
	int upf;
	boolean openf=true;
	double posx;
	double posy;
	double ptx1,ptx2,ptx3,pty1,pty2,pty3;
//	blend b=null;
//	define d=null;
	String name;
	String key;
	grid(){
		this(0,0,null);
	}
	grid(int x,int y){
		this(x,y,null);
	}
	grid(int x,int y,Group g,String name){
		this(x,y,g);
		this.name=name;
	}
	public grid clone() {
		return new grid(x,y);
	}
	public guanka getGk() {
		return gk;
	}
	public void setGk(guanka gk) {
		this.gk = gk;
	}
	public Group getG() {
		return g;
	}
	public void setG(Group g) {
		this.g = g;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getUpf() {
		return upf;
	}
	public void setUpf(int upf) {
		this.upf = upf;
	}
	public boolean isOpenf() {
		return openf;
	}
	public void setOpenf(boolean openf) {
		this.openf = openf;
	}
	public double getPosx() {
		return posx;
	}
	public void setPosx(double posx) {
		this.posx = posx;
	}
	public double getPosy() {
		return posy;
	}
	public void setPosy(double posy) {
		this.posy = posy;
	}
	public double getPtx1() {
		return ptx1;
	}
	public void setPtx1(double ptx1) {
		this.ptx1 = ptx1;
	}
	public double getPtx2() {
		return ptx2;
	}
	public void setPtx2(double ptx2) {
		this.ptx2 = ptx2;
	}
	public double getPtx3() {
		return ptx3;
	}
	public void setPtx3(double ptx3) {
		this.ptx3 = ptx3;
	}
	public double getPty1() {
		return pty1;
	}
	public void setPty1(double pty1) {
		this.pty1 = pty1;
	}
	public double getPty2() {
		return pty2;
	}
	public void setPty2(double pty2) {
		this.pty2 = pty2;
	}
	public double getPty3() {
		return pty3;
	}
	public void setPty3(double pty3) {
		this.pty3 = pty3;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	grid(int x,int y,Group g){
		double s3=Math.sqrt(3);
		this.x=x;
		this.y=y;
		this.g=g;
		upf=(x+y)%2;
		posx=150*x;
		posy=150*s3*y;
		ptx1=0;
		ptx2=150;
		ptx3=300;
		pty1=150*s3*upf;
		pty2=150*s3-pty1;
		pty3=pty1;
	}
	void move(int x, int y) {}
	Group copyg() {
		return new Group();}
	void remove() {}
	void back() {};
	double[] getcenter(){
		if(upf==0)//倒三角
			return new double[]{posx+150,posy+260/3};
		else
			return new double[]{posx+150,posy+520/3};
	}
}
