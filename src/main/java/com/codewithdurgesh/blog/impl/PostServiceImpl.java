package com.codewithdurgesh.blog.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.exceptions.ResouceNotFoundException;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.respositories.CategoryRepo;
import com.codewithdurgesh.blog.respositories.PostRepo;
import com.codewithdurgesh.blog.respositories.UserRepo;
import com.codewithdurgesh.blog.services.PostService;

import net.bytebuddy.asm.Advice.This;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId){
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResouceNotFoundException("User", "User id", userId));	
		
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResouceNotFoundException("Category", "category Id", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("deafult.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId).
				orElseThrow(()->new ResouceNotFoundException("Post", "post id", postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
		
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).
		orElseThrow(()->new ResouceNotFoundException("Post", "post id", postId));
		
		this.postRepo.delete(post);
	}

	@Override
	public List<PostDto> getAllPost(Integer pageNumber,Integer pageSize) {
		

		
		Pageable p=PageRequest.of(pageNumber, pageSize);
		
		Page<Post> pagePost = this.postRepo.findAll(p);
		
		List<Post> allPosts=pagePost.getContent();
		
		List<PostDto> postDtos = allPosts.stream().map((post)-> this.modelMapper.map(post, PostDto.class))
		.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()->new ResouceNotFoundException("Post", "post id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResouceNotFoundException("CategoryId", "category id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResouceNotFoundException("User", "userId", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<Post> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}


}
