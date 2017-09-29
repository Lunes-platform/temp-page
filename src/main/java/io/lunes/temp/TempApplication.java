package io.lunes.temp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/*
	Production
 */
//@SpringBootApplication
//public class TempApplication extends SpringBootServletInitializer {
//
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(TempApplication.class);
//	}
//
//	public static void main(String[] args) throws Exception {
//		SpringApplication.run(TempApplication.class, args);
//	}
//
//}

/*
	Development
 */
@SpringBootApplication
public class TempApplication {

	public static void main(String[] args) {
		SpringApplication.run(TempApplication.class, args);
	}
}
