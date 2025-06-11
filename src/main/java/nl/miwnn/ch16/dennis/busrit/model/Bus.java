package nl.miwnn.ch16.dennis.busrit.model;


import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Bus {
    @Id
    @GeneratedValue
    private long busId;

    @Column(unique = true)
    private int lineNumber;

    private String region;
    private int numberOfSeats;
    private String imageUrl;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<Route> routes;

    @ManyToMany
    private Set<Traveler> travelers;

    public int getNumberOfRoutes() {
        return routes.size();
    }

    public int getNumberOfAvailableRoutes() {
        int numberOfAvailableRoutes = 0;

        for (Route route : routes) {
            if (route.getOperating()) {
                numberOfAvailableRoutes++;
            }
        }

        return numberOfAvailableRoutes;
    }

    public String getTravelerNames() {
        return travelers.stream().map(Traveler::getName).sorted().collect(Collectors.joining(", "));
    }

    @Override
    public String toString() {
        return String.format("bus %d region %s- %d no. seats",
                busId, region, numberOfSeats);

    }


    public long getBusId() {
        return busId;
    }

    public void setBusId(long busId) {
        this.busId = busId;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public Set<Traveler> getTravelers() {
        return travelers;
    }

    public void setTravelers(Set<Traveler> travelers) {
        this.travelers = travelers;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
