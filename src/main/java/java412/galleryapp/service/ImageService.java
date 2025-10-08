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

        LocalDateTime createDate = LocalDateTime.now();

        Image image = new Image(UUID.randomUUID(), ImageUtils.compressImage(file.getBytes()), createDate);

        imageRepository.saveAndFlush(image);

        imageMapper.mapToImageResponseDto(image);

    }

    public Page<Image> getAllImages(Pageable pageable) {

        Page<Image> imagesPage = imageRepository.findAll(pageable);

        List<Image> decompressedImages = imagesPage.getContent().stream()
                .map(image -> {
                    try {

                        byte[] decompressedData = ImageUtils.decompressImage(image.getImage());
                        byte[] resizedData = ImageUtils.resizeImage(decompressedData, 0.1);
                        image.setImage(resizedData);

                    } catch (IOException e){
                        e.printStackTrace();
                    }

                    return image;
                })
                .toList();

        return new PageImpl<>(decompressedImages, pageable, imagesPage.getTotalElements());

    }

}
