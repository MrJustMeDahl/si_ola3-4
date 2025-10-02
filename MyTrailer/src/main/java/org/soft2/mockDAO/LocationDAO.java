package org.soft2.mockDAO;

import org.soft2.DTO.LocationDTO;
import org.soft2.entities.Location;
import org.soft2.entities.Trailer;

import java.util.HashSet;
import java.util.Set;

public class LocationDAO {

    private static final Set<Location> locations = new HashSet<>();

    static {
        Location loc1 = new Location(1, "Location 1", "Address 1");
        Location loc2 = new Location(2, "Location 2", "Address 2");
        Location loc3 = new Location(3, "Location 3", "Address 3");

        locations.add(loc1);
        locations.add(loc2);
        locations.add(loc3);

        Trailer t1 = new Trailer(1);
        Trailer t2 = new Trailer(2);
        Trailer t3 = new Trailer(3);
        Trailer t4 = new Trailer(4);
        Trailer t5 = new Trailer(5);
        Trailer t6 = new Trailer(6);
        Trailer t7 = new Trailer(7);
        Trailer t8 = new Trailer(8);
        Trailer t9 = new Trailer(9);

        loc1.addTrailer(t1);
        loc1.addTrailer(t2);
        loc1.addTrailer(t3);
        loc2.addTrailer(t4);
        loc2.addTrailer(t5);
        loc2.addTrailer(t6);
        loc3.addTrailer(t7);
        loc3.addTrailer(t8);
        loc3.addTrailer(t9);

        // Mark some trailers as unavailable for testing purposes
        t2.setAvailable(false);
        t5.setAvailable(false);
        t8.setAvailable(false);
    }

    public static Set<LocationDTO> getLocations() {
        /*
        Returns the set of all locations. Ideally without trailers, as this is just for selection purposes.
         */
        Set<LocationDTO> locationResult = new HashSet<>();
        for (Location loc : locations) {
            locationResult.add(new LocationDTO(loc));
        }
        return locationResult;
    }

    public static LocationDTO getLocationById(int id) {
        /*
        Returns a location by its ID, this time with all trailers.
        Should include availability status of trailers. Meaning a query has to be made to orders to check if a trailer is currently rented out.
         */
        for (Location loc : locations) {
            if (loc.getId() == id) {
                LocationDTO dto = new LocationDTO(loc);
                return dto;
            }
        }
        return null;
    }

}
