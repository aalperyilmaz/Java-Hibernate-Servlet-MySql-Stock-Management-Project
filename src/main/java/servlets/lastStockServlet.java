package servlets;

import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "lastStockServlet",value = "/last-stock")
public class lastStockServlet extends HttpServlet {
    SessionFactory sf= HibernateUtil.getSessionFactory();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        Session sesi = sf.openSession();

        List<Object[]> ord = sesi.createNativeQuery("SELECT product.pid,product.ptitle,product.pcode,product.buyprice\n" +
                "FROM product  ORDER BY product.pid DESC LIMIT 0,5")

                .getResultList();
        sesi.close();

        String stJson = gson.toJson(ord);
        resp.setContentType("application/json");
        resp.getWriter().write( stJson );
    }
}
