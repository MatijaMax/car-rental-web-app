package model;

import java.time.LocalTime;
import java.util.List;

public class CarRentalFacility {
	
	private boolean deleted;
	
	
	private String id;
	private String name;
	private List<Vehicle> vehicles;
	private boolean status;
	private Location location;
	private String logoPath;
	private double avgRating;
	private String workingHours;
	private String content;

	public CarRentalFacility(String name,  boolean status, String logoPath, double avgRating,
			String workingHours, double longitude, double latitude, String address, String content) {
		super();
		this.id = "";
		this.name = name;
		this.status = status;
		this.logoPath = logoPath;
		this.avgRating = avgRating;
		this.workingHours = workingHours;
		this.location = new Location(longitude, latitude, address);
		this.content=content;
	}
	
	public CarRentalFacility(String name, boolean status, String logoPath, double avgRating,
			String workingHours, Location location, String content) {
		super();
		this.id = "";
		this.name = name;
		this.status = status;
		this.logoPath = logoPath;
		this.avgRating = avgRating;
		this.workingHours = workingHours;
		this.location = location;
		this.content=content;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}	
	
	
	
	
	
}
