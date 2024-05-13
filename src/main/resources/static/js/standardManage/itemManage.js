var itemManageTable = new Tabulator("#itemManageTable", {
    height: "45rem",
    ajaxURL:"/itemManage",
    layout:"fitData",
    selectableRows: "1",
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"품목코드", field:"code"},
        {title:"품목명", field:"name"},
        {title:"규격", field:"specification"},
        {title:"분류", field:"categoryName"},
        {title:"단가", field:"unitPrice"},
        {title:"사용유무", field:"active", hozAlign: "center", formatter:"tickCross"},
    ],
});

let modalTitle = document.querySelector("#itemModal .modal-title");
let itemCode = document.querySelector("#code");

document.getElementById("addedModalBtn").addEventListener("click", function () {
    console.log(itemManageTable.getData());
    modalTitle.textContent = "품목 추가"
    itemCode.readOnly = false
    document.getElementById("categoryId").selectedIndex = 0;
    document.getElementById("active").checked = true

    document.querySelectorAll('.addedModal').forEach(function(element) {
        element.classList.remove('d-none');
    });
    document.querySelectorAll('.modifiedModal').forEach(function(element) {
        element.classList.add('d-none');
    });
})

document.getElementById("modifiedModalBtn").addEventListener("click", function (e) {
    const rows = itemManageTable.getRows("selected");
    if(rows.length === 0){
        return alert("수정할 품목을 선택해주세요");
    }else{
        modalTitle.textContent = "품목 수정"
        itemCode.readOnly = true
        new bootstrap.Modal(document.getElementById('itemModal')).show()
        document.getElementById('code').value = rows[0].getData().code;
        document.getElementById('name').value = rows[0].getData().name;
        document.getElementById('specification').value = rows[0].getData().specification;
        document.getElementById('categoryId').value = rows[0].getData().categoryId;
        document.getElementById('unitPrice').value = rows[0].getData().unitPrice;
        document.getElementById('active').checked = rows[0].getData().active;

        document.querySelectorAll('.addedModal').forEach(function(element) {
            element.classList.add('d-none');
        });
        document.querySelectorAll('.modifiedModal').forEach(function(element) {
            element.classList.remove('d-none');
        });
    }
})

const itemModal = document.getElementById('itemModal')

itemModal.addEventListener('shown.bs.modal', event => {
    const inputElements = event.target.querySelectorAll('.form-input');
    if (inputElements.length > 0) {
        focusFirstValidInput(inputElements);
    }
})
itemModal.addEventListener('hidden.bs.modal', event => {
    inputToNull("form-input")
})

document.getElementById("addBtn").addEventListener("click", function () {
    const data = inputToJson("form-input")

    axios.post("/itemManage", data)
        .then(function (response) {
            alert("저장되었습니다.")
            bootstrap.Modal.getInstance(itemModal).hide();
            itemManageTable.replaceData("/itemManage")
        }).catch(function (error) {
        alert(error.response.data.message);
    })
})

document.getElementById("modifyBtn").addEventListener("click", function () {
    const data = inputToJson("form-input")

    axios.put("/itemManage", data)
        .then(function (response) {
            alert("수정되었습니다.")
            bootstrap.Modal.getInstance(itemModal).hide();
            itemManageTable.replaceData("/itemManage")
        }).catch(function (error) {
        console.log(error)
    })
})
