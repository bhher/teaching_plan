package com.example.thymeleafdemo.service;

import com.example.thymeleafdemo.domain.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final List<Post> posts = new ArrayList<>();

    public PostService() {
        posts.add(new Post(1L, "첫 글", "안녕하세요. 메모리에만 있는 샘플 글입니다."));
        posts.add(new Post(2L, "Thymeleaf", "th:each 로 목록을 그리고, th:href 로 상세로 이동합니다."));
    }

    public List<Post> findAll() {
        return List.copyOf(posts);
    }

    public Optional<Post> findById(Long id) {
        return posts.stream().filter(p -> p.id().equals(id)).findFirst();
    }
}
