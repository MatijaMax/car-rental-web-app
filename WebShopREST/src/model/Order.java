package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import enums.OrderStatus;

public class Order {
	private String id;
	private String vehicles;
	private String facility;
	private LocalDate date;
	private LocalTime time;
	private int leaseDuration;
	private double price;
	private String username;
	private String name;
	private String surname;
	private OrderStatus status;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVehicles() {
		return vehicles;
	}
	public void setVehicles(String vehicles) {
		this.vehicles = vehicles;
	}
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public int getLeaseDuration() {
		return leaseDuration;
	}
	public void setLeaseDuration(int leaseDuration) {
		this.leaseDuration = leaseDuration;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public Order( String vehicles, String facility, LocalDate date, LocalTime time, int leaseDuration,
			double price, String username, String name, String surname, OrderStatus status) {
		super();
		this.vehicles = vehicles;
		this.facility = facility;
		this.date = date;
		this.time = time;
		this.leaseDuration = leaseDuration;
		this.price = price;
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.status = status;
	}
	public Order(String id, String vehicles, String facility, LocalDate date, LocalTime time, int leaseDuration,
			double price, String username, String name, String surname, OrderStatus status) {
		super();
		this.id = id;
		this.vehicles = vehicles;
		this.facility = facility;
		this.date = date;
		this.time = time;
		this.leaseDuration = leaseDuration;
		this.price = price;
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.status = status;
	}
	

	
}
