package com.week4.productReadyFeatures.services;

import com.week4.productReadyFeatures.dto.PostDTO;
import com.week4.productReadyFeatures.entities.PostEntity;
import com.week4.productReadyFeatures.exceptions.ResourceNotFoundException;
import com.week4.productReadyFeatures.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long postId) {
        PostEntity postEntity = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id "+postId));
        return modelMapper.map(postEntity, PostDTO.class);
    }

    @Override
    public PostDTO updatePostByID(PostDTO inputPost, Long postId) {
        PostEntity oldPost = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("given id post is not found"));
        inputPost.setPostId(postId);
        modelMapper.map(inputPost , oldPost);
        PostEntity savedPost = postRepository.save(oldPost);
        return modelMapper.map(savedPost , PostDTO.class);
    }

}
