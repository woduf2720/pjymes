const productOrderMasterEditor = function(cell, onRendered, success, cancel, editorParams){
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

const productOrderMasterEditCheck = function(cell){
    var data = cell.getRow().getData();
    return data.orderNo == null;
}


const productOrderMasterTable = new Tabulator("#productOrderMasterTable", {
    height: "45rem",
    ajaxURL:"/productOrder",
    layout:"fitData",
    rowFormatter:function(row){
        if(row.getData().active){row.getElement().style.color = "red";}
    },
    columns:[
        {title:"발주번호", field:"orderNo"},
        {title:"거래처코드", field:"customerCode", editor:productOrderMasterEditor, editable:productOrderMasterEditCheck},
        {title:"거래처명", field:"customerName"},
        {title:"발주일자", field:"orderDate", editor:"input", editable:productOrderMasterEditCheck},
        {title:"납기일자", field:"deliveryDate", editor:"input", editable:productOrderMasterEditCheck},
        {title:"합계금액", field:"price"}
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

document.getElementById("addMasterBtn").addEventListener("click", function () {
    let rows = productOrderMasterTable.getRows();
    for (const row of rows) {
        if(row.getData().orderNo == null) {
            return row.getCell("customerCode").edit();
        }
    }

    productOrderMasterTable.addRow({
        "orderDate" : luxon.DateTime.local().toFormat('yyyy-MM-dd'),
        "deliveryDate" : luxon.DateTime.local().toFormat('yyyy-MM-dd')
    })
    .then(function(row){
        row.getTable().deselectRow();
        row.select()
        row.getCell("customerCode").edit();
    });
})

document.getElementById("orderSearchBtn").addEventListener("click", function () {
    const startDate = document.getElementById("startDate").value
    const endDate = document.getElementById("endDate").value
    const keyword = document.getElementById("customerSearch").value
    const queryString = "keyword=" + encodeURIComponent(keyword)
        + "&startDate=" + encodeURIComponent(startDate) + "&endDate=" + encodeURIComponent(endDate);
    productOrderMasterTable.setData("/productOrder/orderMaster?" + queryString);
})


const productOrderSubEditor = function(cell, onRendered, success, cancel, editorParams){
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

const productOrderSubEditCheck = function(cell){
    let data = cell.getRow().getData();
    console.log(data)
    return data.orderNo == null;
}

const productOrderSubTable = new Tabulator("#productOrderSubTable", {
    height: "45rem",
    layout:"fitData",
    tabEndNewRow: true,
    selectableRows: true,
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"품목코드", field:"itemCode", editor:productOrderSubEditor, editable:productOrderSubEditCheck},
        {title:"품목명", field:"itemName"},
        {title:"규격", field:"itemSpecification"},
        {title:"단가", field:"unitPrice", editor:"input"},
        {title:"수량", field:"quantity", editor:"input"},
        {title:"금액", field:"price"},
    ],
});

productOrderSubTable.on("cellEdited", function(cell){
    const fields = cell.getField()
    const row = cell.getRow()

    if(fields === "unitPrice" || fields === "quantity") {
        let unitPrice = row.getData().unitPrice
        let quantity = row.getData().quantity
        row.update({"price" : unitPrice * quantity})
        let rows = productOrderSubTable.getRows()
        let totalPrice = 0
        for (let row of rows) {
            totalPrice += row.getData().price
        }
        productOrderMasterTable.getRows("selected")[0].update({"price" : totalPrice})
    }
});

document.getElementById("addSubBtn").addEventListener("click", function () {
    let rows = productOrderSubTable.getRows();
    for (const row of rows) {
        if(row.getData().itemName == null) {
            return row.getCell("itemCode").edit();
        }
    }
    productOrderSubTable.addRow()
        .then(function(row){
            row.getCell("itemCode").edit();
        });
})

document.getElementById("saveBtn").addEventListener("click", function () {
    let selectedMasterRow = productOrderMasterTable.getRows("selected")[0]
    let subData = productOrderSubTable.getData()

    selectedMasterRow.update({"productOrderSubDTOList" : subData})
    axios.post("/productOrder", selectedMasterRow.getData())
        .then(function (response) {
            productOrderMasterTable.replaceData()
            alert("저장되었습니다.")
        }).catch(function (error) {
    })
})

document.getElementById("deleteBtn").addEventListener("click", function () {
    let selectedSubRow = productOrderSubTable.getRows("selected")
    let selectedSubData = productOrderSubTable.getData("selected")

    axios.delete("/productOrder", {data : selectedSubData})
        .then(function (response) {
            productOrderSubTable.deleteRow(selectedSubRow)
            if(productOrderSubTable.getRows().length === 0){
                productOrderMasterTable.replaceData()
            }
            alert("삭제되었습니다.")
        }).catch(function (error) {
    })
})

