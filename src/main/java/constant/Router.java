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
        public static final String ADMIN_HOME = "adminHome.jsp";
        public static final String VIEW_DOCTOR_ACCOUNT_CONTROLLER = "ViewDoctorAccountsController";
        public static final String VIEW_VACCINE_CONTROLLER = "ViewVaccineController";
    }

    //common path
    public static class COMMON{
        public final static String CREATE_ACCOUNT_CONTROLLER = "CreateAccountController";
        public final static String LOGIN_CONTROLLER = "LoginController";
        public final static String LOGOUT_CONTROLLER = "LogoutController";
    }

    //doctor path
    public static class DOCTOR{
        public static final String VIEW_VACCINATION_CONTROLLER = "ViewVaccinationInfoController";
        public final static String ADD_VACCINATION_INFO_CONTROLLER = "AddVaccinationInfoController";
        public static final String LOCATION_CONTROLLER = "LocationController";
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
        public final static String REGISTER_PAGE = "register.jsp";
        public final static String ERROR_PAGE = "error.jsp";
        //public final static String HOME_PAGE = "homepage";
        public  final  static  String VIEW_VACCINATION_INFO = "viewVaccinationInfo.jsp";
        public final static String VIEW_USER_PROFILE = "viewProfile.jsp";
        public  final static String UPDATE_USER_PROFILE = "updateProfile.jsp";
        public  final static String UPDATE_PASSWORD_SUCCESS = "changePassword.jsp";
        public static final String VACCINATE_FORM = "addVaccine.html";
        public  final static String VACCINATE_LIST_PAGE = "vaccineList.jsp";
        public static final String VACCINATION_INFO_FORM = "addVaccinationInfo.jsp";
        public  final static String VACCINATION_INFO_LIST_PAGE = "vaccinationInfoList.jsp";
        public static final String DOCTOR_ACCOUNT_FORM = "addDoctorAccount.jsp";
        public  final static String DOCTOR_ACCOUNT_PAGE = "doctorAccount.jsp";
        public static final String VACCINATION_INFO_INVALID_FORM = "addVaccinationInfoInvalid.jsp";
    }



}
