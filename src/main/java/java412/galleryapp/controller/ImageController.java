package java412.galleryapp.controller;

import java412.galleryapp.mapper.ImageMapper;
import java412.galleryapp.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public String uploadImage(@RequestPart("file") MultipartFile file) throws IOException {

        imageService.uploadImageWithThumbnail(file);

        return "redirect:/images";

    }

    @GetMapping("/upload")
    public String showUploadImage(Model model) {
        return "upload";
    }

}
