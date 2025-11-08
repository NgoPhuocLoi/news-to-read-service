package id.nploi.news_to_read.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.nploi.news_to_read.dto.BookmarkRequestDto;
import id.nploi.news_to_read.entity.BookmarkEntity;
import id.nploi.news_to_read.service.BookmarkService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    private BookmarkService bookmarkService;

    @Autowired
    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @GetMapping()
    ResponseEntity<List<BookmarkEntity>> getAll() {
        return ResponseEntity.ok(bookmarkService.getAllBookmarks());
    }

    @PostMapping
    ResponseEntity<BookmarkEntity> create(
            @Valid @RequestBody BookmarkRequestDto request) {
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(bookmarkService.createBookmark(request));
    }
}
