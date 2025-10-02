package org.soft2.DTO;

import org.soft2.entities.Location;
import org.soft2.entities.Trailer;

import java.util.Set;

public class LocationDTO {

    int id;
    String name;
    String address;
    Set<Trailer> trailers;

    public LocationDTO(Location location) {
        this.id = location.getId();
        this.name = location.getName();
        this.address = location.getAddress();
        this.trailers = location.getTrailers();
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public String getAddress() {return address;}
    public Set<Trailer> getTrailers() {return trailers;}

    public void removeUnavailableTrailers() {
        trailers.removeIf(trailer -> !trailer.isAvailable());
    }

}
