
const orderMasterTable = new Tabulator("#orderMasterTable", {
    height: "45rem",
    ajaxURL:"/materialOrder",
    layout:"fitData",
    columns:[
        {title:"발주번호", field:"orderNo"},
        {title:"거래처코드", field:"customerCode"},
        {title:"거래처명", field:"customerName"},
        {title:"납기일자", field:"deliveryDate"},
        {title:"합계금액", field:"price"},
    ],
});

orderMasterTable.on("rowClick", function(e, row){
    row.getTable().deselectRow();
    row.select();
});

orderMasterTable.on("rowSelected", function(row){
    let orderSubData = row.getData().orderSubs
    if(orderSubData == null) {
        orderSubData = [{}]
    }
    orderSubTable.setData(orderSubData)
});

const orderSubTable = new Tabulator("#orderSubTable", {
    height: "20rem",
    layout:"fitData",
    tabEndNewRow: true,
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"품목코드", field:"itemCode"},
        {title:"품목명", field:"itemName"},
        {title:"규격", field:"standard"},
        {title:"단가", field:"unitPrice"},
        {title:"수량", field:"quantity"},
        {title:"금액", field:"price"},
    ],
});

orderSubTable.on("cellEdited", function(cell){
    const fields = cell.getField()
    const row = cell.getRow()

    if(fields === "unitPrice" || fields === "quantity") {
        let unitPrice = row.getData().unitPrice
        let quantity = row.getData().quantity
        row.update({"price" : unitPrice * quantity})
        let rows = orderSubTable.getRows()
        let totalPrice = 0
        for (let row of rows) {
            totalPrice += row.getData().price
        }
        orderMasterTable.getRows("selected")[0].update({"price" : totalPrice})
    }
});

document.getElementById("saveBtn").addEventListener("click", function () {
    let selectedMasterRow = orderMasterTable.getRows("selected")[0]
    let subData = orderSubTable.getData()

    selectedMasterRow.update({"orderSubs" : subData})

    axios.post("/materialOrder", orderMasterTable.getData("selected"))
        .then(function (response) {
            console.log(response)
            orderMasterTable.replaceData()
        }).catch(function (error) {
        console.log(error)
    })
})


const warehousingTable = new Tabulator("#warehousingTable", {
    height: "20rem",
    layout:"fitData",
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"품목코드", field:"itemCode"},
        {title:"품목명", field:"itemName"},
        {title:"규격", field:"standard"},
        {title:"단가", field:"unitPrice"},
        {title:"수량", field:"quantity"},
        {title:"금액", field:"price"},
    ],
});