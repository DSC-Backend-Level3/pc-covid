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
public class DistrictDTO implements Serializable {

    private int id; //District's id
    private String name; //the name of the district
    private int provinceID; //Province's id for this district

    public DistrictDTO() {
    }

    public DistrictDTO(int id, String name, int provinceID) {
        this.id = id;
        this.name = name;
        this.provinceID = provinceID;
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

    public void setName(String name) {
        this.name = name;
    }

    public int getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(int provinceID) {
        this.provinceID = provinceID;
    }

}
