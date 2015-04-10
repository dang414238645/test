package com.dang.test.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.springframework.asm.Type;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

public class Util {
	private static AntPathMatcher antPathMatcher = new AntPathMatcher();

	public static void doRetrieveMatchingFiles(String fullPattern, File dir,
			ArrayList<File> result) throws IOException {
		File[] dirContents = dir.listFiles();
		if (dirContents == null) {
			return;
		}
		for (File content : dirContents) {
			String currPath = StringUtils.replace(content.getAbsolutePath(),
					File.separator, "/");
			if (content.isDirectory()
					&& antPathMatcher.matchStart(fullPattern, currPath + "/")) {
				if (!content.canRead()) {
				} else {
					doRetrieveMatchingFiles(fullPattern, content, result);
				}
			}
			if (antPathMatcher.match(fullPattern, currPath)) {
				result.add(content);
			}
		}
	}
	
	
	
	static byte[] getClassData(InputStream ins) { 
        try { 
            ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
            int bufferSize = 4096; 
            byte[] buffer = new byte[bufferSize]; 
            int bytesNumRead = 0; 
            while ((bytesNumRead = ins.read(buffer)) != -1) { 
                baos.write(buffer, 0, bytesNumRead); 
            } 
            return baos.toByteArray(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
        return null; 
    } 

	
	static class MYClassVisitor implements ClassVisitor{
		public ArrayList<String> ans=new ArrayList<String>();;

		public void visit(int version, int access, String name,
				String signature, String superName, String[] interfaces) {
			// TODO Auto-generated method stub
			
		}

		public void visitSource(String source, String debug) {
			// TODO Auto-generated method stub
			
		}

		public void visitOuterClass(String owner, String name, String desc) {
			// TODO Auto-generated method stub
			
		}

		public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
			ans.add(Type.getType(desc).getClassName());
			return null;
		}

		public void visitAttribute(Attribute attr) {
			// TODO Auto-generated method stub
			
		}

		public void visitInnerClass(String name, String outerName,
				String innerName, int access) {
			// TODO Auto-generated method stub
			
		}

		public FieldVisitor visitField(int access, String name, String desc,
				String signature, Object value) {
			// TODO Auto-generated method stub
			return null;
		}

		public MethodVisitor visitMethod(int access, String name, String desc,
				String signature, String[] exceptions) {
			// TODO Auto-generated method stub
			return null;
		}

		public void visitEnd() {
			// TODO Auto-generated method stub
			
		}}
	
	
	public static void main(String args[]){
		antPathMatcher.match("com/dang/test/**/*.class", "com/dang/test/ab/b.class");
		try {
			Enumeration<URL> resourceUrls = Thread.currentThread().getContextClassLoader().getResources("com/dang/test/");
			
			ArrayList<File> fs=new ArrayList<File>();
			while (resourceUrls.hasMoreElements()) {
				URL url = resourceUrls.nextElement();
				
				File file=new File(url.getFile());
				if(file.isDirectory()){
					String currPath = StringUtils.replace(file.getAbsolutePath()+"/**/*.class",File.separator, "/");
					doRetrieveMatchingFiles(currPath,file,fs);
				}
			}
			
			for(File file:fs){
				ClassReader classReader = new ClassReader(new FileInputStream(file));
				MYClassVisitor visitor= new MYClassVisitor();
				classReader.accept( visitor, ClassReader.SKIP_DEBUG);
				System.out.println(visitor.ans);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
