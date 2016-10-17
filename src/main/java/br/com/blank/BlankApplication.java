package br.com.blank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import com.mangofactory.swagger.plugin.EnableSwagger;

@SpringBootApplication
@EnableCaching
public class BlankApplication {

	/**Fluxo para fazer uma API
		1) Levantar os requisitos com o usuário
		2) Identificar resources
		3) Identificar endpoints 
		4) Verbo e ação identificação
	 **/
	
	
	private static final Logger logger = LoggerFactory.getLogger(BlankApplication.class);
	
	public static void main(String[] args) {
		logger.info("Iniciando aplicação Spring boot....");
		SpringApplication.run(BlankApplication.class, args);
	}

	@Bean
	public CacheManager cacheManager() {
		logger.info("Iniciando bean CacheManager");
		return new EhCacheCacheManager(ehCacheCacheManager().getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		logger.info("Iniciando bean EhCacheManagerFactoryBean");
		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
		cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
		cmfb.setShared(true);
		return cmfb;
	}
	
	
}
