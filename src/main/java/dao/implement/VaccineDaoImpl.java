package dao.implement;

import dao.VaccineDao;
import dto.VaccineDTO;
import utils.DBHelper;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VaccineDaoImpl implements VaccineDao {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void closeConnection() throws SQLException{
        if (resultSet != null) {
            resultSet.close();
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }


    /**
     * Get all the available vaccine list.
     *
     * @return Vaccine list.
     */
    @Override
    public ArrayList<VaccineDTO> getAllVaccines() throws SQLException{
        ArrayList<VaccineDTO> list = null;
        try {
            connection = DBHelper.makeConnection();
            if (connection != null) {
                String sql = "SELECT [vaccineID], [vaccineName], [firm], [country], [interval]"
                            + " From [Vaccine]";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("vaccineID");
                    String vaccineName = resultSet.getString("vaccineName");
                    String firm = resultSet.getString("firm");
                    String country = resultSet.getString("country");
                    int interval = resultSet.getInt("interval");

                    VaccineDTO dto = new VaccineDTO(id, vaccineName, firm, country, interval);

                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(dto);
                }
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    /**
     * Get the vaccine by vaccine ID.
     *
     * @param vaccineID Vaccine ID.
     * @return The vaccine information or null if the vaccine does not exist.
     */
    @Override
    public VaccineDTO getVaccineByID(int vaccineID) throws SQLException, NamingException{
        VaccineDTO vaccine = null;
        try {
            connection = DBHelper.makeConnection();
            if (connection != null) {
                String sql = "SELECT [vaccineID], [vaccineName], [firm], [country], [interval]"
                        + " FROM [Vaccine]"
                        + " WHERE vaccineID = ?";

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, vaccineID);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String vaccineName = resultSet.getString("vaccineName");
                    String firm = resultSet.getString("firm");
                    String country = resultSet.getString("country");
                    int interval = resultSet.getInt("interval");
                    vaccine = new VaccineDTO(vaccineID, vaccineName, firm, country, interval);
                }
            }
        } finally {
            closeConnection();
        }
        return vaccine;
    }
}
