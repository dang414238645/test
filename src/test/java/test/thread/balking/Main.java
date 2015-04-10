package test.thread.balking;

public class Main {

	public static void main(String[] args) {
		Data data=new Data("data.txt", "(empty)");
		//用户（会有两个操作；写：保存）
		new ChangerThread("ChangerThread", data).start();
		//后台自动保存程序
		new SaverThread("SaverThread", data).start();
	}

}
