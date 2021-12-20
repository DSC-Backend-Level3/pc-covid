package constant;

public class Router {
    //dispatcher path
    public static class DISPATCHER{
        public final static String REQUEST_DISPATCHER = "RequestDispatcher";
        public final static String GUEST_DISPATCHER = "GuestDispatcher";
        public final static String ADMIN_DISPATCHER = "AdminDispatcher";
        public final static String DOCTOR_DISPATCHER = "DoctorDispatcher";
        public final static String USER_DISPATCHER = "UserDispatcher";
    }

    //admin path
    public static class ADMIN{
        public final static String ADD_VACCINE_CONTROLLER = "AddVaccineController";
        public final static String CREATE_DOCTOR_ACCOUNT_CONTROLLER = "CreateDoctorAccountController";
    }

    //common path
    public static class COMMON{
        public final static String CREATE_ACCOUNT_CONTROLLER = "CreateAccountController";
        public final static String LOGIN_CONTROLLER = "LoginController";
        public final static String LOGOUT_CONTROLLER = "LogoutController";
    }

    //doctor path
    public static class DOCTOR{

        public final static String ADD_VACCINATION_INFO_CONTROLLER = "AddVaccinationInfoController";
    }

    //user path
    public static class USER{

        public final static String CHANGE_PASSWORD_CONTROLLER = "ChangePasswordController";
        public final static String UPDATE_PROFILE_CONTROLLER = "UpdateProfileController";
        public final static String VIEW_PROFILE_CONTROLLER = "ViewProfileController";
        public final static String USER_VACCINATION_INFO_CONTROLLER = "UserVaccinationInfoController";
    }

    //helper path
    public static class HELPER{

        public final static String DISTRICT_CONTROLLER = "DistrictController";
        public final static String PROVINCE_CONTROLLER = "ProvinceController";
        public final static String WARD_CONTROLLER = "WardController";
    }

    public static class PAGE{

        public final static String LOGIN_PAGE = "login.jsp";
        public final static String ERROR_PAGE = "error.jsp";
        //public final static String HOME_PAGE = "homepage";
        public  final  static  String VIEW_VACCINATION_INFO = "viewVaccinationInfo.jsp";
        public final static String VIEW_USER_PROFILE = "viewProfile.jsp";
        public  final static String UPDATE_USER_PROFILE = "updateProfile.jsp";
        public  final static String UPDATE_PASSWORD_SUCCESS = "changePassword.jsp";
    }



}
