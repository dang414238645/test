package test.any;

public class Main implements Cloneable{

	private int i=0;
	
	
	
	public int getI() {
		return i;
	}



	public void setI(int i) {
		this.i = i;
	}



	@Override
	protected Object clone() throws CloneNotSupportedException {
		return this.clone();
	}



	public static void main(String[] args) throws CloneNotSupportedException {
		Main main=new Main();
		Main x=(Main) main.clone();
		System.out.println(x.getI());
	}

}
