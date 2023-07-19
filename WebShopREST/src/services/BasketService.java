package services;

import java.util.ArrayList;
import storage.VehicleStorage;
import java.util.Collection;
import java.util.StringTokenizer;

import model.Basket;
import model.CarRentalFacility;
import model.Vehicle;
import storage.BasketStorage;
import storage.FacilityStorage;

public class BasketService {
	private VehicleStorage vehicles = new VehicleStorage();
	private BasketStorage basketSt = new BasketStorage();
	
	public Basket getBasket() {
		BasketStorage basketStX = new BasketStorage();
		return basketStX.getBasket();
	}
	
	
	public Basket newBasket(Basket basket) {
		BasketStorage basketX = new BasketStorage();
		
		return basketX.addBasket(basket);
					
	}
	
	public void addContentToBasket(String vehicleId) {
		BasketStorage basketXX = new BasketStorage();
		Basket basketX = basketXX.getBasket();
		
		if(basketX.getContent().equals("nothing")) {
			basketX.setContent(vehicleId);
		} else {
			StringBuilder str = new StringBuilder();
			str.append(basketX.getContent());
			str.append(",");
			str.append(vehicleId);
			
			basketX.setContent(str.toString());
		}
		
		basketSt.editBasket(basketX);		
	}
	
	public void removeContentFromBasket(String vehicleId) {
		BasketStorage basketXX = new BasketStorage();
		Basket basketX = basketXX.getBasket();
		
		
        String string1 = vehicleId;
        String string2 = basketX.getContent();
        String updatedString = basketX.getContent();
        // Split the second string into an array of values
        String[] values = string2.split(",");

        // Check if "4" exists in the array
        boolean found = false;
        int index = -1;
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(string1)) {
                found = true;
                index = i;
                break;
            }
        }

        if (found) {
            // Remove "4" from the array
            String[] updatedValues = new String[values.length - 1];
            int k = 0;
            for (int i = 0; i < values.length; i++) {
                if (i != index) {
                    updatedValues[k] = values[i];
                    k++;
                }
            }

            // Remove the preceding comma if it exists
            if (index > 0 && values[index - 1].endsWith(",")) {
                updatedValues[index - 1] = values[index - 1].substring(0, values[index - 1].length() - 1);
            }

            // Join the updated array into a string
            updatedString = String.join(",", updatedValues);
            System.out.println(updatedString);
        }   
            basketX.setContent(updatedString);
            basketSt.editBasket(basketX);
            
        }
	public void removeAllContentFromBasket(String vehicleId) {
		BasketStorage basketXX = new BasketStorage();
	    Basket basketX = basketXX.getBasket();
	    String string1 = vehicleId;
	    String string2 = basketX.getContent();
	    String updatedString = basketX.getContent();
	    
	    // Split the second string into an array of values
	    String[] values = string2.split(",");

	    // Create a new array to store the updated values
	    ArrayList<String> updatedValues = new ArrayList<>();

	    // Iterate over the values and add only those that don't match the given vehicleId
	    for (String value : values) {
	        if (!value.equals(string1)) {
	            updatedValues.add(value);
	        }
	    }

	    // Join the updated array into a string
	    updatedString = String.join(",", updatedValues);

	    // Update the content of the basket
	    basketX.setContent(updatedString);
	    basketSt.editBasket(basketX);
	}

		
	
}
