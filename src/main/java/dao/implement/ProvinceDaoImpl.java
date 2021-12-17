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
import java.util.List;

public class ProvinceDaoImpl implements ProvinceDao {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

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
    public List<ProvinceDTO> getAllProvinces() throws NamingException, SQLException {
        List<ProvinceDTO> list = null;
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

                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    @Override
    public ProvinceDTO getProvinceByID(int provinceID) throws SQLException, NamingException {
        try {
            connection = DBHelper.makeConnection();
            if (connection != null) {
                String sql = "SELECT [id], [name] " +
                        "FROM [Province] " +
                        "WHERE [id] = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, provinceID);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    ProvinceDTO dto = new ProvinceDTO(provinceID, name);
                    return dto;
                }
            }
        } finally {
            closeConnection();
        }
        return null;
    }

}
