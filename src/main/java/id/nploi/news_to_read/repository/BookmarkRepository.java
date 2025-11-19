package id.nploi.news_to_read.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.nploi.news_to_read.entity.BookmarkEntity;

@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long> {

    List<BookmarkEntity> findAllByOrderByCreatedAtDesc();
}
