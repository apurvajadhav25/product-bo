package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Filter1;
import com.demo.model.UserDetail;
import com.demo.repository.UserDetailRepository;

@RestController
@CrossOrigin
public class UserDetailController {
	
	@Autowired
	UserDetailRepository userDetailRepository;
	
	@GetMapping("/userDetail")
	public  Iterable<UserDetail> getAllFilter1(){
		return  userDetailRepository.findAll();
	}
	
	@GetMapping("/userDetail/{id}")
	public UserDetail getUserDetailById(@PathVariable(value = "id") Integer id) {
		UserDetail details = userDetailRepository.findById(id).get();
		return details;	
	}
	
	@PostMapping("/userDetail")
	public UserDetail createUserDetail(@RequestBody UserDetail detail) {
		System.out.println(" in create");
		System.out.println(detail.getEmailId());
		return userDetailRepository.save(detail);
	    }
	
	@DeleteMapping("/userDetail/{id}")
	public String deleteUserDetail(@PathVariable(value = "id") Integer id) {
	     UserDetail details = userDetailRepository.findById(id).get();
	     userDetailRepository.delete(details);
	     return "User Detail with id  "+id+" deleted successfully";
	    }
	
	@PutMapping("/userDetail/{id}")
    public ResponseEntity<UserDetail> updateFilter1(@PathVariable(value = "id") Integer id,
                                                 @RequestBody UserDetail details) {
        UserDetail detail = userDetailRepository.findById(id).get();

        detail.setUsername(details.getUsername());
        detail.setCartIds(details.getCartIds());
        detail.setEmailId(details.getEmailId());
        detail.setIsEnable(details.getIsEnable());
        detail.setMobileNumber(details.getMobileNumber());
        detail.setPassword(details.getPassword());
        detail.setWishlistIds(details.getWishlistIds());
        
        
        final UserDetail updatedDetail = userDetailRepository.save(detail);
        return ResponseEntity.ok(updatedDetail);
    }

}
