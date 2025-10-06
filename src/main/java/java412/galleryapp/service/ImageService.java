package java412.galleryapp.service;

import io.swagger.v3.oas.annotations.media.Schema;
import java412.galleryapp.dto.ImageBase64ResponseDto;
import java412.galleryapp.dto.ImageResponseDto;
import java412.galleryapp.entity.Image;
import java412.galleryapp.mapper.ImageMapper;
import java412.galleryapp.repository.ImageRepository;
import java412.galleryapp.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Validated
@Transactional(readOnly = true)
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageMapper imageMapper;

    @Transactional(rollbackFor = Exception.class)
    public ImageResponseDto uploadImage(String name, String description, MultipartFile file) throws IOException {

        LocalDateTime createDate = LocalDateTime.now();

        Image image = new Image(UUID.randomUUID(), name, description, ImageUtils.compressImage(file.getBytes()), createDate);

        imageRepository.saveAndFlush(image);

        return imageMapper.mapToImageResponseDto(image);

    }

    public List<ImageBase64ResponseDto> getAllImages() {

        List<Image> images = imageRepository.findAll();

        List<Image> decompressedImages = images.stream()
                .map(image -> {
                    byte[] decompressedData = ImageUtils.decompressImage(image.getImage());
                    image.setImage(decompressedData);
                    return image;
                })
                .collect(Collectors.toList());

        return decompressedImages.stream()
                .map(imageMapper::mapToImageBase64ResponseDto)
                .collect(Collectors.toList());

    }

}
