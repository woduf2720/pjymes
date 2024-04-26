var itemTable = new Tabulator("#itemTable", {
    height: "45rem",
    ajaxURL:"/itemManage",
    layout:"fitData",
    selectableRows: "1",
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"품목코드", field:"code"},
        {title:"품목명", field:"name"},
        {title:"규격", field:"specification"},
        {title:"분류", field:"category"},
    ],
});

itemTable.on("rowSelected", function(row){
    bomManageTable.setData("/bomManage/"+row.getData().code)
        .then(function(){
            bomManageTable.getRows()[0].select()
        })
});


var bomManageTable = new Tabulator("#bomManageTable", {
    height: "45rem",
    layout:"fitData",
    dataTree:true,
    dataTreeStartExpanded:true,
    dataTreeChildField:"children",
    editTriggerEvent:"dblclick",
    columns:[
        {title:"레벨", field:"level"},
        {title:"품목코드", field:"itemCode"},
        {title:"품목코드", field:"itemName"},
        {title:"규격", field:"itemSpecification"},
        {title:"수량", field:"quantity", editor:"input"},
    ],
});

bomManageTable.on("rowClick", function(e, row){
    row.getTable().deselectRow();
    row.select();
    console.log(row.getData())
});

bomManageTable.on("cellEdited", function(cell){
    const rowData = cell.getRow().getData();

    axios.put("/bomManage", rowData)
        .then(function (response) {
        }).catch(function (error) {
    })
});

const addBomModal = document.getElementById('addBomModal')

document.getElementById("addBomBtn").addEventListener("click", function () {
    let row = bomManageTable.getRows("selected")[0]
    if(row != null) {
        document.getElementById('parentId').value = row.getData().id;
        document.getElementById('parentItemCode').value = row.getData().itemCode;
        document.getElementById('parentItemName').value = row.getData().itemName;
        new bootstrap.Modal(addBomModal).show()
    }else{
        alert("행을 선택해주세요.")
    }
})

addBomModal.addEventListener('hidden.bs.modal', event => {
    inputToNull("form-input")
})

document.getElementById("addBtn").addEventListener("click", function () {
    const data = inputToJson("form-input")

    axios.post("/bomManage", data)
        .then(function (response) {
            console.log(response)
            bootstrap.Modal.getInstance(addBomModal).hide();
            bomManageTable.replaceData()
        }).catch(function (error) {
        console.log(error)
    })
})

var bomChildTable = new Tabulator("#bomChildTable", {
    height: "20rem",
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

bomChildTable.on("rowClick", function(e, row){
    row.getTable().deselectRow();
    row.select();
});

bomChildTable.on("rowSelected", function(row){
    console.log(row.getData());
    document.getElementById('itemCode').value = row.getData().code;
    document.getElementById('itemName').value = row.getData().name;
});

document.getElementById("deleteBomBtn").addEventListener("click", function () {
    let row = bomManageTable.getRows("selected")[0]
    if(row != null) {
        if(confirm("선택한 행을 삭제하시겠습니까?")){
            axios.delete("/bomManage/"+row.getData().id)
                .then(function (response) {
                    console.log(response)
                    row.delete();
                }).catch(function (error) {
                console.log(error)
            })
        }
    }else{
        alert("행을 선택해주세요.")
    }
})