package test.thread.bank;

public class Bank {
	private int money=100000000;
	
	public synchronized int quqian(int r){
		if(money>=r){
			money=money-r;
			return r;
		}else{
			return 0;
		}
	}
	
	
	public int quqian1(int r){
		if(money>=r){
			money=money-r;
			return r;
		}else{
			return 0;
		}
	}
	
	
	public  boolean hasMoney(int r){
		return money>=r;
	}
	
	public  int reduceMoney(int r){
		money=money-r;
		return r;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	
	
}
