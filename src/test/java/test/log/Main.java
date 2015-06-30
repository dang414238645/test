package test.log;

public class Main {
	
	public void throwException(){
		try {
			throw new Exception();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			StackTraceElement stack[] = (new Throwable()).getStackTrace();
		}
	}

	public static void main(String[] args) {
		Main main=new Main();
		main.throwException();
		System.out.println("a");
		StackTraceElement stack[] = (new Throwable()).getStackTrace();  
		for(StackTraceElement s:stack){
			System.out.println("MethodName:"+s.getMethodName()+"	LineNumber:"+s.getLineNumber()+"	ClassName:"+s.getClassName());
		}
		System.out.println("a");
		
		System.out.println("\u57CE\u5E02\u4FBF\u6377\u9152\u5E97(\u5317\u6D41\u6C7D\u8F66\u603B\u7AD9\u5E97)");
		
	}

}
