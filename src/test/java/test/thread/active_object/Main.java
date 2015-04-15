package test.thread.active_object;

import test.thread.active_object.activeobject.ActiveObject;
import test.thread.active_object.activeobject.ActiveObjectFactory;

public class Main {

	public static void main(String[] args) {
		ActiveObject activeObject=ActiveObjectFactory.createActiveObject();
		new MakerClientThread("Alice",activeObject).start();
		new MakerClientThread("Bobby",activeObject).start();
		new DisplayClientThread("Chrise",activeObject).start();
	}

}
