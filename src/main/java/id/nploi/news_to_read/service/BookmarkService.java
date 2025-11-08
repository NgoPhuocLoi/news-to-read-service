package id.nploi.news_to_read.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.nploi.news_to_read.dto.BookmarkRequestDto;
import id.nploi.news_to_read.entity.BookmarkEntity;
import id.nploi.news_to_read.entity.TagEntity;
import id.nploi.news_to_read.repository.BookmarkRepository;
import id.nploi.news_to_read.repository.TagRepository;

@Service
public class BookmarkService {

    private BookmarkRepository bookmarkRepository;
    private TagRepository tagRepository;

    @Autowired
    public BookmarkService(BookmarkRepository bookmarkRepository, TagRepository tagRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.tagRepository = tagRepository;
    }

    public List<BookmarkEntity> getAllBookmarks() {
        return this.bookmarkRepository.findAll();
    }

    @SuppressWarnings("null")
    @Transactional
    public BookmarkEntity createBookmark(BookmarkRequestDto dto) {
        // Create bookmark entity
        BookmarkEntity entity = BookmarkEntity.builder()
                .title(dto.title())
                .description(dto.description())
                .url(dto.url())
                .build();

        // Handle tags if provided
        if (dto.tags() != null && !dto.tags().isEmpty()) {
            List<TagEntity> tagEntities = findOrCreateTags(dto.tags());
            entity.setTags(tagEntities);
        }

        return bookmarkRepository.save(entity);
    }

    /**
     * Finds existing tags or creates new ones if they don't exist.
     * Uses a single batch query to check for existing tags to minimize DB calls.
     * 
     * @param tagNames List of tag names
     * @return List of TagEntity (both existing and newly created)
     */
    private List<TagEntity> findOrCreateTags(List<String> tagNames) {
        // Query all existing tags in a single batch query
        List<TagEntity> existingTags = tagRepository.findByNameIn(tagNames);

        // Create a map of existing tag names for quick lookup
        Map<String, TagEntity> existingTagMap = existingTags.stream()
                .collect(Collectors.toMap(TagEntity::getName, tag -> tag));

        // Identify new tag names that need to be created
        List<TagEntity> newTags = tagNames.stream()
                .filter(name -> !existingTagMap.containsKey(name))
                .map(name -> TagEntity.builder().name(name).build())
                .collect(Collectors.toList());

        // Save new tags if any
        if (!newTags.isEmpty()) {
            newTags = tagRepository.saveAll(newTags);
        }

        // Combine existing and new tags
        List<TagEntity> allTags = new ArrayList<>(existingTags);
        allTags.addAll(newTags);

        return allTags;
    }
}
