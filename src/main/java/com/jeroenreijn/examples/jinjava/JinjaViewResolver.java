package com.jeroenreijn.examples.jinjava;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

import com.hubspot.jinjava.Jinjava;

public class JinjaViewResolver extends AbstractTemplateViewResolver {

	private Jinjava engine;
	private Charset charset = StandardCharsets.UTF_8;
	private boolean renderExceptions = false;
	private String contentType = "text/html;charset=UTF-8";

	public JinjaViewResolver() {
		setViewClass(requiredViewClass());
	}

	@Override
	protected Class<?> requiredViewClass() {
		return JinjaView.class;
	}

	@Override
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		JinjaView view = (JinjaView) super.buildView(viewName);
		view.setEngine(this.engine);
		view.setContentType(contentType);
		view.setRenderExceptions(renderExceptions);
		view.setEncoding(charset);
		return view;
	}
	
	public void setEngine(Jinjava engine) {
		this.engine = engine;
	}
	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
}