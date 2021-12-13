package dao;

import dto.DistrictDTO;

import java.util.ArrayList;

public interface DistrictDao {
    /**
     * Get a list of available district based on province ID.
     * @param provinceID a province ID for searching.
     * @return An {@code ArrayList} of {@code DistrictDTO} object from database.
     */
    ArrayList<DistrictDTO> getDistrictByProvinceID(int provinceID);
}
