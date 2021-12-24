package com.demo.controller;

import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.model.Filter1;
import com.demo.model.Image;
import com.demo.model.Product;
import com.demo.repository.ImageRepository;
import com.demo.repository.ProductRepository;
import com.demo.service.AmazonClient;

@RestController
@CrossOrigin
public class ProductController {
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	private AmazonClient amazonClient;

    @Autowired
    ProductController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }


	/*
	 * @PostMapping("/products") public Product createProduct(@RequestBody
	 * List<Product> productList) {
	 * 
	 * Product savedProduct = null; for(Product product: productList) { savedProduct
	 * = productRepository.save(product); } return savedProduct; }
	 */
	
	@PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
       
    	 Product savedProduct = productRepository.save(product);
    	 
    	 return savedProduct;
    }
	
	/*
	 * @GetMapping("/products") public Iterable<Product> getAllProducts(){ return
	 * productRepository.findAll();
	 * 
	 * }
	 */
	
	@GetMapping("/enableProducts")
	public Iterable<Product> getAllProducts(@RequestParam(name="filter1", required=false) String filter1, 
											@RequestParam(name="filter2", required=false) String filter2,
											@RequestParam(name="price", required=false) String price,
											@RequestParam(name="sort", required=false) String sort
											){
		System.out.println(sort);
		
		if("".equals(filter1) && "".equals(filter2) && "".equals(price)) {
			System.out.println("initial");
			return productRepository.findProducts();
		} /*
			 * else if(!"".equals(filter1) && "".equals(filter2)) { System.out.println("1");
			 * return productRepository.findProductsByType(filter1); } else
			 * if("".equals(filter1) && !"".equals(filter2)) { System.out.println("2");
			 * return productRepository.findProductsByPurity(filter2); }
			 */ else {
			System.out.println("3");
			return productRepository.findProducts(filter1,filter2,price,sort);
		}
	}
	
	@GetMapping("/products")
	public  Iterable<Product> getEnableProduct(){
		return  productRepository.findAll();
	}
	
	@GetMapping("/sortProducts")
	public Iterable<Product> getSortedProducts(){
		return productRepository.sortProducts();
	}
	
	@GetMapping("/sortProductsDesc")
	public Iterable<Product> getSortedProductsDesc(){
		return productRepository.sortProductsByDesc();
	}
	
	@DeleteMapping("/products/{id}")
	public String deleteProduct(@PathVariable(value = "id") Integer id) {
	     Product product = productRepository.findById(id).get();
	        productRepository.delete(product);
	        return "Product with "+id+" deleted successfully";
	    }
	
	@GetMapping("/products/{id}")
	public Product getProductbyId(@PathVariable(value = "id") Integer productId) {
		Product product = productRepository.findById(productId).get();
		return product;	
	}
	
	@PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Integer productId,
          @RequestBody Product productDetails) {
        Product product = productRepository.findById(productId).get();
        
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setDiscount(productDetails.getDiscount());
        product.setFilter1(productDetails.getFilter1());
        product.setFilter2(productDetails.getFilter2());
        product.setFilter3(productDetails.getFilter3());
        product.setFilter4(productDetails.getFilter4());
        product.setFilter5(productDetails.getFilter5());
        product.setFilter6(productDetails.getFilter6());
        product.setGender(productDetails.getGender());
        product.setImagePath(productDetails.getImagePath());
        product.setPrice(productDetails.getPrice());
        product.setRating(productDetails.getRating());
        product.setIsEnable(productDetails.getIsEnable());
        
        final Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }
	
	@PostMapping("/uploadFile")
    public void uploadFile(Image image,@RequestPart(value = "file") MultipartFile[] file,@RequestParam("id") int id) {
		Arrays.asList(file).stream().forEach(file1 -> {
			try {
        String path= this.amazonClient.uploadFile(file1);
        image.setPath(path);
        Product p=new Product(id);
		image.setProduct(p);
        imageRepository.save(image);
        
			}
			catch(Exception e) {
				
			}
        
		});
		
		
    }

	/*
	 * @DeleteMapping("/deleteFile") public String deleteFile(@RequestPart(value =
	 * "url") String fileUrl) { return
	 * this.amazonClient.deleteFileFromS3Bucket(fileUrl); }
	 */

}
