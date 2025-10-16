package java412.galleryapp.controller;

import java412.galleryapp.dto.ThumbnailResponseDto;
import java412.galleryapp.entity.Tag;
import java412.galleryapp.entity.Thumbnail;
import java412.galleryapp.service.TagService;
import java412.galleryapp.service.ThumbnailService;
import java412.galleryapp.utils.PaginationUtils;
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
    private TagService tagService;

    @GetMapping("/images")
    public String showAllThumbnails(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "40") int size, Model model) {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createDate");
        Page<Thumbnail> thumbnailsPage = thumbnailService.getAllThumbnails(pageable);

        List<ThumbnailResponseDto> thumbnails = thumbnailService.convertPageableThumbnailsToDto(thumbnailsPage.getContent());

        List<Tag> sortedUniqueTags = tagService.getSortedUniqueTags(thumbnails);

        int totalPages = thumbnailsPage.getTotalPages();

        int[] pageRange = PaginationUtils.calculatePageRange(page, totalPages, 5);
        int startPage = pageRange[0];
        int endPage = pageRange[1];

        model.addAttribute("thumbnails", thumbnails);
        model.addAttribute("currentPage", page);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("tags", sortedUniqueTags);

        return "images";

    }

}
