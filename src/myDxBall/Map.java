package myDxBall;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;
public class Map implements KeyListener{
    
	private JFrame frame;
	private Mypanel panel;
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private ArrayList<Block> blocks = new ArrayList<Block>();
	private ArrayList<Drop> drops = new ArrayList<Drop>();
	public Ball ball;
	public Controller controller;
	public ArrayList<PhysicalBody> gameBody = new ArrayList<PhysicalBody>();
	private ArrayList<PhysicalBody> destroyList = new ArrayList<PhysicalBody>();
	private ArrayList<PhysicalBody> addList = new ArrayList<PhysicalBody>();
	public int top,bottom,left,right;

	private boolean gameOver = false;
	
	
	public static void main(String args[]) {
		Map m = new Map();
		m.initGame();
		m.initUI();
		m.run();
	}
	
	/**
	 * ��ʼ����Ϸ��������Ϸ����
	 */
	public void initGame() {
		this.top = 0;
		this.bottom = 550;
		this.left = 0;
		this.right = 1000;
		//������
		this.ball = new Ball(this, 20, new Vector2D(512, 500));
		this.ball.setDir(new Vector2D(1, -3));
		this.ball.setSpeed(0);
		//��ball���������Ϸ��������嵥
		this.gameBody.add(ball);
		
		//����ש��
		int blockX = 40;
		int blockY = 20;
		for (int i=1; i<25;i++) {
			for(int j=1; j<20;j++) {
				this.blocks.add(new Block(this, this.ball, 38, 18, new Vector2D(i*blockX, j*blockY)));
			}
		}
		//������block���������Ϸ��������嵥
		this.gameBody.addAll(blocks);
		
		//����ǽ��
		//��ǽ
		this.walls.add(new Wall(this.ball, 1024, 10, new Vector2D(512, 5)));
		//��ǽ
		this.walls.add(new Wall(this.ball, 10, 600, new Vector2D(5, 300)));
		//��ǽ
		this.walls.add(new Wall(this.ball, 10, 600, new Vector2D(995, 300)));
		this.gameBody.addAll(walls);
		
		//���ɿ��ư�
		this.controller = new Controller(this, 50, 10, new Vector2D(512, 550));
		this.gameBody.add(controller);
		
		
	}
	
	/**
	 * ��ʼ����Ϸ����
	 */
	public void initUI() {
		
		// ���� JFrame ʵ��
        this.frame = new JFrame("myDxBall");
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        
        // Setting the width and height of frame
        int windowWidth = 1016; //���ڿ�
        int windowHeight = 600; //���ڸ�
        this.frame.setSize(windowWidth, windowHeight);
        
        //���ھ���
        Toolkit kit = Toolkit.getDefaultToolkit(); //���幤�߰�
        Dimension screenSize = kit.getScreenSize(); //��ȡ��Ļ�ĳߴ�
        int screenWidth = screenSize.width; //��ȡ��Ļ�Ŀ�
        int screenHeight = screenSize.height; //��ȡ��Ļ�ĸ�
        this.frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
        
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �����������Ϸ���������JFrame
        this.panel = new Mypanel();  
        this.frame.add(this.panel);
        
        //���ÿɼ�
        this.frame.setVisible(true);
	}
	
    /**
     * ������Ϸ
     */
    public void run() {    
        this.frame.addKeyListener(this);
        //����ѭ��
        while(!this.gameOver) {
    	
        	
        	//������Ϸ���������
        	this.gameBody.addAll(addList);
        	this.addList.clear();
        	//������Ϸ�����destroy
        	this.gameBody.removeAll(destroyList);
        	this.destroyList.clear();
        	
        	//ÿ����Ϸ�������runPerTick()����
        	for(PhysicalBody body:this.gameBody) {
        		body.runPerTick();
        	}
        	
        	//��������Ϸ������Ƴ���
        	this.panel.setGameBodyList(this.gameBody);
        	this.panel.repaint();
        	
        	
        	
        	//��������
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            

        }

    }

    public void gameOver() {
    	this.gameOver= true;
    }
    
    static class Mypanel extends JPanel{
    	/**
		 * ������Ϸ����
		 */
		private static final long serialVersionUID = 1L;
		
		//�洢��Ҫ���Ƶ���Ϸ����
		ArrayList<PhysicalBody> gameBodyList = new ArrayList<PhysicalBody>();
    	
		//������Ҫ���Ƶ���Ϸ����
    	public void setGameBodyList(ArrayList<PhysicalBody> gameBodyList) {
    		this.gameBodyList = gameBodyList;
    	}
    	
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            //���û�����ɫ
            g.setColor(Color.BLUE);
            //���û��ʴ�С
            g.setFont(new Font(null, 0,50));
            //ѭ������������Ϸ����
            for(PhysicalBody body:gameBodyList) {
            	int x;
            	int y;
            	switch(body.getShape()) {
            	case rectangle:
            		if(body instanceof Block)
            			g.setColor(Color.CYAN);
            		else if(body instanceof Wall)
            			g.setColor(Color.RED);
            		else if(body instanceof Drop) 
            			g.setColor(Color.ORANGE);
            		x = body.getPosition().getX()-body.getWidth()/2;
            		y = body.getPosition().getY()-body.getHeight()/2;
            		g.fillRect(x, y, body.getWidth(), body.getHeight());
            		break;
            	case circular:
            		x = body.getPosition().getX()-body.getR();
            		y = body.getPosition().getY()-body.getR();
            		g.fillOval(x, y, body.getWidth(), body.getHeight());
            		break;
            	default:
            		break;
            	}

            }

        }
    }
    //��Ϸ�������Map��destroy������֪ͨMap�ݻٲ���body����������Ϸ����
    public void destroy(PhysicalBody body) {
    	this.destroyList.add(body);
    }
    
    //��Ϸ�������Map��add������֪ͨMap��Ӳ���body����������Ϸ����
    public void add(PhysicalBody body) {
    	this.addList.add(body);
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			this.controller.setDir(-1);
			break;
		case KeyEvent.VK_RIGHT:
			this.controller.setDir(1);
			break;
		case KeyEvent.VK_SPACE:
			if(!this.controller.isFire())
				this.controller.fire();
			break;
		default:
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			if(this.controller.getDir() == -1)
				this.controller.setDir(0);
			break;
		case KeyEvent.VK_RIGHT:
			if(this.controller.getDir() == 1)
				this.controller.setDir(0);
			break;
		default:
			break;
		}
	}

}