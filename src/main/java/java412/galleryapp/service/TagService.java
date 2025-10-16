package java412.galleryapp.service;

import java412.galleryapp.entity.Tag;
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

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public void updateImageTagsCounter(Set<Tag> imageTags) {
        for (Tag tag : imageTags) {
            tag.setImageCount(tagRepository.countImagesByTagId(tag.getId()));
        }
    }

}
