package test.ctrip.frame;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

import test.ctrip.frame.utils.Constant;
import test.ctrip.frame.utils.Utils;


public class FileTest {

	
	public static void runningLock(String file) {
		FileChannel channel = null;
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(file, "rw");
			channel = raf.getChannel();
			if (channel.tryLock() == null) {
				throw new RuntimeException("Another app is running..");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	 public static void stopDaemonProcess() throws IOException, InterruptedException {
//	        try {
//	            if (Utils.isOSWindows()) {
//	                String currentPid=getCurrentPid();
//	                StringBuilder killcmd=new StringBuilder("taskkill /f /fi \"pid ne ");
//	                killcmd.append(currentPid);
//	                killcmd.append("\" /FI \"IMAGENAME eq javaw.exe\"");
//	                AdslUtils.executeCommand(killcmd.toString());
//	                log.info("kill daemon success");
//	            } else {
//	                AdslUtils.executeCommand("/var/crawl/autoupdatekill.sh");
//	                Thread.sleep(1000);
//	                if (isRunningLock(Constant.ClientupdatePID)) {
//	                    AdslUtils.executeCommand("sh /var/crawl/autoupdatekill.sh");
//	                }
//	                Thread.sleep(1000);
//	                if (isRunningLock(Constant.ClientupdatePID)) {
//	                    AdslUtils.executeCommand("sudo sh /var/crawl/autoupdatekill.sh");
//	                }
//	            }
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
	    }
	
	public static void main(String[] args) {
		//不存在创建文件 锁定文件
		FileTest.runningLock("tt.pid");
		//第二次读取失败
		FileTest.runningLock("tt.pid");

	}

}
