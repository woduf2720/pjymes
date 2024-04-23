var menuTable = new Tabulator("#menuTable", {
    height: "45rem",
    ajaxURL:"/menu",
    layout:"fitData",
    dataTree:true,
    dataTreeStartExpanded:true,
    dataTreeChildField:"children",
    editTriggerEvent:"dblclick",
    columns:[
        {title:"id", field:"menuId"},
        {title:"순서", field:"displayOrder", editor:"input"},
        {title:"메뉴명", field:"menuName", editor:"input"},
        {title:"url", field:"url", editor:"input"}
    ],
});

menuTable.on("rowClick", function(e, row){
    row.getTable().deselectRow();
    row.select();
});

menuTable.on("cellEdited", function(cell){
    const rowData = cell.getRow().getData();

    axios.put("/menu/"+rowData.menuId, rowData)
        .then(function (response) {
            console.log(response)
        }).catch(function (error) {
        console.log(error)
    })
});

document.getElementById("deleteBtn").addEventListener("click", function () {
    const row = menuTable.getSelectedRows()[0];
    if(row){
        axios.delete("/menu/"+row.getData().menuId)
            .then(function (response) {
                console.log(response)
                menuTable.deleteRow(row)
            }).catch(function (error) {
            console.log(error)
        })
    }else{
        alert("삭제할 행을 선택해주세요.")
    }
})

const addMenuModal = document.getElementById('addMenuModal')

addMenuModal.addEventListener('shown.bs.modal', event => {
    const inputElements = event.target.querySelectorAll('.form-input');
    if (inputElements.length > 0) {
        focusFirstValidInput(inputElements);
        let selectedRows = menuTable.getSelectedRows()
        if(selectedRows.length > 0){
            let data = selectedRows[0].getData()
            if(data.parentId == null) {
                document.getElementById('parentId').value = data.menuId
            }else{

            }
        }
    }
})

document.getElementById("addBtn").addEventListener("click", function () {
    const data = inputToJson("form-input")

    axios.post("/menu", data)
        .then(function (response) {
            console.log(response)
            bootstrap.Modal.getInstance(addMenuModal).hide();
            menuTable.replaceData("/menu")
        }).catch(function (error) {
        console.log(error)
    })
})


addMenuModal.addEventListener('hidden.bs.modal', event => {
    inputToNull("form-input")
})