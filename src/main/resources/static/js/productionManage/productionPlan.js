var productionPlanTable = new Tabulator("#productionPlanTable", {
    height: "45rem",
    layout:"fitData",
    selectRow: 1,
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"계획번호", field:"planNo"},
        {title:"수주번호", field:"orderNo"},
        {title:"거래처코드", field:"orderSubCustomerCode"},
        {title:"거래처명", field:"orderSubCustomerName"},
        {title:"품목코드", field:"orderSubItemCode"},
        {title:"품목코드", field:"orderSubItemName"},
        {title:"규격", field:"orderSubItemSpecification"},
        {title:"지시일", field:"orderDate"},
        {title:"마감일", field:"dueDate"},
        {title:"지시수량", field:"orderQuantity", hozAlign: "right"}
    ]
});

document.getElementById("searchPlanBtn").addEventListener("click", function () {
    const data = inputToJson("#productionPlan .form-input")
    productionPlanTable.setData("/productionPlan", data)
})

function getModalData(data){
    let changeData = {
        "orderSubId" : data.orderSubId,
        "orderNo" : data.orderNo,
        "orderSubCustomerCode" : data.orderMasterCustomerCode,
        "orderSubCustomerName" : data.orderMasterCustomerName,
        "orderSubItemCode" : data.itemCode,
        "orderSubItemName" : data.itemName,
        "orderSubItemSpecification" : data.itemSpecification,
        "orderQuantity" : data.quantity - data.deliveryQuantity,
        "orderDate" : luxon.DateTime.local().toFormat('yyyy-MM-dd'),
        "dueDate" : data.orderMasterDeliveryDate
    }
    productionPlanTable.addRow(changeData)
        .then(function(row){
            row.getTable().deselectRow();
            row.select()
        });
}

document.getElementById("savePlanBtn").addEventListener("click", function () {
    let selectedRows = productionPlanTable.getRows("selected");
    if(selectedRows.length === 1){
        axios.post("/productionPlan", selectedRows[0].getData())
            .then(function (response) {
                selectedRows[0].update(response.data);
                alert("저장되었습니다.")
            }).catch(function (error) {
            alert(error.response.data)
        })
    }else{
        alert("저장할 행을 선택해주세요.")
    }
})