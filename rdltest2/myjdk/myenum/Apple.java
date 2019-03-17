package myenum;

public enum Apple {

	BIGAPPLE(3,3), MIDDLEAPPLE(2,2), SMALLAPPLE(1,1);
	
	private final int shape;
	private final int price;
	
	Apple( int shape,int price ){
		this.shape = shape;
		this.price = price;
	}

	public int getMoney() {
		return shape*price;
	}
	
	public String toString() {
		return super.toString() + " price " +price + "  shape " +shape;
		
	}
	
}
