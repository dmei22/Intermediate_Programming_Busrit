package nl.miwnn.ch16.dennis.busrit.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Bus {
    @Id @GeneratedValue
    private long busId;

    private int lijnnummer;
    private String regio;
    private int zitplaatsen;

    @Override
    public String toString() {
        return String.format("bus %d regio %s- %d zitplaatsen",
                busId, regio, zitplaatsen);
    }

    public long getBusId() {
        return busId;
    }

    public void setBusId(long busId) {
        this.busId = busId;
    }

    public int getLijnnummer() {
        return lijnnummer;
    }

    public void setLijnnummer(int lijnnummer) {
        this.lijnnummer = lijnnummer;
    }

    public String getRegio() {
        return regio;
    }

    public void setRegio(String regio) {
        this.regio = regio;
    }

    public int getZitplaatsen() {
        return zitplaatsen;
    }

    public void setZitplaatsen(int zitplaatsen) {
        this.zitplaatsen = zitplaatsen;
    }
}
