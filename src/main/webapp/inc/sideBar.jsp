<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String pagex = request.getRequestURI();
%>

<nav id="sidebar" class="active">
    <h1><a href="dashboard.jsp" class="logo">D.</a></h1>
    <ul class="list-unstyled components mb-5">

        <li class="<%=pagex.contains("dashboard") ? "active": "" %>">
            <a href="dashboard.jsp"><span class="fas fa-tachometer-alt"></span>
                <div class="menu_div">Yönetim</div>
            </a>
        </li>


        <li class="<%=pagex.contains("customer") ? "active": "" %>">
            <a href="customer.jsp"><span class="far fa-user"></span>
                <div class="menu_div">Müşteriler</div>
            </a>
        </li>
        <li class="<%=pagex.contains("products") ? "active": "" %>">
            <a href="products.jsp"><span class="fa fa-shopping-basket"></span>
                <div class="menu_div">Stok Yönetimi</div>
            </a>
        </li>
        <li class="<%=pagex.contains("boxAction") ? "active": "" %>">
            <a href="boxAction.jsp"><span class="fa fa-shopping-cart"></span>
                <div class="menu_div">Siparişler</div>
            </a>
        </li>
        <li class="<%=pagex.contains("checkOutActions") || pagex.contains("payIn")  || pagex.contains("payOut") ? "active": "" %>">
            <a href="checkOutActions.jsp"><span class="fa fa-window-maximize"></span>
                <div class="menu_div">Kasa Yönetimi</div>
            </a>
        </li>
        <li>
            <a href="logout"><span class="fa fa-lock"></span>
                <div class="menu_div">Güvenli Çıkış</div>
            </a>
        </li>
    </ul>

    <div class="footer">
        <p>
            Tüm Hakları Saklıdır &copy;
            <script>document.write(new Date().getFullYear());</script> <i class="icon-heart" aria-hidden="true"></i>
        </p>
    </div>
</nav>