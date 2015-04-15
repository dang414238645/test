package test.thread.active_object.activeobject;

public class FutureResult implements Result{
	
	private Result result;
	private boolean ready=false;
	public synchronized void setResult(Result result){
		this.result=result;
		this.ready=true;
		notifyAll();
	}
	
	
	@Override
	public synchronized Object getResultValue() {
		while(!ready){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result.getResultValue();
	}

}
