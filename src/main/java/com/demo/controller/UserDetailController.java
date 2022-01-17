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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Advertisement;
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
	
	@GetMapping("/userDetailByUsername")
	public UserDetail getUserDetailByUsername(@RequestParam(value = "username") String username) {
		System.out.println("oo");
		UserDetail details = userDetailRepository.findByUsername(username);
		return details;	
	}
	
	@PostMapping("/userDetail")
	public UserDetail createUserDetail(@RequestBody UserDetail detail) {
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
	
	@GetMapping("/updateCartId")
    public ResponseEntity<UserDetail> updateCartIds(@RequestParam(name = "cartIds", required = false) String cartId,
    		                                        @RequestParam(name = "username", required = false) String username){
		String finalCartIds = null;
        UserDetail detail = userDetailRepository.findByUsername(username);
        String initialCartIds = detail.getCartIds();
        if(initialCartIds != null) {
        if(initialCartIds.contains(cartId)) 
            finalCartIds = initialCartIds;
        else if(initialCartIds.equals(""))
        	finalCartIds = cartId;
        else 
        	finalCartIds = initialCartIds.concat("," +cartId);
        }else 
        	finalCartIds = cartId;
        
        detail.setCartIds(finalCartIds);
        
        final UserDetail updatedDetail = userDetailRepository.save(detail);
        return ResponseEntity.ok(updatedDetail);
    }
	
	@GetMapping("/updateWishlistId")
    public ResponseEntity<UserDetail> updateWishlistIds(@RequestParam(name = "wishlistIds", required = false) String wishlistId,
    		                                            @RequestParam(name = "username", required = false) String username){
		String finalWishlistIds = null;
        UserDetail detail = userDetailRepository.findByUsername(username);
        String initialWishlistIds = detail.getWishlistIds();
        if(initialWishlistIds != null) {
        if(initialWishlistIds.contains(wishlistId)) 
            finalWishlistIds = initialWishlistIds;
        else if(initialWishlistIds.equals(""))
        	finalWishlistIds = wishlistId;
        else
        	finalWishlistIds = initialWishlistIds.concat("," +wishlistId);
        }else 
        	finalWishlistIds = wishlistId;
        
        detail.setWishlistIds(finalWishlistIds);
        
        final UserDetail updatedDetail = userDetailRepository.save(detail);
        return ResponseEntity.ok(updatedDetail);
    }
	
	@GetMapping("/deleteCartIds")
	public ResponseEntity<UserDetail> deleteCartIds(@RequestParam("cartIds") String cartId) {
		  String finalCartIds;
		  UserDetail detail = userDetailRepository.findById(1).get();
		  String cartIds = detail.getCartIds();
			
			 /*if(!cartIds.contains(","))
				 finalCartIds = cartIds.replace(cartId, "");
			 else if(cartIds.endsWith(","))*/
		  if(cartIds.endsWith(cartId))
			  finalCartIds = cartIds.replace(cartId, "");
		  else
			  finalCartIds = cartIds.replace(cartId  + ",", "");
					/*
					 * else finalCartIds = cartIds.replace("," + cartId, "");
					 */
			 
		     detail.setCartIds(finalCartIds);
		  
		  
		  final UserDetail updatedDetail = userDetailRepository.save(detail);
		  return ResponseEntity.ok(updatedDetail);
	    }
	
	@GetMapping("/deleteWishlistIds")
	public ResponseEntity<UserDetail> deleteWishlistIds(@RequestParam("wishlistIds") String wishlistId) {
		  String finalWishlistIds;
		  UserDetail detail = userDetailRepository.findById(1).get();
		  String wishlistIds = detail.getWishlistIds();
		  if(wishlistIds.endsWith(wishlistId))
			  finalWishlistIds = wishlistIds.replace(wishlistId, "");
		  else
		     finalWishlistIds = wishlistIds.replace(wishlistId  + ",", "");
		  detail.setWishlistIds(finalWishlistIds);
		  
		  
		  final UserDetail updatedDetail = userDetailRepository.save(detail);
		  return ResponseEntity.ok(updatedDetail);
	    }

}
