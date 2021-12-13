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
public class WardDTO implements Serializable {

    private int id; //ward's id
    private String name; //the name of the ward
    private int districtID; //District's id for this ward

    public WardDTO() {
    }

    public WardDTO(int id, String name, int districtID) {
        this.id = id;
        this.name = name;
        this.districtID = districtID;
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

    public int getDistrictID() {
        return districtID;
    }

    public void setDistrictID(int districtID) {
        this.districtID = districtID;
    }

}
