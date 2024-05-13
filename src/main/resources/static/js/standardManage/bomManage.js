const categoryId = document.getElementById("categoryId");
function handleSelectChange() {
    //itemTable.setData(this.value === "" ? "/itemManage" : "/itemManage/" + this.value)
    itemTable.setData("/itemManage", {"categoryId": this.value})
}
categoryId.addEventListener("change",handleSelectChange)

const itemTable = new Tabulator("#itemTable", {
    height: "45rem",
    layout: "fitData",
    ajaxURL: "/itemManage",
    ajaxParams:{"categoryId": categoryId.value},
    selectableRows: "1",
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"품목코드", field:"code"},
        {title:"품목명", field:"name"},
        {title:"규격", field:"specification"},
        {title:"분류", field:"categoryName"}
    ],
});

itemTable.on("rowSelected", function(row){
    bomManageTable.setData("/bomManage", row.getData())
        .then(function(){
            console.log(bomManageTable.getData())
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
        {title:"분류", field:"itemCategoryName"},
        {title:"수량", field:"quantity", hozAlign: "right", editor:"input"},
    ],
});

bomManageTable.on("rowClick", function(e, row){
    row.getTable().deselectRow();
    row.select();
});

bomManageTable.on("cellEdited", function(cell){
    const rowData = cell.getRow().getData();

    axios.put("/bomManage", rowData)
});

const addBomModal = document.getElementById('addBomModal')

document.getElementById("addBomModalBtn").addEventListener("click", function () {
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

document.getElementById("addBomBtn").addEventListener("click", function () {
    const data = inputToJson("form-input")

    axios.post("/bomManage", data)
        .then(function (response) {
            alert("저장되었습니다.")
            bootstrap.Modal.getInstance(addBomModal).hide();
            bomManageTable.setData("/bomManage", itemTable.getData("selected")[0])
        }).catch(function (error) {
            alert(error.response.data.message)
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
                    alert("삭제되었습니다.")
                }).catch(function (error) {
                console.log(error)
            })
        }
    }else{
        alert("행을 선택해주세요.")
    }
})