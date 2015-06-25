package test.ctrip.frame.httpclient;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: yf_liu
 * Date: 14-2-19
 * Time: 下午6:50
 */
public class ExecutorUtils {
    public static byte[] readStream(InputStream is, int buflen) throws IOException {
        byte[] rt = null;
        byte[] buf = new byte[buflen];
        int readcnt = is.read(buf);
        while (readcnt > 0) {
            if (rt == null) {
                rt = new byte[readcnt];
                System.arraycopy(buf, 0, rt, 0, readcnt);
            } else {
                byte[] tmp = new byte[rt.length + readcnt];
                System.arraycopy(rt, 0, tmp, 0, rt.length);
                System.arraycopy(buf, 0, tmp, rt.length, readcnt);
                rt = tmp;
            }
            readcnt = is.read(buf);
        }
        return rt;
    }

    public static void main(String[] args) {
//        String str = Utils.readFile("D:\\cont");
//        byte[] bt = str.getBytes();
//        byte[] gzipBt = gZip(bt);
//        byte[] unzipBt = unGZip(gzipBt);
//        String cont = new String(unzipBt);
//
//        System.out.println(cont);
    }

    public static byte[] gZip(byte[] data) {
        byte[] b = null;

        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            byte[] buf = new byte[1024];
            int num = - 1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            GZIPOutputStream gzipos = new GZIPOutputStream(baos);
            while ((num = bis.read(buf, 0, buf.length)) != - 1) {
                gzipos.write(buf, 0, num);
            }
            gzipos.finish();
            gzipos.flush();
            baos.flush();
            b = baos.toByteArray();

            gzipos.close();
            baos.close();
            bis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }

    public static byte[] unGZip(byte[] data) {
        byte[] b = null;

        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            GZIPInputStream gzip = new GZIPInputStream(bis);
            byte[] buf = new byte[1024];
            int num = - 1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((num = gzip.read(buf, 0, buf.length)) != - 1) {
                baos.write(buf, 0, num);
            }
            b = baos.toByteArray();
            baos.flush();
            baos.close();
            gzip.close();
            bis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }
}
