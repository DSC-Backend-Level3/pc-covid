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
    private String idNumber; //resident's id
    private int vaccineID; //vaccine's id
    private int province; //Province's id
    private int district; //District's id
    private int ward; //Ward's id
    private Timestamp date; //date of injection

    public VaccinationInfoDTO() {
    }

    public VaccinationInfoDTO(int id, String idNumber, int vaccineID, int province, int district, int ward, Timestamp date) {
        this.id = id;
        this.idNumber = idNumber;
        this.vaccineID = vaccineID;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getVaccineID() {
        return vaccineID;
    }

    public void setVaccineID(int vaccineID) {
        this.vaccineID = vaccineID;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public int getWard() {
        return ward;
    }

    public void setWard(int ward) {
        this.ward = ward;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}
