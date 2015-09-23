package test.book.suanfa.chapter1;

public class Main {
	
	
	public static double sqrt(double c){
		if(c<0){return Double.NaN;}
		double err=1e-15;
		double t=c;
		while(Math.abs(t-c/t)>err*t){
			t=(c/t+t)/2.0;
		}
		return t;
	}
	
	public static double H(int N)
	{
		double sum =0.0;
		for(int i=1;i<N;i++)
		{
			sum +=1.0/i;
		}
		return sum;
	}

	public static void main(String[] args) {
		System.out.println(sqrt(4));
		System.out.println(H(3));
		//java 对数据的溢出不做检查
		System.out.println(Math.abs(-2147483648-10));
	}

}
