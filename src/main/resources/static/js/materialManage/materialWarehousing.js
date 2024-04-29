
const orderMasterTable = new Tabulator("#orderMasterTable", {
    height: "45rem",
    ajaxURL:"/materialOrder",
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

orderMasterTable.on("rowClick", function(e, row){
    if(row.getData().active){
        return;
    }
    row.getTable().deselectRow();
    row.select();
});

orderMasterTable.on("rowSelected", function(row){
    orderSubTable.setData("/materialOrder/orderSub/"+row.getData().orderNo)
});

document.getElementById("wholeBtn").addEventListener("click", function () {
    let rows = orderSubTable.getRows();
    rows.forEach(row => row.select())
})

const orderSubTable = new Tabulator("#orderSubTable", {
    height: "20rem",
    layout:"fitData",
    selectableRows: true,
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

orderSubTable.on("rowSelectionChanged", function(data, rows, selected, deselected){
    for(d of data){d.id = d.orderSubId}
    warehousingTable.setData(data)
});

document.getElementById("saveBtn").addEventListener("click", function () {
    let data = warehousingTable.getData()
    axios.post("/materialWarehousing", data)
        .then(function (response) {
            orderMasterTable.replace();
            orderSubTable.clearData();
            warehousingTable.clearData();
        }).catch(function (error) {
    })
})


const warehousingTable = new Tabulator("#warehousingTable", {
    height: "20rem",
    layout:"fitData",
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"품목코드", field:"itemCode"},
        {title:"품목명", field:"itemName"},
        {title:"규격", field:"itemSpecification"},
        {title:"단가", field:"unitPrice"},
        {title:"수량", field:"quantity"},
        {title:"금액", field:"price"},
    ]
});