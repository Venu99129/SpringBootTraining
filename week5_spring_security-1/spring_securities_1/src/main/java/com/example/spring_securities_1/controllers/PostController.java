package com.example.spring_securities_1.controllers;

import com.example.spring_securities_1.dto.PostDTO;
import com.example.spring_securities_1.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN') OR hasAnyAuthority('POST_VIEW')")
    public PostDTO getPostById(@PathVariable Long postId) {
        log.info("fetching posts details by using getPostById  postId: {}",postId);
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost) {
        return postService.createNewPost(inputPost);
    }

    @PutMapping("{postId}")
    @PreAuthorize("@postSecurityService.isOwnerOfPost(#postId)")
    public PostDTO updatePost(@RequestBody PostDTO inputPost,@PathVariable Long postId ) {
        log.info("update post postDto :{} , post id : {}",inputPost , postId);
        return postService.updatePost(inputPost, postId);
    }


}
