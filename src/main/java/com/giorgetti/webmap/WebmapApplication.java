package com.giorgetti.webmap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

@SpringBootApplication
public class WebmapApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebmapApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("vproxy.univ-lemans.fr", 3128));
		requestFactory.setProxy(proxy);

		return new RestTemplate(requestFactory);
	}
}
