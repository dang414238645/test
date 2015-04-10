package test.asm.modifymethod;

public class C {
	/**
	 * 
	 *
	public class C {
    public static long timer;
    public void m() throws InterruptedException{
        timer -= System.currentTimeMillis();
        Thread.sleep(100); 
        timer += System.currentTimeMillis();
    }
	}
	 * 
	 * @throws InterruptedException
	 */
    public void m() throws InterruptedException{
        Thread.sleep(100); 
    }
}