package com.stewen.repository;

import com.stewen.model.DevModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DevRepository extends JpaRepository<DevModel, Long> {
	
	List<DevModel> findByname(String name);
	
//	@Query("SELECT name, age, to_char(birthday, 'DD/MM/YYYY'), salary, email FROM DevModel")
//	List findAllDev();
	
}
