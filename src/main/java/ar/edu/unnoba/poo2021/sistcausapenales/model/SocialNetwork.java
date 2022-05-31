package ar.edu.unnoba.poo2021.sistcausapenales.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "social_networks")
public class SocialNetwork extends Information {

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "profile", nullable = false)
    private String profile;

    public SocialNetwork() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
