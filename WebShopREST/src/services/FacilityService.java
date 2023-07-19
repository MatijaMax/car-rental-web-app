package services;

import java.util.ArrayList;
import storage.VehicleStorage;
import java.util.Collection;
import java.util.StringTokenizer;

import model.CarRentalFacility;
import model.Vehicle;
import storage.FacilityStorage;

public class FacilityService {
	private FacilityStorage facilities = new FacilityStorage();
	private VehicleStorage vehicles = new VehicleStorage();
	
	public Collection<CarRentalFacility> getFacilities() {
		return facilities.getAll();
	}
	public Collection<Vehicle> getVehicles() {
		return vehicles.getAll();
	}
	
	public ArrayList<CarRentalFacility> getFacilitiesArrayList() {
		return facilities.getArray();
	}
	
	public CarRentalFacility getFacilityById(String id) {
		return facilities.getById(id);
	}
	

	
	public CarRentalFacility newFacility(CarRentalFacility facility) {
		FacilityStorage facilitiesX = new FacilityStorage();
		int id = 0;
		for(CarRentalFacility fac: facilitiesX.getArray()) {
			if(Integer.parseInt(fac.getId()) > id) {
				id++;
			}
		}
		
		facility.setId(Integer.toString(++id));
		
		return facilities.addFacility(facility);
	}
	
	public void addContentToFacility(String vehicleId, String facilityId) {
		CarRentalFacility facility = facilities.getById(facilityId);
		
		if(facility.getContent().equals("nothing")) {
			facility.setContent(vehicleId);
		} else {
			StringBuilder str = new StringBuilder();
			str.append(facility.getContent());
			str.append(",");
			str.append(vehicleId);
			
			facility.setContent(str.toString());
		}
		
		facilities.editFacility(facility);
		
	}
	
	private boolean hasMatchingVehicle(CarRentalFacility facility, String filter) {
	    for (Vehicle v : getVehicles()) {
	        String type = v.getVehicleTypeStr().toLowerCase();
	        if (facility.getId().toString().equals(v.getFacility()) && filter.equals(type)) {
	            return true; // Found a matching vehicle, exit the inner loop
	        }
	    }
	    return false; // No matching vehicle found
	}
	
	private boolean hasMatchingFuelVehicle(CarRentalFacility facility, String filter) {
	    for (Vehicle v : getVehicles()) {
	        String type = v.getFuelTypeStr().toLowerCase();
	        if (facility.getId().toString().equals(v.getFacility()) && filter.equals(type)) {
	            return true; // Found a matching vehicle, exit the inner loop
	        }
	    }
	    return false; // No matching vehicle found
	}
	
	public ArrayList<CarRentalFacility> getFacilitiesBySearch(String filter, 
			ArrayList<CarRentalFacility> facilities, int i) {
		ArrayList<CarRentalFacility> filteredFacilities = new ArrayList<CarRentalFacility>();
		
		filter = filter.toLowerCase();
		
		switch(i) {
			case 0:
				for(CarRentalFacility sf: facilities)
				{
					String name = sf.getName().toLowerCase();
					if(filter.equals(name))
						filteredFacilities.add(sf);
				}
				break;
			case 1:
									
				    for (CarRentalFacility sf : facilities) {
				        if (hasMatchingVehicle(sf, filter)) {
				            filteredFacilities.add(sf);
				        }
				    }
					
				
				break;
			case 2:
			    for (CarRentalFacility sf : facilities) {
			        if (hasMatchingFuelVehicle(sf, filter)) {
			            filteredFacilities.add(sf);
			        }
			    }
				break;
			case 3:
				for(CarRentalFacility sf: facilities)
				{
					String location = sf.getLocation().getAddress().split(",")[1].toLowerCase();
					location = location.substring(1);
					if(filter.equals(location))
						filteredFacilities.add(sf);
				}
					
				break;
			case 4:
				for(CarRentalFacility sf: facilities)
				{
					String rating = Double.toString(sf.getAvgRating());
					if(filter.equals(rating))
						filteredFacilities.add(sf);
				}
				break;
		}
		
		return filteredFacilities;
	}
	
	
}
