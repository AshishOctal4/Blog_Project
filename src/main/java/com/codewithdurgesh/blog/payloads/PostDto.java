package com.codewithdurgesh.blog.payloads;

import java.util.Date;
import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	
	private Integer postId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
}
