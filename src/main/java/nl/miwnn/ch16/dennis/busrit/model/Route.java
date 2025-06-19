package nl.miwnn.ch16.dennis.busrit.model;


import jakarta.persistence.*;

@Entity
public class Route {

    public static final boolean DEFAULT_IS_OPERATING = true;

    @Id @GeneratedValue
    private Long routeId;

    private Boolean operating;

    @ManyToOne
    private Bus bus;

    private String startStation;
    private String endStation;

    public Route(Bus bus) {
        this.bus = bus;
        this.operating = DEFAULT_IS_OPERATING;
    }

    public Route() {
    }

    public void toggleOperating() {
        operating = !operating;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Boolean getOperating() {
        return operating;
    }

    public void setOperating(Boolean operating) {
        this.operating = operating;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }
}
