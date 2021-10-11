package com.topcoder.trains.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.topcoder.trains.demo.model.Train;

public interface TrainRepository  extends JpaRepository<Train, Long>{
	List<Train> findByName(String name);

}