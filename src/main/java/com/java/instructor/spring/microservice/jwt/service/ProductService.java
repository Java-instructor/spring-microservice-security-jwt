package com.java.instructor.spring.microservice.jwt.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.instructor.spring.microservice.jwt.dto.Product;
import com.java.instructor.spring.microservice.jwt.entity.UserInfo;
import com.java.instructor.spring.microservice.jwt.repository.UserInfoRepository;

import jakarta.annotation.PostConstruct;

@Service
public class ProductService {

	List<Product> productList = null;

	@Autowired
	private UserInfoRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void loadProductsFromDB() {
		productList = IntStream
				.rangeClosed(1, 100).mapToObj(i -> Product.builder().productId(i).name("product " + i)
						.qty(new Random().nextInt(10)).price(new Random().nextInt(5000)).build())
				.collect(Collectors.toList());
	}

	public List<Product> getProducts() {
		return productList;
	}

	public Product getProduct(int id) {
		return productList.stream().filter(product -> product.getProductId() == id).findAny()
				.orElseThrow(() -> new RuntimeException("product " + id + " not found"));
	}

	public String addUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		repository.save(userInfo);
		return "user added to system ";
	}
}
