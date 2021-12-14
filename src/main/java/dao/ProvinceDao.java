package dao;

import dto.ProvinceDTO;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ProvinceDao {
    /**
     * Get a list of available provinces.
     * @return An {@code ArrayList} of {@code ProvinceDTO} object from database.
     */
    ArrayList<ProvinceDTO> getAllProvinces() throws SQLException, NamingException;
}
