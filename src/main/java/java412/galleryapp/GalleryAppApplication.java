package java412.galleryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GalleryAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(GalleryAppApplication.class, args);
    }

}
