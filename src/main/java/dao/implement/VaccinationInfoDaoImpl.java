package dao.implement;

import dao.VaccinationInfoDao;
import dto.VaccinationInfoDTO;
import utils.DBHelper;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VaccinationInfoDaoImpl implements VaccinationInfoDao {
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

    @Override
    public List<VaccinationInfoDTO> getAllVaccinationInfo() throws NamingException, SQLException{
        List<VaccinationInfoDTO> list = null;
        try {
            connection = DBHelper.makeConnection();
            if (connection != null) {
                String sql = "SELECT [id], [residentID], [vaccineID], [wardID], [date]"
                        + " FROM [VaccinationInfo]";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String residentID = resultSet.getString("residentID");
                    int vaccineID = resultSet.getInt("vaccineID");
                    int wardID = resultSet.getInt("wardID");
                    Timestamp date = resultSet.getTimestamp("date");

                    VaccinationInfoDTO dto = new VaccinationInfoDTO(id, residentID, vaccineID, wardID, date);

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
    public VaccinationInfoDTO getVaccinationInfoByID(int id) throws NamingException, SQLException{
        VaccinationInfoDTO vaccineInfo = null;
        try {
            connection = DBHelper.makeConnection();
            if (connection != null) {
                String sql = "SELECT [residentID], [vaccineID], [wardID], [date]"
                        + " FROM [VaccinationInfo]"
                        + " WHERE [id] = ?";

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String residentID = resultSet.getString("residentID");
                    int vaccineID = resultSet.getInt("vaccineID");
                    int wardID = resultSet.getInt("ward");
                    Timestamp date = resultSet.getTimestamp("date");

                    vaccineInfo = new VaccinationInfoDTO(id, residentID, vaccineID, wardID, date);
                }
            }
        } finally {
            closeConnection();
        }
        return vaccineInfo;
    }

    @Override
    public List<VaccinationInfoDTO> getVaccinationInfoByIdUser(String residentID) throws NamingException, SQLException {
        List<VaccinationInfoDTO> list = null;
        try {
            connection = DBHelper.makeConnection();
            if (connection != null) {
                String sql = "SELECT [id], [vaccineID], [wardID], [date]"
                        + " FROM [VaccinationInfo]"
                        + " WHERE [residentID] = ?";

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, residentID);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int vaccineID = resultSet.getInt("vaccineID");
                    int wardID = resultSet.getInt("wardID");
                    Timestamp date = resultSet.getTimestamp("date");
                    if(list == null){
                        list = new ArrayList<>();
                    }
                    list.add(new VaccinationInfoDTO(id, residentID, vaccineID, wardID, date));
                }

            }
        } finally {
            closeConnection();
        }
        return list;
    }

    @Override
    public boolean isAvailableFor2ndInjection(int residentID) throws NamingException, SQLException{
        int numOfInjection = 0;
        try {
            connection = DBHelper.makeConnection();
            if (connection != null) {
                String sql = "SELECT COUNT([vaccineID]) as [numOfInjection]"
                        + " FROM [VaccinationInfo]"
                        + " WHERE [residentID] = ?";

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, residentID);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    numOfInjection = resultSet.getInt("numOfInjection");
                }
                return numOfInjection == 2;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
}
