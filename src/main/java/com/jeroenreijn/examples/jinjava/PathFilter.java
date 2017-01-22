package com.jeroenreijn.examples.jinjava;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hubspot.jinjava.Jinjava;
import com.hubspot.jinjava.interpret.JinjavaInterpreter;
import com.hubspot.jinjava.lib.filter.Filter;
import com.lyncode.jtwig.util.FilePath;

public class PathFilter implements Filter {
	
	@Override
	public String getName() {
		return "path";
	}

	@Override
	public Object filter(Object var, JinjavaInterpreter interpreter, String... args) {
       HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	   return new FilePath(request.getContextPath(), (String) var).toString();
	}
	
	public void setEngine(Jinjava engine) {
		engine.getGlobalContext().registerFilter(this);
	}
	
}
