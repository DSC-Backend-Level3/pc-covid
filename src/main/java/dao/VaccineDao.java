package dao;

import dto.VaccineDTO;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface VaccineDao {

    /**
     * Get all the available vaccine list.
     * @return An {@code ArrayList} of {@code VaccineDTO} object from database.
     */
    public ArrayList<VaccineDTO> getAllVaccines() throws SQLException;

    /**
     * Get the vaccine by vaccine ID.
     * @param vaccineID Vaccine ID for searching.
     * @return {@code VaccineDTO} object; In case the vaccine does not exist, return {@code null}.
     */
    public VaccineDTO getVaccineByID(int vaccineID) throws SQLException, NamingException;

}
