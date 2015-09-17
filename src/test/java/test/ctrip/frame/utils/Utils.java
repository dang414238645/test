package test.ctrip.frame.utils;


import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA. User: yf_liu Date: 13-6-20 Time: 下午4:39
 */
public class Utils {
	public static BigDecimal cleanBigDecimal(BigDecimal decimal) {
		DecimalFormat df = new DecimalFormat("#.00");
		BigDecimal bd = new BigDecimal(df.format(decimal));
		return bd;
	}

	public static String today() {
		return new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance()
				.getTime());
	}

	public static long parseDate(String date) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime();
	}

	public static String tomorrow() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	public static String daysLater(int days) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, days);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	public static void setLogbackConfigFile(String loghome,
			String configFileName) throws JoranException {
		if (loghome.endsWith(File.separator)) {
			loghome = loghome.substring(0, loghome.length() - 1);
		}
		System.getProperties().put("LOG_HOME", loghome);
		LoggerContext loggerContext = (LoggerContext) LoggerFactory
				.getILoggerFactory();
		JoranConfigurator configurator = new JoranConfigurator();
		configurator.setContext(loggerContext);
		loggerContext.reset();
		configurator.doConfigure(configFileName);
	}

	public static String getCurrentPath() {
		File file = new File("");
		String path = file.getAbsolutePath();
		if (!path.endsWith(File.separator)) {
			path += File.separator;
		}
		return path;
	}

	public static boolean isOSWindows() {
		return System.getProperty("os.name").toLowerCase()
				.startsWith("windows");
	}

	public static void writePID() {
		String name = java.lang.management.ManagementFactory.getRuntimeMXBean()
				.getName();
		String pid = name.split("@")[0];
		writeFile(pid, Constant.BotexecutorPID);
	}

	public static void runningLock(String file) {
		FileChannel channel = null;
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(file, "rw");
			channel = raf.getChannel();
			if (channel.tryLock() == null) {
				throw new RuntimeException("Another app is running..");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public static void writeDaemonPID() {
		String name = java.lang.management.ManagementFactory.getRuntimeMXBean()
				.getName();
		String pid = name.split("@")[0];
		writeFile(pid, Constant.ClientupdatePID);
	}

	public static String getConfigPath() {
		return Constant.GetWorkingDirectory();
	}

	public static String readFile(String path) {
		if (null == path || path.length() == 0) {
			return null;
		}
		StringBuffer cont = new StringBuffer();
		File file = new File(path);
		if (!file.exists()) {
			return null;
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = br.readLine()) != null) {
				cont.append(line);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cont.toString();
	}

	public static List<String> readFileByLine(String path) {
		if (null == path || path.length() == 0) {
			return null;
		}
		List<String> cont = new LinkedList<String>();
		File file = new File(path);
		if (!file.exists()) {
			return null;
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = br.readLine()) != null) {
				cont.add(line);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cont;
	}

	public static void writeFile(String cont, String path) {
		File file = new File(path);
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(file));
			br.write(cont);
			br.flush();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeFileBinary(byte[] cont, String path) {
		File file = new File(path);
		try {
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
			BufferedOutputStream br = new BufferedOutputStream(
					new FileOutputStream(file));
			br.write(cont);
			br.flush();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * needn't call explicitly
	 */
	@Deprecated
	public static void loadConfig() {
		// try {
		// Config.get().load();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	public static String getHost(String tourUrl) {
		String host = "";
		if (tourUrl != null && tourUrl.trim().length() > 0) {
			try {
				URL url = new URL(tourUrl);
				host = url.getHost();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return host;
	}

	public static void setLogger() {
		String encodingKey = Constant.FileEncodingKey;
		System.setProperty(encodingKey, Constant.FileEncodingValue);

		try {
			Utils.setLogbackConfigFile(Constant.LogHome,
					Constant.Logback_clientXML);
		} catch (JoranException e) {
			System.out.println("config log error. sign");
		}

	}

	public static void Copy(File oldfile, String newPath) {
		try {
			int byteread = 0;
			if (oldfile.exists()) {
				InputStream inStream = new FileInputStream(oldfile);
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
				fs.flush();
				fs.close();
				inStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Copy(InputStream inStream, String newPath) {
		try {
			int byteread = 0;
			FileOutputStream fs = new FileOutputStream(newPath);
			byte[] buffer = new byte[1444];
			while ((byteread = inStream.read(buffer)) != -1) {
				fs.write(buffer, 0, byteread);
			}
			fs.flush();
			fs.close();
		} catch (Exception e) {
			System.out.println("error  ");
			e.printStackTrace();
		} finally {
			try {
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static Properties readProperties(String filePath) {
		Properties props = new Properties();
		try (InputStream in = new BufferedInputStream(new FileInputStream(
				filePath))) {
			props.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return props;
	}
}