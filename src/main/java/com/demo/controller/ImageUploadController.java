package com.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.demo.model.Image;
import com.demo.model.Product;
import com.demo.repository.ImageRepository;


@RestController
@CrossOrigin
class ImageUploadController {
	@Autowired
	ImageRepository imageRepository;
	
	@PostMapping("/uploadImage")
	public ResponseEntity<Object> fileUpload(Image image,@RequestParam("file") MultipartFile[] file,@RequestParam("id") int id) throws IOException{
		Arrays.asList(file).stream().forEach(file1 -> {
			try {
				//file:///D:/storage/photo.jpeg
				//C:\Users\tanu\Downloads\jewellery-admin-master\jewellery-admin-master\src\assets
				
		File myFile = new File("https://apurva25.s3.ap-south-1.amazonaws.com/"+file1.getOriginalFilename());
		myFile.createNewFile();
		FileOutputStream fos =new FileOutputStream(myFile);
		fos.write(file1.getBytes());
		fos.close();
		
		String path="https://apurva25.s3.ap-south-1.amazonaws.com/"+file1.getOriginalFilename();
		image.setPath(path);
		Product p=new Product(id);
		image.setProduct(p);
	
		Image savedUser = imageRepository.save(image);
			}
			catch(Exception e) {
				
			}
		});
		
		return new ResponseEntity<Object>("The File Uploaded Successfully", HttpStatus.OK);
	}
	
	
}
	
