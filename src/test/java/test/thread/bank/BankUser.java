package test.thread.bank;

public class BankUser implements Runnable{
	private Bank bank;
	
	private int my;
	
	public BankUser(Bank bank) {
		this.bank = bank;
	}

	@Override
	public void run() {
		int r=1;
		//实现1
//		while(true){
//			synchronized (bank) {
//				if(bank.hasMoney(r)){
//					setMy(my+bank.reduceMoney(r));
//				}else{
//					break;
//				}
//			}
//		}
		
		//实现2
//		while(true){
//			setMy(my+bank.quqian(r));
//		}
		
		//实现3
		
		while(true){
			synchronized (bank) {
				bank.notify();
				if(bank.hasMoney(r)){
					setMy(my+bank.reduceMoney(r));
				}
				try {
					bank.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		
		
	}

	public int getMy() {
		return my;
	}

	public void setMy(int my) {
		this.my = my;
	}
	
	

}
