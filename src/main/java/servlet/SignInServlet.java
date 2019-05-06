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
import java.io.PrintWriter;

@WebServlet(value = "/signin")
public class SignInServlet extends HttpServlet {

    private static final UserDao USERDAO = UserDao.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        String login = req.getParameter("login");
        String password = req.getParameter("pass");

        User user = USERDAO.getUserByLoginAndPass(login, password);
        if (user != null) {
            out.print("Привет " + user.getFirstName());
        } else {
            out.println("Такого пользователя в базе нет");
            req.setAttribute("allUsers", USERDAO.getAllUsers());

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("signin.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
