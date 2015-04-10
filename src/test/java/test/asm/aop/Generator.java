package test.asm.aop;
import java.io.File; 
import java.io.FileOutputStream; 

import org.objectweb.asm.*; 
    
 class AddSecurityCheckClassAdapter extends ClassAdapter {

	    public AddSecurityCheckClassAdapter(ClassVisitor cv) {
	        //Responsechain 的下一个 ClassVisitor，这里我们将传入 ClassWriter，
	        // 负责改写后代码的输出
	        super(cv); 
	    } 
	    
	    // 重写 visitMethod，访问到 "operation" 方法时，
	    // 给出自定义 MethodVisitor，实际改写方法内容
	    public MethodVisitor visitMethod(final int access, final String name, 
	        final String desc, final String signature, final String[] exceptions) { 
	        MethodVisitor wrappedMv = cv.visitMethod(access, name, desc, signature,exceptions);; 
	            // 对于 "operation" 方法
	            if (name.equals("operation")&&wrappedMv != null) { 
	                // 使用自定义 MethodVisitor，实际改写方法内容
	                wrappedMv = new AddSecurityCheckMethodAdapter(wrappedMv); 
	            } 
	        return wrappedMv; 
	    } 
	}
 
 class AddSecurityCheckMethodAdapter extends MethodAdapter { 
	 public AddSecurityCheckMethodAdapter(MethodVisitor mv) { 
		 super(mv); 
	 } 

	 public void visitCode() { 
		 visitMethodInsn(Opcodes.INVOKESTATIC, "SecurityChecker", 
			"checkSecurity", "()V"); 
	 } 
 }
 
 
 public class Generator{ 
	 public static void main(String args[]) throws Exception { 
		 ClassReader cr = new ClassReader("test/asm/aop/Account"); 
		 ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS); 
		 ClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw); 
		 cr.accept(classAdapter, ClassReader.SKIP_DEBUG); 
		 byte[] data = cw.toByteArray(); 
		 File file = new File(System.getProperty("user.dir") + "\\target\\test-classes\\test\\asm\\aop\\Account.class"); 
		 FileOutputStream fout = new FileOutputStream(file); 
		 fout.write(data); 
		 fout.close(); 
	 } 
 }