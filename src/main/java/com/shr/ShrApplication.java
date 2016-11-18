package com.shr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 打包war包必须配置
 * spring容器必须只有在这个文件目录下面的所有目录和文件
 * @author OLE
 * @date 2016/10/17
 */
@SpringBootApplication
@EnableAutoConfiguration
public class ShrApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ShrApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(ShrApplication.class, args);
	}
}
