let select_id = 0
$('#product_add_form').submit( (event) => {
    event.preventDefault();


    const buyprice= $("#buyprice").val();
    const selprice= $("#selprice").val();
    const pcode= $("#pcode").val();
    const ptax= $("#ptax").val();
    const psection= $("#psection").val();
    const size= $("#size").val();
    const pdetail= $("#pdetail").val();
    const ptitle=$("#ptitle").val();

    const probj={

        buyprice:buyprice,
        selprice:selprice,
        pcode:pcode,
        ptax:ptax,
        psection:psection,
        size:size,
        pdetail:pdetail,
        ptitle:ptitle

    }


    if ( select_id != 0 ) {
        // update
        probj["pid"] = select_id;
    }


    alert(probj.pid)
  $.ajax({
      url: './product-post',
      type: 'POST',
      data: { probj: JSON.stringify(probj) },
      dataType: 'JSON',
      success:function (data){
          if (data>0){

          alert("islem basarili")
              fncReset();
          }else {
              alert("islem sirasinda hata olstu")
          }

      },
      error:function (err){
          console.log(err)
          alert("islem sirasinda hata olustu... error log")
      }
  })



});

function allProduct(){

    $.ajax({
        url: './product-get',
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




let globalProductArr = []
function createRow( data ) {
    globalProductArr = data;
    let html = ``
    for (let i = 0; i < data.length; i++) {
        const itm = data[i];
        const st = itm.cu_status == 1 ? 'Kurumsal' : 'Bireysel'


        let ts = '';
        let us = '';
        if(itm.ptax==0){
            ts = 'Dahil'
        }
        else if(itm.ptax==1){
            ts = '%1'
        }

        else if(itm.ptax==2){
            ts = '%8'
        }
        else if(itm.ptax==3){
            ts = '%18'
        }



        if(itm.psection==0){
            us = 'Adet'
        }
        else if(itm.psection==1){
            us = 'KG'
        }

        else if(itm.psection==2){
            us = 'Metre'
        }
        else if(itm.psection==3){
            us = 'Paket'
        }
        else if(itm.psection==4){
            us = 'Litre'
        }




        html += `<tr role="row" class="odd">
            <td>`+itm.pid+`</td>
            <td>`+itm.ptitle+`</td>
            <td>`+itm.buyprice+`</td>
            <td>`+itm.selprice+`</td>
            <td>`+itm.pcode+`</td>
            <td>`+ts+`</td>
            <td>`+us+`</td>
            <td>`+itm.size+`</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                <button onclick="fncProductDelete(`+itm.pid+`)"  type="button" class="btn btn-outline-primary "><i class="far fa-trash-alt"></i></button>
                <button onclick="fncProductDetail(`+i+`)"  data-bs-toggle="modal" data-bs-target="#customerDetailModel" type="button" class="btn btn-outline-primary "><i class="far fa-file-alt"></i></button>
                <button onclick="fncProductUpdate(`+i+`)" type="button" class="btn btn-outline-primary "><i class="fas fa-pencil-alt"></i></button>
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
allProduct();



function fncProductDelete(pid){
    let answer=confirm("Silmek istediginizden eminmisiniz");
    if (answer){
        $.ajax({
            url: "./product-delete?pid="+pid,
            type: 'DELETE',
            dataType: 'text',
            success: function (data){
                if (data !="0"){
                    fncReset();
                }else {
                 alert("silme islemi sirasinda hata olustu")   ;
                }
            },
            error:function (err) {
                console.log(err);
            }
        })


    }
}

function fncReset() {
    select_id = 0;
    $('#product_add_form').trigger("reset");
    codeGenerator();
    allProduct();
}


function fncProductUpdate(i){
    const itm=globalProductArr[i];
    select_id = itm.pid
    $("#buyprice").val(itm.buyprice);
    $("#selprice").val(itm.selprice);
     $("#pcode").val(itm.pcode);
     $("#ptax").val(itm.ptax);
     $("#psection").val(itm.psection);
     $("#size").val(itm.size);
     $("#pdetail").val(itm.pdetail);
     $("#ptitle").val(itm.ptitle);

}

function fncProductDetail(i){
    const itm = globalProductArr[i];

    let ts = '';
    let us = '';
    if(itm.ptax==0){
        ts = 'Dahil'
    }
    else if(itm.ptax==1){
        ts = '%1'
    }

    else if(itm.ptax==2){
        ts = '%8'
    }
    else if(itm.ptax==3){
        ts = '%18'
    }



    if(itm.psection==0){
        us = 'Adet'
    }
    else if(itm.psection==1){
        us = 'KG'
    }

    else if(itm.psection==2){
        us = 'Metre'
    }
    else if(itm.psection==3){
        us = 'Paket'
    }
    else if(itm.psection==4){
        us = 'Litre'
    }

    $("#mbuyprice").text(itm.buyprice + " " ); //item içindeki cu_namei jspde cu_name e atar
    $("#mselprice").text(itm.selprice );
    $("#mpcode").text(itm.pcode);
    $("#mptax").text(ts);
    $("#mpsection").text(us );

    $("#msize").text(itm.size );
    $("#mpdetail").text(itm.pdetail );
    $("#mptitle").text(itm.ptitle );
}

