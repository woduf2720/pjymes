var menuTable = new Tabulator("#menuTable", {
    height: "45rem",
    ajaxURL:"/menu",
    layout:"fitData",
    dataTree:true,
    dataTreeStartExpanded:true,
    dataTreeChildField:"children",
    editTriggerEvent:"dblclick",
    columns:[
        {title:"id", field:"id"},
        {title:"순서", field:"orderIndex", editor:"input", hozAlign:"center"},
        {title:"메뉴명", field:"name", editor:"input"},
        {title:"url", field:"url", editor:"input"},
        {title:"권한", field:"roleName"}
    ],
});

menuTable.on("rowClick", function(e, row){
    row.getTable().deselectRow();
    row.select();
});

menuTable.on("cellEdited", function(cell){
    const rowData = cell.getRow().getData();

    axios.put("/menu", rowData)
        .then(function (response) {
            console.log(response)
        }).catch(function (error) {
        console.log(error)
    })
});


document.getElementById("addMenuBtn").addEventListener("click", function () {
    const data = inputToJson(".form-input")

    axios.post("/menu", data)
        .then(function (response) {
            console.log(response)
            bootstrap.Modal.getInstance(addMenuModal).hide();
            menuTable.replaceData("/menu")
        }).catch(function (error) {
        console.log(error)
    })
})

const addMenuModal = document.getElementById('addMenuModal')

addMenuModal.addEventListener('shown.bs.modal', event => {
    const inputElements = event.target.querySelectorAll('.form-input');
    if (inputElements.length > 0) {
        document.getElementById("roleId").selectedIndex = 0;
        focusFirstValidInput(inputElements);
        let selectedRows = menuTable.getSelectedRows()

        if(selectedRows.length > 0){
            let data = selectedRows[0].getData()
            if(data.parentId == null) {
                document.getElementById('parentId').value = data.id
            }else{

            }
        }
    }
})

addMenuModal.addEventListener('hidden.bs.modal', event => {
    inputToNull(".form-input")
})

document.getElementById("deleteMenuBtn").addEventListener("click", function () {
    const row = menuTable.getSelectedRows()[0];

    if(row){
        if(confirm("선택한 행을 삭제하시겠습니까?")) {
            axios.delete("/menu/"+row.getData().id)
                .then(function (response) {
                    menuTable.deleteRow(row)
                        .then(function(){
                            alert("삭제되었습니다.")
                        })
                }).catch(function (error) {
                alert("하위 메뉴가 있으면 삭제 할 수 없습니다.")
            })
        }
    }else{
        alert("삭제할 행을 선택해주세요.")
    }
})