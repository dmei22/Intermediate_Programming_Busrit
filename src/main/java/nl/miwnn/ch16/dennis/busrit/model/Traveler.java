package nl.miwnn.ch16.dennis.busrit.model;


import jakarta.persistence.*;

import java.awt.print.Book;
import java.util.Set;

@Entity
public class Traveler {

    @Id @GeneratedValue
    private Long travelerId;

//    @ManyToOne
//    private Bus bus;

    private String name;

    public Long getTravelerId() {
        return travelerId;
    }

    public void setTravelerId(Long travelerId) {
        this.travelerId = travelerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
