/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author DELL
 */
public class VaccinationInfoDTO implements Serializable {

    private int id; //Vaccination information id
    private String residentID; //resident's id
    private int vaccineID; //vaccine's id
    private int wardID; //Ward's id
    private Timestamp date; //date of injection

    public VaccinationInfoDTO() {
    }

    public VaccinationInfoDTO(int id, String residentID, int vaccineID,int wardID, Timestamp date) {
        this.id = id;
        this.residentID = residentID;
        this.vaccineID = vaccineID;
        this.wardID = wardID;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResidentID() {
        return residentID;
    }

    public void setResidentID(String residentID) {
        this.residentID = residentID;
    }

    public int getVaccineID() {
        return vaccineID;
    }

    public void setVaccineID(int vaccineID) {
        this.vaccineID = vaccineID;
    }

    public int getWardID() {
        return wardID;
    }

    public void setWardID(int ward) {
        this.wardID = wardID;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}
