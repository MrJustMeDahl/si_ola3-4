package org.soft2.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Trailer {

    int id;
    boolean available = true;

    public Trailer(int id) {
        this.id = id;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getId() {return id;}
    public boolean isAvailable() {return available;}
}
