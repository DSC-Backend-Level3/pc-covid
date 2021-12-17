package dao;

import dto.ProvinceDTO;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProvinceDao {
    /**
     * Get a list of available provinces.
     * @return An {@code ArrayList} of {@code ProvinceDTO} object from database.
     */
    List<ProvinceDTO> getAllProvinces() throws SQLException, NamingException;
    ProvinceDTO getProvinceByID(int provinceID) throws SQLException, NamingException;
}
