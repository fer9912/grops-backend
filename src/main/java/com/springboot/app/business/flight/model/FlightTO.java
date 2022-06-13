package com.springboot.app.business.flight.model;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlightTO {
	private String code;
	private String origin;
	private String destination;
	private Date date;
	private String route;
	private String status;
	private String company;
	private String day;
	private String hour;
	private String aircraft;
	private int duration;
	private String flightType;
	private String flightRule;
	private String landingHour;
	private Date landingDate;
	private int peopleEstimate;
	private int fuelConsumption;
	private int lubricantConsumption;
	private String routeCode;
	private List<String> aircrafts;
	private String execDate;
}
