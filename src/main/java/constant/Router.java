package constant;

public class Router {
    //dispatcher path
    public final static String GUEST_DISPATCHER = "GuestDispatcher";
    public final static String ADMIN_DISPATCHER = "AdminDispatcher";
    public final static String DOCTOR_DISPATCHER = "DoctorDispatcher";
    public final static String USER_DISPATCHER = "UserDispatcher";

    //admin path
    public final static String ADD_VACCINE_CONTROLLER = "AddVaccineController";
    public final static String CREATE_DOCTOR_ACCOUNT_CONTROLLER = "CreateDoctorAccountController";

    //common path
    public final static String CREATE_ACCOUNT_CONTROLLER = "CreateAccountController";
    public final static String LOGIN_CONTROLLER = "LoginController";
    public final static String LOGOUT_CONTROLLER = "LogoutController";

    //doctor path
    public final static String ADD_VACCINATION_INFO_CONTROLLER = "AddVaccinationInfoController";

    //user path
    public final static String CHANGE_PASSWORD_CONTROLLER = "ChangePasswordController";
    public final static String UPDATE_PROFILE_CONTROLLER = "UpdateProfileController";
    public final static String VIEW_PROFILE_CONTROLLER = "ViewProfileController";
    public final static String USER_VACCINATION_INFO_CONTROLLER  = "UserVaccinationInfoController";

    //helper path
    public final static String DISTRICT_CONTROLLER = "DistrictController";
    public final static String PROVINCE_CONTROLLER = "ProvinceController";
    public final static String WARD_CONTROLLER = "WardController";

    public final static String LOGIN_PAGE = "login.jsp";
    public final static String ERROR_PAGE = "error.jsp";

}
