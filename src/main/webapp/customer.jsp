<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="utils" class="utils.DbUtil"></jsp:useBean>
<%@ page import="entities.Admin" %>
<%@ page import="entities.Adminp" %>
<jsp:useBean id="util" class="utils.Util"></jsp:useBean>
<%

  Adminp adm = (Adminp) util.isLogin(request, response);

%>
<!doctype html>
<html lang="en">

<head>
  <title>Yönetim</title>
  <jsp:include page="inc/css.jsp"></jsp:include>
</head>

<body>
<div class="wrapper d-flex align-items-stretch">
  <jsp:include page="inc/sideBar.jsp"></jsp:include>
  <!-- Page Content  -->
  <div id="content" class="p-4 p-md-5">
    <jsp:include page="inc/topMenu.jsp"></jsp:include>

    <h3 class="mb-3">
      Müşteriler
      <small class="h6">Müşteri Paneli</small>
    </h3>

    <div class="main-card mb-3 card mainCart">
      <div class="card-header cardHeader">Müşteri Ekle</div>

      <form class="row p-3" id="customer_add_form" >
        <div class="col-md-3 mb-3">
          <label for="cname" class="form-label">Adı</label>
          <input type="text" name="cname" id="cname" class="form-control" required />
        </div>
        <div class="col-md-3 mb-3">
          <label for="csurname" class="form-label">Soyadı</label>
          <input type="text" name="csurname" id="csurname" class="form-control" required  />
        </div>
        <div class="col-md-3 mb-3">
          <label for="ctitle" class="form-label">Ünvan (Şirket)</label>
          <input type="text" name="ctitle" id="ctitle" class="form-control" />
        </div>
        <div class="col-md-3 mb-3">
          <label for="ccode" class="form-label">Müşteri Kodu</label>
          <input type="number" name="ccode" id="ccode" class="form-control" required  />
        </div>

        <div class="col-md-3 mb-3">
          <label for="ctype" class="form-label">Müşteri Türü</label>
          <select class="form-select" name="ctype" id="ctype"  required >
            <option value="">Seçiniz</option>
            <option value="1">Kurumsal</option>
            <option value="2">Birseysel</option>
          </select>
        </div>

        <div class="col-md-3 mb-3">
          <label for="tax" class="form-label">Vergi No / Tc No</label>
          <input type="number" name="tax" id="tax" class="form-control" required />
        </div>

        <div class="col-md-3 mb-3">
          <label for="tax_administration" class="form-label">Vergi Dairesi</label>
          <input type="text" name="tax_administration" id="tax_administration" class="form-control" />
        </div>

        <div class="col-md-3 mb-3">
          <label for="address" class="form-label">Adres</label>
          <input type="text" name="address" id="address" class="form-control" />
        </div>

        <div class="col-md-3 mb-3">
          <label for="mobile_phone" class="form-label">Cep Telefonu</label>
          <input type="text" name="mobile_phone" id="mobile_phone" class="form-control" required  />
        </div>

        <div class="col-md-3 mb-3">
          <label for="phone" class="form-label">Sabit Telefonu</label>
          <input type="text" name="phone" id="phone" class="form-control" />
        </div>

        <div class="col-md-3 mb-3">
          <label for="email" class="form-label">E-Mail</label>
          <input type="email" name="email" id="email" class="form-control" />
        </div>

        <div class="col-md-3 mb-3">
          <label for="password" class="form-label">Şifre</label>
          <input type="password" name="password" id="password" class="form-control" />
        </div>

        <div class="btn-group col-md-3 " role="group">
          <button type="submit" class="btn btn-outline-primary mr-1">Gönder</button>
          <button onclick="fncReset()" type="button" class="btn btn-outline-primary">Temizle</button>
        </div>
      </form>
    </div>

    <div class="main-card mb-3 card mainCart">
      <div class="card-header cardHeader">Müşteri Listesi</div>

      <div class="row mt-3" style="padding-right: 15px; padding-left: 15px;">
        <div class="col-sm-3"></div>
        <div class="col-sm-3"></div>
        <div class="col-sm-3"></div>
        <div class="col-sm-3">
          <div class="input-group flex-nowrap">
            <span class="input-group-text" id="addon-wrapping"><i class="fas fa-search"></i></span>
            <input type="text" class="form-control" placeholder="Arama.." aria-label="Username" aria-describedby="addon-wrapping">
            <button class="btn btn-outline-primary">Ara</button>
          </div>
        </div>



      </div>
      <div class="table-responsive">
        <table class="align-middle mb-0 table table-borderless table-striped table-hover">
          <thead>
          <tr>
            <th>Id</th>
            <th>Adı</th>
            <th>Soyadı</th>
            <th>Ünvan</th>
            <th>Kod</th>
            <th>Türü</th>
            <th>Telefon</th>
            <th>Mail</th>
            <th class="text-center" style="width: 155px;" >Yönetim</th>
          </tr>
          </thead>
          <tbody id="tableRow">
          <!-- for loop  -->
          </tbody>
        </table>
      </div>
    </div>


  </div>
</div>


<!-- Modal -->
<div class="modal fade" id="customerDetailModel" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" >
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" style="color: black"   id="cu_name">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="row ">
          <div class="col-md-3 mb-3">
            <label  class="form-label">Email</label>
            <div style="color: black" id="cu_email" class="form-text">Mail</div>
          </div>

          <div class="col-md-3 mb-3">
            <label  class="form-label">Ünvan(Şirket)</label>
            <div style="color: black" id="cu_company_title" class="form-text"></div>
          </div>

          <div class="col-md-3 mb-3">
            <label  class="form-label">Müşteri Kodu</label>
            <div style="color: black" id="cu_code" class="form-text"></div>
          </div>

          <div class="col-md-3 mb-3">
            <label  class="form-label">Müşteri Türü</label>
            <div style="color: black" id="cu_status" class="form-text"></div>
          </div>

          <div class="col-md-3 mb-3">
            <label  class="form-label">Vergi No / Tc No</label>
            <div style="color: black"  id="cu_tax_number" class="form-text"></div>
          </div>

          <div class="col-md-3 mb-3">
            <label  class="form-label">Cep Telefonu</label>
            <h5 style="color: black"  id="cu_mobile" class="form-text"></h5>
          </div>
          <div class="col-md-3 mb-3">
            <label  class="form-label">Vergi Dairesi</label>
            <h5 style="color: black "  id="cu_tax_administration" class="form-text"></h5>
          </div>

          <div class="col-md-3 mb-3">
            <label  class="form-label">Adres</label>
            <div style="color: black"  id="cu_address" class="form-text"></div>
          </div>


        </div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Kapat</button>

      </div>
    </div>
  </div>
</div>




<jsp:include page="inc/js.jsp"></jsp:include>
<script src="js/customer.js"></script>
</body>

</html>