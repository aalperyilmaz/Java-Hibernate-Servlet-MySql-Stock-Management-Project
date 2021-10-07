package utils;

;

import entities.Admin;
import entities.Adminp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Util {

    public final static String base_url = "http://localhost:8080/depo_project_war_exploded/";
    SessionFactory sf = HibernateUtil.getSessionFactory();

    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }


    public Adminp isLogin(HttpServletRequest request, HttpServletResponse response) {
        Adminp padmin=new Adminp();
        if (request.getCookies() != null ) {
            Cookie[] cookies = request.getCookies();
            for ( Cookie cookie : cookies ) {
                if ( cookie.getName().equals("user") ) {
                    String vales = cookie.getValue();
                    try {
                        String[] arr = vales.split("_");
                        request.getSession().setAttribute("aid", Integer.parseInt(arr[0]) );
                        request.getSession().setAttribute("name",arr[1] + " " + arr[2] );
                    } catch (NumberFormatException e) {
                        Cookie cookie1 = new Cookie("user", "");
                        cookie1.setMaxAge(0);
                        response.addCookie(cookie1);
                    }
                    break;
                }
            }
        }


        Object sessionObj = request.getSession().getAttribute("aid");
        Adminp adm = new Adminp();
        if ( sessionObj == null ) {
            try {
                response.sendRedirect(base_url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            int aid = (int) request.getSession().getAttribute("aid");
            String name = (String) request.getSession().getAttribute("name");
            adm.setAid(aid);
            adm.setName(name);
        }
        return adm;
    }

    public int login(String email, String password, String remember, HttpServletRequest req, HttpServletResponse resp) {
        int status = 0;
        Session sesi= sf.openSession();
        try {

            Admin adm= (Admin) sesi.createNativeQuery("select * from admin where email = ?1 and password = ?2").setParameter(1,email).setParameter(2,MD5(password))
                    .addEntity(Admin.class)
                    .getSingleResult();
            System.out.println(adm);

            if ( adm.getEmail()!=null && (adm.getPassword().equals(Util.MD5(password)) )){
                // session create
                System.out.println("session created");
               int  aid= adm.getAid();
               String name= adm.getName();
                req.getSession().setAttribute("aid", aid);
                req.getSession().setAttribute("name", name);

                // cookie create
                if ( remember != null && remember.equals("on")) {
                    name = name.replaceAll(" ", "_");
                    String val = aid+"_"+name;
                    Cookie cookie = new Cookie("user", val);
                    cookie.setMaxAge( 60*60 );
                    resp.addCookie(cookie);
                }

              status=1;
            }

        } catch (Exception e) {
            System.err.println("login Error : " + e);
        }finally {
            sesi.close();
        }
        return status;
    }




}
