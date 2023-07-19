package com.sample;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.*;
import model.webUser.*;
import dbUtils.*;
import view.WebUserView;

@RestController
public class SessionController {

  @RequestMapping(value = "/session/invalidate", produces = "application/json")
    public String invalidateController(HttpServletRequest request) {
        HttpSession session = request.getSession();

        StringData sd = new StringData(); // all fields now set to ""

        try {
            session.invalidate();
            sd.errorMsg = "Session has been invalidated";
        } catch (Exception e) {
            System.out.println("session/invalidate controller error: " + e.getMessage());
            sd.errorMsg += ". " + e.getMessage();
        }

        return Json.toJson(sd);
    }

    @RequestMapping(value = "/session/read", produces = "application/json")
    public String readController(HttpServletRequest request) {

        HttpSession session = request.getSession();
        StringData sd = new StringData();

        try {
            sd = (StringData) session.getAttribute("loggedOnUser");
            if (sd != null) {
                sd.errorMsg = "Above data was read from the session";
            } else {
                sd = new StringData();
                sd.errorMsg = "Sorry but there is no logged in user";
            }
        } catch (Exception e) {
            System.out.println("session/read controller error: " + e.getMessage());
            sd.errorMsg += ". " + e.getMessage();
        }
        return Json.toJson(sd);
    }


    // sd.webUserId = Format.fmtInteger(results.getObject("web_user_id"));
    //             sd.userEmail = Format.fmtString(results.getObject("user_email"));
    //             sd.userPassword = Format.fmtString(results.getObject("user_password"));
    //             sd.userImage = Format.fmtString(results.getObject("image"));
    //             sd.birthday = Format.fmtDate(results.getObject("birthday"));
    //             sd.membershipFee = Format.fmtDollar(results.getObject("membership_fee"));
    //             sd.userRoleId = Format.fmtInteger(results.getObject("web_user.user_role_id"));
    //             sd.userRoleType = Format.fmtString(results.getObject("user_role_type"));
    //             sdl.add(sd);


    @RequestMapping(value = "/session/loginSession", params = {"userEmail", "userPass" }, produces = "application/json")
    public String loginSession(HttpServletRequest request, @RequestParam("userEmail") String userEmail, @RequestParam("userPass") String userPass) {
      HttpSession session = request.getSession();
        StringData sd = new StringData();

        if ((userEmail == null) || (userPass == null)) {
          sd.errorMsg = "Cannot search for user: 'email' and 'password' must be supplied";
        } else {
          DbConn dbc = new DbConn();
          sd.errorMsg = dbc.getErr();
          
          if (sd.errorMsg.length() == 0) {
              System.out.println("*** Ready to call findUser");
              sd = DbMods.findUser(dbc, userEmail, userPass);
              
              if ((sd != null) && (sd.webUserId != "")) {
                  session.setAttribute("loggedOnUser", sd); // write object to JSP session object
                  sd.errorMsg = "Above data was written to the session";
              }
              else{
                  sd = new StringData();
                  sd.errorMsg = "Credentials not found, invalidating session"; 
                  session.invalidate();
                  
              }
          }
          dbc.close();
      }

        return Json.toJson(sd);
    }
}
