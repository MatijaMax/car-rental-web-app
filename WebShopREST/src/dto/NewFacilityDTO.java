package dto;

import model.Location;

public class NewFacilityDTO {
	
	private String name;
	private boolean status;
	private Location location;
	private String logoPath;
	private String workingHours;
	private String content;
	
	private String manager;
	
	public NewFacilityDTO(String name, boolean status, Location location, String logoPath,
			String workingHours, String manager,String content) {
		super();
		this.name = name;
		this.status = status;
		this.location = location;
		this.logoPath = logoPath;
		this.workingHours = workingHours;
		this.manager = manager;
		this.content=content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
	
}