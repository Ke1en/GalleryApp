package java412.galleryapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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

    @ManyToMany(mappedBy = "tags")
    private Set<Image> images;

}
