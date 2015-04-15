package test.thread.active_object;

import test.thread.active_object.activeobject.ActiveObject;
import test.thread.active_object.activeobject.ActiveObjectFactory;

public class Main {

	public static void main(String[] args) {
		//负责把读取request，如果没组装字符串还自动组装字符串
		ActiveObject activeObject=ActiveObjectFactory.createActiveObject();
		//有返回值的（request 放进去，但没字符串还没有组装）
		new MakerClientThread("Alice",activeObject).start();
		new MakerClientThread("Bobby",activeObject).start();
		//无返回值（request 放进去）
		new DisplayClientThread("Chrise",activeObject).start();
	}
}
