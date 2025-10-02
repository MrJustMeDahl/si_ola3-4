package org.soft2.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Trailer {

    int id;

    public Trailer(int id) {
        this.id = id;
    }

}
