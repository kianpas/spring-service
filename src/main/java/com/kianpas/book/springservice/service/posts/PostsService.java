package com.kianpas.book.springservice.service.posts;

import com.kianpas.book.springservice.domain.posts.Posts;
import com.kianpas.book.springservice.domain.posts.PostsRepository;
import com.kianpas.book.springservice.web.dto.PostResponseDto;
import com.kianpas.book.springservice.web.dto.PostsListResponseDto;
import com.kianpas.book.springservice.web.dto.PostsUpdateRequestDto;
import com.kianpas.book.springservice.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        //리포지토리에서 아이디로 찾고
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        //가져온 post를 리포지토리 업데이트로 수정
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostResponseDto findById(Long id) {
        //아이디로 찾고 리스폰스 객체 생성
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }
}
