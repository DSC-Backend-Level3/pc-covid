package utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

public class Helper {

    /**
     * Ensure that access only from authorized users
     *
     * @param request servlet request
     * @param response servlet response
     * @param minRole minimum user's role to be passed
     * @param maxRole maximum user's role to be passed
     * @param page move to this page if user can not be passed
     * @return false if the access is illegal
     * @throws java.lang.Exception
     */
    public static boolean protectedRouter(HttpServletRequest request, HttpServletResponse response, int minRole,
            int maxRole, String page) throws Exception {

        if (!correctRole(request, minRole, maxRole)) {
            //the access is illegal
            request.setAttribute("errorMessage", "Action is not allow because of your role");
            request.getRequestDispatcher(page).forward(request, response);
            return false;
        }
        return true;
    }

    /**
     * Reformat string that is too long
     *
     * @param str input string
     * @param maxLength
     * @return return string with first maxLength characters + "..." if the
     * string is too long or itself if not
     */
    public static String truncateContent(String str, int maxLength) {
        if (str.length() > maxLength) {
            return str.substring(0, maxLength) + "...";
        }
        return str;
    }

    /**
     * Check that user's role is valid or invalid
     *
     * @param request servlet request
     * @param minRole minimum user's role to be passed
     * @param maxRole maximum user's role to be passed
     * @return true if user role between min and max role range
     */
    public static boolean correctRole(HttpServletRequest request, int minRole, int maxRole) {
        HttpSession session = request.getSession(false);
        Integer roleR = (Integer) session.getAttribute("role");
        return (roleR != null) && (roleR >= minRole) && (roleR <= maxRole);
    }

    /**
     * Convert date in String type into date in Date type (yyyy-MM-dd)
     *
     * @param date
     * @return date in Date type
     */
    public static Date convertStringToDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Convert date in String type into date in Date type (yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @return date in Date type
     */
    public static Date convertStringToDateTime(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * get date in Date type and convert it into date in String type
     * @param date
     * @return convert date string
     */
    public static String convertDateTimeToString(Date date){
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date);
    }

    /**
     * get date in Date type and convert it into a date in String type to store
     * in SQL database
     *
     * @param date
     * @return converted date string
     */
    public static String convertDateTimeToSQLString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * get date in Date type and convert it into a date in String type
     *
     * @param date
     * @return converted date string
     */
    public static String convertDateToString(Date date) {
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

    /**
     * get date in Date type and convert it into a date in String type to store
     * in SQL database
     *
     * @param date
     * @return converted date string
     */
    public static String convertDateToSQLString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * Get today date in Date type (yyyy-MM-dd)
     *
     * @return today date
     */
    public static Date getCurrentDate() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return convertStringToDate(formatter.format(new Date(System.currentTimeMillis())));
    }

    /**
     * Get current date time in Date type (yyyy-MM-dd HH:mm:ss)
     *
     * @return current date time
     */
    public static Date getTodayTime() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return convertStringToDateTime(formatter.format(new Date(System.currentTimeMillis())));
    }

    public static String hashString(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return myHash;
    }
    public  static Timestamp convertDate(String date) throws DateTimeParseException {
        LocalDateTime parsedDate = LocalDate.parse(date, ISO_LOCAL_DATE).atStartOfDay();
        Timestamp result = Timestamp.valueOf(parsedDate);
        return result;
    }
}
