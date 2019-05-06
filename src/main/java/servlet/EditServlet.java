package servlet;

import dao.UserDao;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(value = "/edit")
public class EditServlet extends HttpServlet {
    private static final UserDao USERDAO = UserDao.getInstance();
    long id = 1;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        req.setAttribute("login", login);
        req.setAttribute("pass", pass);

        User user = USERDAO.getUserByLoginAndPass(login, pass);
        if (USERDAO.CheckUserExist(user.getFirstName(), user.getLastName())) {
            USERDAO.updateUser(login, pass);
            resp.sendRedirect("afteredit.jsp");
//            RequestDispatcher requestDispatcher = req.getRequestDispatcher("afteredit.jsp");
//            requestDispatcher.forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("noninformation.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
