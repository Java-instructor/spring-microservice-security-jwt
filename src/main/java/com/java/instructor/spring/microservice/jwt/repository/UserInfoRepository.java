package com.java.instructor.spring.microservice.jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.instructor.spring.microservice.jwt.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
	Optional<UserInfo> findByName(String username);

}
