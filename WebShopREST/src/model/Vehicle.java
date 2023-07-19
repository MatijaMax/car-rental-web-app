package model;

import java.time.LocalDate;
import java.util.List;

enum VehicleType {
	CAR("Car"),
	VAN("Van"),
	MOBILEHOME("Mobilehome");
	  
	private final String type;
	
	private VehicleType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}	  
}
enum VehicleKind {
	AUTOMATIC("Automatic"),
	MANUAL("Manual");
	  
	private final String kind;
	
	private VehicleKind(String kind) {
		this.kind = kind;
	}
	
	public String getKind() {
		return kind;
	}	  
}
enum FuelType {
	DIESEL("Diesel"),
	PETROL("Petrol"),
	HYBRID("Hybrid"),
	ELECTRIC("Electric");
	  
	private final String fuel;
	
	private FuelType(String fuel) {
		this.fuel = fuel;
	}
	
	public String getFuel() {
		return fuel;
	}	  
}
enum Status {
	AVAILABLE("Available"),
	RENTED("Rented");
	  
	private final String status;
	
	private Status(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}	  
}


public class Vehicle {
	
	private String id;
	
	private boolean deleted;
	private String brand;
	private String model;
	private double price;
	private VehicleType vehicleType;
	private String facility;
	private VehicleKind vehicleKind;
	private FuelType fuelType;
	private double consumption;
	private int numberOfDoors;
	private int capacity;
	private String description;
	private String photoUrl;
	private Status status;
	private Order orders;
	public Vehicle() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * public List<Order> getOrders() { return orders; }
	 * 
	 * public void setOrders(List<Order> orders) { this.orders = orders; }
	 */
	

	public Vehicle(String id, boolean deleted, String brand, String model, double price, VehicleType vehicleType,
			String facility, VehicleKind vehicleKind, FuelType fuelType, double consumption, int numberOfDoors,
			int capacity, String description, String photoUrl, Status status, Order orders) {
		super();
		this.id = id;
		this.deleted = deleted;
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.vehicleType = vehicleType;
		this.facility = facility;
		this.vehicleKind = vehicleKind;
		this.fuelType = fuelType;
		this.consumption = consumption;
		this.numberOfDoors = numberOfDoors;
		this.capacity = capacity;
		this.description = description;
		this.photoUrl = photoUrl;
		this.status = status;
		this.orders = orders;
	}

	public Order getOrders() {
		return orders;
	}

	public void setOrders(Order orders) {
		this.orders = orders;
	}

	public Vehicle(String brand, String model, String price,String vehicleType, String facility,
			String vehicleKind, String fuelType, String consumption, String numberOfDoors, String capacity,
			String description, String photoUrl, String status, String deleted) {
		super();
		this.deleted=false;
		this.brand = brand;
		this.model = model;
		try {
			this.price = Double.parseDouble(price);
			
		} catch(NumberFormatException e) {
			this.price = 0;
		}
		switch(vehicleType) {
		case "CAR":
			this.vehicleType = VehicleType.CAR;
			break;
		case "VAN":
			this.vehicleType = VehicleType.VAN;
			break;
		case "MOBILEHOME":
			this.vehicleType = VehicleType.MOBILEHOME;
			break;
		}
		this.facility = facility;
		switch(vehicleKind) {
		case "AUTOMATIC":
			this.vehicleKind = VehicleKind.AUTOMATIC;
			break;
		case "MANUAL":
			this.vehicleKind = VehicleKind.MANUAL;
			break;
		}
		switch(fuelType) {
		case "DIESEL":
			this.fuelType = FuelType.DIESEL;
			break;
		case "PETROL":
			this.fuelType = FuelType.PETROL;
			break;
		case "HYBRID":
			this.fuelType = FuelType.HYBRID;
			break;
		case "ELECTRIC":
			this.fuelType = FuelType.ELECTRIC;
			break;
		}
		try {
			this.consumption = Double.parseDouble(consumption);
			
		} catch(NumberFormatException e) {
			this.consumption = 0;
		}
		try {
			this.numberOfDoors = Integer.parseInt(numberOfDoors);
			
		} catch(NumberFormatException e) {
			this.numberOfDoors = 0;
		}
		try {
			this.capacity = Integer.parseInt(capacity);
			
		} catch(NumberFormatException e) {
			this.capacity = 0;
		}
		this.description = description;
		this.photoUrl = photoUrl;
		switch(status) {
		case "AVAILABLE":
			this.status = Status.AVAILABLE;
			break;
		case "RENTED":
			this.status = Status.RENTED;
			break;
		}
		try {
		    this.deleted = Boolean.parseBoolean(deleted);
		} catch (NumberFormatException e) {
		    this.deleted = false;
		}
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted2) {
		this.deleted = deleted2;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String type) {
		switch(type) {
		case "CAR":
			this.vehicleType = VehicleType.CAR;
			break;
		case "VAN":
			this.vehicleType = VehicleType.VAN;
			break;
		case "MOBILEHOME":
			this.vehicleType = VehicleType.MOBILEHOME;
			break;
		}
	}
	public String getVehicleTypeStr() {
		switch(this.vehicleType) {
		case CAR:
			return "CAR";
		case VAN:
			return "VAN";
		case MOBILEHOME:
			return "MOBILEHOME";

		default:
			return "";		}
	} 
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public VehicleKind getVehicleKind() {
		return vehicleKind;
	}
	public void setVehicleKind(VehicleKind vehicleKind) {
		this.vehicleKind = vehicleKind;
	}
	public void setVehicleKind(String kind) {
		switch(kind) {
		case "AUTOMATIC":
			this.vehicleKind = VehicleKind.AUTOMATIC;
			break;
		case "MANUAL":
			this.vehicleKind = VehicleKind.MANUAL;
			break;
		}
	}
	public String getVehicleKindStr() {
		switch(this.vehicleKind) {
		case AUTOMATIC:
			return "AUTOMATIC";
		case MANUAL:
			return "MANUAL";

		default:
			return "";		}
	} 
	public FuelType getFuelType() {
		return fuelType;
	}
	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}
	public void setFuelType(String fuel) {
		switch(fuel) {
		case "DIESEL":
			this.fuelType = FuelType.DIESEL;
			break;
		case "PETROL":
			this.fuelType = FuelType.PETROL;
			break;
		case "HYBRID":
			this.fuelType = FuelType.HYBRID;
			break;
		case "ELECTRIC":
			this.fuelType = FuelType.ELECTRIC;
			break;
		}
	}
	public String getFuelTypeStr() {
		switch(this.fuelType) {
		case DIESEL:
			return "DIESEL";
		case PETROL:
			return "PETROL";
		case HYBRID:
			return "HYBRID";
		case ELECTRIC:
			return "ELECTRIC";

		default:
			return "";		}
	} 
	public double getConsumption() {
		return consumption;
	}
	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}
	public int getNumberOfDoors() {
		return numberOfDoors;
	}
	public void setNumberOfDoors(int numberOfDoors) {
		this.numberOfDoors = numberOfDoors;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(String status) {
		switch(status) {
		case "AVAILABLE":
			this.status = Status.AVAILABLE;
			break;
		case "RENTED":
			this.status = Status.RENTED;
			break;
		}
	}
	public String getStatusStr() {
		switch(this.status) {
		case AVAILABLE:
			return "AVAILABLE";
		case RENTED:
			return "RENTED";

		default:
			return "";		}
	} 
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}
