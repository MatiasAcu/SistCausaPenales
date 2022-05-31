package ar.edu.unnoba.poo2021.sistcausapenales.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bank_movements")
public class BankMovement extends Information {

    @Column(name = "account", nullable = false)
    private String account;

    public BankMovement() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
