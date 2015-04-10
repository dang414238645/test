package test.rhino;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class Main {

	public static void main(String[] args) {
		 String jsPath = "c:\\test.js";
		   Context ctx=Context.enter();
		   Scriptable scope=ctx.initStandardObjects();
		   
		   String jsStr="100*20/10";
//		   Object result=ctx.evaluateReader(scope,  new java.io.FileReader(jsPath), sourceName, lineno, securityDomain); 
//		   System.out.println("result="+result);

	}

}
