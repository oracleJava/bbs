package com.bbs.df.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
        Map<String, Object> session = ctx.getSession();
        //未登录，返回输入
        if(session.get("admin")==null) {
            return "input";
        }
        //否则通过拦截
        return invocation.invoke();
	}

}
