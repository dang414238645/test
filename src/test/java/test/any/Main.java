package test.any;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class Main implements Cloneable{
	Pattern AGODA_FEE = Pattern.compile("服务费 *(\\d*\\.?\\d*)%");
	Pattern AGODA_FEE1 = Pattern.compile("[\u4e00-\u9fa5]+\\s(\\d*\\.?\\d)%|[\u4e00-\u9fa5]+\\s(\\d*\\.?\\d)");
	private int i=0;
	
	
	
	public int getI() {
		return i;
	}



	public void setI(int i) {
		this.i = i;
	}



	@Override
	protected Object clone() throws CloneNotSupportedException {
		return this.clone();
	}



	public static void main(String[] args) throws CloneNotSupportedException {
//		Pattern AGODA_FEE1 = Pattern.compile("[\u4e00-\u9fa5]+\\s(\\d*\\.?\\d)%|[\u4e00-\u9fa5]+\\s(\\d*\\.?\\d)");
		Pattern AGODA_FEE1 = Pattern.compile("\\D+(\\d*\\.?\\d*)%?");
		Pattern AGODA_FEE2 = Pattern.compile("\\D+\\s(\\d*\\.?\\d*%?)");//匹配数字和%
		Pattern AGODA_FEE4 = Pattern.compile("[^TWD][\\D]+\\s(\\d*\\.?\\d*%?)");//匹配数字和%
		Pattern AGODA_FEE3 = Pattern.compile("\\D+TWD\\s(\\d*\\.?\\d*)");//匹配台币
		String ttt="已包含在房价中: 城市税 CNY 9.37,Tax 10%,服务费 15.1%";
		String tttt="不包含在房价中: 城市税 TWD 207.79 (由酒店收取), Tax 5%, 服务费 10%";
		Matcher feePercentMatcher =AGODA_FEE3.matcher(tttt);
		while(feePercentMatcher.find()){
			System.out.println(feePercentMatcher.group(1));
		}
		BigDecimal bigd=new BigDecimal(0);
		String ar[]=tttt.split("不包含");
		for(String t:ar){
			if(!t.contains("已包含")){
				Matcher fee=AGODA_FEE2.matcher(t);
				while(fee.find()){
					String f=fee.group(1);
					if(f.contains("%")&&!StringUtils.isEmpty(f)){
						f=f.replaceAll("%", "");
						bigd=bigd.add(new BigDecimal(100*new Float(f)*0.01));
					}else if(!StringUtils.isEmpty(f)){
						bigd=bigd.add(new BigDecimal(new Float(f)));
					}
				}
			}
		}
		
//		Main main=new Main();
//		Main x=(Main) main.clone();
//		System.out.println(x.getI());   
		String str="http://www.booking.com/searchresults.zh-cn.html?dest_id=6020785;dest_type=city;highlighted_hotels=1222743&";
		str.substring(str.indexOf("dest_id="), str.indexOf(";", str.indexOf("dest_id=")));
		System.out.println(str.substring(str.indexOf("dest_id=")+8, str.indexOf(";", str.indexOf("dest_id="))));
		String searchUrl = "http://m.booking.com/searchresults.zh-cn.html?dcid=1;class_interval=1;dest_id=%s;dest_type=%s;m_occ=2;or_radius=0;property_room_info=1;review_score_group=empty;score_min=0;search_form_id=%s;src=index;ssb=empty;rows=20;";
		//% (\d+\$)?  ([-#+ 0,(\<]*)?   (\d+)?  (\.\d+)?  ([tT])?  ([a-zA-Z%])
		searchUrl = String.format(searchUrl, "000","city", "asdfasdfasdf");
		System.out.println(searchUrl);
	}

}
