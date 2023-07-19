package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.ArrayList;

import com.google.gson.Gson;

import dto.NewFacilityDTO;
import model.CarRentalFacility;
import services.FacilityService;
import util.Authorization;
import util.GsonSerializer;

public class FacilityController {
	
	private static Gson g = GsonSerializer.makeGson();
	public static FacilityService facilityService = new FacilityService();

	public static void getAll() {
		get("rest/facilities/", (req, res) -> {
			res.type("application/json");
			
			//System.out.println("KONACNO JE PRORADILO AJMOOO");
			return g.toJson(facilityService.getFacilitiesArrayList());
		});
	}
	
	public static void getFacilityById() {
		get("rest/facilities/getById/", (req, res) -> {
			res.type("application/json");
			return g.toJson(facilityService.getFacilityById(req.queryParams("id")));
		});
	}
	
	public static void searchFacilities() {
		get("rest/facilities/search/", (req, res) -> {
			res.type("application/json");
			String filter = req.queryParams("filter");
			
			ArrayList<CarRentalFacility> facilities = facilityService.getFacilitiesArrayList();
			String[] filters = filter.split(",");
			
			for(int i = 0; i < filters.length; ++i)
			{
				if(filters[i].equals(""))
					continue;
				
				facilities = facilityService.getFacilitiesBySearch(filters[i], facilities, i);
			}
			
			return g.toJson(facilities);
		});
	}
	
	public static void newFacility() {
		post("rest/facilities/new/", (req, res) -> {
			res.type("application/json");
			
			String jwt = req.headers("Authorization");
			if(!Authorization.isLoggedIn(UserController.key, jwt))
				return "null";
			
			NewFacilityDTO processDto = g.fromJson(req.body(), NewFacilityDTO.class);
			
			CarRentalFacility facility = new CarRentalFacility(processDto.getName(), true, processDto.getLogoPath(), 0.0, processDto.getWorkingHours(), processDto.getLocation(),processDto.getContent());
			facility = facilityService.newFacility(facility);
			
			if(processDto.getManager() != "") {
                UserController.userService.addFacilityToManager(processDto.getManager(), facility);
        }
			
			return facility;
		});
	}
	
	public static void newBlock() {
		post("rest/facilities/block/", (req, res) -> {
			res.type("application/json");
			System.out.println("KONACNO JE PRORADILO AJMOOO");
			String jwt = req.headers("Authorization");
			//if(!Authorization.isLoggedIn(UserController.key, jwt))
			//	return "null";
			System.out.println("KONACNO JE PRORADILO AJMOOO");
			NewFacilityDTO processDto = g.fromJson(req.body(), NewFacilityDTO.class);
			System.out.println(processDto.getName());
			System.out.println(processDto.isStatus());
			CarRentalFacility facility = new CarRentalFacility(processDto.getName(), true, processDto.getLogoPath(), 0.0, processDto.getWorkingHours(), processDto.getLocation(),processDto.getContent());
			facility.setId("BLOKIRAN");
			facility = facilityService.newFacility(facility);
			if(processDto.getManager() != "") {
                UserController.userService.blockUser(processDto.getManager());
        }
			
			return facility;
		});
	}
	
	
	

}
