package test.any;

public class Main implements Cloneable{

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
