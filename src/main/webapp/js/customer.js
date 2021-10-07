// Customer
// add - start
let select_id = 0
$('#customer_add_form').submit( ( event ) => {
    event.preventDefault();

    const cname = $("#cname").val()
    const csurname = $("#csurname").val()
    const ctitle = $("#ctitle").val()
    const ccode = $("#ccode").val()
    const ctype = $("#ctype").val()
    const tax = $("#tax").val()
    const tax_administration = $("#tax_administration").val()
    const address = $("#address").val()
    const mobile_phone = $("#mobile_phone").val()
    const phone = $("#phone").val()
    const email = $("#email").val()
    const password = $("#password").val()

    const obj = {
        cu_name: cname,
        cu_surname: csurname,
        cu_company_title: ctitle,
        cu_code: ccode,
        cu_status: ctype,
        cu_tax_number: tax,
        cu_tax_administration: tax_administration,
        cu_address: address,
        cu_mobile: mobile_phone,
        cu_phone: phone,
        cu_email: email,
        cu_password: password
    }

    if ( select_id != 0 ) {
        // update
        obj["cu_id"] = select_id;
    }
        $.ajax({
            url: './customer-post',
            type: 'POST',
            data: { obj: JSON.stringify(obj) },
            dataType: 'JSON',
            success: function (data) {
                if ( data > 0 ) {
                    alert("İşlem Başarılı")
                    fncReset();
                }else {
                    alert("İşlem sırasında hata oluştu!");
                }
            },
            error: function (err) {
                console.log(err)
                alert("İşlem işlemi sırısında bir hata oluştu!");
            }
        })


})
// add - end

// all cusomer list - start
function allCustomer() {

    $.ajax({
        url: './customer-get',
        type: 'GET',
        dataType: 'Json',
        success: function (data) {
            createRow(data);
        },
        error: function (err) {
            console.log(err)
        }
    })

}

let globalArr = []
function createRow( data ) {
    globalArr = data;
    let html = ``
    for (let i = 0; i < data.length; i++) {
        const itm = data[i];
        const st = itm.cu_status == 1 ? 'Kurumsal' : 'Bireysel'
        html += `<tr role="row" class="odd">
            <td>`+itm.cu_id+`</td>
            <td>`+itm.cu_name+`</td>
            <td>`+itm.cu_surname+`</td>
            <td>`+itm.cu_company_title+`</td>
            <td>`+itm.cu_code+`</td>
            <td>`+ st +`</td>
            <td>`+itm.cu_mobile+`</td>
            <td>`+itm.cu_email+`</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                <button onclick="fncCustomerDelete(`+itm.cu_id+`)" type="button" class="btn btn-outline-primary "><i class="far fa-trash-alt"></i></button>
                <button onclick="fncCustomerDetail(`+i+`)" data-bs-toggle="modal" data-bs-target="#customerDetailModel" type="button" class="btn btn-outline-primary "><i class="far fa-file-alt"></i></button>
                <button onclick="fncCustomeUpdate(`+i+`)" type="button" class="btn btn-outline-primary "><i class="fas fa-pencil-alt"></i></button>
              </div>
            </td>
          </tr>`;
    }
    $('#tableRow').html(html);
}

function codeGenerator() {
    const date = new Date();
    const time = date.getTime();
    const key = time.toString().substring(4);
    $('#ccode').val( key )
    $('#pcode').val( key )
}
allCustomer();
// all cusomer list - end

// reset fnc
function fncReset() {
    select_id = 0;
    $('#customer_add_form').trigger("reset");
    codeGenerator();
    allCustomer();
}


// customer delete - start
function fncCustomerDelete( cu_id ) {
    let answer = confirm("Silmek istediğinizden emin misniz?");
    if ( answer ) {

        $.ajax({
            url: './customer-delete?cu_id='+cu_id,
            type: 'DELETE',
            dataType: 'text',
            success: function (data) {
                if ( data != "0" ) {
                    fncReset();
                }else {
                    alert("Silme sırasında bir hata oluştu!");
                }
            },
            error: function (err) {
                console.log(err)
            }
        })
    }
}
// customer delete - end


// customer detail - start
function fncCustomerDetail(i){
    const itm = globalArr[i];
    $("#cu_name").text(itm.cu_name + " " + itm.cu_surname.toUpperCase() + " - " + itm.cu_id); //item içindeki cu_namei jspde cu_name e atar
    $("#cu_email").text(itm.cu_email == "" ? '------' : itm.cu_email);
    $("#cu_company_title").text(itm.cu_company_title == "" ? '------' : itm.cu_company_title);
    $("#cu_code").text(itm.cu_code == "" ? '------' : itm.cu_code);
    $("#cu_status").text(itm.cu_status == 1 ? 'Kurumsal' : 'Bireysel');
    $("#cu_tax_number").text(itm.cu_tax_number == "" ? '------' : itm.cu_tax_number);
    $("#cu_mobile").text(itm.cu_mobile == "" ? '------' : itm.cu_mobile);
    $("#cu_tax_administration").text(itm.cu_tax_administration == "" ? '------' : itm.cu_tax_administration);
    $("#cu_address").text(itm.cu_address == "" ? '------' : itm.cu_address);
}
// customer detail - end

// cutomer update select
function fncCustomeUpdate( i ) {
    const itm = globalArr[i];
    select_id = itm.cu_id
    $("#cname").val(itm.cu_name)
    $("#csurname").val(itm.cu_surname)
    $("#ctitle").val(itm.cu_company_title)
    $("#ccode").val(itm.cu_code)
    $("#ctype").val(itm.cu_status)
    $("#tax").val(itm.cu_tax_number)
    $("#tax_administration").val(itm.cu_tax_administration)
    $("#address").val(itm.cu_address)
    $("#mobile_phone").val(itm.cu_mobile)
    $("#phone").val(itm.cu_phone)
    $("#email").val(itm.cu_email)
    $("#password").val(itm.cu_password)
}

