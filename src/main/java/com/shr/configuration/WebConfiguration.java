package com.shr.configuration;

import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;

@Order(1)
public class WebConfiguration implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) {
        /**设置servlet编码开始*/
        // Register Filter : Set Character Encoding
        FilterRegistration scefilter = servletContext.addFilter("Set Character Encoding", CharacterEncodingFilter.class);
        Map<String, String> sceMap = new HashMap<String, String>();
        sceMap.put("encoding", "UTF-8");
        sceMap.put("forceEncoding", "true");
        scefilter.setInitParameters(sceMap);
        scefilter.addMappingForUrlPatterns(null, true, "/*");
        /**设置servlet编码结束*/
    }
}
