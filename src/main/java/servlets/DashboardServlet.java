package servlets;

import com.google.gson.Gson;
import entities.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.DbUtil;
import utils.HibernateUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "dashboardServlet",value = {"/dashboard-servlet"})
public class DashboardServlet extends HttpServlet {
   SessionFactory sf= HibernateUtil.getSessionFactory();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        Session sesi = sf.openSession();

        List<Object[]> ord = sesi.createNativeQuery("SELECT ord.or_id,ord.fis_no,c.cu_name,c.cu_surname,p.ptax,ord.total\n" +
                "FROM  or_order as ord \n" +
                "INNER JOIN customer as c \n" +
                "ON c.cu_id=ord.customer_cu_id INNER JOIN product as p on ord.product_pid=p.pid\n" +
                "ORDER BY ord.or_id DESC LIMIT 0,5")

                .getResultList();
        sesi.close();

        String stJson = gson.toJson(ord);
        resp.setContentType("application/json");
        resp.getWriter().write( stJson );


    }
}
