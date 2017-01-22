package com.jeroenreijn.examples.jinjava;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractTemplateView;

import com.hubspot.jinjava.Jinjava;
import com.hubspot.jinjava.interpret.FatalTemplateErrorsException;

public class JinjaView extends AbstractTemplateView {

	private Charset encoding = StandardCharsets.UTF_8;
	private Jinjava engine;
	private boolean renderExceptions = false;
	private String contentType;

	@Override
	protected void renderMergedTemplateModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		doRender(model, response);
	}

	private void doRender(Map<String, Object> model,
			HttpServletResponse response) throws IOException {
		logger.trace("Rendering Jinja template [" + getUrl() + "] in JinjaView '"
			+ getBeanName() + "'");

		if (contentType != null) {
			response.setContentType(contentType);
		}

		PrintWriter responseWriter = response.getWriter();

		if (renderExceptions) {
			try {
				responseWriter.write(engine.render(getTemplate(), model));
			} catch (FatalTemplateErrorsException e) {
				// TODO: render exception
				responseWriter.write(e.getLocalizedMessage());
				logger.error("failed to render template [" + getUrl() + "]", e);
			} catch (IOException e) {
				responseWriter.write("<pre>could not find template: " + getUrl() + "\n");
				e.printStackTrace(responseWriter);
				responseWriter.write("</pre>");
				logger.error("could not find template", e);
			}
		} else {
			try {
				responseWriter.write(engine.render(getTemplate(), model));
			} catch (Throwable e) {
				logger.error("failed to render template [" + getUrl() + "]\n", e);
			}
		}
	}

	protected String getTemplate() throws IOException {
		return engine.getResourceLocator().getString(getUrl(), encoding,
				null);
	}

	@Override
	public boolean checkResource(Locale locale) throws Exception {
		try {
			engine.getResourceLocator().getString(getUrl(), encoding, null);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public Charset getEncoding() {
		return encoding;
	}

	public void setEncoding(Charset encoding) {
		this.encoding = encoding;
	}

	public Jinjava getEngine() {
		return engine;
	}

	public void setEngine(Jinjava engine) {
		this.engine = engine;
	}

	public boolean isRenderExceptions() {
		return renderExceptions;
	}

	public void setRenderExceptions(boolean renderExceptions) {
		this.renderExceptions = renderExceptions;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	
}