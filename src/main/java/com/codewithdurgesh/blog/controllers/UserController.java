package com.codewithdurgesh.blog.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	// POST-create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);

	}

	// PUT-update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer uid) {
		UserDto updateUser = this.userService.updateUser(userDto, uid);
		return ResponseEntity.ok(updateUser);

	}

	// DELETE-delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid) {
		this.userService.deleteUser(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
	}

	// GET-user get
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		String sMsg="Start point of getAllUsers()";
		System.out.println(LocalDate.now()+""+sMsg);
		logger.info("Received request to fetch all users");
		logger.info(sMsg);
		List<UserDto> users = this.userService.getAllUsers();
		String eMsg = "Exit point of getAllUsers()";
		System.out.println(LocalDate.now()+""+eMsg);
		logger.info("Fetched {} users successfully", users.size());
		logger.info(eMsg);
		return ResponseEntity.ok(users);
//	public ResponseEntity<List<UserDto>> getAllUsers(){
//		logger.info("Received request to fetch all users");
//		return ResponseEntity.ok(this.userService.getAllUsers());
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
}
