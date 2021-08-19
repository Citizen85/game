package myproject.game.config;

import javax.servlet.http.HttpSession;

public abstract class BaseController {


    public void handleSession(HttpSession session) {
        if (session.isNew()) {
            session.setAttribute("Param", "Value");
        } else {
            session.getAttribute("Param");
        }
    }
}
