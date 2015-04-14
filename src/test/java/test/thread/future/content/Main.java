package test.thread.future.content;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		long start=System.currentTimeMillis();
		Content content1=Retriever.retrieve("http://www.zhihu.com/");
		Content content2=Retriever.retrieve("http://www.baidu.com/");
		Content content3=Retriever.retrieve("http://www.sohu.com/");
		
		saveToFile("zhihu.txt",content1);
		saveToFile("baidu.txt",content2);
		saveToFile("sohu.txt",content3);	
		
		long end=System.currentTimeMillis();
		
		System.out.println("Elapsed time="+(end-start)+"msec");
	}
	
	
	
	public static void saveToFile(String filename,Content content){
		byte[]bytes=content.getBytes();
		
		
		System.out.println(Thread.currentThread().getName()+":Saving to "+filename);
		try {
			FileOutputStream out = new FileOutputStream(filename);
			for(int i=0;i<bytes.length;i++){
				out.write(bytes[i]);
			}
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
