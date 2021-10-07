package servlets;


import com.google.gson.Gson;
import entities.*;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.DbUtil;
import utils.HibernateUtil;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "checkOutBoxActionServlet",value = {"/search-servlet","/search-list"})
public class CheckOutBoxActionServlet extends HttpServlet {
    SessionFactory sf = HibernateUtil.getSessionFactory();


    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Session sesi= sf.openSession();
         String obj =req.getParameter("obj");
        Gson gson=new Gson();
        CheckOutSrchObj chscr = gson.fromJson(obj, CheckOutSrchObj.class);

        ///////////////////Geri DAta Dönmüyor Listelemek İçin

////CALL `checkOutBoxActionSearch`(custid=?1 ,sdate=?2 ,fdate=3?
if(chscr.getCtype()==1){
 List<CashIn> cs=sesi.createNativeQuery("SELECT*FROM Cashin where Cashin.pdate BETWEEN :sdate and :fdate ")

         .setParameter("sdate",chscr.getStartDate())
         .setParameter("fdate",chscr.getEndDate())
         .addEntity(CashIn.class)
         .getResultList();

List<Customer> ord=new ArrayList<>();
        cs.forEach(item->{
            System.out.println(item.getCash_id()+"-"+ item.getCash_id()+"--"+item.getCash_status()+"--"+item.getOr_id());
            Customer cstmr= (Customer) sesi.createQuery("from Customer where cu_id=?1")
                    .setParameter(1,item.getCu_id()).getSingleResult();
            ord.add(cstmr);
    });
        System.out.println(ord);
    String data = gson.toJson(ord);
    System.out.println(data);
    resp.setContentType("application/json");
    resp.getWriter().write(data);
sesi.close();
}else {
    Session sesi1= sf.openSession();
    List<CashOut>cs=sesi.createQuery("from CashOut where CashOut.odate BETWEEN :sdate and :fdate")
            .setParameter("sdate",chscr.getStartDate())
            .setParameter("fdate",chscr.getEndDate())
            .getResultList();
    sesi1.close();
    System.out.println(cs);
    String data = gson.toJson(cs);
    System.out.println(data);
    resp.setContentType("application/json");
    resp.getWriter().write(data);
}

    }

}
