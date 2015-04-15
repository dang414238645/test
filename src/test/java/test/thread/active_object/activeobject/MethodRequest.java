package test.thread.active_object.activeobject;

public abstract class MethodRequest {
	protected final Servant servant;
	protected final FutureResult future;
	public MethodRequest(Servant servant, FutureResult future) {
		super();
		this.servant = servant;
		this.future = future;
	}
	public abstract void execute();
}
