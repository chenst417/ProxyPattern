package com.cn.study.proxypattern.dynamicproxy.myselfdynamicproxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class MyselfClassLoader extends ClassLoader {

	private File classPathFile;
	
	public MyselfClassLoader() {
		String classPath = MyselfClassLoader.class.getResource("").getPath();
		classPathFile = new File(classPath);
	}
	
	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		String className= MyselfClassLoader.class.getPackage().getName() + "." + name;
		if(classPathFile != null) {
			File classFile = new File(classPathFile, 
					name.replaceAll("\\.", "/") + ".class");
			if(classFile.exists()) {
				FileInputStream in = null;
				ByteArrayOutputStream out = null;
				
				try {
					in = new FileInputStream(classFile);
					out = new ByteArrayOutputStream();
					byte[] buff = new byte[1024];
					int len;
					while((len = in.read(buff))!= -1) {
						out.write(buff, 0, len);
					}
					return defineClass(className, out.toByteArray(), 0, out.size());
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return super.findClass(name);
	}
	
}
