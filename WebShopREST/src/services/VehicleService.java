package services;

import java.time.LocalDate;
import storage.OrderStorage;
import java.util.ArrayList;
import java.util.StringTokenizer;

import model.CarRentalFacility;
import model.Order;
import model.Vehicle;
import storage.FacilityStorage;
import storage.VehicleStorage;

public class VehicleService {
private VehicleStorage vehicles = new VehicleStorage();
private OrderStorage orders = new OrderStorage();
	
	public Vehicle addContent(Vehicle vehicle) {
		VehicleStorage vehiclesX = new VehicleStorage();
		//add function to training
		int id = 0;
		for(Vehicle tr: vehiclesX.getAll()) {
			if(tr.getBrand().equals(vehicle.getBrand()) && tr.getFacility().equals(vehicle.getFacility())) return null;
			
			if(Integer.parseInt(tr.getId()) > id) {
				id++;
			}
			
		}
		
		vehicle.setId(Integer.toString(++id));
		return vehiclesX.addVehicle(vehicle);
		
	}
	public ArrayList<Vehicle> getVehiclesArrayList() {
		VehicleStorage vehiclesX = new VehicleStorage();
		return vehiclesX.getArray();
	}
	
	public Vehicle getVehicleById(String id) {
		VehicleStorage vehiclesX = new VehicleStorage();
		return vehiclesX.getById(id);
	}
	

	
	/*
	 * public Vehicle cancelTraining(String trainingId) { Training training =
	 * trainings.getById(trainingId); training.setDeleted(true);
	 * 
	 * trainings.addTraining(training);
	 * 
	 * return training; }
	 */
	
	public Vehicle editContent(Vehicle vehicle) {
		VehicleStorage vehiclesX = new VehicleStorage();
		return vehiclesX.addVehicle(vehicle);
	}
	
	/*
	 * public ArrayList<Vehicle> getAllContent(String parse) { ArrayList<Vehicle>
	 * content = new ArrayList<Vehicle>();
	 * 
	 * StringTokenizer st = new StringTokenizer(parse, ",");
	 * 
	 * while(st.hasMoreTokens()) {
	 * content.add(vehicles.getById(st.nextToken().trim())); }
	 * 
	 * 
	 * return content; }
	 */
	public ArrayList<Vehicle> getAllContent(String parse) {
	    ArrayList<Vehicle> content = new ArrayList<Vehicle>();
	    VehicleStorage vehiclesX = new VehicleStorage();

	    StringTokenizer st = new StringTokenizer(parse, ",");

	    while (st.hasMoreTokens()) {
	        String id = st.nextToken().trim();
	        Vehicle vehicle = vehiclesX.getById(id);
	        if (vehicle != null && !vehicle.isDeleted()) {
	            content.add(vehicle);
	        }
	    }

	    return content;
	}
	
	
	public void goAvailable(String parse) {
		
		ArrayList<Vehicle> goAvailable = getAllContent(parse);
		for(Vehicle v:goAvailable) {
			v.setStatus("AVAILABLE");
			System.out.println(v.getStatusStr());
			System.out.println(v.getStatusStr());
			System.out.println(v.getStatusStr());
			editContent(v);
		}
		
	}
	
	public void goRented(String parse) {
		
		ArrayList<Vehicle> goRented = getAllContent(parse);
		for(Vehicle v:goRented) {
			v.setStatus("RENTED");
			editContent(v);
		}
		
		
	}
	
	
	
	public ArrayList<Vehicle> getAllContentMAX() {
		VehicleStorage vehiclesX = new VehicleStorage();
	    return vehiclesX.getArray();
	}
	
	/*
	 * public ArrayList<Training> getByCoach(String coach) { ArrayList<Training>
	 * trainingsC = new ArrayList<Training>();
	 * 
	 * for(Training tr: trainings.getAll()) { if(tr.isDeleted()) continue;
	 * 
	 * if(tr.getCoach().equals(coach)) trainingsC.add(tr); }
	 * 
	 * return trainingsC; }
	 */
	public ArrayList<String> getAllTypes() {
		ArrayList<String> types = new ArrayList<String>();
		types.add("");
		for(Vehicle sf: vehicles.getAll()) {
			if(!types.contains(sf.getVehicleTypeStr())) {
				types.add(sf.getVehicleTypeStr());
			}
		}
		return types;
	}
	
	public ArrayList<String> getAllFuelTypes(){
		ArrayList<String> types = new ArrayList<String>();
		types.add("");
		for(Vehicle sf: vehicles.getAll()) {
			if(!types.contains(sf.getFuelTypeStr())) {
				types.add(sf.getFuelTypeStr());
				
			}
		}
		return types;
		
	}
	
	
	/*
	 * public ArrayList<Vehicle> getAvailableVehicle(LocalDate start, LocalDate
	 * end){ System.out.println(start); System.out.println(end); ArrayList<Vehicle>
	 * availableVehicles = vehicles.getArray(); ArrayList<Vehicle> available = new
	 * ArrayList<Vehicle>(); System.out.println("BEFORE REMOVING"); for(Vehicle
	 * vv:availableVehicles) { System.out.println(vv.getBrand()); } for(Order o :
	 * orders.getAll()) { boolean info = start.compareTo(o.getDate())<=0 &&
	 * end.compareTo(o.getDate())>=0; System.out.println(info); boolean info2 =
	 * o.getDate().compareTo(end)>=0 ||
	 * o.getDate().plusDays(o.getLeaseDuration()).compareTo(end)<=0; if(info ||
	 * info2) { for(Vehicle v : o.getVehicles()) {
	 * System.out.println("ORDER VEHICLES"); System.out.println(v.getBrand());
	 * //if(availableVehicles.contains(v)) { available.add(v);
	 * System.out.println("PROBA"); availableVehicles.remove(v);
	 * System.out.println("AFTER REMOVING"); for(Vehicle vv:availableVehicles) {
	 * System.out.println(vv.getBrand()); } //} } } } return availableVehicles;
	 * 
	 * }
	 */
	
	 public ArrayList<Vehicle> getOrderVehicles(Order o){
		 VehicleStorage vehiclesX = new VehicleStorage();
		 ArrayList<Vehicle> orderVehicles = new ArrayList<Vehicle>();
	        String Ids = o.getVehicles();	        	        
	        String[] values = Ids.split(",");
	        
	        for(Vehicle vehicle:vehiclesX.getArray()) {
	        	for (int i = 0; i < values.length; i++) {
		            if (values[i].equals(vehicle.getId())) {
		                orderVehicles.add(vehicle);
		                		                
		            }
	        	}
	        }
	        return orderVehicles;
	 }	
	public ArrayList<Vehicle> getAvailableVehicle(LocalDate start, LocalDate end) {
		VehicleStorage vehiclesX = new VehicleStorage();
	    ArrayList<Vehicle> availableVehicles = vehiclesX.getArray();
	    ArrayList<Vehicle> available = new ArrayList<Vehicle>();
	    ArrayList<Vehicle> availableFIX = new ArrayList<Vehicle>();
	    for (Order o : orders.getAll()) {
	    	
	        boolean info = start.compareTo(o.getDate()) <= 0 && end.compareTo(o.getDate()) >= 0;

	        if (info) {
	            for (Vehicle v : getOrderVehicles(o)) {
	                available.add(v);
	            }
	        }
	    }
	    

	    

	    ArrayList<Vehicle> vehiclesToRemove = new ArrayList<Vehicle>();
	    for (Vehicle v : availableVehicles) {
	        boolean found = false;
	        for (Vehicle vv : available) {
	            if (v.getStatus()==vv.getStatus()) {
	                found = true;
	                break;
	            }
	        }
	        if (!found) {
	            vehiclesToRemove.add(v);
	        }
	    }
	    
	    
	    
	    for (Vehicle v : vehiclesX.getArray()) {
	    	if(v.getStatusStr().equals("AVAILABLE")) {
	    		availableFIX.add(v);
	    	}
	    }
	    
	    
	    
	    
	    return availableFIX;
	    
	}



	
}
