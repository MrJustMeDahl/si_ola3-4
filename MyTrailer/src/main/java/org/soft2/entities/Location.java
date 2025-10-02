package org.soft2.entities;

import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Location {

    int id;
    String name;
    String address;
    Set<Trailer> trailers;

    public Location(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.trailers = new HashSet<>();
    }

    public Set<Trailer> addTrailer(Trailer trailer) {
        this.trailers.add(trailer);
        return this.trailers;
    }

}
