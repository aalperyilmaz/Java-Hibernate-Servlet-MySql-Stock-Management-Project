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
      Ürünler
      <small class="h6">Ürün Paneli</small>
    </h3>

    <div class="main-card mb-3 card mainCart">
      <div class="card-header cardHeader">Ürün Ekle</div>

      <form id="product_add_form" class="row p-3">
        <div class="col-md-3 mb-3">
          <label for="ptitle" class="form-label">Başlık</label>
          <input type="text" name="ptitle" id="ptitle" class="form-control" />
        </div>
        <div class="col-md-3 mb-3">
          <label for="buyprice" class="form-label">Alış Fiyatı</label>
          <input type="number" name="buyprice" id="buyprice" class="form-control" />
        </div>
        <div class="col-md-3 mb-3">
          <label for="selprice" class="form-label">Satış Fiyatı</label>
          <input type="number" name="selprice" id="selprice" class="form-control" />
        </div>
        <div class="col-md-3 mb-3">
          <label for="pcode" class="form-label">Ürün Kodu</label>
          <input type="number" name="pcode" id="pcode" class="form-control" />
        </div>


        <div class="col-md-3 mb-3">
          <label for="ptax" class="form-label">KDV</label>
          <select class="form-select" name="ptax" id="ptax">
            <option value="-1">Seçiniz</option>
            <option value="0">Dahil</option>
            <option value="1">%1</option>
            <option value="2">%8</option>
            <option value="3">%18</option>
          </select>
        </div>

        <div class="col-md-3 mb-3">
          <label for="psection" class="form-label">Birim</label>
          <select class="form-select" name="psection" id="psection">
            <option value="-1">Seçiniz</option>
            <option value="0">Adet</option>
            <option value="1">KG</option>
            <option value="2">Metre</option>
            <option value="3">Paket</option>
            <option value="4">Litre</option>
          </select>
        </div>

        <div class="col-md-3 mb-3">
          <label for="size" class="form-label">Miktar</label>
          <input type="number" name="size" id="size" class="form-control" />
        </div>

        <div class="col-md-3 mb-3">
          <label for="pdetail" class="form-label">Ürün Detay</label>
          <input type="text" name="pdetail" id="pdetail" class="form-control" />
        </div>


        <div class="btn-group col-md-3 " role="group">
          <button type="submit" class="btn btn-outline-primary mr-1">Gönder</button>
          <button type="reset" class="btn btn-outline-primary">Temizle</button>
        </div>
      </form>
    </div>


    <div class="main-card mb-3 card mainCart">
      <div class="card-header cardHeader">Ürün Listesi</div>

      <div class="table-responsive">
        <table class="align-middle mb-0 table table-borderless table-striped table-hover">
          <thead>
          <tr>
            <th>Uid</th>
            <th>Başlık</th>
            <th>Alış Fiyatı</th>
            <th>Satış Fiyatı</th>
            <th>Kod</th>
            <th>KDV</th>
            <th>Birim</th>
            <th>Miktar</th>
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
            <label  class="form-label">Alış Fiyatı</label>
            <div style="color: black" id="mbuyprice" class="form-text">Alış Fiyatı</div>
          </div>

          <div class="col-md-3 mb-3">
            <label  class="form-label">Satış Fiyatı</label>
            <div style="color: black" id="mselprice" class="form-text"></div>
          </div>

          <div class="col-md-3 mb-3">
            <label  class="form-label">Ürün Kodu</label>
            <div style="color: black" id="mpcode" class="form-text"></div>
          </div>

          <div class="col-md-3 mb-3">
            <label  class="form-label">Vergi Türü</label>
            <div style="color: black" id="mptax" class="form-text"></div>
          </div>

          <div class="col-md-3 mb-3">
            <label  class="form-label">Birim</label>
            <div style="color: black"  id="mpsection" class="form-text"></div>
          </div>

          <div class="col-md-3 mb-3">
            <label  class="form-label">Miktar</label>
            <h5 style="color: black"  id="msize" class="form-text"></h5>
          </div>
          <div class="col-md-3 mb-3">
            <label  class="form-label">Ürün Detay</label>
            <h5 style="color: black "  id="mpdetail" class="form-text"></h5>
          </div>

          <div class="col-md-3 mb-3">
            <label  class="form-label">Ürün Başlığı</label>
            <div style="color: black"  id="mptitle" class="form-text"></div>
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
<script src="js/product.js"></script>
</body>

</html>