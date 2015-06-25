package test.ctrip.frame.httpclient;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author t_huang
 * 
 */
public class NetworkUtils {

	private static final Random random = new Random(188);

    private static List<String> headers = new ArrayList<String>(16);
    private static Random agtRandom = new Random();
    private static String header = "";

    //private static void init() {
    static{
        headers.add("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
        headers.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0");
        headers.add("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
        headers.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:21.0) Gecko/20100101 Firefox/22.0");
        headers.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.72 Safari/537.36");
        headers.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36");
        headers.add("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:22.0) Gecko/20100101 Firefox/21.0");
        headers.add("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:22.0) Gecko/20100101 Firefox/22.0");

        headers.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; SLCC1)");                                                               // IE7(Vista)
        headers.add("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)");                                                         // IE8(Win7)
        headers.add("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; WOW64; Trident/4.0)");                                                  // IE8(Win2k3 x64)
        headers.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");                                                         // IE9(Win7)
        headers.add("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0)");                                                 // IE10(Win8)
        headers.add("Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko");                                                    // IE11(Win8.1)
        headers.add("Mozilla/5.0 (Windows NT 6.3; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");                                                // Firefox27
        headers.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.2.7) Gecko/20100625 Firefox/3.6.7");                                // Firefox3.6
        headers.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:24.0) Gecko/20100101 Firefox/24.0");                                       // Firefox(Mac)
        headers.add("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.58 Safari/537.36");            // Chrome
        headers.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9) AppleWebKit/537.71 (KHTML, like Gecko) Version/7.0 Safari/537.71");           // Safari7(Mac)
        headers.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/533.21.1 (KHTML, like Gecko) Version/5.0.5 Safari/533.21.1");  // Safari7(Windows)
        resetHeader();
    }

	/**
	 * 
	 * @return
	 */
	public static String generateRandomIp() {
		int item1 = random.nextInt(220);
		if (item1 == 10 || item1 == 172 || item1 == 192 || item1 == 127) {
			item1++;
		}
		int item2 = random.nextInt(252);
		int item3 = random.nextInt(253);
		int item4 = random.nextInt(254);
		return item1 + "." + item2 + "." + item3 + "." + item4;
	}

	/**
	 * 
	 * @return
	 */
	public static String getRandomUserAgent() {
		return header;
	}
	/**
	 * generate Explorer version prop。
	 * @param level
	 * @return
	 */
	public static String getNewerUserAgentrandomly() {
		return header;
	}
	
	public static void resetHeader(){
		header = getUserAgentwithLevelrandomly(4);
	}

	private static String getUserAgentwithLevelrandomly(int level) {
		/*if (0==headers.size()) {
            init();
        }*/
		level=level>headers.size()?headers.size():level;
		return headers.get(level+agtRandom.nextInt(headers.size()-level - 1));
	}
}
