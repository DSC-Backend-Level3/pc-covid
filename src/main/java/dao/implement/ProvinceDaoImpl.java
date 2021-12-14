package dao.implement;

import dao.ProvinceDao;
import dto.ProvinceDTO;
import utils.DBHelper;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProvinceDaoImpl implements ProvinceDao {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private ArrayList<ProvinceDTO> list;

    public void closeConnection() throws SQLException {
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
     * Get a list of available provinces.
     *
     * @return An {@code ArrayList} of {@code ProvinceDTO} object from database.
     */
    @Override
    public ArrayList<ProvinceDTO> getAllProvinces() throws NamingException, SQLException {

        try {
            connection = DBHelper.makeConnection();
            if (connection != null) {
                String sql = "SELECT [id], [name]"
                        + " FROM [Province]";

                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");

                    ProvinceDTO dto = new ProvinceDTO(id, name);

                    if (this.list == null) {
                        this.list = new ArrayList<>();
                    }
                    this.list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
