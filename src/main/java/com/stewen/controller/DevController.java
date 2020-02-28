package com.stewen.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.text.MaskFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stewen.model.DevModel;
import com.stewen.repository.DevRepository;

import antlr.StringUtils;
import net.bytebuddy.implementation.bind.MethodDelegationBinder.Record;

import java.util.List;

import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/scheduledev"})
public class DevController {
	
	private DevRepository devRepository;
	
	DevController(DevRepository devRepository){
		this.devRepository = devRepository;
		
	}
    
	@PostMapping("/create")
	public String create(@RequestBody DevModel devModel){
		DevModel devModell =  new DevModel();
		devModell = devModel;
		this.devRepository.save(devModell);
	//	   this.devRepository.save(devModel);
		return "Dev created";
	}
	
	@GetMapping("/dev")
	public List findAll(){
	   return devRepository.findAll();
	}
	
	@GetMapping(value="/dev/{name}")
	public List getUser(@PathVariable("name") String name) {
		return devRepository.findByname(name);
    }
	
	@PutMapping(value="/{id}")
	public String update(@PathVariable("id") long id, @RequestBody DevModel devModel) {
		
		// mask for date
		Date pegarData = devModel.getBirthday();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formatter.format(pegarData);
		
		// mask for numbers floating
		String numb = devModel.getSalary()+"";
		
		devRepository.findById(id)
		.map(record -> {
			record.setName(devModel.getName());
			record.setAge(devModel.getAge());
			try {
				record.setBirthday(dataFormatada);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			record.setSalary(numb);
			record.setEmail(devModel.getEmail());
		DevModel updated = devRepository.save(record);
		return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
		
		return "Updated dev";
		
	}
	
	@DeleteMapping(path ={"/{id}"})
	public String delete(@PathVariable long id) {
	   devRepository.findById(id)
	           .map(record -> {
	               devRepository.deleteById(id);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.notFound().build());
	   
	   return "Dev deleted";
	}
	
	
	
}
