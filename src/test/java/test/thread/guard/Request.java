package test.thread.guard;

public class Request {
	private final String name;

	public Request(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "[ Request : name="+name+"]";
	}
	
}
