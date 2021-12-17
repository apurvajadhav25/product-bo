package com.demo.controller;

import java.util.List;
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
import com.demo.model.Image;
import com.demo.repository.ImageRepository;

@RestController
@CrossOrigin(origins = "*")
public class ImageController {
	
	@Autowired
	ImageRepository imageRepository;
	
	@GetMapping("/images")
	public  Iterable<Image> getAllImages(@RequestParam()int id){
		return imageRepository.findByImages(id);
	}
	
	@PostMapping("/images")
	public Image createImage(@RequestBody List<Image> imageList) {
		  
		  Image savedImage = null;
		  for(Image image: imageList) {
			  savedImage = imageRepository.save(image);
		  }
	    	 return savedImage;
	    }
	
	@GetMapping("/images/{id}")
	public Image getProductbyId(@PathVariable(value = "id") Integer imageId) {
		
		Image image = imageRepository.findById(imageId).get();
		return image;	
	}
	
	@PutMapping("/images/{id}")
    public ResponseEntity<Image> updateImage(@PathVariable(value = "id") Integer imageId,
                                                 @RequestBody Image imageDetails) {
		
        Image image = imageRepository.findById(imageId).get();
        
        image.setPath(imageDetails.getPath());
        image.setProduct(imageDetails.getProduct());
       
        final Image updatedImage = imageRepository.save(image);
        return ResponseEntity.ok(updatedImage);
    }
	
	@DeleteMapping("/images/{id}")
	public String deleteProduct(@PathVariable(value = "id") Integer imageId) {
		
	     Image image = imageRepository.findById(imageId).get();
	        imageRepository.delete(image);
	        return "Product with id "+imageId+" deleted successfully";
	    }

}
