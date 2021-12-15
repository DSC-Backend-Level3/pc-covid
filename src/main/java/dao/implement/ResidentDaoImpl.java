package dao.implement;

import dao.ResidentDao;
import dto.ResidentDTO;
import utils.DBHelper;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;

public class ResidentDaoImpl implements ResidentDao {
    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    @Override
    public ResidentDTO getResidentById(String idNumber)
            throws SQLException, NamingException {
        try {
            //1. Connect DB
            con = DBHelper.makeConnection();
            //2. Create SQL Statement
            if (con != null) {
                //3. Create Statement to set SQL
                String sql = "SELECT idNumber, firstName, lastName, phoneNumber, email, healthInsuranceID, gender, DOB, "
                        + "nationality, province, district, ward, houseNumber, role "
                        + "FROM Resident "
                        + "WHERE idNumber = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, idNumber);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String firstName = rs.getString(2);
                    String lastName = rs.getString(3);
                    String phoneNumber = rs.getString(4);
                    String email = rs.getString(5);
                    String healthInsuranceID = rs.getString(6);
                    String gender = rs.getString(7);
                    Timestamp DOB = rs.getTimestamp(8);
                    String nationality = rs.getString(9);
                    int province = rs.getInt(10);
                    int district = rs.getInt(11);
                    int ward = rs.getInt(12);
                    String houseNumber = rs.getString(13);
                    int role = rs.getInt(14);
                    ResidentDTO dto = new ResidentDTO(idNumber, firstName, lastName, phoneNumber, email, healthInsuranceID, gender, DOB,
                            nationality, province, district, ward, houseNumber, role, null);
                    return dto;
                }
            }

        } finally {
            closeConnection();
        }
        return null;

    }

    @Override
    public ArrayList<ResidentDTO> getAllResidents()
            throws SQLException, NamingException {
        ArrayList<ResidentDTO> list = null;
        try {
            //1. Connect DB
            con = DBHelper.makeConnection();
            //2. Create SQL Statement
            if (con != null) {
                //3. Create Statement to set SQL
                String sql = "SELECT idNumber, firstName, lastName, phoneNumber, email, healthInsuranceID, gender, DOB, " +
                        "nationality, province, district, ward, houseNumber, role "
                        + "FROM Resident";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String idNumber = rs.getString(1);
                    String firstName = rs.getString(2);
                    String lastName = rs.getString(3);
                    String phoneNumber = rs.getString(4);
                    String email = rs.getString(5);
                    String healthInsuranceID = rs.getString(6);
                    String gender = rs.getString(7);
                    Timestamp DOB = rs.getTimestamp(8);
                    String nationality = rs.getString(9);
                    int province = rs.getInt(10);
                    int district = rs.getInt(11);
                    int ward = rs.getInt(12);
                    String houseNumber = rs.getString(13);
                    int role = rs.getInt(14);
                    list.add(new ResidentDTO(idNumber, firstName, lastName, phoneNumber, email, healthInsuranceID, gender, DOB,
                            nationality, province, district, ward, houseNumber, role, null));

                }
                return list;
            }

        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public boolean addNewResident(ResidentDTO residentDTO)
            throws SQLException, NamingException {
        try {
            //1. Connect DB
            con = DBHelper.makeConnection();
            //2. Create SQL Statement
            if (con != null) {
                //3. Create Statement to set SQL
                String sql = "INSERT INTO Resident(idNumber, firstName, lastName, phoneNumber, email, healthInsuranceID, gender, DOB, " +
                        "nationality, province, district, ward, houseNumber, role, password) " +
                        " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, residentDTO.getIdNumber());
                stm.setString(2, residentDTO.getFirstName());
                stm.setString(3, residentDTO.getLastName());
                stm.setString(4, residentDTO.getPhoneNumber());
                stm.setString(5, residentDTO.getEmail());
                stm.setString(6, residentDTO.getHealthInsuranceID());
                stm.setString(7, residentDTO.getGender());
                stm.setTimestamp(8, residentDTO.getDOB());
                stm.setString(9, residentDTO.getNationality());
                stm.setInt(10, residentDTO.getProvince());
                stm.setInt(11, residentDTO.getDistrict());
                stm.setInt(12, residentDTO.getWard());
                stm.setString(13, residentDTO.getHouseNumber());
                stm.setInt(14, residentDTO.getRole());
                stm.setString(15, residentDTO.getPassword());
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    @Override
    public void updateResidentInformation(ResidentDTO residentDTO)
            throws SQLException, NamingException {
        try {
            //1. Connect DB
            con = DBHelper.makeConnection();
            //2. Create SQL Statement
            if (con != null) {
                //3. Create Statement to set SQL
                String sql = "UPDATE Resident " +
                        "SET firstName = ISNULL(?, firstName) , lastName = ISNULL(?, lastName), phoneNumber = ISNULL(?, phoneNumber), " +
                        "email = ISNULL(?, email), healthInsuranceID = ISNULL(?, healthInsuranceID), gender = ISNULL(?, gender), DOB = ISNULL(?, DOB), " +
                        "nationality = ISNULL(?, nationality), province = ISNULL(?, province), district = ISNULL(?, district), " +
                        "ward = ISNULL(?, ward), houseNumber = ISNULL(?, houseNumber), role = ISNULL(?, role), password = ISNULL(?, password) " +
                        "WHERE idNumber = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, residentDTO.getFirstName());
                stm.setString(2, residentDTO.getLastName());
                stm.setString(3, residentDTO.getPhoneNumber());
                stm.setString(4, residentDTO.getEmail());
                stm.setString(5, residentDTO.getHealthInsuranceID());
                stm.setString(6, residentDTO.getGender());
                stm.setTimestamp(7, residentDTO.getDOB());
                stm.setString(8, residentDTO.getNationality());
                stm.setInt(9, residentDTO.getProvince());
                stm.setInt(10, residentDTO.getDistrict());
                stm.setInt(11, residentDTO.getWard());
                stm.setString(12, residentDTO.getHouseNumber());
                stm.setInt(13, residentDTO.getRole());
                stm.setString(14, residentDTO.getPassword());
                stm.setString(15, residentDTO.getIdNumber());
                stm.executeUpdate();
            }
        } finally {
            closeConnection();
        }
    }

    @Override
    public void updateResidentPassword(String idNumber, String password)
            throws SQLException, NamingException {
        try {
            //1. Connect DB
            con = DBHelper.makeConnection();
            //2. Create SQL Statement
            if (con != null) {
                //3. Create Statement to set SQL
                String sql = "UPDATE Resident " +
                        "SET  password = ? " +
                        "WHERE idNumber = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, idNumber);
                stm.executeUpdate();
            }
        } finally {
            closeConnection();
        }
    }

    @Override
    public boolean isUpdated(String idNumber)
            throws SQLException, NamingException {
        try {
            //1. Connect DB
            con = DBHelper.makeConnection();
            //2. Create SQL Statement
            if (con != null) {
                //3. Create Statement to set SQL
                String sql = "SELECT idNumber " +
                        "FROM Resident " +
                        "WHERE idNumber = ? AND firstName IS NOT NULL AND lastName IS NOT NULL AND phoneNumber IS NOT NULL AND " +
                        "email IS NOT NULL AND healthInsuranceID IS NOT NULL AND gender IS NOT NULL AND DOB IS NOT NULL AND " +
                        "nationality IS NOT NULL AND province IS NOT NULL AND district IS NOT NULL AND " +
                        "ward IS NOT NULL AND houseNumber IS NOT NULL AND role IS NOT NULL AND password IS NOT NULL";
                stm = con.prepareStatement(sql);
                stm.setString(1, idNumber);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }
}
