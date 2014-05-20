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
        //δ��¼����������
        if(session.get("admin")==null) {
            return "input";
        }
        //����ͨ������
        return invocation.invoke();
	}

}
