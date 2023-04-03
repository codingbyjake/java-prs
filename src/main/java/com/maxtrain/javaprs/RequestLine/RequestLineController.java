package com.maxtrain.javaprs.RequestLine;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


public class RequestLineController {
	
	@Autowired
	private RequestLineRepository reqlineRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<RequestLine>> getAllRequestLines(){
		Iterable<RequestLine> requestLines = reqlineRepo.findAll();
		return new ResponseEntity<Iterable<RequestLine>>(requestLines, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<RequestLine> getRequestLine(@PathVariable int id){
		Optional<RequestLine> requestLine = reqlineRepo.findById(id);
		if(requestLine.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RequestLine>(requestLine.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<RequestLine> postRequestLine(@RequestBody RequestLine requestLine){
		RequestLine newRequestLine = reqlineRepo.save(requestLine);
		return new ResponseEntity<RequestLine>(newRequestLine, HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity putRequestLine(@PathVariable int id, @RequestBody RequestLine requestLine) {
		if(requestLine.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		reqlineRepo.save(requestLine);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteRequestLine(@PathVariable int id) {
		Optional<RequestLine> requestLine = reqlineRepo.findById(id);
		if(requestLine.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		reqlineRepo.delete(requestLine.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
