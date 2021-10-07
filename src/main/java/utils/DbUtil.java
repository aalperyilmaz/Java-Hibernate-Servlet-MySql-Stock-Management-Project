package utils;


import entities.*;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DbUtil {

    SessionFactory sf= HibernateUtil.getSessionFactory();

//all products for live Search => boxAction.jsp
    public List<Product> allProducts (){
        Session sesi= sf.openSession();
List<Product> ls= sesi.createQuery("from Product ").getResultList();
sesi.close();
        return ls;
    }

   //all customer for live search => boxAction.jsp
    public List<Customer> allCustomer(){
        Session sesi= sf.openSession();
        List<Customer> ls= sesi.createQuery("from Customer ").getResultList();
        sesi.close();
        return ls;
    }

public Product singlePro(int pid){
    Session sesi= sf.openSession();
    Product prod=sesi.get(Product.class,pid);

             return prod;
}

    public Customer singleCust(int cid){
        Session sesi= sf.openSession();
        Customer cust=sesi.get(Customer.class,cid);

        return cust;
    }

    public void insertOrder(Product pro ,Customer cust ,int fis_numb, Date date,int or_size){
        Session sesi= sf.openSession();
        Transaction tr=sesi.beginTransaction();
         int total=or_size*pro.getSelprice();
        Or_Order order =new Or_Order();
        order.setStatus(1);
        order.setProduct(pro);
        order.setDate(date);
        order.setCustomer(cust);
        order.setFis_no(fis_numb);
        order.setOr_size(or_size);
        order.setTotal(total);
        sesi.save(order);
        tr.commit();
        sesi.close();
    }

public List<Object[]> allNmb(){
    Session sesi =sf.openSession();


    List<Object[]> ord = sesi.createNativeQuery("SELECT ord.or_id,c.cu_id,cu_name,c.cu_surname,ord.fis_no,p.ptitle,ord.or_size,p.selprice FROM product as p INNER JOIN or_order as ord on p.pid=ord.product_pid INNER JOIN customer as c ON c.cu_id=ord.customer_cu_id WHERE cu_id and ord.`status`='1' ")

            .getResultList();


    sesi.close();

    return ord;
}


public Long CashInTotal(){
        Session sesi=sf.openSession();
    Long sumOfAllAges = (Long) sesi.createQuery("SELECT sum(payInTotal) from CashIn ").getSingleResult();
    sesi.close();

return sumOfAllAges;
}
    public Long CashOutTotal(){
        Session sesi=sf.openSession();
        Long sumOfAllAges = (Long) sesi.createQuery("SELECT sum(payOutTotal) from CashOut ").getSingleResult();
        sesi.close();

        return sumOfAllAges;
    }

    public Long CountCust(){
        Session sesi=sf.openSession();
        Long cu_id = (Long) sesi.createQuery("SELECT count (cu_id) from Customer ").getSingleResult();

        sesi.close();
        return cu_id;
    }

    public Long CountOrder(){
        Session sesi=sf.openSession();
        Long or_id = (Long) sesi.createQuery("SELECT count (or_id) from Or_Order ").getSingleResult();

        sesi.close();
        return or_id;
    }

    public Long CountStock(){
        Session sesi=sf.openSession();
        Long size = (Long) sesi.createQuery("SELECT sum (size) from Product ").getSingleResult();
        sesi.close();

        return size;
    }

    public Long stockVariety(){
        Session sesi=sf.openSession();
        Long size = (Long) sesi.createQuery("SELECT count (pid) from Product ").getSingleResult();

        sesi.close();
        return size;
    }


    public Long stockBuyprice(){
        Session sesi=sf.openSession();
        Long size = (Long) sesi.createQuery("SELECT sum (buyprice) from Product ").getSingleResult();

        sesi.close();
        return size;
    }
    public Long stockSelprice(){
        Session sesi=sf.openSession();
        Long size = (Long) sesi.createQuery("SELECT sum (selprice) from Product ").getSingleResult();
        sesi.close();

        return size;
    }


    @SneakyThrows
    public List<Object[]> dates(String start, String finish ){
        Session sesi=sf.openSession();

        String HQL_QUERY =
                "select i from CashIn i where i.pdate between :start and :end ";

        Query query = sesi.createQuery(HQL_QUERY);
        String strDateFrom = start;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom = formatter.parse(strDateFrom);

        String strDateTo = finish;
        Date dateTo = formatter.parse(strDateTo);

        List<Object[]> r=query.getResultList();
        sesi.close();
return r;
    }

public List<Object[]> lastorders(){
    Session sesi =sf.openSession();


    List<Object[]> ord = sesi.createNativeQuery("SELECT ord.or_id,ord.fis_no,c.cu_name,c.cu_surname,p.ptax,ord.total\n" +
            "FROM  or_order as ord \n" +
            "INNER JOIN customer as c \n" +
            "ON c.cu_id=ord.customer_cu_id INNER JOIN product as p on ord.product_pid=p.pid\n" +
            "ORDER BY ord.or_id DESC LIMIT 0,8")

            .getResultList();


    sesi.close();

    return ord;

}

public int dailyCashIn(){
        Date date=new Date();
        Session sesi=sf.openSession();
        List<CashIn>cs=sesi.createQuery("from CashIn where pdate=?1")
                .setParameter(1,date).getResultList();
 int total= cs.stream().mapToInt(CashIn::getPayInTotal).sum();
 return total;
}

    public int dailyCashOut(){
        Date date=new Date();
        Session sesi=sf.openSession();
        List<CashOut>cs=sesi.createQuery("from CashOut where odate=?1")
                .setParameter(1,date).getResultList();
        int total= cs.stream().mapToInt(CashOut::getPayOutTotal).sum();
        return total;
    }


}
