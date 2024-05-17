document.getElementById("materialOrderSearchBtn").addEventListener("click", function () {
    const data = inputToJson("#productOrder .form-input")
    productOrderMasterTable.setData("/productOrder/orderMaster", data)
    productOrderSubTable.clearData();
    lotTable.clearData();
    deliveryTable.clearData();
})

const productOrderMasterTable = new Tabulator("#productOrderMasterTable", {
    height: "20rem",
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
        {title:"수주일자", field:"orderDate"},
        {title:"납기일자", field:"deliveryDate"},
        {title:"합계금액", field:"price", hozAlign: "right"},
    ],
});

productOrderMasterTable.on("rowClick", function(e, row){
    if(row.getData().orderStatusValue === 2){
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
    rowFormatter:function(row){
        let delivery = row.getData().quantity - row.getData().deliveryQuantity
        if(delivery === 0){
            row.getElement().style.color = "red";
        }
    },
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"품목코드", field:"itemCode"},
        {title:"품목명", field:"itemName"},
        {title:"규격", field:"itemSpecification"},
        {title:"규격", field:"itemCategoryName"},
        {title:"단가", field:"unitPrice", hozAlign: "right"},
        {title:"수량", field:"quantity", hozAlign: "right"},
        {title:"출고수량", field:"deliveryQuantity", hozAlign: "right"},
        {title:"금액", field:"price", hozAlign: "right"},
    ],
});
productOrderSubTable.on("rowClick", function(e, row){
    let delivery = row.getData().quantity - row.getData().deliveryQuantity
    if(delivery !== 0){
        row.getTable().deselectRow();
        row.select();
    }
});
productOrderSubTable.on("rowSelected", function(row){
    lotTable.setData("/materialDelivery/"+row.getData().itemCode)
});

document.getElementById("wholeBtn").addEventListener("click", function () {
    let rows = lotTable.getRows();
    let newRows = [];
    let subQuantity = productOrderSubTable.getData("selected")[0].quantity
    for(row of rows){
        let rowQuantity = row.getData().quantity
        if(subQuantity > 0){
            subQuantity = subQuantity > rowQuantity ? subQuantity - rowQuantity : 0
            newRows.push(row)
        }else{
            break
        }
    }
    lotTable.selectRow(newRows)
})

const lotTable = new Tabulator("#lotTable", {
    height: "20rem",
    layout:"fitData",
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"lot번호", field:"lotNo"},
        {title:"품목코드", field:"itemCode"},
        {title:"품목명", field:"itemName"},
        {title:"규격", field:"itemSpecification"},
        {title:"분류", field:"itemCategoryName"},
        {title:"수량", field:"quantity", hozAlign: "right"},
    ],
});
lotTable.on("rowClick", function(e, row){
    // 선택되어있으면 무조건 해제 되도록하고
    // 선택되어있지 않으면 수량에 한해서 가능하도록 함
    const subData = productOrderSubTable.getSelectedData()[0];
    let subQuantity = subData.quantity - subData.deliveryQuantity;
    const selectedDatas = lotTable.getData("selected")
    let selectedQunatity = 0;

    for(data of selectedDatas){
        selectedQunatity += data.quantity;
    }
    if(row.isSelected()){
        row.toggleSelect();
    }else{
        if(subQuantity > selectedQunatity){
            row.toggleSelect();
        }
    }
});

lotTable.on("rowSelectionChanged", function(data, rows, selected, deselected){
    const subData = productOrderSubTable.getSelectedData()[0];
    let subQuantity = subData.quantity;
    for(d of data){
        d.orderSubId = subData.orderSubId;
        d.orderNo = subData.orderNo;

        if(subQuantity>d.quantity){
            d.price = d.itemUnitPrice * d.quantity;
            subQuantity - d.quantity
        }else {
            d.quantity = subQuantity;
        }
    }
    deliveryTable.setData(data)
});

document.getElementById("saveBtn").addEventListener("click", function () {
    let data = deliveryTable.getData()
    axios.post("/productDelivery", data)
        .then(function (response) {
            alert("저장되었습니다.")
            console.log(response)
            productOrderSubTable.getRows("selected")[0].update(response.data)
            lotTable.clearData();
            deliveryTable.clearData();
        }).catch(function (error) {
        alert(error.response.data)
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
        {title:"분류", field:"itemCategoryName"},
        {title:"단가", field:"itemUnitPrice", hozAlign: "right"},
        {title:"수량", field:"quantity", hozAlign: "right"},
        {title:"금액", field:"price", hozAlign: "right"},
    ]
});