package java412.galleryapp.service;

import java412.galleryapp.entity.Image;
import java412.galleryapp.entity.Tag;
import java412.galleryapp.repository.ImageRepository;
import java412.galleryapp.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping
public class TagService {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    ImageRepository imageRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public void updateAllTagsCount() {

        List<Image> images = imageRepository.findAll();

        Map<UUID, Integer> counts = new HashMap<>();

        for (Image img : images) {
            for (Tag tag : img.getTags()) {
                counts.put(tag.getId(), counts.getOrDefault(tag.getId(), 0) + 1);
            }
        }

        List<Tag> tags = tagRepository.findAll();

        for (Tag tag : tags) {
            tag.setImageCount(counts.getOrDefault(tag.getId(), 0));
        }

    }

    public void updateImageTagsCounts(Set<Tag> imageTags) {
        for (Tag tag : imageTags) {
            tag.setImageCount(tagRepository.countImagesByTagId(tag.getId()));
        }
    }
}
