package ar.edu.unnoba.poo2021.sistcausapenales.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name = "causes")
public class Cause {

    @Id
    @Column(name = "number")
    @Pattern(regexp = "^PP-\\d{2}-\\d{2}-\\d{6}-\\d{2}/\\d{2}$", message = "Incorrect Format")
    private String number;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "victim", nullable = false)
    @NotBlank(message = "Victim name cant be blank")
    private String victim;

    @Column(name = "victimizer", nullable = false)
    @NotBlank(message = "Victimizer name cant be blank")
    private String victimizer;

    @Column(name = "description", nullable = false)
    @NotBlank(message = "Description cant be blank")
    private String description;

    public Cause() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getVictim() {
        return victim;
    }

    public void setVictim(String victim) {
        this.victim = victim;
    }

    public String getVictimizer() {
        return victimizer;
    }

    public void setVictimizer(String victimizer) {
        this.victimizer = victimizer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
