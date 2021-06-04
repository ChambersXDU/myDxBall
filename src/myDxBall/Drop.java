package myDxBall;

public class Drop extends PhysicalBody implements CollisionalBody {

	private DropFunc df = null; 
	private Map map;
	private int fallSpeed = 1;
	
	/**
	 * ���췽��������ʵ����һ��Drop����
	 * @param fallSpeed	�����ٶ�
	 * @param df	���߹���
	 * @param map	������ͼ����
	 * @param width		���
	 * @param height	�߶�
	 * @param position		λ��
	 */
	public Drop(int fallSpeed, DropFunc df ,Map map ,int width, int height, Vector2D position) {
		super(width, height, position);
		this.map = map;
		this.df = df;
		this.fallSpeed = fallSpeed;
	}

	
	/**
	 * ÿһ��ϷTick������ִ��
	 */
	@Override
	public void runPerTick() {
		// TODO Auto-generated method stub
		this.fall();
		this.check();

	}

	
	/**
	 * ������ư巢������ʱ������
	 */
	@Override
	public void onCollision() {
		// TODO Auto-generated method stub
		this.effect();
		this.destroy();

	}
	
	
	/**
	 * ���ü����룬����Ϸ������Ҫ��ע�����ݽ��м��
	 * eg.���������Ҫ����Ƿ��ռ����Ƿ������Ϸ����
	 * 		������ư巢����ײʱ�����߱��ռ���������Ч���ݻ�
	 * 		��������Ϸ����ʱ�����ߴݻ�
	 */
	public void check() {
		if(this.getPosition().getY() > this.map.bottom) {
			this.destroy();
		}
		if(this.isPhysicalBodyIn(this.map.controller)) {
			this.onCollision();
		}
	}
	
	/**
	 * ֪ͨmap���󣬶��Լ����дݻ�
	 */
	private void destroy() {
		// TODO Auto-generated method stub
		this.map.destroy(this);
	}

	/**
	 * ���ݵ����ٶ�fallSpeed�ƶ�
	 */
	private void fall() {
		this.setPosition(new Vector2D(this.getPosition().getX(),
				this.getPosition().getY()+this.fallSpeed));
	}
	
	/**
	 * ������Ч�����ݹ���df������Ϸ������ӦЧ��
	 */
	public void effect() {
		switch(df) {
		case doubleSpeed:
			if(this.map.ball.getSpeed()<5) {
				this.map.ball.setSpeed(this.map.ball.getSpeed()*2);
			}
			break;
		case halfSpeed:
			if(this.map.ball.getSpeed()>1) {
				this.map.ball.setSpeed(this.map.ball.getSpeed()/2);
			}
			break;
		}
	}

}
