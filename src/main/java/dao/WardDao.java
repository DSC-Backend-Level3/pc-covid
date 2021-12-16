package dao;

import dto.WardDTO;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface WardDao {
    /**
     * Get a list of available ward based on districtID.
     * @param districtID a district ID for searching.
     * @return An {@code ArrayList} of {@code WardDTO} from database.
     */
    ArrayList<WardDTO> getWardByDistrictID(int districtID) throws SQLException, NamingException;
    WardDTO getWardByID(int id) throws SQLException, NamingException;
}
