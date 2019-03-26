package com.cn.study.proxypattern.dynamicproxy.myselfdynamicproxy;

import java.lang.reflect.*;
import com.cn.study.proxypattern.staticproxypartten.Person;

public final class $Proxy0 implements Person {
	private MyselfInvocationHandler h;

	public $Proxy0(MyselfInvocationHandler h) {
		this.h = h;
	}

	public void findLove() {
		try {
			Method m = Person.class.getMethod("findLove", new Class[] {});
			this.h.invoke(this, m, new Object[] {});
		} catch (Throwable able) {
			throw new UndeclaredThrowableException(able);
		}

	}

	public String a(String string, int a) {
		try {
			Method m = Person.class.getMethod("a", new Class[] { String.class, int.class });
			this.h.invoke(this, m, new Object[] { string, a });
		} catch (Throwable able) {
			throw new UndeclaredThrowableException(able);
		}
		return null;
	}

	public int b(String string, int a) {
		try {
			Method m = Person.class.getMethod("b", new Class[] { String.class, int.class });
			this.h.invoke(this, m, new Object[] { string, a });
		} catch (Throwable able) {
			throw new UndeclaredThrowableException(able);
		}
		return 0;
	}
}