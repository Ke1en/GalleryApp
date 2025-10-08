package java412.galleryapp.service;

import java412.galleryapp.entity.Image;
import java412.galleryapp.mapper.ImageMapper;
import java412.galleryapp.repository.ImageRepository;
import java412.galleryapp.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Validated
@Transactional(readOnly = true)
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageMapper imageMapper;

    @Transactional(rollbackFor = Exception.class)
    public void uploadImage(MultipartFile file) throws IOException {

        String fileName = UUID.randomUUID() + "." +  ImageUtils.getImageFormat(file.getBytes());

        Path staticImagesPath = Paths.get("/src/main/resources/static/images");
        Files.createDirectories(staticImagesPath);

        Path filePath = staticImagesPath.resolve(fileName);

        Files.write(filePath, file.getBytes());

        Image image = new Image(UUID.randomUUID(), "/images/" + fileName, LocalDateTime.now());

        imageRepository.saveAndFlush(image);

    }

    public Page<Image> getAllImages(Pageable pageable) {
        return imageRepository.findAll(pageable);
    }

}
