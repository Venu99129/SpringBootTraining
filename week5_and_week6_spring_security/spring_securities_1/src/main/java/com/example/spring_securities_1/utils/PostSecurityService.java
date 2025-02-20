package com.example.spring_securities_1.utils;

import com.example.spring_securities_1.dto.PostDTO;
import com.example.spring_securities_1.entities.PostEntity;
import com.example.spring_securities_1.entities.User;
import com.example.spring_securities_1.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostSecurityService {

    private final PostService  postService;

    public boolean isOwnerOfPost(Long postId){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostEntity postEntity =  postService.getPostByIdReturnEntity(postId);
        return postEntity.getUpdatedByAsLong().equals(user.getId());
    }
}
