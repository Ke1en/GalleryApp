package java412.galleryapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AppProperties appProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///" + appProperties.getResourceLocations().getImages())
                .setCacheControl(CacheControl.noCache());

        registry.addResourceHandler("/thumbnails/**")
                .addResourceLocations("file:///" + appProperties.getResourceLocations().getThumbnails())
                .setCacheControl(CacheControl.noCache());

    }

}
