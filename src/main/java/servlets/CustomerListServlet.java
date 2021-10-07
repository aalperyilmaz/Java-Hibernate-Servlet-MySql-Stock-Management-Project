package servlets;

import com.google.gson.Gson;
import entities.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.DbUtil;
import utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "customer-list" ,value = "/customer-list")
public class CustomerListServlet extends HttpServlet {
SessionFactory sf= HibernateUtil.getSessionFactory();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     int cu_id= Integer.parseInt(req.getParameter("cash_id")) ;
        Session sesi=sf.openSession();
        List<Customer> cs=sesi.createQuery("from Customer where Customer .cu_id=?1")
                .setParameter(1,cu_id).getResultList();
        Gson gson=new Gson();

        String stJson = gson.toJson(cs);
        resp.setContentType("application/json");
        resp.getWriter().write( stJson );
    }
}
