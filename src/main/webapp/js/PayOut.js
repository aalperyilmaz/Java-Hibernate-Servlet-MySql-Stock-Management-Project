$('#pay_out_form').submit((event)=>{
    event.preventDefault();
    const payOutDetail=$('#payOutDetail').val()
    const payOutTotal=$('#payOutTotal').val()
    const payOutType=$('#payOutType').val()
    const payOutTitle=$('#payOutTitle').val()

    const obj={
        payOutDetail:payOutDetail,
        payOutTotal:payOutTotal,
        payOutTitle:payOutTitle,
        payOutType:payOutType

    }
    allpayOutList()
    $.ajax({

        url: './payout-insert',
        type: 'POST',
        data: { obj: JSON.stringify(obj) },
        dataType: 'JSON',
        success:function (data){
            allpayOutList()
            if (data>0){
                alert("İslem Basarili")
                allpayOutList()
            }else {
                alert("İslem sirasinda hata olustu!");
                allpayOutList()
            }
            allpayOutList()
        }

    })

    allpayOutList()
})


function allpayOutList(){

    $.ajax({
        url: './payout-get',
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


        let us = '';


        if(itm[4]==0){
            us = 'Nakit'
        }
        else if(itm[4]==1){
            us = 'Kredi Karti'
        }

        else if(itm[4]==2){
            us = 'Eft'
        }
        else if(itm[4]==3){
            us = 'Banka Çeki'
        }
        else if(itm[4]==4){
            us = 'Havale'
        }


        html += `<tr role="row" class="odd">
            <td>`+itm[0]+`</td>
            <td>`+itm[2]+`</td>
            <td>`+us+`</td>
            <td>`+itm[1]+`</td>
            <td>`+itm[3]+`</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                <button onclick="fncPayinDelete(`+itm[0]+`)" type="button" class="btn btn-outline-primary "><i class="far fa-trash-alt"></i></button>
                <button onclick="fncPayinDetail(`+i+`)" data-bs-toggle="modal" data-bs-target="#customerDetailModel" type="button" class="btn btn-outline-primary "><i class="far fa-file-alt"></i></button>
                <button onclick="fncPayinUpdate( `+i+`)" type="button" class="btn btn-outline-primary "><i class="fas fa-pencil-alt"></i></button>
              </div>
            </td>
          </tr>`;
    }
    $('#tableRow').html(html);
}
allpayOutList()

function fncPayinDelete( cu_id ) {

    let answer = confirm("Silmek istediğinizden emin misniz?");
    if ( answer ) {

        $.ajax({
            url: './payout-delete?cash_id='+cu_id,
            type: 'DELETE',
            dataType: 'text',
            success: function (data) {
                allpayOutList()
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
        allpayOutList()
    }
    allpayOutList()
}
// customer delete - end


// customer detail - start
function fncPayinDetail(i){
    const itm = globalArr[i];
    let us = '';


    if(itm[4]==0){
        us = 'Nakit'
    }
    else if(itm[4]==1){
        us = 'Kredi Karti'
    }

    else if(itm[4]==2){
        us = 'Eft'
    }
    else if(itm[4]==3){
        us = 'Banka Çeki'
    }
    else if(itm[4]==4){
        us = 'Havale'
    }

    $("#cash-id").val(itm[5])
    $("#cu_name").val(itm[7])
    $("#cash-detail").val(itm[6])
    $("#fis_nox").val(itm[3])
    $("#cash-amounth").val(us)


    $("#cu_namex").text(itm[0]); //item
    $("#cu_email").text(itm[5]);
    $("#cu_company_title").text(itm[1]);
    $("#cu_code").text(itm[3]);
    $("#cu_status").text(us);







}
// customer detail - end

// cutomer update select
function fncPayinUpdate( i ) {
    const itm = globalArr[i];
    select_id = itm[2]
    $("#cu_id").val(itm[5])
    $("#or_id").val(itm[7])
    $("#payInTotal").val(itm[4])
    $("#payInDetail").val(itm[6])


}
allpayOutList()