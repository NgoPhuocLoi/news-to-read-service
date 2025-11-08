package id.nploi.news_to_read.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.nploi.news_to_read.entity.TagEntity;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {

    Optional<TagEntity> findByName(String name);

    List<TagEntity> findByNameIn(List<String> names);
}
