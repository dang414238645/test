package test.thread.balking.timeout;

public class TimeoutException extends InterruptedException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TimeoutException(String s) {
		super(s);
	}

}
