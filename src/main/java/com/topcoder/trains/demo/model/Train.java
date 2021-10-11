package com.topcoder.trains.demo.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@Entity
@Table(name = "trains")
public class Train {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "\"distance_between_stop\"")
    @JsonProperty("distance-between-stop")
	private String distanceBetweenStop;
	
	@Column(name = "\"max_speed\"")
    @JsonProperty("max-speed")
	private String maxSpeed;

	@Column(name = "\"sharing_tracks\"")
    @JsonProperty("sharing-tracks")
	private boolean sharingTracks;

	@Column(name = "\"grade_crossing\"")
    @JsonProperty("grade-crossing")
	private boolean gradeCrossing;

	@Column(name = "\"train_frequency\"")
    @JsonProperty("train-frequency")
	private String trainFrequency;

	@Column(name = "\"amenities\"")
    @JsonProperty("amenities")
	private String amenities;

	public Train() {

	}
}