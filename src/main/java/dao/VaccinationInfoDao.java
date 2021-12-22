package dao;

import dto.VaccinationInfoDTO;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VaccinationInfoDao {

    /**
     * Get all the available vaccination info.
     *
     * @return An {@code ArrayList} of {@code VaccinationInfoDTO} object from database.
     */
    List<VaccinationInfoDTO> getAllVaccinationInfo() throws NamingException, SQLException;

    /**
     * Get the vaccination info by ID.
     * @param id The vaccination info ID for searching.
     * @return {@code VaccinationInfoDTO} object; In case the vaccination info does not exist, return {@code null}.
     */
    VaccinationInfoDTO getVaccinationInfoByID(int id) throws NamingException, SQLException;

    List<VaccinationInfoDTO> getVaccinationInfoByIdUser(String residentID) throws  NamingException, SQLException;
    /**
     * Check if the resident is available for the 2nd injection.
     *
     * @param id The vaccination info ID for searching.
     * @return {@code True} if the resident is available or {@code False} if is not.
     */
    boolean isAvailableFor2ndInjection(int id) throws NamingException, SQLException;
    boolean addNewVaccinationInfo(VaccinationInfoDTO vaccinationInfo) throws SQLException, NamingException;
    VaccinationInfoDTO getTheLatestVaccinationInfoByIdUser(String residentID) throws NamingException, SQLException;
}
