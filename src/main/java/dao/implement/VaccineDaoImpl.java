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
import java.util.List;

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
    public List<VaccineDTO> getAllVaccines() throws SQLException{
        List<VaccineDTO> list = null;
        try {
            connection = DBHelper.makeConnection();
            if (connection != null) {
                String sql = "SELECT [id], [name], [firm], [country], [interval]"
                            + " From [Vaccine]";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String firm = resultSet.getString("firm");
                    String country = resultSet.getString("country");
                    int interval = resultSet.getInt("interval");

                    VaccineDTO dto = new VaccineDTO(id, name, firm, country, interval);

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
     * @param id Vaccine ID.
     * @return The vaccine information or null if the vaccine does not exist.
     */
    @Override
    public VaccineDTO getVaccineByID(int id) throws SQLException, NamingException{
        VaccineDTO vaccine = null;
        try {
            connection = DBHelper.makeConnection();
            if (connection != null) {
                String sql = "SELECT [id], [name], [firm], [country], [interval]"
                        + " FROM [Vaccine]"
                        + " WHERE [id] = ?";

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String firm = resultSet.getString("firm");
                    String country = resultSet.getString("country");
                    int interval = resultSet.getInt("interval");
                    vaccine = new VaccineDTO(id, name, firm, country, interval);
                }
            }
        } finally {
            closeConnection();
        }
        return vaccine;
    }

    @Override
    public boolean addNewVaccine(VaccineDTO vaccine) throws SQLException, NamingException {
        try {
            //1. Connect DB
            connection = DBHelper.makeConnection();
            //2. Create SQL Statement
            if (connection != null) {
                //3. Create Statement to set SQL
                String sql = "INSERT INTO [Vaccine]([id], [name], [firm], [country], [interval]) "
                        + " VALUES (?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, vaccine.getId());
                preparedStatement.setString(2, vaccine.getName());
                preparedStatement.setString(3, vaccine.getFirm());
                preparedStatement.setString(3, vaccine.getCountry());
                preparedStatement.setInt(1, vaccine.getInterval());
                int row = preparedStatement.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }
}
