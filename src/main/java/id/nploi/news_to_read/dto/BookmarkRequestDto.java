package id.nploi.news_to_read.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.util.List;

public record BookmarkRequestDto(
        @NotBlank(message = "Title is required") @Size(max = 255, message = "Title must not exceed 255 characters") String title,

        @NotBlank(message = "URL is required") @URL(message = "URL must be valid") @Size(max = 2048, message = "URL must not exceed 2048 characters") String url,

        String description,

        List<String> tags) {
}
