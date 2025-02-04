package com.codewithdurgesh.blog.services;

import java.util.List;
import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.payloads.PostDto;

public interface PostService {

//create

	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

//update

	PostDto updatePost(PostDto postDto, Integer postId);

//delete
	
	void deletePost(Integer postId);
	
//get all posts
	
	List<PostDto> getAllPost(Integer pageNumber,Integer pageSize);
	
//get single post
	
	PostDto getPostById(Integer postId);
	
//get all post by category
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
//get all posts by user
	List<PostDto> getPostsByUser(Integer userId);
	
//search posts
	List<Post> searchPosts(String keyword);


}
