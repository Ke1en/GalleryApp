package java412.galleryapp.controller;

import java412.galleryapp.dto.ImageResponseDto;
import java412.galleryapp.entity.Tag;
import java412.galleryapp.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping(value = {"/", "/home"})
    public String redirectAllImagesPage() {
        return "redirect:/images";
    }

    @PostMapping(consumes = "multipart/form-data", path = "/upload")
    public String uploadImage(@RequestPart("file") MultipartFile file,
                              @RequestParam(value = "tagIds", required = false) List<UUID> tagIds) throws IOException {

        imageService.uploadImageWithThumbnail(file, tagIds);

        return "redirect:/images";

    }

    @GetMapping("/upload")
    public String showUploadImage(Model model) {

        List<Tag> tags = imageService.findImageTags();

        model.addAttribute("tags", tags);

        return "upload";
    }

    @GetMapping("/images/view/{id}")
    public String viewImagePage(@PathVariable UUID id, Model model) {

        ImageResponseDto imageById = imageService.findImageById(id);

        model.addAttribute("image", imageById);

        return "image-view";

    }

}
