package test.thread.balking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Data {
	private final String filename;
	private String content;
	private boolean changed;
	public Data(String filename, String content) {
		this.filename = filename;
		this.content = content;
	}
	
	public synchronized void change(String newcontent){
		content=newcontent;
		changed=true;
	}
	
	public synchronized void save() throws IOException{
		if(!changed){
			System.out.println("--保存操作取消----");
			return;
		}
		doSave();
		changed=false;
	}

	private void doSave() throws IOException {
		System.out.println(Thread.currentThread().getName()+" calls dosave ,content="+content);
		Writer writer=new FileWriter(filename);
		writer.write(content);
		writer.close();
		
	}
	
}
