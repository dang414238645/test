package test.ctrip.frame.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
	private static ThreadLocal<String> suffix_ = new ThreadLocal<>();

	public static String getSuffix() {
		return suffix_.get();
	}

	public static void setSuffix(String suffix) {
		suffix_.set(suffix);
	}

	private static ThreadLocal<Throwable> t_ = new ThreadLocal<>();

	public static Throwable getThrowable() {
		return t_.get();
	}

	public static void setThrowable(Throwable t) {
		t_.set(t);
	}

	private static AtomicReference<Logger> logger_ =
			new AtomicReference<>(Logger.getGlobal());

	static {
		Logger.getGlobal().setLevel(Level.INFO);
	}

	public static Logger getAndSet(Logger logger) {
		return logger_.getAndSet(logger);
	}

	private static final int CUT_DEPTH = 4;
	private static final StackTraceElement[] EMPTY_STACK_TRACE = {};

	public static Throwable concat(Throwable t) {
		Throwable atop = t_.get();
		if (atop == null) {
			return t;
		}
		// clone t
		Throwable cloned;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(t);
			ObjectInputStream ois = new ObjectInputStream(new
					ByteArrayInputStream(baos.toByteArray()));
			cloned = (Throwable) ois.readObject();
		} catch (IOException | ReflectiveOperationException e) {
			return t;
		}
		// concatenate t with atop
		ArrayList<StackTraceElement> stes = new ArrayList<>();
		StackTraceElement[] stackTrace = cloned.getStackTrace();
		for (int i = 0; i < stackTrace.length - CUT_DEPTH; i ++) {
			stes.add(stackTrace[i]);
		}
		int len;
		do {
			stackTrace = atop.getStackTrace();
			len = Math.max(1, stackTrace.length - CUT_DEPTH);
			for (int i = 1; i < len; i ++) {
				stes.add(stackTrace[i]);
			}
			// t_.getCause() is atop t_, see Executors.execute() for more details
			atop = atop.getCause();
		} while (atop != null);
		for (int i = len; i < stackTrace.length; i ++) {
			stes.add(stackTrace[i]);
		}
		cloned.setStackTrace(stes.toArray(EMPTY_STACK_TRACE));
		return cloned;
	}

	public static void log(Level l, StackTraceElement source, String s, Throwable t) {
		String suffix = suffix_.get();
		logger_.get().logp(l, source.getClassName(), source.getMethodName(),
				suffix == null ? s : s + suffix, t == null ? null : concat(t));
	}

	private static void log(Level l, String s, Throwable t) {
		log(l, new Throwable().getStackTrace()[2], s, t);
	}

	public static void v(String s) {
		log(Level.FINE, s, null);
	}

	public static void v(Throwable t) {
		log(Level.FINE, "", t);
	}

	public static void v(String s, Throwable t) {
		log(Level.FINE, s, t);
	}

	public static void d(String s) {
		log(Level.CONFIG, s, null);
	}

	public static void d(Throwable t) {
		log(Level.CONFIG, "", t);
	}

	public static void d(String s, Throwable t) {
		log(Level.CONFIG, s, t);
	}

	public static void i(String s) {
		log(Level.INFO, s, null);
	}

	public static void i(Throwable t) {
		log(Level.INFO, "", t);
	}

	public static void i(String s, Throwable t) {
		log(Level.INFO, s, t);
	}

	public static void w(String s) {
		log(Level.WARNING, s, null);
	}

	public static void w(Throwable t) {
		log(Level.WARNING, "", t);
	}

	public static void w(String s, Throwable t) {
		log(Level.WARNING, s, t);
	}

	public static void e(String s) {
		log(Level.SEVERE, s, null);
	}

	public static void e(Throwable t) {
		log(Level.SEVERE, "", t);
	}

	public static void e(String s, Throwable t) {
		log(Level.SEVERE, s, t);
	}
}