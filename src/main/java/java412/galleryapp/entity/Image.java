package java412.galleryapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "images")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
public class Image {

    @Id
    @Column
    private UUID id;

    @Column(nullable = false)
    private String imageUrl;

    @CreatedDate
    @Column
    private LocalDateTime createDate;

}
