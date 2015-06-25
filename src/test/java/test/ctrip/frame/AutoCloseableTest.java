package test.ctrip.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class AutoCloseableTest implements AutoCloseable {
	
	private String str;
	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	@Override
	public void close() throws Exception {
		System.out.println("Close resource!");
	}

	public void readResource() {
		System.out.println("Read resource!");
	}

	@Test
	public void testCloseResource() throws Exception {
//		AutoCloseableTest autoCloseable = new AutoCloseableTest();
		try (AutoCloseableTest autoCloseable = new AutoCloseableTest()) {
			autoCloseable.readResource();
		}
	}
	
	
	@Test
	public void testConlections(){
		ArrayList<String> li=new ArrayList<String>();
		li.add("a");
		li.add("b");
		List<ArrayList<String>> list=Collections.singletonList(li);
		System.out.println(list.get(0).get(0));
	}
	
	

}