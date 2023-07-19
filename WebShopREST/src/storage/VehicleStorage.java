package storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

import model.CarRentalFacility;
import model.Vehicle;

public class VehicleStorage {
	private HashMap<String, Vehicle> allContent = new HashMap<String, Vehicle>();
	private ArrayList<Vehicle> allCont = new ArrayList<Vehicle>();
	private File file;
	
	public VehicleStorage() {
		this("resources\\data");
	}
	
	private VehicleStorage(String path) {
		BufferedReader in = null;
		try {
			file = new File(path + "/vehicles.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			readAllContent(in);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (Exception e){}
			}
		}
	}
	 
	private void readAllContent(BufferedReader in) {
		String line, brand = "", model = "", price = "", vehicleType = "", facility = "", vehicleKind = "", id = "", fuelType = ""
				, consumption = "", numberOfDoors = "", capacity = "", description = "", photoUrl = "", status = ""; 
		String deleted = "false";
		StringTokenizer st;
		
		try {
			while((line = in.readLine()) != null) {
				line = line.trim();
				if(line.equals("") || line.indexOf("#") == 0) 
					continue;
				st = new StringTokenizer(line, ";");
				while(st.hasMoreTokens()) {
					id = st.nextToken().trim();
					brand = st.nextToken().trim();
					model = st.nextToken().trim();
					price = st.nextToken().trim();
					vehicleType = st.nextToken().trim();
					facility = st.nextToken().trim();
					vehicleKind = st.nextToken().trim();
					fuelType = st.nextToken().trim();
					consumption = st.nextToken().trim();
					numberOfDoors = st.nextToken().trim();
					capacity = st.nextToken().trim();
					description = st.nextToken().trim();
					photoUrl = st.nextToken().trim();
					status = st.nextToken().trim();
					deleted = st.nextToken();
				}
				
				Vehicle v = new Vehicle(brand,model,price,vehicleType,facility,vehicleKind,fuelType,consumption,numberOfDoors,capacity,description, 
						photoUrl,status,deleted);
				v.setId(id);
				
				allContent.put(v.getId(), v);
				allCont.add(v);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void save() {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(file), true);
			
			for(Vehicle vehicle: allContent.values()) {
				String str = makeLine(vehicle);
				out.println(str);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String makeLine(Vehicle vehicle) {
		StringBuilder str = new StringBuilder();
		str.append(vehicle.getId());
		str.append(";");
		str.append(vehicle.getBrand());
		str.append(";");
		str.append(vehicle.getModel());
		str.append(";");
		str.append(vehicle.getPrice());
		str.append(";");
		str.append(vehicle.getVehicleType());
		str.append(";");
		str.append(vehicle.getFacility());
		str.append(";");
		str.append(vehicle.getVehicleKind());
		str.append(";");
		str.append(vehicle.getFuelType());
		str.append(";");
		str.append(vehicle.getConsumption());
		str.append(";");
		str.append(vehicle.getNumberOfDoors());
		str.append(";");
		str.append(vehicle.getCapacity());
		str.append(";");
		str.append(vehicle.getDescription());
		str.append(";");
		str.append(vehicle.getPhotoUrl());
		str.append(";");
		str.append(vehicle.getStatus());
		str.append(";");
		str.append(vehicle.isDeleted());
		

		return str.toString();
	}
	
	/*
	 * public Collection<Vehicle> getAll() { return allContent.values(); }
	 */
	public Collection<Vehicle> getAll() {
        List<Vehicle> nonDeletedVehicles = new ArrayList<>();

        for (Vehicle vehicle : allContent.values()) {
            if (!vehicle.isDeleted()) {
                nonDeletedVehicles.add(vehicle);
            }
        }

        return nonDeletedVehicles;
    }
	
	public ArrayList<Vehicle> getArray() {
		return allCont;
	}
	
	public Vehicle addVehicle(Vehicle v) {
		allContent.put(v.getId(), v);
		allCont.add(v);
		save();
		return v;
	}
	
	public Vehicle getById(String id) {
		return allContent.get(id);
	}
	
}
