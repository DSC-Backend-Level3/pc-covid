package dao;

import dto.ResidentDTO;

import java.util.ArrayList;

public interface ResidentDao {
    /**
     * Get a resident object by its ID number.
     * @param idNumber Resident ID number for searching.
     * @return  {@code ResidentDTO} object - if the ID number exist in database;
     *          In case the ID number does not exist, return {@code null}.
     */
    ResidentDTO getResidentById(String idNumber);

    /**
     * Get a list of available residents.
     * @return An {@code ArrayList} of {@code ResidentDTO} object from database.
     */
    ArrayList<ResidentDTO> getAllResidents();

    /**
     * Add a new resident to the database.
     * @param residentDTO {@code ResidentDTO} object for insertion.
     * @return {@code True} - if the insertion is successful;
     *         {@code False} - if the insertion is fail.
     */
    boolean addNewResident(ResidentDTO residentDTO);

    /**
     * Update the resident information.
     * @param residentDTO {@code ResidentDTO} object for updating information.
     */
    void updateResidentInformation(ResidentDTO residentDTO);

    /**
     * Update the resident password
     * @param idNumber Resident ID number for searching.
     * @param password New password {@code String} for updating.
     */
    void updateResidentPassword(String idNumber, String password);

    /**
     * Check if the resident information is updated or not.
     * @param idNumber
     * @return {@code True} - if the resident information is updated;
     *         Otherwise, return {@code False}.
     */
    boolean isUpdated(String idNumber);
}
