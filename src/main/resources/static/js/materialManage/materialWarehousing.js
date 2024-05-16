document.getElementById("materialOrderSearchBtn").addEventListener("click", function () {
    const data = inputToJson("#materialOrder .form-input")
    orderMasterTable.setData("/materialOrder/orderMaster", data)
    orderSubTable.clearData();
    warehousingTable.clearData();
})

const orderMasterTable = new Tabulator("#orderMasterTable", {
    height: "45rem",
    layout:"fitData",
    rowFormatter:function(row){
        let orderStatus = row.getData().orderStatusValue;
        if(orderStatus === 2){
            row.getElement().style.color = "red";
        }else if(orderStatus === 1){
            row.getElement().style.color = "blue";
        }
    },
    columns:[
        {title:"발주번호", field:"orderNo"},
        {title:"거래처코드", field:"customerCode"},
        {title:"거래처명", field:"customerName"},
        {title:"발주일자", field:"orderDate"},
        {title:"납기일자", field:"deliveryDate"},
        {title:"합계금액", field:"price"},
    ],
});

orderMasterTable.on("rowClick", function(e, row){
    if(row.getData().orderStatusValue === 2){
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
    let newRows = [];
    for(row of rows){
        let warehousing = row.getData().quantity - row.getData().warehousingQuantity
        if(warehousing !== 0){
            newRows.push(row)
        }
    }
    orderSubTable.selectRow(newRows)
})

const orderSubTable = new Tabulator("#orderSubTable", {
    height: "20rem",
    layout:"fitData",
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"품목코드", field:"itemCode"},
        {title:"품목명", field:"itemName"},
        {title:"규격", field:"itemSpecification"},
        {title:"단가", field:"unitPrice"},
        {title:"수량", field:"quantity"},
        {title:"입고수량", field:"warehousingQuantity"},
        {title:"금액", field:"price"},
    ],
});
orderSubTable.on("rowClick", function(e, row){
    console.log(row)
    let warehousing = row.getData().quantity - row.getData().warehousingQuantity
    if(warehousing !== 0){
        row.toggleSelect();
    }
});

orderSubTable.on("rowSelectionChanged", function(data, rows, selected, deselected){
    for(d of data){
        d.id = d.orderSubId
        d.quantity = d.quantity-d.warehousingQuantity
    }
    warehousingTable.setData(data)
});

document.getElementById("saveBtn").addEventListener("click", function () {
    let data = warehousingTable.getData()
    axios.post("/materialWarehousing", data)
        .then(function (response) {
            alert("저장되었습니다.")
            console.log(response)
            orderMasterTable.replaceData();
            orderSubTable.clearData();
            warehousingTable.clearData();
        }).catch(function (error) {
            alert(error.response.data)
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