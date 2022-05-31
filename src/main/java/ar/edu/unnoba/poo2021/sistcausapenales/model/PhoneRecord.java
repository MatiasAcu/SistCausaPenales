package ar.edu.unnoba.poo2021.sistcausapenales.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "phone_records")
public class PhoneRecord extends Information {

    @Column(name = "origin_number", nullable = false)
    @Pattern(regexp = "^\\+(?:[0-9]●?){6,14}[0-9]$", message = "Incorrect Format")
    private String originNumber;

    @Column(name = "destination_number", nullable = false)
    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "Incorrect Format")
    private String destinationNumber;

    @Column(name = "duration", nullable = false)
    private int duration;

    public PhoneRecord() {
    }

    public String getOriginNumber() {
        return originNumber;
    }

    public void setOriginNumber(String originNumber) {
        this.originNumber = originNumber;
    }

    public String getDestinationNumber() {
        return destinationNumber;
    }

    public void setDestinationNumber(String destinationNumber) {
        this.destinationNumber = destinationNumber;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
