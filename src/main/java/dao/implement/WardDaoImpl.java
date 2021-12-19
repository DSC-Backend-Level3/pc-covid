package dao.implement;

import dao.WardDao;
import dto.DistrictDTO;
import dto.WardDTO;
import utils.DBHelper;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WardDaoImpl implements WardDao {
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

    @Override
    public ArrayList<WardDTO> getWardByDistrictID(int districtIDValue) throws SQLException, NamingException {
        ArrayList<WardDTO> list = null;
        try {
            connection = DBHelper.makeConnection();
            if (connection != null) {
                String sql = "SELECT [id], [name], [districtID]"
                        + " FROM [Ward]"
                        + " WHERE [districtID] = ?";

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, districtIDValue);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");

                    WardDTO dto = new WardDTO(id, name, districtIDValue);

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
    public WardDTO getWardByID(int id) throws SQLException, NamingException {
        try{
            connection = DBHelper.makeConnection();
            if(connection!=null){
                String sql = "SELECT [id], [name], [districtID] " +
                        "FROM [Ward] " +
                        "WHERE [id] = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    String name = resultSet.getString("name");
                    int districtID = resultSet.getInt("districtID");
                    WardDTO dto = new WardDTO(id, name, districtID);
                    return dto;
                }
            }
        }finally {
            closeConnection();
        }
        return null;
    }
}
