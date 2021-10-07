<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">

        <button type="button" id="sidebarCollapse" class="btn btn-primary">
            <i class="fa fa-bars"></i>
            <span class="sr-only">Toggle Menu</span>
        </button>

        <button class="btn btn-dark d-inline-block d-lg-none ml-auto " type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <i class="fa fa-bars"></i>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="nav navbar-nav ml-auto">

                <li class="nav-item active">
                    <a class="nav-link" href="#"><span class="fas fa-home"></span> Anasayfa</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><span class="fas fa-sliders-h"></span> Ayarlar</a>
                </li>

            </ul>
        </div>
    </div>
</nav>
