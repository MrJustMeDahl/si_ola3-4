package org.soft2.handlers;

import io.javalin.http.Context;
import org.soft2.entities.Location;
import org.soft2.exceptions.APIException;
import org.soft2.mockDAO.LocationDAO;

import java.util.Set;

public class LocationController {

    public static void getLocations(Context ctx) throws APIException {
        Set<Location> locations = LocationDAO.getLocations();
        if(locations.isEmpty()) {
            throw new APIException(204, "No locations found");
        }
        ctx.json(locations);
        ctx.status(200);
    }

}
