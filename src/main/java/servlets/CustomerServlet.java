package servlets;

import com.google.gson.Gson;
import entities.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "customerServlet", value = { "/customer-post", "/customer-delete", "/customer-get" })
public class CustomerServlet extends HttpServlet {

    SessionFactory sf = HibernateUtil.getSessionFactory();

    // /customer-insert
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int cid = 0;
        Session sesi = sf.openSession();
        Transaction tr = sesi.beginTransaction();
        try {
            String obj = req.getParameter("obj");
            Gson gson = new Gson();
            Customer customer = gson.fromJson(obj, Customer.class);
            sesi.saveOrUpdate(customer);
            tr.commit();
            sesi.close();
            cid = 1;
        }catch ( Exception ex) {
            System.err.println("Save OR Update Error : " + ex);
        }finally {
            sesi.close();
        }

        resp.setContentType("application/json");
        resp.getWriter().write( "" +cid );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();
        Session sesi = sf.openSession();
        List<Customer> ls = sesi.createQuery("from Customer").getResultList();
        sesi.close();

        String stJson = gson.toJson(ls);
        resp.setContentType("application/json");
        resp.getWriter().write( stJson );
    }

    // /customer-remove
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int return_id = 0;
        Session sesi = sf.openSession();
        Transaction tr = sesi.beginTransaction();
        try {
            int cu_id = Integer.parseInt( req.getParameter("cu_id") );
            Customer customer = sesi.load(Customer.class, cu_id);
            sesi.delete(customer);
            tr.commit();
            return_id = customer.getCu_id();
        }catch (Exception ex) {
            System.err.println("Delete Error : " + ex);
        }finally {
            sesi.close();
        }

        resp.setContentType("application/json");
        resp.getWriter().write( ""+return_id );
    }


}
