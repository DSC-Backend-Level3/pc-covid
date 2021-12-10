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

    private int vaccineID;
    private String vaccineName;
    private String firm;
    private String country;
    private int interval;

    public VaccineDTO() {
    }

    public VaccineDTO(int vaccineID, String vaccineName, String firm, String country, int interval) {
        this.vaccineID = vaccineID;
        this.vaccineName = vaccineName;
        this.firm = firm;
        this.country = country;
        this.interval = interval;
    }

    public int getVaccineID() {
        return vaccineID;
    }

    public void setVaccineID(int vaccineID) {
        this.vaccineID = vaccineID;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
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
