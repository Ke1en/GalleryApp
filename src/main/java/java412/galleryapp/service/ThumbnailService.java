package java412.galleryapp.service;

import java412.galleryapp.entity.Thumbnail;
import java412.galleryapp.repository.ThumbnailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@Transactional(readOnly = true)
public class ThumbnailService {

    @Autowired
    private ThumbnailRepository thumbnailRepository;

    public Page<Thumbnail> getAllThumbnails(Pageable pageable) {
        return thumbnailRepository.findAll(pageable);
    }

}
