package br.com.blank;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class BlankMvcConfigAdapter extends WebMvcConfigurerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(BlankMvcConfigAdapter.class);
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
	    PageableHandlerMethodArgumentResolver phmar = new PageableHandlerMethodArgumentResolver();

	    logger.info("Iniciando Resolver para páginação");
	    phmar.setFallbackPageable(new PageRequest(0, 5));
	    argumentResolvers.add(phmar);
	    super.addArgumentResolvers(argumentResolvers);
	}
}