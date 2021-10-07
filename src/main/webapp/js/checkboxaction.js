$('#search_form').submit( ( event ) => {

    const cname = $("#cname").val()
    const ctype = $("#ctype").val()
    const startDate = $("#startDate").val()
    const endDate = $("#endDate").val()


    const obj = {
        cname: cname,
        ctype: ctype,
        startDate: startDate,
        endDate: endDate,

    }


    $.ajax({
        url: './search-servlet',
        type: 'POST',
        data: { obj: JSON.stringify(obj) },
        dataType: 'JSON',
        success: function (data) {
                alert("Islem Basarili")
                createRow(data)
        },
        error: function (err) {
            console.log(err)
            alert("İslem  sirasinda bir hata olustu!");
        }
    })



})




let globalArr = []
function createRow( data ) {
    globalArr = data;
    let html = ``
    for (let i = 0; i < data.length; i++) {
        const itm = data[i];

        html += `<tr role="row" class="odd">
            <td>`+itm.cu_name+`</td>
            <td>`+itm.cu_surname+`</td>
            <td>`+itm.cu_code+`</td>
            <td>`+itm.cu_email+`</td>
            <td>`+itm.cu_code+`</td>
          
            <td>`+itm.cu_mobile+`</td>
            <td>`+itm.cu_email+`</td>
            <td class="text-right" >
              
            </td>
          </tr>`;
    }
    $('#tableRow1').html(html);
}



