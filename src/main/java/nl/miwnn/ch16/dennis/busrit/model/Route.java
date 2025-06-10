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

    public Route(Bus bus) {
        this.bus = bus;
        this.operating = DEFAULT_IS_OPERATING;
    }

    public Route() {
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
}
