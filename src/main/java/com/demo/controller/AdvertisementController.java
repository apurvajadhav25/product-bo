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
import com.demo.model.Configuration;
import com.demo.repository.AdvertisementRepository;

@RestController
@CrossOrigin
public class AdvertisementController {
	
	@Autowired
	AdvertisementRepository advertisementRepository;
	
	@GetMapping("/advertisement")
	public  Iterable<Advertisement> getAllAdvertisement(){
		return  advertisementRepository.findAll();
	}
	
	@GetMapping("/advertisementByName")
	public Advertisement getAllProducts(@RequestParam(name="name") String name){
		return advertisementRepository.findByName(name);
		
	}
	
	@PostMapping("/advertisement")
	public Advertisement createAdvertisement(@RequestBody Advertisement advertisement) {
		  
		/*
		 * Filter1 savedFilter = null; for(Filter1 filter: filter1List) { savedFilter =
		 * filter1Repository.save(filter); } return savedFilter;
		 */
		return advertisementRepository.save(advertisement);
	    }
	
	@DeleteMapping("/advertisement/{id}")
	public String deleteAdvertisement(@PathVariable(value = "id") Integer id) {
	     Advertisement advertisement = advertisementRepository.findById(id).get();
	     advertisementRepository.delete(advertisement);
	     return "Product with id  "+id+" deleted successfully";
	    }
	
	@GetMapping("/advertisement/{id}")
	public Advertisement getAdvertisementById(@PathVariable(value = "id") Integer id) {
		Advertisement advertisement = advertisementRepository.findById(id).get();
		return advertisement;	
	}
	
	@PutMapping("/advertisement/{id}")
    public ResponseEntity<Advertisement> updateAdvertisement(@PathVariable(value = "id") Integer id,
                                                 @RequestBody Advertisement advertisementDetails) {
        Advertisement advertisement = advertisementRepository.findById(id).get();

        advertisement.setName(advertisementDetails.getName());
        advertisement.setIsEnable(advertisementDetails.getIsEnable());
      //  advertisement.setImagePath(advertisementDetails.getImagePath());
        
        final Advertisement updatedAdvertisement = advertisementRepository.save(advertisement);
        return ResponseEntity.ok(updatedAdvertisement);
    }

}
