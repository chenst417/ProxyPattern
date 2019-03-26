package com.cn.study.proxypattern.dynamicproxy.myselfdynamicproxy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class MyselfProxy implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private final static String LN = "\r\n";

	public static Object newProxyInstance(MyselfClassLoader loader,
										  Class<?>[] interfaces,
										  MyselfInvocationHandler h) {

		//1.动态生成源代码.java 文件
		String src = generateSrc(interfaces);

		//2.Java文件输出到磁盘
		String filePath = MyselfProxy.class.getResource("").getPath();
		File file = null;
		try {
			file = new File(filePath + "$Proxy0.java");
			FileWriter fw = new FileWriter(file);
			fw.write(src);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JavaCompiler compier = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager sjf = compier.getStandardFileManager(null, null, null);
		Iterable<? extends JavaFileObject> iterable = sjf.getJavaFileObjects(file);

		CompilationTask task = compier.getTask(null, sjf, null, null, null, iterable);
		task.call();
		try {
			sjf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			//4、编译生成的.class文件加载到JVM中来
			Class<?> findClass = loader.findClass("$Proxy0");

			Constructor c = findClass.getConstructor(MyselfInvocationHandler.class);
			file.delete();

			//5、返回字节码重组以后的新的代理对象
			return c.newInstance(h);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String generateSrc(Class<?>[] interfaces) {
		StringBuffer sb = new StringBuffer();

		sb.append("package com.cn.study.proxypattern.dynamicproxy.myselfdynamicproxy;" + LN);
		sb.append("import java.lang.reflect.*;" + LN);
		for (int i = 0; i < interfaces.length; i++) {
			sb.append("import " + interfaces[i].getName() + ";" + LN);
		}
		sb.append("public class $Proxy0 implements "  + interfaceInfo(interfaces) + " {" + LN);

		sb.append("private MyselfInvocationHandler h;" + LN);

		sb.append("public $Proxy0 (MyselfInvocationHandler h) {" + LN);
		sb.append("this.h = h;").append(LN);
		sb.append("}" + LN);

		for (Method method : interfaces[0].getMethods()) {
			Class<?>[] params = method.getParameterTypes();

			StringBuffer paramNames = new StringBuffer();
			StringBuffer paramVaules = new StringBuffer();
			StringBuffer paramClasses = new StringBuffer();

			for (int i = 0; i < params.length; i++) {
				Class clazz = params[i];
				String type = clazz.getSimpleName();

				String paramName = toLowerFirstCase(clazz.getSimpleName());
				if(paramName.equals("int")) {
					paramName = "a";
				}
				if(i > 0 && i< params.length) {
					paramNames.append(", " + type + " " + paramName);
					paramVaules.append("," + paramName);
					paramClasses.append("," + clazz.getSimpleName() + ".class");
				} else {
					paramNames.append(type + " " + paramName);
					paramVaules.append(paramName);
					paramClasses.append(clazz.getSimpleName() + ".class");
				}
			}

			sb.append("public " + method.getReturnType().getSimpleName() + " " + method.getName() + " ( ");
			//参数
			sb.append(paramNames.toString());
			sb.append("){").append(LN);

			sb.append("try { " + LN);

			sb.append("Method m = " + interfaces[0].getSimpleName() +
					".class.getMethod(\"" + method.getName() + "\", new Class[] {" +
					paramClasses.toString() + "});" + LN);

			sb.append("this.h.invoke(this,m,new Object[]{ " + paramVaules + "});" + LN);

			sb.append("} catch(Throwable able){" + LN);
			sb.append("throw new UndeclaredThrowableException(able);" + LN);
			sb.append("} " + LN);

			String returnType = method.getReturnType().getSimpleName();
			if(returnType.equals("void")) {
				sb.append(LN);
			}else if(returnType.equals("int") || returnType.equals("Integer")) {
				sb.append("return 0;" + LN);
			}else if(returnType.equals("String")) {
				sb.append("return null;" + LN);
			}
			sb.append("}").append(LN);
		}
		sb.append("}");
		return sb.toString();
	}

	private static String interfaceInfo(Class<?>[] interfaces) {
		StringBuffer sb = new StringBuffer();
		if(interfaces.length == 1) {
			sb.append(interfaces[0].getSimpleName());
			return sb.toString();
		}
		for (int i = 0; i < interfaces.length; i++) {
			if(i != interfaces.length - 1) {
				sb.append(interfaces[i].getSimpleName() + ",");
			} else {
				sb.append(interfaces[i].getSimpleName());
			}
		}
		return sb.toString();
	}

	private static String toLowerFirstCase(String src){
		if(Character.isLowerCase(src.charAt(0)))
			return src;
		else
			return (new StringBuilder()).
					append(Character.toLowerCase(src.charAt(0))).
					append(src.substring(1)).toString();
	}

}
