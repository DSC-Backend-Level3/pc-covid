package dao.implement;

import dao.DistrictDao;
import dto.DistrictDTO;
import utils.DBHelper;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DistrictDaoImpl implements DistrictDao {
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
     * Get the list of district by province ID.
     *
     * @param provinceID Province ID.
     * @return The list province.
     */
    @Override
    public ArrayList<DistrictDTO> getDistrictByProvinceID(int provinceID) throws SQLException, NamingException {
        ArrayList<DistrictDTO> list = null;
        try {
            connection = DBHelper.makeConnection();
            if (connection != null) {
                String sql = "SELECT [id], [name], [provinceID]"
                        + " FROM [District]"
                        + " WHERE [provinceID] = ?";

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, provinceID);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String vaccineName = resultSet.getString("vaccineName");
                    String name = resultSet.getString("name");

                    DistrictDTO dto = new DistrictDTO(id, vaccineName, provinceID);

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
}
