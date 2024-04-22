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
    ajaxURL:"/materialOrder",
    layout:"fitData",
    columns:[
        {title:"발주번호", field:"orderNo"},
        {title:"거래처코드", field:"customerCode", editor:orderMasterEditor, editable:orderMasterEditCheck},
        {title:"거래처명", field:"customerName"},
        {title:"납기일자", field:"deliveryDate", editor:"input", editable:orderMasterEditCheck},
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

const orderSubTable = new Tabulator("#orderSubTable", {
    height: "20rem",
    layout:"fitData",
    tabEndNewRow: true,
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"품목코드", field:"itemCode", editor:orderSubEditor},
        {title:"품목명", field:"itemName"},
        {title:"규격", field:"standard", editor:"input"},
        {title:"단가", field:"unitPrice", editor:"input"},
        {title:"수량", field:"quantity", editor:"input"},
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
