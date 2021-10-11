package com.topcoder.trains.demo.controller;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// import java.util.Date;

import  com.topcoder.trains.demo.exception.ResourceNotFoundException;
import  com.topcoder.trains.demo.exception.InvalidRequestException;

import com.topcoder.trains.demo.repository.TrainRepository;
import com.topcoder.trains.demo.model.Train;

@RestController
@RequestMapping("/api")
public class TrainController {
	@Autowired
	TrainRepository trainRepository;

	@GetMapping("/trains")
	@ExceptionHandler( InvalidRequestException.class )
	public ResponseEntity<List<Train>> getAllTrains(@RequestParam(required = false) String name) {
		List<Train> trains = new ArrayList<Train>();
		try {
			trainRepository.findAll().forEach(trains::add);

			return new ResponseEntity<> (trains, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/trains/{id}")
	public ResponseEntity<Train> getTrain(@PathVariable("id") long id) {
		Train trainData = trainRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("train not found"));

	
		return new ResponseEntity<>(trainData, HttpStatus.OK);
	
	}

}