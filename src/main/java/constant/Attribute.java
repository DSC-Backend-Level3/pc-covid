package constant;

public class Attribute {
    /**
     *
     */
    public final static String PATH = "path";

    public static class USER {
        /**
         *
         */
        public final static String USER = "user";
        /**
         * User ID number attribute
         */
        public final static String USER_ID = "idNumber";
        /**
         * User password attribute
         */
        public final static String USER_PASSWORD = "password";
        /**
         *
         */
        public final static String FIRST_NAME = "firstName";
        /**
         *
         */
        public final static String LAST_NAME = "lastName";
        /**
         * User confirm password
         */
        public final static String CONFIRM_PASSWORD = "confirmPassword";
        /**
         * User role attribute
         */
        public final static String ROLE = "role";
    }

    public static class ERROR{

        /**
         * Error message for displaying attribute
         */
        public final static String ERROR_MESSAGE = "errorMessage";

        /**
         *
         */
        public final static String MISSING_PARAMETER = "missingParameterError";

        /**
         *
         */
        public final static String OBJECT_NOT_FOUND = "objectNotFoundError";
    }

    public static class ERROR_MESSAGE{
        /**
         *
         */
        public final static String NOT_SUPPORTED_ACTION = "This action is not supported or exist!";

        /**
         *
         * @param objectName
         * @return
         */
        public static String OBJECT_NOT_FOUND (String objectName){
            return String.format("%s does not exist",objectName);
        }
    }

}
