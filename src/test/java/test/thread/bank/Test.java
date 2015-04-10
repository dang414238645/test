package test.thread.bank;

public class Test {
	public static void main(String args[]) throws InterruptedException{
		Bank b=new Bank();
		BankUser u1=new BankUser(b);
		BankUser u2=new BankUser(b);
		BankUser u3=new BankUser(b);
		new Thread(u1).start();
		new Thread(u2).start();
		new Thread(u3).start();
		
		Thread.sleep(51000);
		System.out.println(b.getMoney());
		System.out.println(u1.getMy());
		System.out.println(u2.getMy());
		System.out.println(u3.getMy());
		System.out.println(u3.getMy()+u2.getMy()+u1.getMy());
	}
}
