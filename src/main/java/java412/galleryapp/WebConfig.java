package java412.galleryapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:C:/media/images/")
                .setCacheControl(CacheControl.noCache());

        registry.addResourceHandler("/thumbnails/**")
                .addResourceLocations("file:C:/media/thumbnails/")
                .setCacheControl(CacheControl.noCache());

    }

}
