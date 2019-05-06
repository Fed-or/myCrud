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

@WebServlet(value = "/delete")
public class DeleteServlet extends HttpServlet {
    private static final UserDao USERDAO = UserDao.getInstance();
    long id = 1;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        User user = USERDAO.getUserByLoginAndPass(login, pass);
        id = USERDAO.getIdByName(user.getFirstName(), user.getLastName());
        if (id != 0) {
            USERDAO.deleteUserById(id);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
