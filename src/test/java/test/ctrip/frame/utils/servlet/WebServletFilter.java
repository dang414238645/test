//package test.ctrip.frame.utils.servlet;
//
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.net.URL;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.TreeMap;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.FutureTask;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//
//import com.ctrip.fx.octopus.util.Conf;
//import com.ctrip.fx.octopus.util.Log;
//
//public class WebServletFilter implements Filter, ServletConfig {
//	private static final String REQUEST_FACADE_CLASS = "org.apache.catalina.connector.RequestFacade";
//
//	// Only available under Tomcat
//	private static Field requestField = null, contextField = null, multipartField = null;
//
//	static {
//		try {
//			Class<?> clazz = Class.forName(REQUEST_FACADE_CLASS);
//			requestField = clazz.getDeclaredField("request");
//			requestField.setAccessible(true);
//			clazz = Class.forName("org.apache.catalina.connector.Request");
//			contextField = clazz.getDeclaredField("context");
//			contextField.setAccessible(true);
//			clazz = Class.forName("org.apache.catalina.core.StandardContext");
//			multipartField = clazz.getDeclaredField("allowCasualMultipartParsing");
//			multipartField.setAccessible(true);
//		} catch (ReflectiveOperationException e) {
//			// Ignored
//		}
//	}
//
//	private FilterConfig filterConfig = null;
//	private HashSet<Class<?>> multiparts = new HashSet<>();
//	private HashMap<String, Class<?>> pathMap = new HashMap<>();
//	private TreeMap<String, Class<?>> pathMap2 = new TreeMap<>();
//	private ConcurrentHashMap<Class<?>, FutureTask<HttpServlet>> servletMap = new ConcurrentHashMap<>();
//
//	@Override
//	public String getServletName() {
//		return filterConfig.getFilterName();
//	}
//
//	@Override
//	public ServletContext getServletContext() {
//		return filterConfig.getServletContext();
//	}
//
//	@Override
//	public Enumeration<String> getInitParameterNames() {
//		return new Enumeration<String>() {
//			@Override
//			public boolean hasMoreElements() {
//				return false;
//			}
//
//			@Override
//			public String nextElement() {
//				return null;
//			}
//		};
//	}
//
//	@Override
//	public String getInitParameter(String name) {
//		return null;
//	}
//
//	@Override
//	public void init(final FilterConfig config) {
//		String packages = config.getInitParameter("packages");
//		if (packages == null) {
//			return;
//		}
//		URL url = getClass().getResource("/Startup.class");
//		if (url == null) {
//			return;
//		}
//		filterConfig = config;
//		for (String className : Conf.getClasses(packages.split(","))) {
//			Class<?> clazz;
//			try {
//				clazz = Class.forName(className);
//			} catch (Error | RuntimeException | ReflectiveOperationException e) {
//				Log.e(e);
//				continue;
//			}
//			WebServlet annot = clazz.getAnnotation(WebServlet.class);
//			if (annot == null || !HttpServlet.class.isAssignableFrom(clazz)) {
//				continue;
//			}
//			for (String path : annot.urlPatterns()) {
//				int len = path.length();
//				if (path.endsWith("/*")) {
//					pathMap2.put(path.substring(0, len - 1), clazz);
//				} else if (path.endsWith("*")) {
//					pathMap2.put(path.substring(0, len - 1) + '/', clazz);
//				} else if (path.endsWith("/")) {
//					pathMap.put(path, clazz);
//				} else {
//					pathMap.put(path + '/', clazz);
//				}
//			}
//			MultipartConfig annot2 = clazz.getAnnotation(MultipartConfig.class);
//			if (multipartField != null && annot2 != null) {
//				multiparts.add(clazz);
//			}
//		}
//	}
//
//	@Override
//	public void destroy() {
//		filterConfig = null;
//		pathMap.clear();
//		pathMap2.clear();
//		for (FutureTask<HttpServlet> task : servletMap.values()) {
//			try {
//				HttpServlet servlet = task.get();
//				if (servlet != null) {
//					servlet.destroy();
//				}
//			} catch (InterruptedException e) {
//				// Ignored
//			} catch (ExecutionException e) {
//				Log.e(e.getCause());
//			}
//		}
//		servletMap.clear();
//	}
//
//	@Override
//	public void doFilter(ServletRequest req, ServletResponse resp,
//			FilterChain chain) throws IOException, ServletException {
//		if (filterConfig == null || !(req instanceof HttpServletRequest)) {
//			chain.doFilter(req, resp);
//			return;
//		}
//		HttpServletRequest req_ = (HttpServletRequest) req;
//		String prefix = req_.getContextPath();
//		String path = req_.getServletPath();
//		if (path == null) {
//			chain.doFilter(req, resp);
//			return;
//		}
//		if (path.startsWith(prefix)) {
//			path = path.substring(prefix.length());
//		}
//		int len = path.length();
//		if (len == 0 || path.charAt(len - 1) != '/') {
//			path += '/';
//		}
//		String pathInfo = null;
//		Class<?> clazz = pathMap.get(path);
//		if (clazz == null) {
//			Map.Entry<String, Class<?>> entry = pathMap2.floorEntry(path);
//			if (entry != null) {
//				prefix = entry.getKey();
//				if (path.startsWith(prefix)) {
//					clazz = entry.getValue();
//					int begin = prefix.length();
//					if (begin < len) {
//						pathInfo = path.substring(begin, len);
//					}
//				}
//			}
//		}
//		if (clazz == null) {
//			chain.doFilter(req, resp);
//			return;
//		}
//		final Class<?> clazz_ = clazz;
//		FutureTask<HttpServlet> task = servletMap.get(clazz);
//		if (task == null) {
//			FutureTask<HttpServlet> newTask = new FutureTask<>(new Callable<HttpServlet>() {
//				@Override
//				public HttpServlet call() throws Exception {
//					HttpServlet servlet = (HttpServlet) clazz_.newInstance();
//					servlet.init(WebServletFilter.this);
//					return servlet;
//				}
//			});
//			task = servletMap.putIfAbsent(clazz, newTask);
//			if (task == null) {
//				task = newTask;
//				task.run();
//			}
//		}
//		HttpServlet servlet = null;
//		try {
//			servlet = task.get();
//		} catch (InterruptedException e) {
//			// Ignored
//		} catch (ExecutionException e) {
//			Log.e(e.getCause());
//		}
//		if (servlet == null) {
//			chain.doFilter(req, resp);
//		} else {
//			if (multiparts.contains(servlet.getClass())) {
//				try {
//					Object req__ = req;
//					if (req__.getClass().getName().equals(REQUEST_FACADE_CLASS)) {
//						req__ = requestField.get(req__);
//					}
//					multipartField.set(contextField.get(req__), Boolean.TRUE);
//				} catch (ReflectiveOperationException e) {
//					Log.e(e);
//				}
//			}
//			final String pathInfo_ = pathInfo;
//			servlet.service(new HttpServletRequestWrapper(req_) {
//				@Override
//				public String getPathInfo() {
//					return pathInfo_;
//				}
//			}, resp);
//		}
//	}
//}