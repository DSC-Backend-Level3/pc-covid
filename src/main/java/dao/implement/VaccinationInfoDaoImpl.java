package dao.implement;

import dao.VaccinationInfoDao;
import dto.VaccinationInfoDTO;
import utils.DBHelper;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;

public class VaccinationInfoDaoImpl implements VaccinationInfoDao {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private ArrayList<VaccinationInfoDTO> list;
    private VaccinationInfoDTO vaccineInfo;

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
    public ArrayList<VaccinationInfoDTO> getAllVaccinationInfo() throws NamingException, SQLException{
        try {
            connection = DBHelper.makeConnection();
            if (connection != null) {
                String sql = "SELECT [id], [idNumber], [vaccineID], [province], [district], [ward], [date]"
                        + " FROM [VaccinationInfo]";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String idNumber = resultSet.getString("idNumber");
                    int vaccineID = resultSet.getInt("vaccineID");
                    int province = resultSet.getInt("province");
                    int district = resultSet.getInt("district");
                    int ward = resultSet.getInt("ward");
                    Timestamp date = resultSet.getTimestamp("date");

                    VaccinationInfoDTO dto = new VaccinationInfoDTO(id, idNumber, vaccineID, province, district, ward, date);

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


    @Override
    public VaccinationInfoDTO getVaccinationInfoByID(int id) throws NamingException, SQLException{
        try {
            connection = DBHelper.makeConnection();
            if (connection != null) {
                String sql = "SELECT [idNumber], [vaccineID], [province], [district], [ward], [date]"
                        + " FROM [VaccinationInfo]"
                        + " WHERE id = ?";

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String idNumber = resultSet.getString("idNumber");
                    int vaccineID = resultSet.getInt("vaccineID");
                    int province = resultSet.getInt("province");
                    int district = resultSet.getInt("district");
                    int ward = resultSet.getInt("ward");
                    Timestamp date = resultSet.getTimestamp("date");

                    vaccineInfo = new VaccinationInfoDTO(id, idNumber, vaccineID, province, district, ward, date);
                }
            }
        } finally {
            closeConnection();
        }
        return vaccineInfo;
    }

    @Override
    public boolean isAvailableFor2ndInjection(int id) throws NamingException, SQLException{
        int count = 0;
        try {
            connection = DBHelper.makeConnection();
            if (connection != null) {
                String sql = "SELECT [vaccineID]"
                        + " FROM [VaccinationInfo]"
                        + " WHERE idNumber = ?";

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    count ++;
                }
                return count == 2;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
}
