package com.jeroenreijn.examples.jinjava;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import com.hubspot.jinjava.interpret.JinjavaInterpreter;
import com.hubspot.jinjava.loader.ResourceLocator;

public class JinjaSpringResourceLocator implements ResourceLocator, ResourceLoaderAware {

	private String prefix = "";
	private String suffix = "";
	private ResourceLoader resourceLoader = new DefaultResourceLoader();
	
	
	@Override
	public String getString(String name, Charset encoding, JinjavaInterpreter interpreter) throws IOException {
		return IOUtils.toString(resourceLoader.getResource(prefix + name + suffix).getInputStream(), encoding);
	}


	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
}
