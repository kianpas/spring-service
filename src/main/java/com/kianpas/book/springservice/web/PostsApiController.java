package com.kianpas.book.springservice.web;

import com.kianpas.book.springservice.service.posts.PostsService;
import com.kianpas.book.springservice.web.dto.PostResponseDto;
import com.kianpas.book.springservice.web.dto.PostUpdateRequestDto;
import com.kianpas.book.springservice.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);

    }

    @GetMapping("/api/v1/posts/{id}")
    public PostResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }
}
