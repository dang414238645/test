package test.rhino;

import org.json.JSONException;
import org.json.JSONObject;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class Main {

	public static void main(String[] args) throws JSONException {
		 String jsPath = "c:\\test.js";
		   Context ctx=Context.enter();
		   Scriptable scope=ctx.initStandardObjects();
		   System.out.println("可住：asdfasdfasdf  asdf".replaceAll("可住：|\t|\r|\n|\\s", "")); 
		   
		   String jsStr="100*20/10";
//		   Object result=ctx.evaluateReader(scope,  new java.io.FileReader(jsPath), sourceName, lineno, securityDomain); 
//		   System.out.println("result="+result);
		   jsonParse();

	}
	
	
	public static void jsonParse() throws JSONException{
		org.mozilla.javascript.Context ctx = org.mozilla.javascript.Context.enter();
		org.mozilla.javascript.Scriptable jsBeautyScope = ctx.initStandardObjects();
		System.out.println(ctx.evaluateString(jsBeautyScope, "var tt={a:1,b:2}" + "; \"\"+tt.b", null, 0, null));
		
		
		JSONObject json = new JSONObject("{a:{b:1}}");
		System.out.println(json.getJSONObject("a").get("b"));
	}
	

}
