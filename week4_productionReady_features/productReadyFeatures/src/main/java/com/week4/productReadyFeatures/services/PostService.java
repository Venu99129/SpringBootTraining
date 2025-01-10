package com.week4.productReadyFeatures.services;


import com.week4.productReadyFeatures.dto.PostDTO;
import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);
//
//    PostDTO updatePost(PostDTO inputPost, Long postId);
}
