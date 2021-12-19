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
public class ResidentDTO implements Serializable {

    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String healthInsuranceID;
    private String gender;
    private Timestamp DOB;
    private String nationality;
    private int wardID;
    private String houseNumber;
    private Integer roleID;
    private String password;

    public ResidentDTO() {
    }

    public ResidentDTO(String id, String firstName, String lastName, String phoneNumber, String email, String healthInsuranceID, String gender, Timestamp DOB, String nationality, int wardID, String houseNumber, Integer roleID, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.healthInsuranceID = healthInsuranceID;
        this.gender = gender;
        this.DOB = DOB;
        this.nationality = nationality;
        this.wardID = wardID;
        this.houseNumber = houseNumber;
        this.roleID = roleID;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHealthInsuranceID() {
        return healthInsuranceID;
    }

    public void setHealthInsuranceID(String healthInsuranceID) {
        this.healthInsuranceID = healthInsuranceID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Timestamp getDOB() {
        return DOB;
    }

    public void setDOB(Timestamp DOB) {
        this.DOB = DOB;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getWardID() {
        return wardID;
    }

    public void setWardID(int wardID) {
        this.wardID = wardID;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer role) {
        this.roleID = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
