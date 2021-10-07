package servlets;

import com.google.gson.Gson;
import entities.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.DbUtil;
import utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "orderManager" , value = {"/order-insert-servlet","/order-list","/delete-order"})
public class OrderManagerServlet extends HttpServlet {
    SessionFactory sf = HibernateUtil.getSessionFactory();
    DbUtil dbUtil=new DbUtil();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     int cid=Integer.parseInt(req.getParameter("cid"));
         Session sesi =sf.openSession();


        List<Object[]> ord = sesi.createNativeQuery("SELECT c.cu_id,cu_name,c.cu_surname,ord.fis_no,p.ptitle,ord.or_size,p.selprice,ord.or_id,ord.total FROM product as p INNER JOIN or_order as ord on p.pid=ord.product_pid INNER JOIN customer as c ON c.cu_id=ord.customer_cu_id WHERE cu_id=?1 and ord.status=1 ")
                    .setParameter(1,cid)
                    .getResultList();

        sesi.close();


            for (Object[] item :ord){
                System.out.println(item[0]+"--"+item[1]+"--"+item[3]);
            }


        Gson gson=new Gson();
           String setJson= gson.toJson(ord);
        resp.setContentType("application/json");
        resp.getWriter().write(setJson);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session sesi=sf.openSession();
        Transaction tr= sesi.beginTransaction();
int pid=0;

        try {
        String obj=req.getParameter("obj");
            Gson gson=new Gson();
           OrderMngrObj order=gson.fromJson(obj, OrderMngrObj.class);
           DbUtil dbUtil=new DbUtil();
            System.out.println(order);
            int cid=Integer.parseInt(order.getCustomer());
            int pidx=Integer.parseInt(order.getProduct());
            Product px= dbUtil.singlePro(pidx);
            Customer cx=dbUtil.singleCust(cid);
         dbUtil.insertOrder(px,cx, order.getFis_no(), new Date(),order.getOr_size());


            pid=1;
        }catch (Exception e){
            System.out.println("Order Insert Error"+e);
        }

        resp.setContentType("application/json");
        resp.getWriter().write( "" +pid );
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       int or_id= Integer.parseInt(req.getParameter("or_id"));
        Session sesi= sf.openSession();
        Transaction tr=sesi.beginTransaction();

        Or_Order ord= (Or_Order) sesi.createQuery("from Or_Order where or_id=?1 ")
                .setParameter(1,or_id)
                .getSingleResult();
        sesi.delete(ord);
        tr.commit();
        sesi.close();

    }
}
