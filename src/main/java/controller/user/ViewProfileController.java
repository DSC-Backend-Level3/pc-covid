package controller.user;

import constant.Attribute;
import dao.implement.DistrictDaoImpl;
import dao.implement.ProvinceDaoImpl;
import dao.implement.ResidentDaoImpl;
import dao.implement.WardDaoImpl;
import dto.DistrictDTO;
import dto.ProvinceDTO;
import dto.ResidentDTO;
import dto.WardDTO;
import utils.GetParam;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static constant.Router.*;

@WebServlet(name = "ViewProfileController", value = "/ViewProfileController")
public class ViewProfileController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String button = request.getParameter("btAction");
        String url = ERROR_PAGE;
        try {
            if (session != null) {
                String id = (String) session.getAttribute(Attribute.USER.USER_ID);
                if (id!=null) {
                    ResidentDaoImpl dao = new ResidentDaoImpl();
                    ResidentDTO dto = dao.getResidentById(id);

                    WardDaoImpl wardDao = new WardDaoImpl();
                    WardDTO ward = wardDao.getWardByID(dto.getWardID());

                    DistrictDaoImpl districtDao = new DistrictDaoImpl();
                    DistrictDTO district = districtDao.getDistrictByID(ward.getDistrictID());

                    ProvinceDaoImpl provinceDao = new ProvinceDaoImpl();
                    ProvinceDTO province = provinceDao.getProvinceByID(district.getProvinceID());
                    List<ProvinceDTO> listrProvince = provinceDao.getAllProvinces();

                    request.setAttribute("PROFILE_PAGE", dto);
                    request.setAttribute("PROFILE_PROVINCE", province);
                    request.setAttribute("PROFILE_DISTRICT", district);
                    request.setAttribute("PROFILE_WARD", ward);
                    request.setAttribute("PROVINCE_LIST", listrProvince);
                    if (button.equals("View Profile")) {
                        url = VIEW_USER_PROFILE;
                    } else if (button.equals("Update Profile")) {
                        url = UPDATE_USER_PROFILE;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
    }
}
