package com.sample;

import org.springframework.web.bind.annotation.RequestMapping;
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
}
