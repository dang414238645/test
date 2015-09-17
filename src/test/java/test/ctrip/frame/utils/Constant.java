package test.ctrip.frame.utils;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * Created with IntelliJ IDEA.
 * User: yf_liu
 * Date: 14-1-21
 * Time: 下午2:19
 */
public class Constant {
    /* Common */
    public static final String FileEncodingKey = "file.encoding";
    public static final String FileEncodingValue = "UTF-8";

    public static final String GetWorkingDirectory() {
    	return "/var/crawl/";
    }

    /* Config files */
    public static final int ConfReloadInterval = 10; // 10s
    public static final String ClientProperty = GetWorkingDirectory() + "client.properties";
    public static final String Logback_clientXML = GetWorkingDirectory() + "logback_client.xml";
    public static final String LogHome = GetWorkingDirectory() + "log";

    /* Process id */
    public static final String BotexecutorPID = GetWorkingDirectory() + "pid";
    public static final String ClientupdatePID = GetWorkingDirectory() + "daemonpid";

    /* Process Jar */
    public static final String ClientJarName = "botexecutor";
    public static final String ClientJarAbsPath = GetWorkingDirectory() + ClientJarName;
    public static final String DaemonJarName = "clientupdate";
    public static final String DaemonJarAbsPath = GetWorkingDirectory() + DaemonJarName;

    /* Update Client status */
    public static final int DefaultUpdateTimeClip = 3;

    /* Upgrading 0/2 - Upgrade */
    public static final int UpgradeHeartBeatTime = 30;
    public static final int UpgradeRandomSleep = 60;

    /* File lock */
    public static final String FILE_LOCK_DIRECTORY = Utils.getConfigPath() + "ServiceApp" + File.separator + "GlobalADSLControl";
    public static final String DOWNLOADING_CLINET_FILE_LOCK = FILE_LOCK_DIRECTORY + File.separator + "ClientDownloading";
    public static final String DOWNLOADING_DAEMON_FILE_LOCK = FILE_LOCK_DIRECTORY + File.separator + "DaemonDownloading";
    public static final String REDIAL_FILE_LOCK = FILE_LOCK_DIRECTORY + File.separator + "ADSLRECONNECTING";
    public static final String UPDATING_CONFIG_LOCK = FILE_LOCK_DIRECTORY + File.separator + "ConfigUpdating";

    /* Executor */
    public static final int DEFAULT_CONNECTION_TIMEOUT = 10;
    public static final String DEFAULT_URL_ENCODING = "UTF-8";

    /* Central Control */
    public final static int RUNNING_INTERVAL = 100;
    public final static int WAIT_SLEEP_SECOND = 5;

    /* Redial */
    public final static long Default_Redial_Max_Interval = 20 * 1000; // 设定 20s 内重拨必须结束，否则判定此次重拨无效
    public final static int Default_SYSRedial_Interval = 120;        // 系统默认重拨时间间隔，60s
    public final static long Default_Redial_Interval = 1000 * 20;   // 默认重拨间隔
    public final static int Preredial_isOKToRedial_Sleep = 100;     // 重拨前判断是否可以重拨，每次检测时间间隔
    public final static int PreRedial_isOKToRedial_Cnt = 80;        // 重拨前判断是否可以重拨，检测次数。如果到达检测次数仍然不满足重拨要求，会强制重拨. 100 * 80 = 8000 = 8s
    public final static int Preredial_Get_Lock_Sleep = 500;         // 重拨前上文件锁重试时间间隔

    public final static int Default_waiting_sleep = 1000;            // 业务爬虫用来判读是否在重拨；如果在重拨，等待1秒。直到重拨结束，才会继续发送 http 请求（需要使用 Executor)
    public final static int Redial_connection_detect_sleep = 500;   // 判断网络是否已经中断或可用的检测时间间隔
    public final static int Redial_pppoe_cnt = 16;                  // pppoe 重拨模式，最多等待时间。 500 × 16 = 8000 = 8s
    public final static int Redial_pppoe_retry = 1;                 // pppoe 尝试次数，1次
    public final static int Redial_networkService_cnt = 600;        // 重启网卡 重拨模式，最多等待时间。 500 × 600 = 30000 = 300s = 5min
    public final static int Redial_networkService_retry = 1;        // 重启网卡 尝试次数，1次

    /* Statistics */
    public static final int STATISTICS_LOG_INTERVAL = 60000;

    /* URL */
    public static final String dispatchPath = "/task/dispatch";
    
    public static final Map<String, List<String>> BOT_HEADER ;
    
    public static final boolean isADSL;
    
    private static String clientCode;
    
    static {
    	BOT_HEADER = new HashMap<>();
        String agent=Conf.load("Agent").getProperty("agent");
        if(agent!=null&&agent.startsWith("OctopusDaemon")) {
            agent = agent + "/" +
                    Conf.load("Version").getProperty("version");
        }
        else{
//            Map<String,String> buVerMap=Conf.getVersion();
//            buVerMap.put("client",Conf.load("Version").getProperty("version"));
//            String clientBuVer= JsonUtil.toString(buVerMap);
//            agent=agent+"/"+clientBuVer;
        }
		Properties p = Conf.load("Auth");
		String auth = "Basic " + Base64.encode((p.getProperty("userid") + ":" +
				p.getProperty("password")).getBytes());
		BOT_HEADER.put("Client-Code", Collections.singletonList(p.getProperty("userid")));
		BOT_HEADER.put("Authorization", Collections.singletonList(auth));
		BOT_HEADER.put("User-Agent", Collections.singletonList(agent));
		isADSL = (p.getProperty("userid") != null && p.getProperty("userid").contains("ADSL"));
		clientCode = p.getProperty("userid");
    }
    
    public static String getClientCode() {
    	return clientCode;
    }
}