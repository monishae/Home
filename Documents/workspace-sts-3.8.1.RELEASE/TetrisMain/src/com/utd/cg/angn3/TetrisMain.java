package com.utd.cg.angn3;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.SwingUtilities;
/**
 * 
 * @author monishaelumalai
 *
 */
public class TetrisMain extends Canvas implements MouseMotionListener, MouseListener,MouseWheelListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Vector v=new Vector();
	float x0,y0, rwidth=10.0F, rHeight=20.0F,pixelSize;
	boolean ready=true;
	int centerX,centerY, movX,movY;
	static int hover=0;
	String msg;
	int boxX,boxY,start,current,next;
	int tet[][],tetshape[];
	int rotation;
	int line_count=0;
	boolean game_over=false;
	int NoOfRows,score_fact,level;
	float score,speedfact,fallSpeed;
	TetrisMain(){
		boxX=5;
		boxY=0;
		start=0;
		rotation=0;
		fallSpeed=100;
		//default values for scoring
		score=0;speedfact=0.1F;NoOfRows=5;level=1;score_fact=1;
		tet=new int[10][20];
		tetshape= new int[7];
		for(int i=0;i<10;i++)
			for(int j=0;j<20;j++){
				tet[i][j]=0;
			}
		/* 0 - square shape
		 * 1 - L shape
		 * 2 - L inverted shape
		 * 3 - T shape
		 * 4 - Z shape
		 * 5 - Z inverted shape
		 * 6 - Line
		 */
		for(int i=0;i<7;i++)
			tetshape[i]=i+1;			
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0){
				movX=arg0.getX();
				movY=arg0.getY();
				int left=iX(-rwidth/2),right=iX(rwidth/2), bottom=iY(-rHeight/2),top=iY(rHeight/2);
				int xx=7*right/8, yy=3*bottom/4;
				if((movX>=xx&&movX<=(xx+(3*(right-left)/10)))&&(movY>=yy&&movY<=(yy+(2*(right-left)/10)))){
					System.exit(0);
				}
				//if(!(movX>=xx&&movX<=(xx+right-left))&&!(movY>=yy&&movY<=(yy+bottom-top-20))){
					xx=(int)(left/3); yy=top+10;
					if(SwingUtilities.isRightMouseButton(arg0)){
							if(checkRight()){
								boxX=boxX+1;
							}
					}
					else if(SwingUtilities.isLeftMouseButton(arg0)){
							if(checkLeft()){
								boxX=boxX-1;
							}
					}
				if(hover==0&&!game_over)
				repaint();
			}
		});
		
	}
	TetrisMain(int score,int rows,float speed){
		this.score_fact=score;
		this.NoOfRows=rows;
		this.speedfact=speed;
		boxX=5;
		boxY=0;
		start=0;
		rotation=0;
		fallSpeed=100;
		//default values for scoring
		score=0;level=1;
		tet=new int[10][20];
		tetshape= new int[7];
		for(int i=0;i<10;i++)
			for(int j=0;j<20;j++){
				tet[i][j]=0;
			}
		/* 0 - square shape
		 * 1 - L shape
		 * 2 - L inverted shape
		 * 3 - T shape
		 * 4 - Z shape
		 * 5 - Z inverted shape
		 * 6 - Line
		 */
		for(int i=0;i<7;i++)
			tetshape[i]=i+1;			
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0){
				movX=arg0.getX();
				movY=arg0.getY();
				int left=iX(-rwidth/2),right=iX(rwidth/2), bottom=iY(-rHeight/2),top=iY(rHeight/2);
				int xx=7*right/8, yy=3*bottom/4;
				if((movX>=xx&&movX<=(xx+(3*(right-left)/10)))&&(movY>=yy&&movY<=(yy+(2*(right-left)/10)))){
					System.exit(0);
				}
				//if(!(movX>=xx&&movX<=(xx+right-left))&&!(movY>=yy&&movY<=(yy+bottom-top-20))){
					xx=(int)(left/3); yy=top+10;
					if(SwingUtilities.isRightMouseButton(arg0)){
							if(checkRight()){
								boxX=boxX+1;
							}
					}
					else if(SwingUtilities.isLeftMouseButton(arg0)){
							if(checkLeft()){
								boxX=boxX-1;
							}
					}
				if(hover==0&&!game_over)
				repaint();
			}
		});
		
	}
	boolean checkLeft(){
		switch(current){
		case 1: //square
			if(boxX-1>=0){
				if(tet[boxX-1][boxY]==0&&tet[boxX-1][boxY+1]==0){
					return true;
				}
			}
			break;
		case 2: //L shape
			if(rotation==0){
				if(boxX-1>=0){
					if(tet[boxX-1][boxY]==0&&tet[boxX-1][boxY+1]==0){
						return true;
					}
				}
			}else if(rotation==1){
				if(boxX-2>=0){
					if(tet[boxX-2][boxY+1]==0&&tet[boxX-1][boxY]==0&&tet[boxX-1][boxY-1]==0){
						return true;
					}
				}
			}
			else if(rotation==2){
				if(boxX-2>=0){
					if(tet[boxX-2][boxY]==0&&tet[boxX][boxY+1]==0){
						return true;
					}
				}
			}
			else{
				if(boxX-1>=0){
					if(tet[boxX-1][boxY-1]==0&&tet[boxX-1][boxY+1]==0&&tet[boxX-1][boxY]==0){
						return true;
					}
				}
			}
			
			break;
		case 3: //L reverse
			if(rotation==0){
				if(boxX-3>=0){
					if(tet[boxX-3][boxY+1]==0&&tet[boxX-1][boxY+1]==0){
						return true;
					}
				}
			}
			else if(rotation==1){
				if(boxX-2>=0){
					if(tet[boxX-2][boxY-1]==0&&tet[boxX-1][boxY]==0&&tet[boxX-1][boxY+1]==0){
						return true;
					}
				}
			}
			else if(rotation==2){
				if(boxX-2>=0){
					if(tet[boxX-2][boxY]==0&&tet[boxX-2][boxY+1]==0){
						return true;
					}
				}
			}
			else{
				if(boxX-1>=0){
					if(tet[boxX-1][boxY-1]==0&&tet[boxX-1][boxY]==0&&tet[boxX-1][boxY+1]==0){
						return true;
					}
				}
			}
			
			break;
		case 4: //T shape
			if(rotation==0){
				if(boxX-2>=0){
					if(tet[boxX-2][boxY+1]==0&&tet[boxX-1][boxY]==0){
						return true;
					}
				}
			}
			else if(rotation==1){
				if(boxX-2>=0){
					if(tet[boxX-2][boxY]==0&&tet[boxX-1][boxY-1]==0&&tet[boxX-1][boxY+1]==0){
						return true;
					}
				}
			}
			else if(rotation==2){
				if(boxX-2>=0){
					if(tet[boxX-2][boxY]==0&&tet[boxX-1][boxY+1]==0){
						return true;
					}
				}
			}
			else{
				if(boxX-1>=0){
					if(tet[boxX-1][boxY]==0&&tet[boxX-1][boxY-1]==0&&tet[boxX-1][boxY+1]==0){
						return true;
					}
				}
			}
			
			break;
		case 5: //Z shape
			if(rotation==0){
				if(boxX-1>=0){
					if(tet[boxX-1][boxY]==0&&tet[boxX][boxY+1]==0){
						return true;
					}
				}
			}
			else{
				if(boxX-2>=0){
					if(tet[boxX-2][boxY]==0&&tet[boxX-2][boxY+1]==0&&tet[boxX-1][boxY-1]==0){
						return true;
					}
				}
			}
			
			break;
		case 6: //Z inverse
			if(rotation==0){
				if(boxX-3>=0){
					if(tet[boxX-3][boxY+1]==0&&tet[boxX-2][boxY]==0){
						return true;
					}
				}
			}
			else{
				if(boxX-2>=0){
					if(tet[boxX-2][boxY-1]==0&&tet[boxX-2][boxY]==0&&tet[boxX-1][boxY+1]==0){
						return true;
					}
				}
			}
			
			break;
		case 7: //line
			if(rotation==0){
				if(boxX-2>=0){
					if(tet[boxX-2][boxY]==0){
						return true;
					}
				}
			}
			else{
				if(boxX-1>=0){
					if(tet[boxX-1][boxY-1]==0&&tet[boxX-1][boxY]==0&&tet[boxX-1][boxY+1]==0&&tet[boxX-1][boxY+2]==0){
						return true;
					}
				}
			}
			
			break;
		}
		return false;
	}
	boolean checkRight(){
		switch(current){
		case 1: //square
			if(boxX+2<=9){
				if(tet[boxX+2][boxY+1]==0&&tet[boxX+2][boxY]==0){
					return true;
				}
			}
			break;
		case 2: //L shape
			if(rotation==0){
				if(boxX+3<=9){
					if(tet[boxX+3][boxY+1]==0&&tet[boxX+1][boxY]==0){
						return true;
					}
				}
			}
			else if(rotation==1){
				if(boxX+1<=9){
					if(tet[boxX+1][boxY-1]==0&&tet[boxX+1][boxY]==0&&tet[boxX+1][boxY+1]==0){
						return true;
					}
				}
			}
			else if(rotation==2){
				if(boxX+2<=9){
					if(tet[boxX+2][boxY]==0&&tet[boxX+2][boxY+1]==0){
						return true;
					}
				}
			}
			else{
				if(boxX+2<=9){
					if(tet[boxX+1][boxY+1]==0&&tet[boxX+1][boxY]==0&&tet[boxX+2][boxY-1]==0){
						return true;
					}
				}
			}
			
			break;
		case 3: //L reverse
			if(rotation==0){
				if(boxX+1<=9){
					if(tet[boxX+1][boxY+1]==0&&tet[boxX+1][boxY]==0){
						return true;
					}
				}
			}
			else if(rotation==1){
				if(boxX+1<=9){
					if(tet[boxX+1][boxY+1]==0&&tet[boxX+1][boxY]==0&&tet[boxX+1][boxY-1]==0){
						return true;
					}
				}
			}
			else if(rotation==2){
				if(boxX+2<=9){
					if(tet[boxX+2][boxY]==0&&tet[boxX][boxY+1]==0){
						return true;
					}
				}
			}
			else{
				if(boxX+2<=9){
					if(tet[boxX+2][boxY+1]==0&&tet[boxX+1][boxY]==0&&tet[boxX+1][boxY-1]==0){
						return true;
					}
				}
			}
			
			break;
		case 4: //T shape
			if(rotation==0){
				if(boxX+2<=9){
					if(tet[boxX+2][boxY+1]==0&&tet[boxX+1][boxY]==0){
						return true;
					}
				}
			}
			else if(rotation==1){
				if(boxX+1<=9){
					if(tet[boxX+1][boxY+1]==0&&tet[boxX+1][boxY]==0&&tet[boxX+1][boxY-1]==0){
						return true;
					}
				}
			}
			else if(rotation==2){
				if(boxX+2<=9){
					if(tet[boxX+2][boxY]==0&&tet[boxX+1][boxY+1]==0){
						return true;
					}
				}
			}
			else{
				if(boxX+2<=9){
					if(tet[boxX+1][boxY+1]==0&&tet[boxX+2][boxY]==0&&tet[boxX+1][boxY-1]==0){
						return true;
					}
				}
			}
			
			break;
		case 5: //Z shape
			if(rotation==0){
				if(boxX+3<=9){
					if(tet[boxX+3][boxY+1]==0&&tet[boxX+2][boxY]==0){
						return true;
					}
				}
			}
			
			else{
				if(boxX+1<=9){
					if(tet[boxX+1][boxY-1]==0&&tet[boxX+1][boxY]==0&&tet[boxX][boxY+1]==0){
						return true;
					}
				}
			}
			
			break;
		case 6: //Z inverse
			if(rotation==0){
				if(boxX+1<=9){
					if(tet[boxX+1][boxY]==0&&tet[boxX][boxY+1]==0){
						return true;
					}
				}				
			}
			else{
				if(boxX+1<=9){
					if(tet[boxX][boxY-1]==0&&tet[boxX+1][boxY]==0&&tet[boxX+1][boxY+1]==0){
						return true;
					}
				}
			}


			break;
		case 7: //line
			if(rotation==0){
				if(boxX+3<=9){
					if(tet[boxX+3][boxY]==0){
						return true;
					}
				}
			}
			else{
				if(boxX+1<=9){
					if(tet[boxX+1][boxY-1]==0&&tet[boxX+1][boxY]==0&&tet[boxX+1][boxY+1]==0&&tet[boxX+1][boxY+2]==0){
						return true;
					}
				}
			}
			break;
		}
		return false;
	}
	void initgr(){
		Dimension d=getSize();
		int maxX=d.width-1, maxY=d.height-1;
		pixelSize=Math.max(rwidth/maxX, rHeight/maxY);
		centerX=maxX/2;centerY=maxY/2;
	}
	int iX(float x){return Math.round(centerX+x/pixelSize);}
	int iY(float y){return Math.round(centerY-y/pixelSize);}
	float fx(int x){return (x-centerX)*pixelSize;}
	float fy(int y){return (centerY-y)*pixelSize;}
	
	public void paint(Graphics g){
		initgr();
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		
		if(hover==1){
			Font myFont = new Font ("Courier New", 1, 28);
			g.setFont(myFont);
			g.setColor(Color.BLACK);
			g.drawRect(120,285,115,35);
			g.drawString(msg, 135, 310);
			g.setColor(Color.BLACK);
			boxY--;
		}
		int left=iX(-rwidth/2),right=iX(rwidth/2), bottom=iY(-rHeight/2),top=iY(rHeight/2);
		//g.drawRect(left/3,top+10,right-left, bottom-top-20);
		g.drawRect(left/3,top,right-left, 2*(right-left));
		g.drawRect((7*right/8),top+10,(14*(right-left)/20), (bottom-top-20)/5);
		
		score(g, 7*right/8,right/2);
		quitGame(g,7*right/8,3*bottom/4);
		if(start==0){
			Random r = new Random();
	        current = Math.abs(r.nextInt()) % 7+1;
	        next = Math.abs(r.nextInt()) % 7+1;
		}
		drawBlock(15,1, g,next,0);
		start=1;
		check();
		if(tet[4][2]!=0||tet[5][2]!=0||tet[6][2]!=0){
			int size=(right-left)/10;
			for(int i=0;i<10;i++){
				for(int j=0;j<20;j++){
					int X=((i)*(right-left)/10)+(left/3);
					int Y=((j)*(right-left)/10)+top;
					if(tet[i][j]!=0){
						
						switch(tet[i][j]){
						case 1:
							g.setColor(new Color(34,139,34));
							break;
						case 2:
							g.setColor(new Color(0,0,204));
							break;
						case 3:
							g.setColor(new Color(255,0,0));
							break;
						case 4:
							g.setColor(new Color(255,153,51));
							break;
						case 5:
							g.setColor(new Color(153,0,153));
							break;
						case 6:
							g.setColor(new Color(255,255,51));
							break;
						case 7:
							g.setColor(new Color(0,204,204));
							break;
						}
						g.setColor(Color.BLACK);
			    		g.fillRect(X+1,Y+1, size-1, size-1);
			    		g.setColor(Color.BLACK);
			    		g.drawRect(X,Y, size, size);
					}/*
					g.setColor(Color.BLACK);
		    		g.drawRect(X,Y, size, size);*/
				}
			}
			//g.drawRect(120,285,115,35);
			g.setColor(Color.WHITE);
			g.drawString("GAME OVER", 125, 310);
			if((tet[4][1]==0&&tet[5][1]==0&&tet[6][1]==0))
			drawBlock(boxX,boxY,g,current,rotation);
			game_over=true;
		}
		else{
			fillRecs(g);
		}
		
	}
	
	void check(){
		int left=iX(-rwidth/2),right=iX(rwidth/2), bottom=iY(-rHeight/2),top=iY(rHeight/2);
		switch(current){
			case 1: //square
				if(boxY+1==19||(tet[boxX][boxY+2]!=0||tet[boxX+1][boxY+2]!=0))
				{
					tet[boxX][boxY]=1;
					tet[boxX+1][boxY]=1;
					tet[boxX][boxY+1]=1;
					tet[boxX+1][boxY+1]=1;
					boxX=5;
					boxY=0;
					current=next;
					rotation=0;
					Random r = new Random();
					next=Math.abs(r.nextInt()) % 7+1;
					clear();
				}
				break;
			case 2: //L shape
				if(rotation==0){
					if(boxY+1==19||(tet[boxX][boxY+2]!=0||tet[boxX+1][boxY+2]!=0||tet[boxX+2][boxY+2]!=0))
					{
						tet[boxX][boxY]=2;
						tet[boxX][boxY+1]=2;
						tet[boxX+1][boxY+1]=2;
						tet[boxX+2][boxY+1]=2;
						boxX=5;
						boxY=0;
						current=next;
						rotation=0;
						Random r = new Random();
						next=Math.abs(r.nextInt()) % 7+1;
						clear();
					}
				}
				else if(rotation==1){
					if(boxY+1==19||(tet[boxX-1][boxY+2]!=0||tet[boxX][boxY+2]!=0)){
						tet[boxX][boxY]=2;
						tet[boxX][boxY-1]=2;
						tet[boxX][boxY+1]=2;
						tet[boxX-1][boxY+1]=2;
						boxX=5;
						boxY=0;
						current=next;
						rotation=0;
						Random r = new Random();
						next=Math.abs(r.nextInt()) % 7+1;
						clear();
					}
				}
				else if(rotation==2){
					if(boxY+1==19||tet[boxX+1][boxY+2]!=0||tet[boxX][boxY+1]!=0||tet[boxX-1][boxY+1]!=0){
						tet[boxX][boxY]=2;
						tet[boxX-1][boxY]=2;
						tet[boxX+1][boxY]=2;
						tet[boxX+1][boxY+1]=2;
						boxX=5;
						boxY=0;
						current=next;
						rotation=0;
						Random r = new Random();
						next=Math.abs(r.nextInt()) % 7+1;
						clear();
					}
				}
				else{
					if(boxY+1==19||tet[boxX][boxY+2]!=0||tet[boxX+1][boxY]!=0){
						tet[boxX][boxY-1]=2;
						tet[boxX][boxY]=2;
						tet[boxX][boxY+1]=2;
						tet[boxX+1][boxY-1]=2;
						boxX=5;
						boxY=0;
						current=next;
						rotation=0;
						Random r = new Random();
						next=Math.abs(r.nextInt()) % 7+1;
						clear();
					}
				}
				break;
			case 3: //L inverted
				if(rotation==0){
					if(boxY+1==19||(tet[boxX][boxY+2]!=0||tet[boxX-1][boxY+2]!=0||tet[boxX-2][boxY+2]!=0))
					{
						tet[boxX][boxY]=3;
						tet[boxX][boxY+1]=3;
						tet[boxX-1][boxY+1]=3;
						tet[boxX-2][boxY+1]=3;
						boxX=5;
						boxY=0;
						current=next;
						rotation=0;
						Random r = new Random();
						next=Math.abs(r.nextInt()) % 7+1;
						clear();
					}
				}
				else if(rotation==1){
					if(boxY+1==19||(tet[boxX-1][boxY]!=0||tet[boxX][boxY+2]!=0)){
						tet[boxX][boxY]=3;
						tet[boxX][boxY-1]=3;
						tet[boxX][boxY+1]=3;
						tet[boxX-1][boxY-1]=3;
						boxX=5;
						boxY=0;
						current=next;
						rotation=0;
						Random r = new Random();
						next=Math.abs(r.nextInt()) % 7+1;
						clear();
					}
				}
				else if(rotation==2){
					if(boxY+1==19||(tet[boxX-1][boxY+2]!=0||tet[boxX][boxY+1]!=0||tet[boxX+1][boxY+1]!=0)){
						tet[boxX][boxY]=3;
						tet[boxX-1][boxY]=3;
						tet[boxX+1][boxY]=3;
						tet[boxX-1][boxY+1]=3;
						boxX=5;
						boxY=0;
						current=next;
						rotation=0;
						Random r = new Random();
						next=Math.abs(r.nextInt()) % 7+1;
						clear();
					}
				}
				else{
					if(boxY+1==19||(tet[boxX][boxY+2]!=0||tet[boxX+1][boxY+2]!=0)){
						tet[boxX][boxY]=3;
						tet[boxX][boxY-1]=3;
						tet[boxX][boxY+1]=3;
						tet[boxX+1][boxY+1]=3;
						boxX=5;
						boxY=0;
						current=next;
						rotation=0;
						Random r = new Random();
						next=Math.abs(r.nextInt()) % 7+1;
						clear();
					}
				}
				
				break;
			case 4: //T shape
				if(rotation==0){
					if(boxY+1==19||(tet[boxX][boxY+2]!=0||tet[boxX+1][boxY+2]!=0||tet[boxX-1][boxY+2]!=0))
					{
						tet[boxX][boxY]=4;
						tet[boxX+1][boxY+1]=4;
						tet[boxX-1][boxY+1]=4;
						tet[boxX][boxY+1]=4;
						boxX=5;
						boxY=0;
						current=next;
						rotation=0;
						Random r = new Random();
						next=Math.abs(r.nextInt()) % 7+1;
						clear();
					}
				}
				else if(rotation==1){
					if(boxY+1==19||(tet[boxX-1][boxY+1]!=0||tet[boxX][boxY+2]!=0)){
						tet[boxX][boxY]=4;
						tet[boxX][boxY+1]=4;
						tet[boxX][boxY-1]=4;
						tet[boxX-1][boxY]=4;
						boxX=5;
						boxY=0;
						current=next;
						rotation=0;
						Random r = new Random();
						next=Math.abs(r.nextInt()) % 7+1;
						clear();
					}
				}
				else if(rotation==2){
					if(boxY+1==19||(tet[boxX-1][boxY+1]!=0||tet[boxX][boxY+2]!=0||tet[boxX+1][boxY+1]!=0)){
						tet[boxX][boxY]=4;
						tet[boxX-1][boxY]=4;
						tet[boxX+1][boxY]=4;
						tet[boxX][boxY+1]=4;
						boxX=5;
						boxY=0;
						current=next;
						rotation=0;
						Random r = new Random();
						next=Math.abs(r.nextInt()) % 7+1;
						clear();
					}
				}
				else{
					if(boxY+1==19||(tet[boxX][boxY+2]!=0||tet[boxX+1][boxY+1]!=0)){
						tet[boxX][boxY]=4;
						tet[boxX][boxY-1]=4;
						tet[boxX][boxY+1]=4;
						tet[boxX+1][boxY]=4;
						boxX=5;
						boxY=0;
						current=next;
						rotation=0;
						Random r = new Random();
						next=Math.abs(r.nextInt()) % 7+1;
						clear();
					}
				}
				
				break;
			
			case 5: //Z shape
				if(rotation==0){
					if(boxY+1==19||(tet[boxX][boxY+1]!=0||tet[boxX+1][boxY+2]!=0||tet[boxX+2][boxY+2]!=0))
					{
						tet[boxX][boxY]=5;
						tet[boxX+1][boxY]=5;
						tet[boxX+1][boxY+1]=5;
						tet[boxX+2][boxY+1]=5;
						boxX=5;
						boxY=0;
						current=next;
						rotation=0;
						Random r = new Random();
						next=Math.abs(r.nextInt()) % 7+1;
						clear();
					}
				}
				else{
					if(boxY+1==19||(tet[boxX-1][boxY+2]!=0||tet[boxX][boxY+1]!=0))
					{
						tet[boxX][boxY]=5;
						tet[boxX][boxY-1]=5;
						tet[boxX-1][boxY]=5;
						tet[boxX-1][boxY+1]=5;
						boxX=5;
						boxY=0;
						current=next;
						rotation=0;
						Random r = new Random();
						next=Math.abs(r.nextInt()) % 7+1;
						clear();
					}
				}
				
				break;
			case 6: //Z inverted
				if(rotation==0){
					if(boxY+1==19||(tet[boxX][boxY+1]!=0||tet[boxX-1][boxY+2]!=0||tet[boxX-2][boxY+2]!=0))
					{
						tet[boxX][boxY]=6;
						tet[boxX-1][boxY]=6;
						tet[boxX-1][boxY+1]=6;
						tet[boxX-2][boxY+1]=6;
						boxX=5;
						boxY=0;
						current=next;
						rotation=0;
						Random r = new Random();
						next=Math.abs(r.nextInt()) % 7+1;
						clear();
					}
				}
				else{
					if(boxY+1==19||(tet[boxX-1][boxY+1]!=0||tet[boxX][boxY+2]!=0))
					{
						tet[boxX][boxY]=6;
						tet[boxX][boxY+1]=6;
						tet[boxX-1][boxY]=6;
						tet[boxX-1][boxY-1]=6;
						boxX=5;
						boxY=0;
						current=next;
						rotation=0;
						Random r = new Random();
						next=Math.abs(r.nextInt()) % 7+1;
						clear();
					}
				}
				
				break;
			case 7: //line
				if(rotation==0){
					if(boxY==19||(tet[boxX][boxY+1]!=0||tet[boxX+1][boxY+1]!=0||tet[boxX+2][boxY+1]!=0||tet[boxX-1][boxY+1]!=0))
					{
						tet[boxX-1][boxY]=7;
						tet[boxX][boxY]=7;
						tet[boxX+1][boxY]=7;
						tet[boxX+2][boxY]=7;
						boxX=5;
						boxY=0;
						current=next;
						rotation=0;
						Random r = new Random();
						next=Math.abs(r.nextInt()) % 7+1;
						String s="";
						clear();
					}
				}
				else{
					if(boxY+2==19||(boxY+3<=19&&tet[boxX][boxY+3]!=0))
					{
						tet[boxX][boxY-1]=7;
						tet[boxX][boxY]=7;
						tet[boxX][boxY+1]=7;
						tet[boxX][boxY+2]=7;
						boxX=5;
						boxY=0;
						current=next;
						rotation=0;
						Random r = new Random();
						next=Math.abs(r.nextInt()) % 7+1;
						String s="";
						clear();
					}
				}
				break;
		}
	}
	
	void clear(){
		int left=iX(-rwidth/2),right=iX(rwidth/2), bottom=iY(-rHeight/2),top=iY(rHeight/2);
		int count=0;
		
		for(int i=0;i<20;i++){
			count=0;
			for(int j=0;j<10;j++){
				if(tet[j][i]!=0){
					count++;
				}
			}
			if(count==10){
				//System.out.println("Count is ten i: "+i);
				for(int k=i;k>0;k--){
					for(int j=0;j<10;j++){
						tet[j][k]=tet[j][k-1];
					}
				}
				line_count++;
				score=score+(level*score_fact);
				//fallSpeed=fallSpeed*(1+(level*score_fact));
				if(line_count==NoOfRows){
					level++;
					fallSpeed=fallSpeed*(1+level*speedfact);
				}
			}
		}
		
	}
	void drawBlock(int X,int Y,Graphics g, int current, int rotation){
		int left=iX(-rwidth/2),right=iX(rwidth/2), bottom=iY(-rHeight/2),top=iY(rHeight/2);
		X=((X)*(right-left)/10)+(left/3);
		Y=((Y)*(right-left)/10)+top;
		int size=(right-left)/10;
		switch(current){
    	case 1: //square shape
    		g.setColor(Color.BLACK);
    		g.drawRect(X, Y, size, size);
    		g.drawRect(X+size, Y, size, size);
    		g.drawRect(X+size, Y+size, size, size);
    		g.drawRect(X, Y+size, size, size);
    		g.setColor(new Color(34,139,34));
    		g.fillRect(X+1, Y+1, size-1, size-1);
    		g.fillRect(X+1+size, Y+1, size-1, size-1);
    		g.fillRect(X+1+size, Y+1+size, size-1, size-1);
    		g.fillRect(X+1, Y+1+size, size-1, size-1);
    		g.setColor(Color.BLACK);
    		break;
    	case 2: // L shape
    		if(rotation==0){
	    		g.setColor(Color.BLACK);
	    		g.drawRect(X, Y, size, size);
	    		g.drawRect(X, Y+size, size, size);
	    		g.drawRect(X+size, Y+size, size, size);
	    		g.drawRect(X+(2*size), Y+size, size, size);
	    		g.setColor(new Color(0,0,204));
	    		g.fillRect(X+1, Y+1, size-1, size-1);
	    		g.fillRect(X+1, Y+size+1, size-1, size-1);
	    		g.fillRect(X+1+size, Y+1+size, size-1, size-1);
	    		g.fillRect(X+1+(2*size), Y+1+size, size-1, size-1);
	    		g.setColor(Color.BLACK);
    		}
    		else if(rotation==1){
    			g.setColor(Color.BLACK);
	    		g.drawRect(X, Y, size, size);
	    		g.drawRect(X, Y-size, size, size);
	    		g.drawRect(X-size, Y+size, size, size);
	    		g.drawRect(X, Y+size, size, size);
	    		g.setColor(new Color(0,0,204));
	    		g.fillRect(X+1, Y+1, size-1, size-1);
	    		g.fillRect(X+1, Y-size+1, size-1, size-1);
	    		g.fillRect(X+1-size, Y+1+size, size-1, size-1);
	    		g.fillRect(X+1, Y+1+size, size-1, size-1);
	    		g.setColor(Color.BLACK);
    		}
    		else if(rotation==2){
    			g.setColor(Color.BLACK);
	    		g.drawRect(X-size, Y, size, size);
	    		g.drawRect(X, Y, size, size);
	    		g.drawRect(X+size, Y, size, size);
	    		g.drawRect(X+size, Y+size, size, size);
	    		g.setColor(new Color(0,0,204));
	    		g.fillRect(X+1-size, Y+1, size-1, size-1);
	    		g.fillRect(X+1, Y+1, size-1, size-1);
	    		g.fillRect(X+1+size, Y+1, size-1, size-1);
	    		g.fillRect(X+1+size, Y+1+size, size-1, size-1);
	    		g.setColor(Color.BLACK);
    		}
    		else if(rotation==3){
    			g.setColor(Color.BLACK);
	    		g.drawRect(X, Y-size, size, size);
	    		g.drawRect(X, Y, size, size);
	    		g.drawRect(X, Y+size, size, size);
	    		g.drawRect(X+size, Y-size, size, size);
	    		g.setColor(new Color(0,0,204));
	    		g.fillRect(X+1, Y+1-size, size-1, size-1);
	    		g.fillRect(X+1, Y+1, size-1, size-1);
	    		g.fillRect(X+1, Y+1+size, size-1, size-1);
	    		g.fillRect(X+1+size, Y+1-size, size-1, size-1);
	    		g.setColor(Color.BLACK);
    		}
    		break;
    	case 3: // L inverted shape
    		if(rotation==0){
    			g.setColor(Color.BLACK);
        		g.drawRect(X, Y, size, size);
        		g.drawRect(X, Y+size, size, size);
        		g.drawRect(X-size, Y+size, size, size);
        		g.drawRect(X-(2*size), Y+size, size, size);
        		g.setColor(new Color(255,0,0));
        		g.fillRect(X+1, Y+1, size-1, size-1);
        		g.fillRect(X+1, Y+size+1, size-1, size-1);
        		g.fillRect(X+1-size, Y+1+size, size-1, size-1);
        		g.fillRect(X+1-(2*size), Y+1+size, size-1, size-1);
        		g.setColor(Color.BLACK);
    		}
    		else if(rotation==1){
    			
        		g.setColor(Color.BLACK);
        		g.drawRect(X-size, Y-size, size, size);
        		g.drawRect(X, Y-size, size, size);
        		g.drawRect(X, Y, size, size);
        		g.drawRect(X, Y+size, size, size);
        		g.setColor(new Color(255,0,0));
        		g.fillRect(X+1-size, Y+1-size, size-1, size-1);
        		g.fillRect(X+1, Y-size+1, size-1, size-1);
        		g.fillRect(X+1, Y+1, size-1, size-1);
        		g.fillRect(X+1, Y+1+size, size-1, size-1);
        		g.setColor(Color.BLACK);
    		}
    		else if(rotation==2){
    			g.setColor(Color.BLACK);
        		g.drawRect(X-size, Y, size, size);
        		g.drawRect(X, Y, size, size);
        		g.drawRect(X+size, Y, size, size);
        		g.drawRect(X-size, Y+size, size, size);
        		g.setColor(new Color(255,0,0));
        		g.fillRect(X+1-size, Y+1, size-1, size-1);
        		g.fillRect(X+1, Y+1, size-1, size-1);
        		g.fillRect(X+1+size, Y+1, size-1, size-1);
        		g.fillRect(X+1-size, Y+1+size, size-1, size-1);
        		g.setColor(Color.BLACK);
    		}
    		else if(rotation==3){
    			g.setColor(Color.BLACK);
        		g.drawRect(X, Y-size, size, size);
        		g.drawRect(X, Y, size, size);
        		g.drawRect(X, Y+size, size, size);
        		g.drawRect(X+size, Y+size, size, size);
        		g.setColor(new Color(255,0,0));
        		g.fillRect(X+1, Y+1-size, size-1, size-1);
        		g.fillRect(X+1, Y+1, size-1, size-1);
        		g.fillRect(X+1, Y+1+size, size-1, size-1);
        		g.fillRect(X+1+size, Y+1+size, size-1, size-1);
        		g.setColor(Color.BLACK);
    		}
    		
    		break;
    	case 4: // T shape
    		if(rotation==0){
    			g.setColor(Color.BLACK);
        		g.drawRect(X, Y, size, size);
        		g.drawRect(X, Y+size, size, size);
        		g.drawRect(X-size, Y+size, size, size);
        		g.drawRect(X+size, Y+size, size, size);
        		g.setColor(new Color(255,153,51));
        		g.fillRect(X+1, Y+1, size-1, size-1);
        		 g.fillRect(X+1, Y+size+1, size-1, size-1);
        		g.fillRect(X+1-size, Y+1+size, size-1, size-1);
        		g.fillRect(X+1+size, Y+1+size, size-1, size-1);
        		g.setColor(Color.BLACK);
    		}
    		else if(rotation==1){
    			g.setColor(Color.BLACK);
        		g.drawRect(X, Y-size, size, size);
        		g.drawRect(X, Y, size, size);
        		g.drawRect(X, Y+size, size, size);
        		g.drawRect(X-size, Y, size, size);
        		g.setColor(new Color(255,153,51));
        		g.fillRect(X+1, Y+1-size, size-1, size-1);
        		g.fillRect(X+1, Y+1, size-1, size-1);
        		g.fillRect(X+1, Y+1+size, size-1, size-1);
        		g.fillRect(X+1-size, Y+1, size-1, size-1);
        		g.setColor(Color.BLACK);
    		}
    		else if(rotation==2){
    			g.setColor(Color.BLACK);
        		g.drawRect(X-size, Y, size, size);
        		g.drawRect(X, Y, size, size);
        		g.drawRect(X+size, Y, size, size);
        		g.drawRect(X, Y+size, size, size);
        		g.setColor(new Color(255,153,51));
        		g.fillRect(X+1-size, Y+1, size-1, size-1);
        		g.fillRect(X+1, Y+1, size-1, size-1);
        		g.fillRect(X+1+size, Y+1, size-1, size-1);
        		g.fillRect(X+1, Y+1+size, size-1, size-1);
        		g.setColor(Color.BLACK);
    		}
    		else if(rotation==3){
    			g.setColor(Color.BLACK);
        		g.drawRect(X, Y-size, size, size);
        		g.drawRect(X, Y, size, size);
        		g.drawRect(X, Y+size, size, size);
        		g.drawRect(X+size, Y, size, size);
        		g.setColor(new Color(255,153,51));
        		g.fillRect(X+1, Y+1-size, size-1, size-1);
        		g.fillRect(X+1, Y+1, size-1, size-1);
        		g.fillRect(X+1, Y+1+size, size-1, size-1);
        		g.fillRect(X+1+size, Y+1, size-1, size-1);
        		g.setColor(Color.BLACK);
    		}
    		break;
    	case 5: // Z shape
    		if(rotation==0){
    			g.setColor(Color.BLACK);
        		g.drawRect(X, Y, size, size);
        		g.drawRect(X+size, Y, size, size);
        		g.drawRect(X+size, Y+size, size, size);
        		g.drawRect(X+(2*size), Y+size, size, size);
        		g.setColor(new Color(153,0,153));
        		g.fillRect(X+1, Y+1, size-1, size-1);
        		g.fillRect(X+1+size, Y+1, size-1, size-1);
        		g.fillRect(X+1+size, Y+1+size, size-1, size-1);
        		g.fillRect(X+1+(2*size), Y+1+size, size-1, size-1);
        		g.setColor(Color.BLACK);
    		}
    		else{
    			g.setColor(Color.BLACK);
        		g.drawRect(X, Y-size, size, size);
        		g.drawRect(X, Y, size, size);
        		g.drawRect(X-size, Y, size, size);
        		g.drawRect(X-size, Y+size, size, size);
        		g.setColor(new Color(153,0,153));
        		g.fillRect(X+1, Y+1-size, size-1, size-1);
        		g.fillRect(X+1, Y+1, size-1, size-1);
        		g.fillRect(X+1-size, Y+1, size-1, size-1);
        		g.fillRect(X+1-size, Y+1+size, size-1, size-1);
        		g.setColor(Color.BLACK);
    		}
    		
    		break;
    	case 6: // Z inverted shape
    		if(rotation==0){
    			g.setColor(Color.BLACK);
        		g.drawRect(X, Y, size, size);
        		g.drawRect(X-size, Y, size, size);
        		g.drawRect(X-size, Y+size, size, size);
        		g.drawRect(X-(2*size), Y+size, size, size);
        		g.setColor(new Color(255,255,51));
        		g.fillRect(X+1, Y+1, size-1, size-1);
        		g.fillRect(X+1-size, Y+1, size-1, size-1);
        		g.fillRect(X+1-size, Y+1+size, size-1, size-1);
        		g.fillRect(X+1-(2*size), Y+1+size, size-1, size-1);
        		g.setColor(Color.BLACK);
    		}
    		else {
    			g.setColor(Color.BLACK);
        		g.drawRect(X-size, Y-size, size, size);
        		g.drawRect(X-size, Y, size, size);
        		g.drawRect(X, Y, size, size);
        		g.drawRect(X, Y+size, size, size);
        		g.setColor(new Color(255,255,51));
        		g.fillRect(X+1-size, Y+1-size, size-1, size-1);
        		g.fillRect(X+1-size, Y+1, size-1, size-1);
        		g.fillRect(X+1, Y+1, size-1, size-1);
        		g.fillRect(X+1, Y+1+size, size-1, size-1);
        		g.setColor(Color.BLACK);
    		}
    		break;
    	case 7: // Line
    		if(rotation==0){
    			g.setColor(Color.BLACK);
        		g.drawRect(X, Y, size, size);
        		g.drawRect(X+size, Y, size, size);
        		g.drawRect(X+(2*size), Y, size, size);
        		g.drawRect(X-size, Y, size, size);
        		g.setColor(new Color(0,204,204));
        		g.fillRect(X+1, Y+1, size-1, size-1);
        		g.fillRect(X+1+size, Y+1, size-1, size-1);
        		g.fillRect(X+1+(2*size), Y+1, size-1, size-1);
        		g.fillRect(X+1-size, Y+1, size-1, size-1);
        		g.setColor(Color.BLACK);
    		}
    		else{
    			g.setColor(Color.BLACK);
        		g.drawRect(X, Y-size, size, size);
        		g.drawRect(X, Y, size, size);
        		g.drawRect(X, Y+size, size, size);
        		g.drawRect(X, Y+(2*size), size, size);
        		g.setColor(new Color(0,204,204));
        		g.fillRect(X+1, Y+1-size, size-1, size-1);
        		g.fillRect(X+1, Y+1, size-1, size-1);
        		g.fillRect(X+1, Y+1+size, size-1, size-1);
        		g.fillRect(X+1, Y+1+(2*size), size-1, size-1);
        		g.setColor(Color.BLACK);
    		}
    		break;
    }
	}
	void fillRecs(Graphics g){
		int left=iX(-rwidth/2),right=iX(rwidth/2), bottom=iY(-rHeight/2),top=iY(rHeight/2);
		drawBlock(boxX,boxY,g,current,rotation);
		int size=(right-left)/10;
		g.setColor(Color.BLACK);
		for(int i=0;i<10;i++){
			for(int j=0;j<20;j++){
				if(tet[i][j]!=0){
					int X=((i)*(right-left)/10)+(left/3);
					int Y=((j)*(right-left)/10)+top;
					
					switch(tet[i][j]){
					case 1:
						g.setColor(new Color(34,139,34));
						break;
					case 2:
						g.setColor(new Color(0,0,204));
						break;
					case 3:
						g.setColor(new Color(255,0,0));
						break;
					case 4:
						g.setColor(new Color(255,153,51));
						break;
					case 5:
						g.setColor(new Color(153,0,153));
						break;
					case 6:
						g.setColor(new Color(255,255,51));
						break;
					case 7:
						g.setColor(new Color(0,204,204));
						break;
					}
		    		g.fillRect(X+1,Y+1, size-1, size-1);
		    		g.setColor(Color.BLACK);
		    		g.drawRect(X,Y, size, size);
				}
			}
		}
		g.setColor(Color.WHITE);
		//g.fillRect(((boxX+1)*(right-left)/10)+(left/3),((boxY+1)*(right-left)/10)+top ,(right-left)/10 , (right-left)/10);
		try {
			long t=(long) (100000/fallSpeed);
			if(fallSpeed==100.0F){
				
			}else{
				t=(long) (t-fallSpeed);
			}
			//System.out.println(100000/fallSpeed);
			Thread.sleep((long) (100000/fallSpeed));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boxY++;
		if(hover==0&&!game_over)
		repaint();
	}
	void quitGame(Graphics g, int X, int Y){
		int left=iX(-rwidth/2),right=iX(rwidth/2), bottom=iY(-rHeight/2),top=iY(rHeight/2);
		//Font myFont = new Font ("Courier New", 1, (int)(Math.min(X, Y)/15));
		Font myFont = new Font ("Courier New", 1, (right-left)/10);
		g.setFont(myFont);
		g.setColor(Color.WHITE);
		g.drawRect(X, Y, 3*(right-left)/10, 2*(right-left)/10);
		g.drawString("QUIT", X+(3*(right-left)/100), Y+((right-left)/10));
	}

	void score(Graphics g,int X, int Y){
		//Font myFont = new Font ("Courier New", 1, (int)(Math.min(X, Y)/15));
		int left=iX(-rwidth/2),right=iX(rwidth/2), bottom=iY(-rHeight/2),top=iY(rHeight/2);
		Font myFont = new Font ("Courier New", 1, (right-left)/10);
		g.setFont(myFont);
		g.setColor(Color.WHITE);
		g.drawString("Level: "+level, X, Y);
		g.drawString("Lines: "+line_count, X, Y+(2*(right-left)/10));
		g.drawString("Score: "+score, X, Y+(4*(right-left)/10));
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		movX=arg0.getX();
		movY=arg0.getY();
		int left=iX(-rwidth/2),right=iX(rwidth/2), bottom=iY(-rHeight/2),top=iY(rHeight/2);
		int xx=(int)(left-(2*left/3)),yy=top+10;
		if((movX>=xx&&movX<=(xx+right-left))&&(movY>=yy&&movY<=(yy+bottom-top-20)))
		{
			hover=1;
			msg = "PAUSE";
			if(!game_over)
			repaint();
		}
		else{
			hover=0;
			if(!game_over)
			repaint();
		}
			
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub
		
		int notches = arg0.getWheelRotation();
		   if (notches < 0) {
	           switch(current){
	           		case 2:
	           		case 3:
	           		case 4:
	           			if(rotation==0) rotation=4;
	           			rotation= (rotation-1);
	           			break;
	           		case 5:
	           		case 6:
	           		case 7:
	           			if(rotation==0) rotation=2;
	           			rotation= (rotation-1);
	           			break;
		        	   
	           }
	    	   
	       } else {
	    	   switch(current){
          		case 2:
          		case 3:
          		case 4:
          			if(rotation==3) rotation=-1;
          			rotation= (rotation+1)%4;
          			break;
          		case 5:
          		case 6:
          		case 7:
          			if(rotation==1) rotation=-1;
          			rotation= (rotation+1)%2;
          			break;   
	    	   }
	       }
		   if(!game_over)
	       repaint();
	}
	
}
