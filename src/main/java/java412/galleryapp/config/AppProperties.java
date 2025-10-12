package java412.galleryapp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class AppProperties {

    private ResourceLocations resourceLocations;

    @Getter
    @Setter
    public static class ResourceLocations {

        private String images;
        private String thumbnails;

    }

}
