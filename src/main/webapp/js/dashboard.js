

function allpayInList(){

    $.ajax({
        url: './dashboard-servlet',
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


function allstockInList(){

    $.ajax({
        url: './last-stock',
        type: 'GET',
        dataType: 'Json',

        success: function (data) {
            createRow2(data);

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

        html += `<tr role="row" class="odd">
           
            <td>`+itm[1]+`</td>
            <td>`+itm[2]+` `+itm[3]+`</td>
            <td>`+itm[4]+`</td>
            <td>`+itm[5]+`</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="Basic mixed styles example">
              </div>
            </td>
          </tr>`;
    }
    $('#tableRow').html(html);
}







let globalArr2 = []
function createRow2( data ) {
    globalArr2 = data;
    let html = ``
    for (let i = 0; i < data.length; i++) {
        const itm = data[i];

        html += `<tr role="row" class="odd">
           
            <td>`+itm[0]+`</td>
            <td>`+itm[1]+` </td>
            <td>`+itm[2]+`</td>
            <td>`+itm[3]+`</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="Basic mixed styles example">
              </div>
            </td>
          </tr>`;
    }
    $('#tableRow2').html(html);
}


allpayInList()
allstockInList()