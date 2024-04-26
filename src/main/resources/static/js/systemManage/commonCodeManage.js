var commonCodeParentTable = new Tabulator("#commonCodeParentTable", {
    height: "45rem",
    ajaxURL:"/commonCode/0",
    layout:"fitData",
    selectableRows: "1",
    editTriggerEvent:"dblclick",
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"코드명", field:"name", editor:"input"},
        {title:"비고", field:"description", editor:"input"},
    ],
});

commonCodeParentTable.on("rowSelected", function(row){
    commonCodeChildTable.setData("/commonCode/"+row.getData().id);
});

commonCodeParentTable.on("cellEdited", function(cell){
    const rowData = cell.getRow().getData();

    axios.put("/commonCode", rowData)
        .then(function (response) {
        }).catch(function (error) {
    })
});

const commonCodeModal = document.getElementById('commonCodeModal')

document.getElementById("childAddBtn").addEventListener("click", function () {
    let row = commonCodeParentTable.getRows("selected")[0]
    if(row != null){
        document.getElementById('parentId').value = row.getData().id;
        bootstrap.Modal.getInstance(commonCodeModal).show()
    }else{
        alert("상위코드를 선택해주세요.")
    }
})

commonCodeModal.addEventListener('shown.bs.modal', event => {
    const inputElements = event.target.querySelectorAll('.form-input');
    if (inputElements.length > 0) {
        focusFirstValidInput(inputElements);
    }
})
document.getElementById("addBtn").addEventListener("click", function () {
    const data = inputToJson("form-input")
    console.log(data)
    axios.post("/commonCode", data)
        .then(function (response) {
            bootstrap.Modal.getInstance(commonCodeModal).hide();
            if(data.parentId === null){
                commonCodeParentTable.replaceData("/commonCode/0")
            }else{
                commonCodeChildTable.replaceData("/commonCode/"+data.parentId)
            }

        }).catch(function (error) {
    })
})


commonCodeModal.addEventListener('hidden.bs.modal', event => {
    inputToNull("form-input")
})

let commonCodeChildTable = new Tabulator("#commonCodeChildTable", {
    height: "45rem",
    layout:"fitData",
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"코드명", field:"name", editor:"input"},
        {title:"비고", field:"description", editor:"input"},
        {title:"사용유무", field:"active", hozAlign: "center", formatter:"tickCross", editor: "tickCross"},
    ],
});

commonCodeChildTable.on("cellEdited", function(cell){
    const rowData = cell.getRow().getData();

    axios.put("/commonCode", rowData)
        .then(function (response) {
        }).catch(function (error) {
    })
});