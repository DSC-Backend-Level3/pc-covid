package dao.implement;

import dao.ResidentDao;
import dto.ResidentDTO;
import utils.DBHelper;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public ResidentDTO getResidentById(String id)
            throws SQLException, NamingException {
        try {
            //1. Connect DB
            con = DBHelper.makeConnection();
            //2. Create SQL Statement
            if (con != null) {
                //3. Create Statement to set SQL
                String sql = "SELECT id, firstName, lastName, phoneNumber, email, healthInsuranceID, gender, DOB, "
                        + "nationality, wardID, houseNumber, roleID, password "
                        + "FROM Resident "
                        + "WHERE id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String firstName = rs.getNString(2);
                    String lastName = rs.getNString(3);
                    String phoneNumber = rs.getString(4);
                    String email = rs.getString(5);
                    String healthInsuranceID = rs.getString(6);
                    String gender = rs.getString(7);
                    Timestamp DOB = rs.getTimestamp(8);
                    String nationality = rs.getString(9);
                    int wardID = rs.getInt(10);
                    String houseNumber = rs.getString(11);
                    int roleID = rs.getInt(12);
                    String password = rs.getString(13);
                    ResidentDTO dto = new ResidentDTO(id, firstName, lastName, phoneNumber, email, healthInsuranceID, gender, DOB,
                            nationality, wardID, houseNumber, roleID, password);
                    return dto;
                }
            }

        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public List<ResidentDTO> getAllResidents()
            throws SQLException, NamingException {
        List<ResidentDTO> list = null;
        try {
            //1. Connect DB
            con = DBHelper.makeConnection();
            //2. Create SQL Statement
            if (con != null) {
                //3. Create Statement to set SQL
                String sql = "SELECT id, firstName, lastName, phoneNumber, email, healthInsuranceID, gender, DOB, " +
                        "nationality, wardID, houseNumber, roleID "
                        + "FROM Resident";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String firstName = rs.getNString(2);
                    String lastName = rs.getNString(3);
                    String phoneNumber = rs.getString(4);
                    String email = rs.getString(5);
                    String healthInsuranceID = rs.getString(6);
                    String gender = rs.getString(7);
                    Timestamp DOB = rs.getTimestamp(8);
                    String nationality = rs.getString(9);
                    int wardID = rs.getInt(12);
                    String houseNumber = rs.getString(13);
                    int roleID = rs.getInt(14);
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(new ResidentDTO(id, firstName, lastName, phoneNumber, email, healthInsuranceID, gender, DOB,
                            nationality, wardID, houseNumber, roleID, null));

                }
            }

        } finally {
            closeConnection();
        }
        return list;
    }

    @Override
    public List<ResidentDTO> getResidentsByRoleId(int roleID) throws SQLException, NamingException {
        List<ResidentDTO> residentList = new ArrayList<>();
        try {
            //1. Connect DB
            con = DBHelper.makeConnection();
            //2. Create SQL Statement
            if (con != null) {
                //3. Create Statement to set SQL
                String sql = "SELECT id, firstName, lastName, phoneNumber, email, healthInsuranceID, gender, DOB, "
                        + "nationality, wardID, houseNumber, password "
                        + "FROM Resident "
                        + "WHERE roleID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, roleID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String firstName = rs.getNString(2);
                    String lastName = rs.getNString(3);
                    String phoneNumber = rs.getString(4);
                    String email = rs.getString(5);
                    String healthInsuranceID = rs.getString(6);
                    String gender = rs.getString(7);
                    Timestamp DOB = rs.getTimestamp(8);
                    String nationality = rs.getString(9);
                    int wardID = rs.getInt(10);
                    String houseNumber = rs.getString(11);
                    String password = rs.getString(12);
                    ResidentDTO dto = new ResidentDTO(id, firstName, lastName, phoneNumber, email, healthInsuranceID, gender, DOB,
                            nationality, wardID, houseNumber, roleID, password);
                    residentList.add(dto);

                }
                return residentList;
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
                String sql = "INSERT INTO Resident(id, firstName, lastName, phoneNumber, email, healthInsuranceID, gender, DOB, " +
                        "nationality, wardID, houseNumber, roleID, password) " +
                        " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, residentDTO.getId());
                stm.setNString(2, residentDTO.getFirstName());
                stm.setNString(3, residentDTO.getLastName());
                stm.setString(4, residentDTO.getPhoneNumber());
                stm.setString(5, residentDTO.getEmail());
                stm.setString(6, residentDTO.getHealthInsuranceID());
                stm.setString(7, residentDTO.getGender());
                stm.setTimestamp(8, residentDTO.getDOB());
                stm.setString(9, residentDTO.getNationality());
                stm.setInt(10, residentDTO.getWardID());
                stm.setString(11, residentDTO.getHouseNumber());
                stm.setInt(12, residentDTO.getRoleID());
                stm.setString(13, residentDTO.getPassword());
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
            System.out.println("connection");
            //1. Connect DB
            con = DBHelper.makeConnection();
            System.out.println(con);
            //2. Create SQL Statement
            if (con != null) {

                //3. Create Statement to set SQL
                String sql = "UPDATE Resident " +
                        "SET firstName = ISNULL(?, firstName) , lastName = ISNULL(?, lastName), phoneNumber = ISNULL(?, phoneNumber), " +
                        "email = ISNULL(?, email), healthInsuranceID = ISNULL(?, healthInsuranceID), gender = ISNULL(?, gender), DOB = ISNULL(?, DOB), " +
                        "nationality = ISNULL(?, nationality), " +
                        "wardID = ISNULL(?, wardID), houseNumber = ISNULL(?, houseNumber), roleID = ISNULL(?, roleID), password = ISNULL(?, password) " +
                        "WHERE id = ?";
                stm = con.prepareStatement(sql);
                stm.setNString(1, residentDTO.getFirstName());
                stm.setNString(2, residentDTO.getLastName());
                stm.setString(3, residentDTO.getPhoneNumber());
                stm.setString(4, residentDTO.getEmail());
                stm.setString(5, residentDTO.getHealthInsuranceID());
                stm.setString(6, residentDTO.getGender());
                stm.setTimestamp(7, residentDTO.getDOB());
                stm.setString(8, residentDTO.getNationality());
                stm.setInt(9, residentDTO.getWardID());
                stm.setString(10, residentDTO.getHouseNumber());
                stm.setInt(11, residentDTO.getRoleID());
                stm.setString(12, residentDTO.getPassword());
                stm.setString(13, residentDTO.getId());
                stm.executeUpdate();
            }
        } finally {
            closeConnection();
        }
    }

    @Override
    public void updateResidentPassword(String id, String password)
            throws SQLException, NamingException {
        try {
            //1. Connect DB
            con = DBHelper.makeConnection();
            //2. Create SQL Statement
            if (con != null) {
                //3. Create Statement to set SQL
                String sql = "UPDATE Resident " +
                        "SET  password = ? " +
                        "WHERE id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, id);
                stm.executeUpdate();
            }
        } finally {
            closeConnection();
        }
    }

    @Override
    public boolean isUpdated(String id)
            throws SQLException, NamingException {
        try {
            //1. Connect DB
            con = DBHelper.makeConnection();
            //2. Create SQL Statement
            if (con != null) {
                //3. Create Statement to set SQL
                String sql = "SELECT id " +
                        "FROM Resident " +
                        "WHERE id = ? AND firstName IS NOT NULL AND lastName IS NOT NULL AND phoneNumber IS NOT NULL AND " +
                        "email IS NOT NULL AND healthInsuranceID IS NOT NULL AND gender IS NOT NULL AND DOB IS NOT NULL AND " +
                        "nationality IS NOT NULL AND " +
                        "wardID IS NOT NULL AND houseNumber IS NOT NULL AND roleID IS NOT NULL AND password IS NOT NULL";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
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

    @Override
    public boolean checkPassword(String id, String password) throws SQLException, NamingException {
        try{
            con = DBHelper.makeConnection();
            if (con != null){
                String sql = "SELECT id " +
                            "FROM Resident " +
                            "WHERE id = ? AND password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if(rs.next()){
                    return true;
                }
            }
        }finally {
            closeConnection();
        }
        return false;
    }

}
