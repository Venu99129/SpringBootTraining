package com.example.spring_securities_1.services;


import com.example.spring_securities_1.dto.PostDTO;
import com.example.spring_securities_1.entities.PostEntity;

import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);

    PostEntity getPostByIdReturnEntity(Long postId);

    PostDTO updatePost(PostDTO inputPost, Long postId);
}
