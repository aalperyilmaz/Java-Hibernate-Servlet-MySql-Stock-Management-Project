package servlets;

import com.google.gson.Gson;
import entities.CashOut;
import entities.Or_Order;
import entities.SaveObj;
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


@WebServlet(name ="orderStatusChange", value = {"/order-status-change","/order-obj-send"})
public class OrderStatusChange extends HttpServlet {
    SessionFactory sf= HibernateUtil.getSessionFactory();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cid=Integer.parseInt(req.getParameter("cid"));
        String objSave=req.getParameter("objSave");
        Gson gson=new Gson();
        SaveObj saveObj=gson.fromJson(objSave, SaveObj.class);
        System.out.println("json----------------------");
        System.out.println("----"+saveObj.getPricex()+"-"+saveObj.getFis_obj_no()+"-"+saveObj.getOr_obj_id());
        System.out.println("json----------------------");
        Session sesi =sf.openSession();



        List<Or_Order> orderStatus=sesi.createQuery("from Or_Order WHERE fis_no=?1")
                .setParameter(1,cid).getResultList();


        orderStatus.forEach(item->{
            Transaction tr1= sesi.beginTransaction();
            System.out.println( item.getFis_no()+"--"+item.getProduct().getPtitle()+"--"+ item.getOr_id());
            int total= item.getTotal();
            int selprice=item.getProduct().getSelprice();
            int buyAmounth=saveObj.getPricex();
            int newTotal=selprice*buyAmounth;
            newTotal=total-newTotal;
            item.setTotal(newTotal);
            int status =2;
                    item.setStatus(status);
            tr1.commit();


        });

sesi.close();
        resp.setContentType("application/json");
        resp.getWriter().write( "" +cid );


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
