package com.xxm.douban.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * 包装类
 */
public class EscapeWrapper extends HttpServletRequestWrapper {
    public EscapeWrapper(HttpServletRequest request) {
        super(request);
    }
    
    @Override
    public String getParameter(String name) {
    	//保证中文不被转义
        String str1 = getRequest().getParameter(name);
        String str2 = StringEscapeUtils.escapeJava(str1);
        String str3 = StringEscapeUtils.escapeHtml(str2);
        return StringEscapeUtils.unescapeJava(str3);
    }
}
