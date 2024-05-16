const orderMasterEditor = function(cell, onRendered, success, cancel, editorParams){
    //cell - the cell component for the editable cell
    //onRendered - function to call when the editor has been rendered
    //success - function to call to pass thesuccessfully updated value to Tabulator
    //cancel - function to call to abort the edit and return to a normal cell
    //editorParams - params object passed into the editorParams column definition property

    //create and style editor
    let editor = document.createElement("input");

    editor.setAttribute("type", "text");

    //create and style input
    editor.style.padding = "3px";
    editor.style.width = "100%";
    editor.style.boxSizing = "border-box";


    //set focus on the select box when the editor is selected (timeout allows for editor to be added to DOM)
    onRendered(function(){
        editor.focus();
        editor.style.css = "100%";
    });

    //when the value has been set, trigger the cell to update
    function successFunc(){
        success();
    }

    editor.addEventListener("change", successFunc);
    editor.addEventListener("blur", successFunc);

    editor.addEventListener("keydown", function(e) {
        if (e.key === "Enter") {
            if (cell.getField() === "customerCode") {
                customerModalShow(editor.value, cell, "grid");
            }
        }
    });

    //return the editor element
    return editor;
};

var orderMasterEditCheck = function(cell){
    var data = cell.getRow().getData();
    return data.orderNo == null;
}


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
        {title:"거래처코드", field:"customerCode", editor:orderMasterEditor, editable:orderMasterEditCheck},
        {title:"거래처명", field:"customerName"},
        {title:"발주일자", field:"orderDate", editor:"input", editable:orderMasterEditCheck},
        {title:"납기일자", field:"deliveryDate", editor:"input", editable:orderMasterEditCheck},
        {title:"합계금액", field:"price", hozAlign: "right"}
    ],
});

orderMasterTable.on("rowClick", function(e, row){
    let buttons = document.querySelectorAll("#materialOrderSub button")
    if(row.getData().orderStatusValue === 2){
        return;
    }else if(row.getData().orderStatusValue === 1){
        buttons.forEach(button => button.disabled = true)
    }else{
        buttons.forEach(button => button.disabled = false)
    }
    row.getTable().deselectRow();
    row.select();
});

orderMasterTable.on("rowSelected", function(row){
    orderSubTable.setData("/materialOrder/orderSub/"+row.getData().orderNo)
});

document.getElementById("addMasterBtn").addEventListener("click", function () {
    let rows = orderMasterTable.getRows();
    for (const row of rows) {
        if(row.getData().orderNo == null) {
            return row.getCell("customerCode").edit();
        }
    }

    orderMasterTable.addRow({
        "orderDate" : luxon.DateTime.local().toFormat('yyyy-MM-dd'),
        "deliveryDate" : luxon.DateTime.local().toFormat('yyyy-MM-dd')
    })
    .then(function(row){
        row.getTable().deselectRow();
        row.select()
        row.getCell("customerCode").edit();
    });
})

document.getElementById("materialOrderSearchBtn").addEventListener("click", function () {
    const data = inputToJson("#materialOrderMaster .form-input")
    orderMasterTable.setData("/materialOrder/orderMaster", data)
    orderSubTable.clearData();
})


const orderSubEditor = function(cell, onRendered, success, cancel, editorParams){
    //cell - the cell component for the editable cell
    //onRendered - function to call when the editor has been rendered
    //success - function to call to pass thesuccessfully updated value to Tabulator
    //cancel - function to call to abort the edit and return to a normal cell
    //editorParams - params object passed into the editorParams column definition property

    //create and style editor
    let editor = document.createElement("input");

    editor.setAttribute("type", "text");

    //create and style input
    editor.style.padding = "3px";
    editor.style.width = "100%";
    editor.style.boxSizing = "border-box";


    //set focus on the select box when the editor is selected (timeout allows for editor to be added to DOM)
    onRendered(function(){
        editor.focus();
        editor.style.css = "100%";
    });

    //when the value has been set, trigger the cell to update
    function successFunc(){
        success();
    }

    editor.addEventListener("change", successFunc);
    editor.addEventListener("blur", successFunc);

    editor.addEventListener("keydown", function(e) {
        if (e.key === "Enter") {
            if (cell.getField() === "itemCode") {
                itemModalShow(editor.value, cell, "grid");
            }
        }
    });

    //return the editor element
    return editor;
};

var orderSubEditCheck = function(cell){
    let data = cell.getRow().getData();
    return data.orderNo == null || data.warehousingQuantity === 0;
}

const orderSubTable = new Tabulator("#orderSubTable", {
    height: "45rem",
    layout:"fitData",
    tabEndNewRow: true,
    selectableRows: true,
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"품목코드", field:"itemCode", editor:orderSubEditor, editable:orderSubEditCheck},
        {title:"품목명", field:"itemName"},
        {title:"규격", field:"itemSpecification"},
        {title:"단가", field:"unitPrice", hozAlign: "right", editor:"input", editable:orderSubEditCheck},
        {title:"수량", field:"quantity", hozAlign: "right", editor:"input", editable:orderSubEditCheck},
        {title:"입고수량", field:"warehousingQuantity", hozAlign: "right"},
        {title:"금액", field:"price", hozAlign: "right"},
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

document.getElementById("addSubBtn").addEventListener("click", function () {
    const masterData = orderMasterTable.getData("selected")
    if(masterData.length === 0){
        alert("행을 선택해주세요.")
    }else{
        let rows = orderSubTable.getRows();
        for (const row of rows) {
            if(row.getData().itemName == null) {
                return row.getCell("itemCode").edit();
            }
        }
        orderSubTable.addRow()
            .then(function(row){
                row.getCell("itemCode").edit();
            });
    }
})

document.getElementById("saveBtn").addEventListener("click", function () {
    let selectedMasterRow = orderMasterTable.getRows("selected")[0]
    let subData = orderSubTable.getData()

    selectedMasterRow.update({"orderSubDTOList" : subData})

    axios.post("/materialOrder", selectedMasterRow.getData())
        .then(function (response) {
            alert("저장되었습니다.")
            orderMasterTable.getRows("selected")[0].update(response.data)
            orderSubTable.setData(response.data.orderSubDTOList);
        }).catch(function (error) {
        alert(error.response.data)
    })
})

document.getElementById("deleteBtn").addEventListener("click", function () {
    let selectedSubRow = orderSubTable.getRows("selected")
    let selectedSubData = orderSubTable.getData("selected")

    axios.delete("/materialOrder", {data : selectedSubData})
        .then(function (response) {
            console.log(response)
            orderSubTable.deleteRow(selectedSubRow)
            if(orderSubTable.getRows().length === 0){
                console.log("확인")
                orderMasterTable.deleteRow(orderMasterTable.getRows("selected"));
            }
            alert("삭제되었습니다.")
        }).catch(function (error) {
        console.log(error)
    })
})

