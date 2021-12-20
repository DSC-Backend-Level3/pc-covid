/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class VaccineDTO implements Serializable {

    private int id;
    private String name;
    private String firm;
    private String country;
    private int interval;

    public VaccineDTO() {
    }

    public VaccineDTO(int id, String name, String firm, String country, int interval) {
        this.id = id;
        this.name = name;
        this.firm = firm;
        this.country = country;
        this.interval = interval;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setVaccineName(String name) {
        this.name = name;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

}
