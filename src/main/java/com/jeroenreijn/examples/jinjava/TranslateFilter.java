package com.jeroenreijn.examples.jinjava;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import com.hubspot.jinjava.Jinjava;
import com.hubspot.jinjava.interpret.JinjavaInterpreter;
import com.hubspot.jinjava.lib.filter.Filter;

public class TranslateFilter implements Filter, MessageSourceAware{

	private MessageSource messageSource;
	
	@Override
	public String getName() {
		return "translate";
	}

	@Override
	public Object filter(Object o, JinjavaInterpreter interpreter, String... args) {
		return messageSource.getMessage((String)o,args, Locale.getDefault());
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	
	public void setEngine(Jinjava engine) {
		engine.getGlobalContext().registerFilter(this);
	}
	
}
