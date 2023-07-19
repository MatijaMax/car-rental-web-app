package storage;

import java.util.Collection;
import java.util.Iterator;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import model.CarRentalFacility;
import java.util.HashMap;

public class FacilityStorage
{
    private HashMap<String, CarRentalFacility> allFacilities;
    private ArrayList<CarRentalFacility> allfacs;
    private File file;
    
    public FacilityStorage() {
        this("resources\\data");
    }
    
    public FacilityStorage(final String path) {
        this.allFacilities = new HashMap<String, CarRentalFacility>();
        this.allfacs = new ArrayList<CarRentalFacility>();
        BufferedReader in = null;
        try {
            this.file = new File(String.valueOf(path) + "/facilities.txt");
            System.out.println(this.file.getCanonicalPath());
            in = new BufferedReader(new FileReader(this.file));
            this.readAllFacilities(in);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (Exception ex) {}
            }
        }
        if (in != null) {
            try {
                in.close();
            }
            catch (Exception ex2) {}
        }
    }
    
    private void readAllFacilities(final BufferedReader in) {
        String name = "";
        String logoPath = "";
        String workingHours = "";
        String address = "";
        String id = "";
        String content = "";
        boolean status = false;
        double avgRating = 0.0;
        double longitude = 0.0;
        double latitude = 0.0;
        try {
            final int i = 0;
            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (!line.equals("")) {
                    if (line.indexOf("#") == 0) {
                        continue;
                    }
                    final StringTokenizer st = new StringTokenizer(line, ";");
                    while (st.hasMoreTokens()) {
                        id = st.nextToken().trim();
                        name = st.nextToken().trim();
                        status = Boolean.parseBoolean(st.nextToken().trim());
                        longitude = Double.parseDouble(st.nextToken().trim());
                        latitude = Double.parseDouble(st.nextToken().trim());
                        address = st.nextToken().trim();
                        logoPath = st.nextToken().trim();
                        avgRating = Double.parseDouble(st.nextToken().trim());
                        workingHours = st.nextToken().trim();
                        content = st.nextToken().trim();
                    }
                    final CarRentalFacility fac = new CarRentalFacility(name, status, logoPath, avgRating, workingHours, longitude, latitude, address, content);
                    fac.setId(id);
                    this.allFacilities.put(fac.getId(), fac);
                    this.allfacs.add(fac);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void save() {
        try {
            final PrintWriter out = new PrintWriter(new FileWriter(this.file), true);
            for (final CarRentalFacility facility : this.allFacilities.values()) {
                final String str = this.makeLine(facility);
                out.println(str);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private String makeLine(final CarRentalFacility facility) {
        final StringBuilder str = new StringBuilder();
        str.append(facility.getId());
        str.append(";");
        str.append(facility.getName());
        str.append(";");
        str.append(facility.isStatus());
        str.append(";");
        str.append(facility.getLocation().getLongitude());
        str.append(";");
        str.append(facility.getLocation().getLatitude());
        str.append(";");
        str.append(facility.getLocation().getAddress());
        str.append(";");
        str.append(facility.getLogoPath());
        str.append(";");
        str.append(facility.getAvgRating());
        str.append(";");
        str.append(facility.getWorkingHours());
        str.append(";");
        str.append(facility.getContent());
        return str.toString();
    }
    
    public Collection<CarRentalFacility> getAll() {
        return this.allFacilities.values();
    }
    
    public HashMap<String, CarRentalFacility> getHashMap() {
        return this.allFacilities;
    }
    
    public ArrayList<CarRentalFacility> getArray() {
        return this.allfacs;
    }
    
    public CarRentalFacility getById(final String id) {
        return this.allFacilities.get(id);
    }
    
    public CarRentalFacility addFacility(final CarRentalFacility facility) {
        if (facility.getContent().equals("")) {
            facility.setContent("0");
        }
        this.allFacilities.put(facility.getId(), facility);
        this.allfacs.add(facility);
        this.save();
        return facility;
    }
    
    public CarRentalFacility editFacility(final CarRentalFacility facility) {
        this.allFacilities.put(facility.getId(), facility);
        for (final CarRentalFacility sf : this.allfacs) {
            if (sf.getId().equals(facility.getId())) {
                this.allfacs.remove(sf);
                this.allfacs.add(facility);
                break;
            }
        }
        this.save();
        return facility;
    }
}
