
const itemTable = new Tabulator("#itemTable", {
    height: "45rem",
    ajaxURL:"/itemManage",
    layout:"fitData",
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"품목코드", field:"code"},
        {title:"품목명", field:"name"},
        {title:"규격", field:"specification"},
        {title:"분류", field:"category"},
    ],
});

itemTable.on("rowClick", function(e, row){
    row.getTable().deselectRow();
    row.select();
});

itemTable.on("rowSelected", function(row){
    let code = row.getData().code
    lotTable.setData("/materialDelivery/"+code);
});

document.getElementById("wholeBtn").addEventListener("click", function () {
    let rows = lotTable.getRows();
    rows.forEach(row => row.select())
})

const lotTable = new Tabulator("#lotTable", {
    height: "20rem",
    layout:"fitData",
    selectableRows: true,
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"lot번호", field:"lotNo"},
        {title:"품목코드", field:"itemCode"},
        {title:"품목명", field:"itemName"},
        {title:"규격", field:"itemSpecification"},
        {title:"수량", field:"quantity"},
    ],
});

lotTable.on("rowSelectionChanged", function(data, rows, selected, deselected){
    deliveryTable.setData(data)
});

document.getElementById("saveBtn").addEventListener("click", function () {

    axios.post("/materialDelivery", deliveryTable.getData())
        .then(function (response) {
            itemTable.replaceData();
            lotTable.clearData();
            deliveryTable.clearData();
            alert("저장되었습니다.")
        }).catch(function (error) {
    })
})

const deliveryTable = new Tabulator("#deliveryTable", {
    height: "20rem",
    layout:"fitData",
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"lot번호", field:"lotNo"},
        {title:"품목코드", field:"itemCode"},
        {title:"품목명", field:"itemName"},
        {title:"규격", field:"itemSpecification"},
        {title:"수량", field:"quantity"},
    ],
});
