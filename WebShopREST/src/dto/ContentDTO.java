package dto;



public class ContentDTO {
	private String brand;
	private String model;
	private String price;
	private String vehicleType;
	private String facilityId;
	private String vehicleKind;
	private String fuelType;
	private String consumption;
	private String id;
	private String numberOfDoors;
	private String capacity;
	private String description;
	private String photoUrl;
	private String status;
	private String deleted;
	public ContentDTO(String brand, String model, String price, String vehicleType, String facility, String vehicleKind,
			String fuelType, String consumption, String numberOfDoors, String capacity, String description, String photoUrl,
			String status, String deleted, String id) {
		super();
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.vehicleType = vehicleType;
		this.facilityId = facility;
		this.vehicleKind = vehicleKind;
		this.fuelType = fuelType;
		this.consumption = consumption;
		this.numberOfDoors = numberOfDoors;
		this.capacity = capacity;
		this.description = description;
		this.photoUrl = photoUrl;
		this.status = status;
		this.deleted = deleted;
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(String facility) {
		this.facilityId = facility;
	}
	public String getVehicleKind() {
		return vehicleKind;
	}
	public void setVehicleKind(String vehicleKind) {
		this.vehicleKind = vehicleKind;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public String getConsumption() {
		return consumption;
	}
	public void setConsumption(String consumption) {
		this.consumption = consumption;
	}
	public String getNumberOfDoors() {
		return numberOfDoors;
	}
	public void setNumberOfDoors(String numberOfDoors) {
		this.numberOfDoors = numberOfDoors;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
