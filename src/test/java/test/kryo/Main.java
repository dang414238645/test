package test.kryo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class Main {
	
	private int i=10;
	
	private String str="test";
	
	private Main main=null;
	
	

	public int getI() {
		return i;
	}



	public void setI(int i) {
		this.i = i;
	}



	public String getStr() {
		return str;
	}



	public void setStr(String str) {
		this.str = str;
	}



	public Main getMain() {
		return main;
	}



	public void setMain(Main main) {
		this.main = main;
	}



	public static void main(String[] args) throws IOException {
		Main main=new Main();
		main.setMain(main);
		Kryo kryo=new Kryo();
		OutputStream os=new FileOutputStream(new File("test.txt"));
		/**
		 * try(){}
		 * try后括号中 参数必须实现AutoCloseable接口 并且可以声明多个实现AutoCloseable接口的类
		 * 
		 * 上面的特性是jdk1.7后引入的
		 */
		try(Output output = new Output(os);){
		kryo.writeObject(output, main);
		}
		
		InputStream is=new FileInputStream(new File("test.txt"));
		try(Input input = new Input(is)){
			main=kryo.readObject(input, Main.class);
		}
		
		

	}

}
