package myDxBall;

public abstract class PhysicalBody implements Base{
	protected int width;
	protected int height;
	protected Vector2D position;
	protected Shape shape;
	

	public PhysicalBody(int width, int height, Vector2D position) {
		/**
		 * �������PysicalBody
		 */
		super();
		this.width = width;
		this.height = height;
		this.position = position;
		this.shape = Shape.rectangle;
	}
	
	public PhysicalBody(int r, Vector2D position) {
		/**
		 * ����Բ��PysicalBody
		 */
		super();
		this.width = r;
		this.height = r;
		this.position = position;
		this.shape = Shape.circular;
	}
	
	public int getR() {
		return width/2;
	}

	public void setR(int r) {
		this.width = r*2;
		this.height = r*2;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Vector2D getPosition() {
		return position;
	}


	public void setPosition(Vector2D position) {
		this.position = position;
	}


	public Shape getShape() {
		return shape;
	}

	public boolean isPhysicalBodyIn(PhysicalBody body) {
	/**
	 * ��ײ���
	 */
		switch(body.getShape()) {
		case circular:
			return isCircularIn(body);
		case rectangle:
			return isRectangleIn(body);
		default:
			return false;
		}
	}
	
	private boolean isCircularIn(PhysicalBody cir) {
		switch(this.getShape()) {
		case circular:
			//Բ��Բ����ײ
			return false;
		case rectangle:
			//Բ�;��ε���ײ
			return isCirInRec(cir, this);
		default:
			return false;
		}
	}
	
	private boolean isRectangleIn(PhysicalBody rec) {
		switch(this.getShape()) {
		case circular:
			//���κ�Բ����ײ
			return isCirInRec(this, rec);
		case rectangle:
			//���κ;��ε���ײ
			return isRecInRec(this, rec);
		default:
			return false;
		}
	}
	
	private boolean isRecInRec(PhysicalBody A, PhysicalBody B) {
		/*
		 * ���κ;�����ײ���*/
		//��ࣺ�������ľ���
		Vector2D distance = A.position.distanceOf(B.position);
		if(distance.getX()<=(A.width+B.width)/2
				&& distance.getY()<=(A.height+B.height)/2) {
			return true;
		}
		return false;
	}
	
	private boolean isCirInRec(PhysicalBody cir, PhysicalBody rec) {
		/*
		 * Բ�;�����ײ���*/
		int r = cir.getR();
		int x = Math.abs(cir.getPosition().getX() - rec.getPosition().getX());
		int y = Math.abs(cir.getPosition().getY() - rec.getPosition().getY());
		int minX = Math.min(Math.abs(rec.getPosition().getX()+rec.getWidth()/2-cir.getPosition().getX()), 
				Math.abs(rec.getPosition().getX()-rec.getWidth()/2-cir.getPosition().getX()));
		int minY = Math.min(Math.abs(rec.getPosition().getY()+rec.getHeight()/2-cir.getPosition().getY()), 
				Math.abs(rec.getPosition().getY()-rec.getHeight()/2-cir.getPosition().getY()));
		int dy = Math.abs(y-this.getHeight()/2);
		if((x-r)<rec.getWidth()/2 && (y-r)<rec.getHeight()/2) {
			return true;
		}
		if(Math.pow(minX, 2)+Math.pow(minY, 2)<=Math.pow(cir.getR(), 2)) {
			return true;
		}
		return false;
	}
	
}
