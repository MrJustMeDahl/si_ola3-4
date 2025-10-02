package org.soft2.handlers;

import io.javalin.http.Context;
import org.soft2.DTO.LocationDTO;
import org.soft2.exceptions.APIException;
import org.soft2.mockDAO.LocationDAO;

import java.util.Set;

public class LocationController {

    public static void getLocations(Context ctx) throws APIException {
        Set<LocationDTO> locations = LocationDAO.getLocations();
        if(locations.isEmpty()) {
            throw new APIException(204, "No locations found");
        }
        ctx.json(locations);
        ctx.status(200);
    }

    public static void getTrailersByLocation(Context ctx) throws APIException {
        int locationId;
        try {
            locationId = Integer.parseInt(ctx.pathParam("locationId"));
        } catch (NumberFormatException e) {
            throw new APIException(400, "Invalid location ID format");
        }
        LocationDTO location = LocationDAO.getLocationById(locationId);
        if (location == null) {
            throw new APIException(404, "Location not found");
        }
        if (location.getTrailers().isEmpty()) {
            throw new APIException(204, "No trailers are currently available at this location");
        }
        location.removeUnavailableTrailers();
        ctx.json(location.getTrailers());
        ctx.status(200);
    }
}
