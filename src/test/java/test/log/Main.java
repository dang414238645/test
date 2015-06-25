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
		
		System.out.println(new Integer(1).toString());
		
	}

}
