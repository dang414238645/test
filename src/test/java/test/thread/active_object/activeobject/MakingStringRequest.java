package test.thread.active_object.activeobject;

public class MakingStringRequest extends MethodRequest{
	
	private final int count;
	
	private final char fillchar;

	public MakingStringRequest(Servant servant, FutureResult future, int count,
			char fillchar) {
		super(servant, future);
		this.count=count;
		this.fillchar=fillchar;
	}

	@Override
	public void execute() {
		Result result=this.servant.makeString(count,fillchar);
		future.setResult(result);
	}

}
