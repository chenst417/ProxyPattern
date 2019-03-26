package com.cn.study.proxypattern.dbroute.db;

public class DynamicDataSourceEntity {
	
	public final static String DEFAULE_SOURCE = null;
	
	private final static ThreadLocal<String> dataSource = 
			new ThreadLocal<String>();
	
	private DynamicDataSourceEntity() {
		
	}
	
	public static String get() {
		return dataSource.get();
	}
	
	public static void set(String source) {
		dataSource.set(source);
	}
	
	public static void set(int year) {
		dataSource.set("DB_"+year);
	}
	
	public static void restore() {
		dataSource.set(DEFAULE_SOURCE);
	}
	
}


