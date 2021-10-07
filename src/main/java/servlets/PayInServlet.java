package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import entities.CashIn;
import entities.Or_Order;
import entities.Product;
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
import java.util.Date;
import java.util.List;

@WebServlet(name = "payin",value ={ "/insert-payin", "/payin-delete", "/payin-get","/delete-payin" })
public class PayInServlet extends HttpServlet {
    SessionFactory sf = HibernateUtil.getSessionFactory();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         Session sesi=sf.openSession();
        Transaction tr=sesi.beginTransaction();
        String obj=req.getParameter("obj");
        Gson gson=new Gson();
        CashIn ch=gson.fromJson(obj, CashIn.class);
        try {
         int or_id= ch.getOr_id();

            Or_Order ord= (Or_Order) sesi.createQuery("from Or_Order where or_id=?1")
                       .setParameter(1,or_id)
                           .getSingleResult();


            ch.setOrder(ord);
            ch.setCash_status(1);
            sesi.save(ch);
            tr.commit();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } finally {
            sesi.close();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      Session sesi= sf.openSession();
      Gson gson=new Gson();
        List<Object[]> payIn= sesi.createNativeQuery("SELECT cashin.cash_id, customer.cu_name,customer.cu_surname,or_order.fis_no,cashin.payInTotal,customer.cu_id,cashin.payInDetail\n" +
                "FROM cashin INNER JOIN or_order  on cashin.or_id =or_order.or_id\n" +
                "INNER JOIN customer on customer.cu_id=or_order.customer_cu_id  ")
                .getResultList();


        String stJson = gson.toJson(payIn);
        resp.setContentType("application/json");
        resp.getWriter().write( stJson );

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int proReturnId=0;
        Session sesi=sf.openSession();
        Transaction tr=sesi.beginTransaction();
        try{
            int cash_id = Integer.parseInt(req.getParameter("cash_id"));
            CashIn cashIn= sesi.find(CashIn.class,cash_id);
            System.out.println(cashIn);
            sesi.delete(cashIn);
            tr.commit();

            proReturnId=1;
        }catch (Exception e){
            System.out.println("Delete Error " + e );


        }finally {
            sesi.close();
        }
        resp.setContentType("application/json");
        resp.getWriter().write( ""+proReturnId );
    }

}
