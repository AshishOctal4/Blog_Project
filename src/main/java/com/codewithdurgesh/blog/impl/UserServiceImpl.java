package com.codewithdurgesh.blog.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.respositories.UserRepo;
import com.codewithdurgesh.blog.services.UserService;
import com.codewithdurgesh.blog.exceptions.*;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User saveUser = this.userRepo.save(user);
		return this.userToDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResouceNotFoundException("User","Id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updateUser = this.userRepo.save(user);
		UserDto userToDto1 = this.userToDto(updateUser);
		return userToDto1
				;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResouceNotFoundException("User","Id",userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users = this.userRepo.findAll();
		
		List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResouceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);
	}
	
	public User dtoToUser (UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class); //using modelmapper

//		User user = new User();
//
//		user.setId(userDto.getId());
//
//		user.setName(userDto.getName());
//
//		user.setEmail(userDto.getEmail());
//
//		user.setAbout(userDto.getAbout());
//
//		user.setPassword (userDto.getPassword());
//
		return user;

		}
	
	public UserDto userToDto (User user) {
		
		UserDto userDto = this.modelMapper.map(user, UserDto.class); // using modelmappper

//		UserDto userDto = new UserDto();
//
//		userDto.setId(user.getId());
//
//		userDto.setName(user.getName());
//
//		userDto.setEmail(user.getEmail());
//
//		userDto.setPassword(user.getPassword());
//
//		userDto.setAbout(user.getAbout());

		return userDto;
		
	}

}
