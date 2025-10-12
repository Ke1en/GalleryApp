package java412.galleryapp.controller;

import java412.galleryapp.dto.ThumbnailResponseDto;
import java412.galleryapp.entity.Thumbnail;
import java412.galleryapp.mapper.ThumbnailMapper;
import java412.galleryapp.service.ThumbnailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping
public class ThumbnailController {

    @Autowired
    private ThumbnailService thumbnailService;

    @Autowired
    private ThumbnailMapper thumbnailMapper;

    @GetMapping("/images")
    public String showAllThumbnails(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "40") int size, Model model) {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createDate");
        Page<Thumbnail> thumbnailsPage = thumbnailService.getAllThumbnails(pageable);

        List<ThumbnailResponseDto> thumbnails = thumbnailsPage.getContent().stream()
                .map(thumbnailMapper::mapToThumbnailResponseDto)
                .toList();

        int maxPagesToShow = 5;
        int totalPages = thumbnailsPage.getTotalPages();

        int startPage = Math.max(0, page - maxPagesToShow / 2);
        int endPage = Math.min(totalPages - 1, startPage + maxPagesToShow - 1);

        if (endPage - startPage < maxPagesToShow - 1) {
            startPage = Math.max(0, endPage - maxPagesToShow + 1);
        }

        model.addAttribute("thumbnails", thumbnails);
        model.addAttribute("currentPage", page);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", totalPages);

        return "images";

    }

}
