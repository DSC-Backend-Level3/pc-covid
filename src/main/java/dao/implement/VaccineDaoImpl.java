package dao.implement;

import dao.VaccineDao;
import dto.VaccineDTO;

import java.util.ArrayList;

public class VaccineDaoImpl implements VaccineDao {
    /**
     * Get all the available vaccine list.
     *
     * @return Vaccine list.
     */
    @Override
    public ArrayList<VaccineDTO> getAllVaccines() {
        return null;
    }

    /**
     * Get the vaccine by vaccine ID.
     *
     * @param vaccineID Vaccine ID.
     * @return The vaccine information or null if the vaccine does not exist.
     */
    @Override
    public VaccineDTO getVaccineByID(String vaccineID) {
        return null;
    }
}
