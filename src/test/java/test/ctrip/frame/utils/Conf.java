package test.ctrip.frame.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Conf {
	public static boolean DEBUG = false;

	private static String confDir, logDir;

	static {
		for (String argument : ManagementFactory.
				getRuntimeMXBean().getInputArguments()) {
			if (argument.startsWith("-agentlib:jdwp")) {
				DEBUG = true;
				break;
			}
		}

		Properties p = new Properties();
		try (InputStream in = Conf.class.getResourceAsStream("/Conf.properties")) {
			p.load(in);
		} catch (IOException e) {
			Log.w(e.getMessage());
		}
		confDir = p.getProperty("conf_dir");
		logDir = p.getProperty("log_dir");
	}

	public static Properties load(String name) {
		Properties p = new Properties();
		// Warning: "getResourceAsStream" may return null !
		InputStream in_ = Conf.class.getResourceAsStream("/" + name + ".properties");
		if (in_ == null) {
			Log.w("Resource /" + name + ".properties Not Found");
		} else {
			try {
				p.load(in_);
			} catch (IOException e) {
				Log.w(e.getMessage());
			}
		}
		if (confDir != null) {
			try (FileInputStream fin = new FileInputStream(confDir + name + ".properties")) {
				p.load(fin);
			} catch (IOException e) {
				Log.w(e.getMessage());
			}
		}
		return p;
	}

	public static void store(String name, Properties p) {
		try (FileOutputStream out = new FileOutputStream(confDir + name + ".properties")) {
			p.store(out, null);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String locate(String path) {
		try {
			String root = new File(Conf.class.getResource("/").toURI()).getPath();
			return new File(root + File.separator + ".." +
					File.separator + path).getCanonicalPath();
		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	public static Logger openLogger(String name, int limit, int count) {
		Logger logger = Logger.getAnonymousLogger();
		logger.setLevel(Level.INFO);
		logger.setUseParentHandlers(false);
		if (DEBUG) {
			ConsoleHandler handler = new ConsoleHandler();
			handler.setLevel(Level.INFO);
			logger.addHandler(handler);
		}
		FileHandler handler;
		try {
			String pattern = logDir == null ? locate("logs/" +
					name + "%g.log") : logDir + name + "%g.log";
			handler = new FileHandler(pattern, limit, count, true);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		handler.setFormatter(new SimpleFormatter());
		handler.setLevel(Level.INFO);
		logger.addHandler(handler);
		return logger;
	}

	public static void closeLogger(Logger logger) {
		for (Handler handler : logger.getHandlers()) {
			logger.removeHandler(handler);
			handler.close();
		}
	}

	private static void search(ArrayList<String> classes, String root, String path) {
		File folder = new File(root + path);
		String[] list = folder.list();
		if (list == null) {
			return;
		}
		for (String file : folder.list()) {
			if (new File(root + path + "/" + file).isDirectory()) {
				search(classes, root, path + "/" + file);
			} else if (file.endsWith(".class")) {
				classes.add(path.replace('/', '.') + "." +
						file.substring(0, file.length() - 6));
			}
		}
	}

	public static ArrayList<String> getClasses(String... packageNames) {
		ArrayList<String> classes = new ArrayList<>();
		for (String packageName : packageNames) {
			String packagePath = packageName.replace('.', '/');
			URL url = Conf.class.getResource("/" + packagePath);
			if (url == null) {
				return classes;
			}
			if (url.getProtocol().equals("jar")) {
				String path = url.getPath();
				int i = path.lastIndexOf('!');
				if (i >= 0) {
					path = path.substring(0, i);
				}
				try (JarFile jar = new JarFile(new File(new URL(path).toURI()))) {
					Enumeration<JarEntry> e = jar.entries();
					while (e.hasMoreElements()) {
						path = e.nextElement().getName();
						if (path.startsWith(packagePath) && path.endsWith(".class")) {
							classes.add(path.substring(0, path.length() - 6).replace('/', '.'));
						}
					}
				} catch (IOException | URISyntaxException e) {
					throw new RuntimeException(e);
				}
			} else {
				try {
					String root = new File(url.toURI()).getPath();
					root = root.substring(0, root.length() - packagePath.length());
					search(classes, root, packagePath);
				} catch (URISyntaxException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return classes;
	}
}