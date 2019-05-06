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

@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {

    long id = 1;
    private static final UserDao USERDAO = UserDao.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String address = req.getParameter("address");
        String email = req.getParameter("email");

        if (USERDAO.CheckUserExist(firstName, lastName)) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("signin.jsp?message=error");
            requestDispatcher.forward(req, resp);
        }
        id = UserDao.getInstance().getIdBylogin(login);
        boolean newUser = USERDAO.addUser(new User(id, firstName, lastName,
                login, pass, address, email));
        if (newUser) {
            req.setAttribute("login", login);
            req.setAttribute("firstName", firstName);
            req.setAttribute("pass", pass);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("afterregistration.jsp");
            requestDispatcher.forward(req, resp);

        } else if (USERDAO.getUserById(id).getFirstName().length() == 0
                || USERDAO.getUserById(id).getLastName().length() == 0
                || USERDAO.getUserById(id).getLogin().length() == 0
                || USERDAO.getUserById(id).getPassword().length() == 0
                || USERDAO.getUserById(id).getAddress().length() == 0
                || USERDAO.getUserById(id).getEmail().length() == 0) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("noninformation.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
