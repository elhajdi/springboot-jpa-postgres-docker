package com.topcoder.trains.demo.controller;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import java.util.Optional;
// import java.util.Date;
import java.util.Map;

import  com.topcoder.trains.demo.exception.ResourceNotFoundException;
import com.topcoder.trains.demo.exception.EmptyMessageException;
import  com.topcoder.trains.demo.exception.InvalidRequestException;

import com.topcoder.trains.demo.repository.TrainRepository;
import com.topcoder.trains.demo.model.Train;

@RestController
@RequestMapping("/api/trains")
public class TrainController {
	@Autowired
	TrainRepository trainRepository;

	@GetMapping("/")
	@ExceptionHandler( InvalidRequestException.class )
	public ResponseEntity<List<Train>> getAllTrains(@RequestParam(required = false) String amenities) {
		List<Train> trains = new ArrayList<Train>();
		try {
			if(amenities != null) {
				trainRepository.findByAmenitiesContaining(amenities).forEach(trains::add);
				if(trains.isEmpty()) {
					throw new EmptyMessageException("train not found");
				}
			} else {
				trainRepository.findAll().forEach(trains::add);
			}

			return new ResponseEntity<> (trains, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<Train> getTrain(@PathVariable("id") long id) {
		Train trainData = trainRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("train not found"));
	
		return new ResponseEntity<>(trainData, HttpStatus.OK);
	
	}
	/**
	 * getTrainBySharingTracks
	 * @return ResponseEntity
	 */
	@GetMapping("/sharing-tracks")
	public ResponseEntity<List<Train>> getTrainBySharingTracks() {
		List<Train> trains = trainRepository.findBySharingTracks(true); 
				
		return new ResponseEntity<>(trains, HttpStatus.OK);
				
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deleteTrainById(@PathVariable("id") long id) {
		try {
			trainRepository.deleteById(id);
			Map<String, String> resp = new HashMap<>();
			resp.put("message", "train removed successfully");
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch(Exception e) {
			throw new ResourceNotFoundException("train not found");
		}
	}

}