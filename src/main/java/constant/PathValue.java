package constant;

public class PathValue {
    /**
     *
     */
    public static class USER {
        /**
         *
         */
        public final static String UPDATE_PASSWORD = "/update-password";
        /**
         *
         */
        public final static String UPDATE_INFO = "/update-info";
        /**
         *
         */
        public final static String VACCINATION_INFO = "/vaccination-info";
        /**
         *
         */
        public final static String VIEW_INFO = "/view";
    }

    /**
     *
     */
    public static class GUEST {
        /**
         *
         */
        public final static String LOGIN = "/login";
        /**
         *
         */
        public final static String LOGOUT = "/logout";
        /**
         *
         */
        public final static String CREATE = "/create";
    }

    /**
     *
     */
    public static class ADMIN {
        /**
         *
         */
        public final static String ADD_NEW_VACCINE = "/add";
        /**
         *
         */
        public final static String CREATE_DOCTOR_ACCOUNT = "/create";
    }

    public static class DOCTOR {
        public final static String ADD_NEW_VACCINATION_INFO = "/add";
    }
    public final  static String HOME_PAGE = "homepage";
}
