package com.karyawan.penlaian.dir_controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.karyawan.penlaian.dir_models.UsersModel;
import com.karyawan.penlaian.dir_services.ScoresService;
import com.karyawan.penlaian.dir_services.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private ScoresService scoreService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Index() {

        return "users/index";
    }

    @RequestMapping(value = "/keluar", method = RequestMethod.GET)
    public String logout(Model sendDataToPublic, HttpSession sessi, HttpServletResponse httpResponse) {

        try {
            if (sessi.getAttribute("id_session") != null) {
                sessi.removeAttribute("id_session");
            }

            httpResponse.sendRedirect("/");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
            sendDataToPublic.addAttribute("msg", e);
        }

        return "/";
    }

    @RequestMapping(value = "/masuk", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String login(Model sendDataToPublic, HttpSession sessi, HttpServletResponse httpResponse,
            @RequestParam Map<String, String> body) {

        if (body.get("passwordTxt") != "") {

            try {
                var idUser = "";
                var nameUser = "";
                var flagUser = "";

                List<UsersModel> loginUserData = usersService.serviceUserPassword(body.get("passwordTxt"));
                int countData = loginUserData.size();

                for (UsersModel udata : loginUserData) {
                    idUser = udata.getId().toString();
                    nameUser = udata.getNameUser();
                    flagUser = udata.getFlag();
                }

                sendDataToPublic.addAttribute("name_user_nya", nameUser);

                System.out.println("Datanta ADA " + countData);
                System.out.println("ID USER SESSION DARI DB " + idUser);
                System.out.println("NAMA USER SESSION DARI DB " + nameUser);

                if (countData > 0) {
                    sessi.setAttribute("id_session", idUser);
                    sessi.setAttribute("name_session", nameUser);
                    sessi.setAttribute("flag_session", flagUser);

                    httpResponse.sendRedirect("/dashboard");
                    return null;
                } else {
                    sendDataToPublic.addAttribute("msg", "Password salah atau tidak ditemukan");
                }
            } catch (Exception e) {
                System.out.println(e);
                sendDataToPublic.addAttribute("msg", e);
            }
        } else {
            sendDataToPublic.addAttribute("msg", "Password tidak boleh kosong");
        }

        return "users/index";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(Model sendDataToPublic, HttpSession sessi, HttpServletResponse httpResponse) {

        try {
            if (sessi.getAttribute("id_session") != null) {
                List<UsersModel> loadUsersData = usersService
                        .serviceListUsersExcept(sessi.getAttribute("id_session").toString());

                sendDataToPublic.addAttribute("list_user", loadUsersData);
                sendDataToPublic.addAttribute("user_name_login", sessi.getAttribute("name_session"));
                sendDataToPublic.addAttribute("user_flag", sessi.getAttribute("flag_session"));

                return "users/dashboard";
            } else {
                httpResponse.sendRedirect("/");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
            sendDataToPublic.addAttribute("msg", e);
            return "users/dashboard";
        }
    }

    @RequestMapping(value = "/submitnilai", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String savenilai(Model sendDataToPublic, HttpSession sessi, HttpServletResponse httpResponse,
            @RequestParam(value = "id[]") List<String> idArr,
            @RequestParam(value = "kepemimpinan[]") List<String> kepemimpinanArr,
            @RequestParam(value = "kerjatim[]") List<String> kerjaTimArr,
            @RequestParam(value = "kehadiran[]") List<String> kehadiranArr,
            @RequestParam(value = "kemampuan[]") List<String> kemampuanArr,
            @RequestParam(value = "kontribusi[]") List<String> kontribusiArr) {

        try {
            if (sessi.getAttribute("id_session") != null) {

                for (int i = 0; i < idArr.size(); i++) {

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date tanggal = new Date();

                    scoreService.savePenilaian(sessi.getAttribute("id_session").toString(), idArr.get(i),
                            kepemimpinanArr.get(i), kerjaTimArr.get(i), kehadiranArr.get(i), kemampuanArr.get(i),
                            kontribusiArr.get(i), simpleDateFormat.format(tanggal));

                    System.out.println("ID SESSI ====" + sessi.getAttribute("id_session").toString());
                    System.out.println("ID USER LIST ====" + idArr.get(i));
                }

                usersService.updateFlaging("ya", sessi.getAttribute("id_session").toString());

                // return "users/dashboard";
                httpResponse.sendRedirect("/dashboard");
                return null;
            } else {
                httpResponse.sendRedirect("/");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
            sendDataToPublic.addAttribute("msg", e);
            return "users/dashboard";
        }
    }
}
