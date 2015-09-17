package test.ctrip.frame.httpclient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import test.ctrip.frame.utils.ExecutorUtils;



public class HttpClientTest {
	
	/*
	 * 
	 * 
	 */
	
	@Test
	public void testAjax() throws ClientProtocolException, IOException{
		
		HttpGet httpGet = new HttpGet("http://www.agoda.com/zh-cn/wuzhen-cape-no-77-inn/hotel/wuzhen-cn.html");
		httpGet.setHeader("User-Agent",NetworkUtils.getNewerUserAgentrandomly());
		httpGet.setHeader("X-Forward-For", NetworkUtils.generateRandomIp());
		httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpGet.setHeader("Host", "www.agoda.com");
		httpGet.setHeader("Accept-Charset", "UTF-8");
		httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
		HttpClient httpClient= HttpClients.createDefault();
		//代理服务器开启后，设置代理
//		HttpHost proxy = new HttpHost("127.0.0.1", 8888);
//		RequestConfig config = RequestConfig.custom().setProxy(proxy).build();  
//		httpGet.setConfig(config);
		
		
//		httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
		
		for(int i=0;i<10000;i++){
			HttpResponse response=httpClient.execute(httpGet);
			HttpEntity entity=response.getEntity();
			InputStream inputStream=entity.getContent();
			String content=inputStream2String(inputStream);
			if(content.indexOf("__VIEWSTATE")==-1){
				System.out.print(content);
			}
//			System.out.print(content);
		}
		
//		byte[] b=new byte[16];
//		while(inputStream.read(b)!=-1){
//			String str=new String(b);
//			System.out.print(str);
//		}
	}
	
	
	
	
	@Test
	public void testPage() throws ClientProtocolException, IOException, JSONException{
		
		HttpGet httpGet = new HttpGet("http://ihotel.elong.com/detail-kualalumpur_andvicinity_-180008/100249/");
		httpGet.setHeader("User-Agent",NetworkUtils.getNewerUserAgentrandomly());
		HttpClient httpClient= HttpClients.createDefault();
		HttpResponse response=httpClient.execute(httpGet);
		HttpEntity entity=response.getEntity();
		InputStream inputStream=entity.getContent();
		
		byte[] respContent = ExecutorUtils.readStream(inputStream, 2048);
		String cont = new String(respContent);
		Document doc=Jsoup.parse(cont);
		String title=doc.select("title").text();
		String jsonData = getJsJsonData(cont);
		JSONObject obj = new JSONObject(jsonData);
		int hotelEANId = obj.getJSONObject("AjaxHotelInfo").getInt("hotelEANId");
		
		String phone = doc.select("span.hotelphone").text();
        String cityName = doc.select(".breadcrumb a:nth-last-child(1)").text();
        String desc=doc.select("#hotelSummaryMore").text();
        String zone=doc.select(".area").text();
        
        Elements contents=doc.select(".contentUl li");
        
        for (Element ele:contents) {
			String spantitle=ele.select(".s1").text();
			if (StringUtils.isNotBlank(spantitle)) {
				spantitle=spantitle.trim();
				for (Element spans:ele.select("span")) {
					List<String> list=new ArrayList();
					list.add(spantitle);
					list.add(spantitle);
					list.add(StringUtils.trimToEmpty(spans.text()));
					list.add(StringUtils.trimToEmpty(spans.text()));
					list.add("true");
				}
			}
			
		}
		
		
		
		
		
		
		
		
	}
	
	

	
	@Test
	public void randomTest(){
		Random random=new Random();
		System.out.println(random.nextInt(1000));
	}
	
	
	
	
	
	
	
	
	
	private String getJsJsonData(String cont) {
		String jsonData = "";
		if(cont.contains("DetailController")) {
			int index = cont.indexOf("DetailController");
			int start = cont.indexOf("{", index);
			int end = cont.indexOf("</script>", index);
			jsonData = cont.substring(start, end);
			jsonData = jsonData.replaceAll("\\: *function.+}",":\"\"");
		}
		return jsonData;
	}
	
	@Test
	public void Test(){
		String str="{GetNewCommentByPage:function(PageSize,PageNum,hotelid,callback,enabledCache,httpmethod,isPipeFrist,timeOut){elongAjax.callBack(\"/isajax/Detail/GetNewCommentByPage?regionid=9517&cityen=PetalingJaya&HotelId=100249&viewpath=~/views/channel/Detail.aspx&\",{PageSize:PageSize,PageNum:PageNum,hotelid:hotelid},callback,enabledCache,httpmethod,isPipeFrist,null,timeOut);},GetSupplierRoomPrice:function(hotelid,supplierIdList,SimpleSupplierList,Search_id,callback,enabledCache,httpmethod,isPipeFrist,timeOut){elongAjax.callBack(\"/isajax/Detail/GetSupplierRoomPrice?regionid=9517&cityen=PetalingJaya&HotelId=100249&viewpath=~/views/channel/Detail.aspx&\",{hotelid:hotelid,supplierIdList:supplierIdList,SimpleSupplierList:SimpleSupplierList,Search_id:Search_id},callback,enabledCache,httpmethod,isPipeFrist,null,timeOut);},GetHotelReview:function(hotelid,pagesize,pageno,callback,enabledCache,httpmethod,isPipeFrist,timeOut){elongAjax.callBack(\"/isajax/Detail/GetHotelReview?regionid=9517&cityen=PetalingJaya&HotelId=100249&viewpath=~/views/channel/Detail.aspx&\",{hotelid:hotelid,pagesize:pagesize,pageno:pageno},callback,enabledCache,httpmethod,isPipeFrist,null,timeOut);},GetHotelNewReview:function(hotelid,pagesize,pageno,callback,enabledCache,httpmethod,isPipeFrist,timeOut){elongAjax.callBack(\"/isajax/Detail/GetHotelNewReview?regionid=9517&cityen=PetalingJaya&HotelId=100249&viewpath=~/views/channel/Detail.aspx&\",{hotelid:hotelid,pagesize:pagesize,pageno:pageno},callback,enabledCache,httpmethod,isPipeFrist,null,timeOut);},getTripadvisor:function(hotelId,callback,enabledCache,httpmethod,isPipeFrist,timeOut){elongAjax.callBack(\"/isajax/Detail/getTripadvisor?regionid=9517&cityen=PetalingJaya&HotelId=100249&viewpath=~/views/channel/Detail.aspx&\",{hotelId:hotelId},callback,enabledCache,httpmethod,isPipeFrist,null,timeOut);},GetHotelRoomInfo:function(){},getSupplierRoomList:function(){},SRoomList:vdata,staticUrl:\"http://www.elongstatic.com/ihotelStatic\",Search:function(param,callback,enabledCache,httpmethod,isPipeFrist,timeOut){elongAjax.callBack(\"/isajax/Detail/Search?regionid=9517&cityen=PetalingJaya&HotelId=100249&viewpath=~/views/channel/Detail.aspx&\",param,callback,enabledCache,httpmethod,isPipeFrist,null,timeOut);}}";
		String jsonData = str.replaceAll("\\: *function.+}",":\"\"");
	}
	
	
	
	
	public static String inputStream2String(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}
	
	public static InputStream String2InputStream(String content) throws UnsupportedEncodingException{
		InputStream in_nocode = new ByteArrayInputStream(content.getBytes());   
		InputStream in_withcode = new ByteArrayInputStream(content.getBytes("UTF-8"));  
		return in_nocode;
	}
	
	
	
}
