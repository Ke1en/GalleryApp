package java412.galleryapp.service;

import java412.galleryapp.dto.ImageResponseDto;
import java412.galleryapp.entity.Image;
import java412.galleryapp.entity.Tag;
import java412.galleryapp.entity.Thumbnail;
import java412.galleryapp.mapper.ImageMapper;
import java412.galleryapp.repository.ImageRepository;
import java412.galleryapp.repository.TagRepository;
import java412.galleryapp.repository.ThumbnailRepository;
import java412.galleryapp.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Validated
@Transactional(readOnly = true)
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ThumbnailRepository thumbnailRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ImageMapper imageMapper;

    @Transactional(rollbackFor = Exception.class)
    public void uploadImageWithThumbnail(MultipartFile file, List<UUID> tagIds) throws IOException {

        Image originalImage = uploadOriginalImage(file, tagIds);

        createThumbnail(originalImage);

    }

    public ImageResponseDto findImageById(UUID id) {

        Image image = imageRepository.findById(id).orElseThrow();

        return imageMapper.mapToImageResponseDto(image);

    }

    /*public int getTagsCountForImage(UUID id) {

    }
*/
    public Set<Tag> getTagsForImage(UUID id) {
        return imageRepository.findTagsByImageId(id);
    }

    private Image uploadOriginalImage(MultipartFile file, List<UUID> tagIds) throws IOException {

        String fileName = UUID.randomUUID() + "." +  ImageUtils.getImageFormat(file.getBytes());

        Path imagesPath = Paths.get("C:/media/images");
        Files.createDirectories(imagesPath);

        Path filePath = imagesPath.resolve(fileName);
        Files.write(filePath, file.getBytes());

        List<Tag> tags = tagRepository.findAllById(tagIds);

        Image image = new Image(UUID.randomUUID(), "/images/" + fileName, LocalDateTime.now(), new HashSet<>(tags));

        imageRepository.saveAndFlush(image);

        return image;

    }

    private void createThumbnail(Image originalImage) throws IOException {

        Path originalImagePath = Paths.get("C:/media").resolve(originalImage.getImageUrl().substring(1));
        byte[] originalImageBytes = Files.readAllBytes(originalImagePath);

        byte[] thumbnailImageBytes = ImageUtils.resizeImage(originalImageBytes);

        String filename = UUID.randomUUID() + "." +  ImageUtils.getImageFormat(originalImageBytes);

        Path thumbnailImagesPath = Paths.get("C:/media/thumbnails");
        Path filePath = thumbnailImagesPath.resolve(filename);
        Files.write(filePath, thumbnailImageBytes);

        Thumbnail thumbnailImage = new Thumbnail(UUID.randomUUID(), "/thumbnails/" + filename, LocalDateTime.now(), originalImage.getId());

        thumbnailRepository.saveAndFlush(thumbnailImage);

    }

}
