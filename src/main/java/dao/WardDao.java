package dao;

import dto.WardDTO;

import java.util.ArrayList;

public interface WardDao {
    /**
     * Get a list of available ward based on districtID.
     * @param districtID a district ID for searching.
     * @return An {@code ArrayList} of {@code WardDTO} from database.
     */
    ArrayList<WardDTO> getWardByDistrictID(int districtID);
}
