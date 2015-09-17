package test.rhino;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.io.FileUtils;
import org.apache.xerces.impl.dv.util.Base64;
import org.bouncycastle.util.encoders.UrlBase64;
import org.json.JSONException;
import org.json.JSONObject;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class Main {

	public static void main(String[] args) throws JSONException, ScriptException, IOException {
		
		long currentTime=System.currentTimeMillis();
		System.out.println("  (".replaceAll("\\s+\\(", "\\q("));
		
		for(int i=0;i<100000;i++){
			System.out.println(i);
		}
		System.out.println((System.currentTimeMillis()-currentTime)/1000d+"s");
		 String jsPath = "c:\\test.js";
		   Context ctx=Context.enter();
		   Scriptable scope=ctx.initStandardObjects();
		   System.out.println("可住：asdfasdfasdf  asdf".replaceAll("可住：|\t|\r|\n|\\s", "")); 
		   "哈哈,2,3家酒店".replaceAll("\\D", "");
		   System.out.println(URLDecoder.decode("%2b"));
		   System.out.println(URLDecoder.decode("%2f"));
		   System.out.println(URLDecoder.decode("%3d"));
		   System.out.println(URLDecoder.decode("http://www.agoda.com/zh-cn/ascott-heng-shan-road/hotel/shanghai-cn.html?asq=XqlQ7bJ0pUN0G2iz%2fnzAiII1NAH2cWN6G7i2QPo7RDK22Gg5Gco8o9DCu2MYePUIKZm34dMyFmh8pYyJ8UAuguRRtkxVmWr8nX%2fj8TRVQhz%2f6RG8E3mdoqVht3ouVAVTwBrcVxxqdmFqNIXokW5FAdcrUYqR71JRcfN2kycW7CaP0giqN55q9qf6CLWh5Re1%2b5W9nhOlIiuHrEicxv6VC48V9EOUtHvZ3xnRZWTKgvU%3d&tyra=1%7c2&searchrequestid=07860e8d-6651-49e1-87d2-4085f8691a04&pingnumber=1"));
		   URLDecoder.decode("%E4%B8%8A%E6%B5%B7");
		   System.out.println(Base64.decode("http://www.agoda.com/zh-cn/country/japan.html?asq=7ylWkWYo99jpLRsOqDXpOhB2TKxNx+lAwj0NnSLkOeFItHBxaSjrlGUbpQ4g+jH0rYwQqR0+VYIZDCWgC8twwrwu+GLP5wvMw5+XZi6CgrD/6RG8E3mdoqVht3ouVAVT3ngd/xvmcF0CKNVZkcCS0yxlHNQa/ePAaQAT5CcPh6yDRIR8k2SWu2UlpzNhcer+H5EydpKTE4WqiJs+8qSjZxEg/cCzrY6gmqYg2ENuuZQ="));
		   
		   String jsStr="100*20/10";
//		   Object result=ctx.evaluateReader(scope,  new java.io.FileReader(jsPath), sourceName, lineno, securityDomain); 
//		   System.out.println("result="+result);
		   jsonParse();

	}
	
	
	public static void jsonParse() throws JSONException, ScriptException, IOException{
		org.mozilla.javascript.Context ctx = org.mozilla.javascript.Context.enter();
		org.mozilla.javascript.Scriptable jsBeautyScope = ctx.initStandardObjects();
		String script="load('env.js');window.location = 'http://www.agoda.com/zh-cn/lucky-plaza-hotel/hotel/shanghai-cn.html';";
		
		System.out.println(ctx.evaluateString(jsBeautyScope, FileUtils.readFileToString(new File("env.js")), null, 0, null).toString());
		System.out.println(ctx.evaluateString(jsBeautyScope, "window.XMLHttpRequest", null, 0, null));
		
		
		org.mozilla.javascript.tools.shell.Main main=new org.mozilla.javascript.tools.shell.Main();
		
//		ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
//		engine.eval(new FileReader("env.js"));
		
		JSONObject json = new JSONObject("{a:{b:1}}");
		System.out.println(json.getJSONObject("a").get("b"));
	}
	

}
