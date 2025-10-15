package java412.galleryapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Set;
import java.util.UUID;

@Data
@Entity(name = "tags")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
public class Tag {

    @Id
    @Column
    private UUID id;

    @Column
    private String name;

    @Transient
    private int imageCount;

    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private Set<Image> images;

}
