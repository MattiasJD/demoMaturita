package com.example.demomaturita;

import java.math.BigDecimal;

public class Transaction {
    private String FROM;
    private String TO;
    private Double MONEY;

    public Transaction(String FROM, String TO, Double MONEY) {
        this.FROM = FROM;
        this.TO = TO;
        this.MONEY = MONEY;
    }

    public String getFROM() {
        return FROM;
    }

    public void setFROM(String FROM) {
        this.FROM = FROM;
    }

    public String getTO() {
        return TO;
    }

    public void setTO(String TO) {
        this.TO = TO;
    }

    public Double getMONEY() {
        return MONEY;
    }

    public void setMONEY(Double MONEY) {
        this.MONEY = MONEY;
    }
}
