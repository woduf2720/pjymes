
const productOrderMasterTable = new Tabulator("#productOrderMasterTable", {
    height: "20rem",
    ajaxURL:"/productOrder",
    layout:"fitData",
    rowFormatter:function(row){
        if(row.getData().active){row.getElement().style.color = "red";}
    },
    columns:[
        {title:"발주번호", field:"orderNo"},
        {title:"거래처코드", field:"customerCode"},
        {title:"거래처명", field:"customerName"},
        {title:"납기일자", field:"orderDate"},
        {title:"납기일자", field:"deliveryDate"},
        {title:"합계금액", field:"price"},
    ],
});

productOrderMasterTable.on("rowClick", function(e, row){
    if(row.getData().active){
        return;
    }
    row.getTable().deselectRow();
    row.select();
});

productOrderMasterTable.on("rowSelected", function(row){
    productOrderSubTable.setData("/productOrder/orderSub/"+row.getData().orderNo)
});

const productOrderSubTable = new Tabulator("#productOrderSubTable", {
    height: "25rem",
    layout:"fitData",
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"품목코드", field:"itemCode"},
        {title:"품목명", field:"itemName"},
        {title:"규격", field:"itemSpecification"},
        {title:"단가", field:"unitPrice"},
        {title:"수량", field:"quantity"},
        {title:"금액", field:"price"},
    ],
});
productOrderSubTable.on("rowClick", function(e, row){
    row.getTable().deselectRow();
    row.select();
});
productOrderSubTable.on("rowSelected", function(row){
    lotTable.setData("/materialDelivery/"+row.getData().itemCode)
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
    let subData = productOrderSubTable.getSelectedData()[0];
    console.log(subData)
    for(d of data){
        d.id = subData.orderSubId;
        d.orderNo = subData.orderNo;
        d.price = d.itemUnitPrice * d.quantity;
    }
    deliveryTable.setData(data)
});

document.getElementById("saveBtn").addEventListener("click", function () {
    let data = deliveryTable.getData()
    axios.post("/productDelivery", data)
        .then(function (response) {
            productOrderMasterTable.replace();
            productOrderSubTable.clearData();
            lotTable.clearData();
            deliveryTable.clearData();
        }).catch(function (error) {
    })
})


const deliveryTable = new Tabulator("#deliveryTable", {
    height: "20rem",
    layout:"fitData",
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"품목코드", field:"itemCode"},
        {title:"품목명", field:"itemName"},
        {title:"규격", field:"itemSpecification"},
        {title:"단가", field:"itemUnitPrice"},
        {title:"수량", field:"quantity"},
        {title:"금액", field:"price"},
    ]
});