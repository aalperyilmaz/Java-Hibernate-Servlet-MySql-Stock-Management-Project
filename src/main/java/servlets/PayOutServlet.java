package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import entities.CashIn;
import entities.CashOut;
import org.hibernate.HibernateException;
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

@WebServlet(name = "payOutServlet", value = { "/payout-insert","/payout-get","/payout-delete" })
public class PayOutServlet extends HttpServlet {

    SessionFactory sf = HibernateUtil.getSessionFactory();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


            Session sesi=sf.openSession();
            Transaction tr=sesi.beginTransaction();
            String obj=req.getParameter("obj");
            Gson gson=new Gson();
            CashOut cho=gson.fromJson(obj, CashOut.class);
            cho.setOdate(new Date());
            sesi.save(cho);
            tr.commit();
            sesi.close();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session sesi= sf.openSession();
        Gson gson=new Gson();
        List<Object[]> payIn= sesi.createNativeQuery("SELECT*FROM cashout")
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
            CashOut cashIn= sesi.find(CashOut.class,cash_id);
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

