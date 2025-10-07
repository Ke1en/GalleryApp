package java412.galleryapp.controller;

import java412.galleryapp.dto.ImageBase64ResponseDto;
import java412.galleryapp.dto.ImageResponseDto;
import java412.galleryapp.entity.Image;
import java412.galleryapp.mapper.ImageMapper;
import java412.galleryapp.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping
public class ImageController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageMapper imageMapper;

    @GetMapping(value = {"/", "/home"})
    public String redirectAllImagesPage() {
        return "redirect:/images";
    }

    @PostMapping(consumes = "multipart/form-data", path = "/upload")
    //TODO: переделать под @controller а не API restcontroller
    public String uploadImage(@RequestPart("file") MultipartFile file) throws IOException {

        imageService.uploadImage(file);

        return "redirect:/images";

    }

    @GetMapping("/upload")
    public String showUploadImage(Model model) {
        return "upload";
    }

    @GetMapping("/images")
    public String showAllImages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "40") int size, Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Image> imagesPage = imageService.getAllImages(pageable);

        List<ImageBase64ResponseDto> images = imagesPage.getContent().stream()
                .map(imageMapper::mapToImageBase64ResponseDto)
                .toList();

        int maxPagesToShow = 5;
        int totalPages = imagesPage.getTotalPages();

        int startPage = Math.max(0, page - maxPagesToShow / 2);
        int endPage = Math.min(totalPages - 1, startPage + maxPagesToShow - 1);

        if (endPage - startPage < maxPagesToShow - 1) {
            startPage = Math.max(0, endPage - maxPagesToShow + 1);
        }

        model.addAttribute("images", images);
        model.addAttribute("currentPage", page);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", imagesPage.getTotalPages());

        return "images";

    }

}
