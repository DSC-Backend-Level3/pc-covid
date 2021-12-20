package dao;

import dto.DistrictDTO;
import dto.ProvinceDTO;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DistrictDao {
    /**
     * Get a list of available district based on province ID.
     * @param provinceID a province ID for searching.
     * @return An {@code ArrayList} of {@code DistrictDTO} object from database.
     */
    List<DistrictDTO> getDistrictByProvinceID(int provinceID) throws SQLException, NamingException;
    DistrictDTO getDistrictByID(int id) throws SQLException, NamingException;
}
