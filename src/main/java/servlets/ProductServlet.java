package servlets;

import com.google.gson.Gson;
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
import java.lang.management.GarbageCollectorMXBean;
import java.util.List;

@WebServlet(name ="productServlet" , value ={"/product-post","/product-delete","/product-get"})
public class ProductServlet  extends HttpServlet {

    SessionFactory sf= HibernateUtil.getSessionFactory();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int  pid=0;
        Session sesi=sf.openSession();
        Transaction tr= sesi.beginTransaction();


        try {
            String probj=  req.getParameter("probj");
            Gson gson=new Gson();
            Product product= gson.fromJson(probj,Product.class);
            sesi.saveOrUpdate(product);
            tr.commit();
            pid=1;
        }catch (Exception e){
            System.err.println("Save OR Update Error : " + e);
        }finally {
            sesi.close();
        }

        resp.setContentType("application/json");
        resp.getWriter().write( "" +pid );

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson=new Gson();
        Session sesi=sf.openSession();
        List<Product> ls=sesi.createQuery("from Product ").getResultList();
        sesi.close();
        String toJson=gson.toJson(ls);
        resp.setContentType("application/json");
        resp.getWriter().write( toJson );
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     int proReturnId=0;
     Session sesi=sf.openSession();
     Transaction tr=sesi.beginTransaction();
     try{
         int pid = Integer.parseInt(req.getParameter("pid"));
         Product product= sesi.find(Product.class,pid);
         System.out.println(product);
         sesi.delete(product);
         tr.commit();
         proReturnId=product.getPid();

     }catch (Exception e){
         System.out.println("Delete Error " + e );


     }finally {
         sesi.close();
     }
        resp.setContentType("application/json");
        resp.getWriter().write( ""+proReturnId );
    }
}
