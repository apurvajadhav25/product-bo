package com.demo.controller;

import java.util.Arrays;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.model.Advertisement;
import com.demo.model.Configuration;
import com.demo.model.Image;
import com.demo.model.Product;
import com.demo.repository.AdvertisementRepository;
import com.demo.service.AmazonClient;

@RestController
@CrossOrigin
public class AdvertisementController {
	
	@Autowired
	AdvertisementRepository advertisementRepository;
	
	private AmazonClient amazonClient;

    @Autowired
    AdvertisementController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }
	
	@GetMapping("/advertisement")
	public  Iterable<Advertisement> getAllAdvertisement(){
		return  advertisementRepository.findAll();
	}
	
	@GetMapping("/advertisementByName")
	public Advertisement getAllProducts(@RequestParam(name="name") String name){
		return advertisementRepository.findByName(name);
		//return advertisementRepository.findById(1).get();
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
        advertisement.setImagePath(advertisementDetails.getImagePath());
        
        final Advertisement updatedAdvertisement = advertisementRepository.save(advertisement);
        return ResponseEntity.ok(updatedAdvertisement);
    }
	
	@PostMapping("/uploadAdvertisement")
    public void uploadFile(Advertisement advertisement,@RequestPart(value = "file") MultipartFile[] file,@RequestParam("id") Integer id) {
		System.out.println(id);
		String path[] = new String[file.length];
		String finalPath = null;
		for(int i=0;i<file.length;i++) {
			path[i] = this.amazonClient.uploadFile(file[i]);
		}
		finalPath = Arrays.toString(path).replace(" ", "").replace(" ", "").replace("[", "").replace("]", "");
		System.out.println(finalPath);
		Advertisement a = advertisementRepository.findById(id).get();
		a.setImagePath(finalPath);
		advertisementRepository.save(a);
		
    }
	
	@GetMapping("/deleteAdvertisement")
	public String deleteAdvertisementImage(@RequestParam("id") Integer id, @RequestParam("name") String name) {
		  String finalImagePath;
		System.out.println(id);
		System.out.println(name);
		  Advertisement advertisement = advertisementRepository.findById(id).get();
		  String imagePath = advertisement.getImagePath();
		  if(imagePath.endsWith(name))
			  finalImagePath = imagePath.replace(name , "");
		  else
		      finalImagePath = imagePath.replace(name + ",", "");
		  advertisement.setImagePath(finalImagePath);
		  advertisementRepository.save(advertisement);
		 
	        return "Product with id  deleted successfully";
	    }

}
