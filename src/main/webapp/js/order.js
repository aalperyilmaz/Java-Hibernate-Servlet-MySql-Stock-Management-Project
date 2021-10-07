

$('#form_addOrder').submit((event)=>{
    event.preventDefault();


    const selectedItem2 = $('#pname').val();
        const selectedItem1 = $('#cname').val();
    const cid = $('#cname').val();

const count=$('#count').val();
const bNo=$('#bNo').val();





    const obj={
       product:selectedItem2,
        customer: selectedItem1,
        or_size: count,
        fis_no: bNo,

    }


$.ajax({

    url: './order-insert-servlet',
    type: 'POST',
    data: { obj: JSON.stringify(obj) },
    dataType: 'JSON',
    success:function (data){
        allOrderList(cid)
        $("#count").val("")
        $("#pname").val('default');
        $("#pname").selectpicker("refresh");

        if (data>0){
            alert("İslem Basarili")

        }else {
            alert("İslem sirasinda hata olustu!");
        }
    }
})


})

//////insert end
function allOrderList(cid){

    $.ajax({
        url: './order-list?cid='+cid,
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

let globalOrdId=[]
let globalArr = []

let objSave={

}
function createRow( data ) {
    globalArr = data;
    let html = ``
    for (let i = 0; i < data.length; i++) {
        const itm = data[i];
        globalOrdId.push(itm)
        objSave.fis_obj_no = itm[3];
        objSave.cu_obj_id=itm[0];
        objSave.or_obj_id = itm[7];
        objSave.pricex=itm[5]
        html += `<tr role="row" class="odd">
                        <td>`+itm[7]+`</td>
                        <td>`+itm[4]+`</td>
                        <td>`+itm[8]+`</td>
                        <td>`+itm[5]+`</td>
                         <td>`+itm[1]+` `+itm[2]+`</td>
                        <td>`+itm[3]+`</td>
                        <td class="text-right" >
                            <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                                <button id="for_brn" onclick="fncOrderDelete(`+itm[7]+`,`+itm[0]+`)  " type="button" class="btn btn-outline-primary "><i class="far fa-trash-alt"></i></button>
                            </div>
                        </td>
                    </tr> `;
    }
    $('#tableRow').html(html);





}


$("#cname").on("change",function (){
    allOrderList(this.value);
    codeGenerator();


})




function fncOrderDelete( or_id ,cid) {
    let answer = confirm("Silmek istediğinizden emin misniz?");
    if ( answer ) {

        $.ajax({
            url: './delete-order?or_id='+or_id,
            type: 'DELETE',
            dataType: 'text',
            success: function (data) {
                allOrderList(cid)
                if ( data != "0" ) {

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


function codeGenerator() {
    const date = new Date();
    const time = date.getTime();
    const key = time.toString().substring(4);
    $('#bNo').val( key )

}


function fncObjc(){
    console.log(globalOrdId)
     const item=globalOrdId
    console.log(objSave)

    $.ajax({
        url: './order-status-change?cid='+objSave.fis_obj_no,
        type: 'GET',
        data:{ objSave: JSON.stringify(objSave) },
        dataType: 'Json',

        success: function (data) {

                alert("İslem basarili")


        },
        error: function (err) {
            console.log(err)
        }

    })


    location.reload();

}
