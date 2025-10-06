package java412.galleryapp.controller;

import java412.galleryapp.dto.ImageBase64ResponseDto;
import java412.galleryapp.dto.ImageResponseDto;
import java412.galleryapp.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping(value = {"/", "/home"})
    public String redirectAllImagesPage() {
        return "redirect:/images";
    }

    @PostMapping(consumes = "multipart/form-data", path = "/upload") //TODO: переделать под @controller а не API restcontroller
    public ResponseEntity<ImageResponseDto> uploadImage(@RequestParam("name") String name, @RequestParam("description") String description,@RequestPart("file") MultipartFile file) throws IOException {

        ImageResponseDto imageResponseDto = imageService.uploadImage(name, description, file);

        return ResponseEntity.ok(imageResponseDto);

    }

    @GetMapping("/images")
    public String showAllImages(Model model) {

        List<ImageBase64ResponseDto> images = imageService.getAllImages();

        model.addAttribute("images", images);

        return "images";

    }

}
