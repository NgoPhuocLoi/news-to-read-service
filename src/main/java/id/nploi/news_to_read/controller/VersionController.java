package id.nploi.news_to_read.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import id.nploi.news_to_read.dto.VersionResponseDto;

@RestController
public class VersionController {

    @GetMapping("/api/version")
    ResponseEntity<VersionResponseDto> getVersion() {
        var dto = new VersionResponseDto("0.0.1");
        return ResponseEntity.ok(dto);
    }
}
