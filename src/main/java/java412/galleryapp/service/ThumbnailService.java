package java412.galleryapp.service;

import java412.galleryapp.dto.ThumbnailResponseDto;
import java412.galleryapp.entity.Tag;
import java412.galleryapp.entity.Thumbnail;
import java412.galleryapp.mapper.ThumbnailMapper;
import java412.galleryapp.repository.ThumbnailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
@Transactional(readOnly = true)
public class ThumbnailService {

    @Autowired
    private ImageService imageService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ThumbnailRepository thumbnailRepository;

    @Autowired
    private ThumbnailMapper thumbnailMapper;

    public Page<Thumbnail> getAllThumbnails(Pageable pageable) {
        return thumbnailRepository.findAll(pageable);
    }

    public List<ThumbnailResponseDto> convertPageableThumbnailsToDto(List<Thumbnail> thumbnails) {
        return thumbnails.stream()
                .map(thumbnail -> {
                    Set<Tag> tags = imageService.getTagsForImage(thumbnail.getImageId());
                    tagService.updateImageTagsCounter(tags);
                    return thumbnailMapper.mapToThumbnailResponseDto(thumbnail, tags);
                })
                .collect(Collectors.toList());
    }

    public Page<Thumbnail> getThumbnailsByTags(List<String> tags, Pageable pageable) {

        int tagCount = tags.size();
        List<Thumbnail> thumbnails = thumbnailRepository.findThumbnailsByTags(tags, tagCount);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), thumbnails.size());

        return new PageImpl<>(thumbnails.subList(start, end), pageable, thumbnails.size());

    }

    public Page<Thumbnail> getSearchFilter(String search, Pageable pageable) {

        if (search != null && !search.trim().isEmpty()) {

            String[] tagsToFilter = search.trim().split("\\s+");

            return getThumbnailsByTags(List.of(tagsToFilter), pageable);

        } else {
            return getAllThumbnails(pageable);
        }

    }

}
