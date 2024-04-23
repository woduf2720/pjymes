var majorCodeTable = new Tabulator("#majorCodeTable", {
    height: "45rem",
    ajaxURL:"/commonCode/major",
    layout:"fitData",
    selectableRows: "1",
    editTriggerEvent:"dblclick",
    columns:[
        {title:"상위코드", field:"majorCode"},
        {title:"코드명", field:"commonCodeName", editor:"input"},
        {title:"비고", field:"remarks", editor:"input"},
    ],
});

majorCodeTable.on("rowSelected", function(row){
    subCodeTable.setData("/commonCode/sub", row.getData());
});

majorCodeTable.on("cellEdited", function(cell){
    const rowData = cell.getRow().getData();

    axios.put("/commonCode/"+rowData.commonCodeId, rowData)
        .then(function (response) {
            console.log(response)
        }).catch(function (error) {
        console.log(error)
    })
});

document.getElementById("majorCodeAddBtn").addEventListener("click", function () {
    document.getElementById('subCode').value = "00";
})

const commonCodeModal = document.getElementById('commonCodeModal')

document.getElementById("subCodeAddBtn").addEventListener("click", function () {
    let rows = majorCodeTable.getRows("selected")
    if(rows.length>0){
        document.getElementById('majorCode').value = rows[0].getData().majorCode;
        new bootstrap.Modal(commonCodeModal).show()
    }else{
        alert("상위코드를 선택해주세요.")
    }
})

commonCodeModal.addEventListener('shown.bs.modal', event => {
    const firstInput = event.target.querySelector('.form-input');
    if (firstInput) {
        firstInput.focus();
    }
})
document.getElementById("addBtn").addEventListener("click", function () {
    const data = inputToJson("form-input")
    const subCode = data.subCode
    console.log(subCode)
    axios.post("/commonCode", data)
        .then(function (response) {
            console.log(response)
            bootstrap.Modal.getInstance(commonCodeModal).hide();
            if(subCode === "00"){
                majorCodeTable.replaceData("/commonCode/major")
            }else{
                subCodeTable.replaceData("/commonCode/sub", majorCodeTable.getData("select")[0])
            }

        }).catch(function (error) {
        console.log(error)
    })
})


commonCodeModal.addEventListener('hidden.bs.modal', event => {
    inputToNull("form-input")
})

let subCodeTable = new Tabulator("#subCodeTable", {
    height: "45rem",
    layout:"fitData",
    columns:[
        {title:"하위코드", field:"subCode"},
        {title:"코드명", field:"commonCodeName", editor:"input"},
        {title:"비고", field:"remarks", editor:"input"},
        {title:"사용유무", field:"useStatus", hozAlign: "center", formatter:"tickCross", editor: "tickCross"},
    ],
});

subCodeTable.on("cellEdited", function(cell){
    const rowData = cell.getRow().getData();

    axios.put("/commonCode/"+rowData.commonCodeId, rowData)
        .then(function (response) {
            console.log(response)
        }).catch(function (error) {
        console.log(error)
    })
});