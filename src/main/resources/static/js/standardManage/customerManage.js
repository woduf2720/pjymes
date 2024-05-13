var customerManageTable = new Tabulator("#customerManageTable", {
    height: "45rem",
    ajaxURL:"/customerManage",
    layout:"fitData",
    selectableRows: "1",
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"거래처코드", field:"code"},
        {title:"거래처명", field:"name"},
        {title:"분류", field:"categoryName"},
        {title:"사업자 등록번호", field:"registrationNumber"},
        {title:"주소", field:"address"},
        {title:"담당자", field:"manager"},
        {title:"담당자 전화번호", field:"managerPhone"},
        {title:"담당자 이메일", field:"managerEmail"},
        {title:"사용유무", field:"active", hozAlign: "center", formatter:"tickCross"},
    ],
});

let modalTitle = document.querySelector("#customerModal .modal-title");
let customerCode = document.querySelector("#code");

document.getElementById("addedModalBtn").addEventListener("click", function () {
    modalTitle.textContent = "거래처 추가"
    customerCode.readOnly = false
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
    const rows = customerManageTable.getRows("selected");
    if(rows.length === 0){
        return alert("수정할 품목을 선택해주세요");
    }else{
        modalTitle.textContent = "거래처 수정"
        customerCode.readOnly = true
        new bootstrap.Modal(document.getElementById('customerModal')).show();

        document.getElementById('code').value = rows[0].getData().code;
        document.getElementById('name').value = rows[0].getData().name;
        document.getElementById('categoryId').value = rows[0].getData().categoryId;
        document.getElementById('registrationNumber').value = rows[0].getData().registrationNumber;
        document.getElementById('address').value = rows[0].getData().address;
        document.getElementById('manager').value = rows[0].getData().manager;
        document.getElementById('managerPhone').value = rows[0].getData().managerPhone;
        document.getElementById('managerEmail').value = rows[0].getData().managerEmail;
        document.getElementById('active').checked = rows[0].getData().active;

        document.querySelectorAll('.addedModal').forEach(function(element) {
            element.classList.add('d-none');
        });
        document.querySelectorAll('.modifiedModal').forEach(function(element) {
            element.classList.remove('d-none');
        });
    }
})

const customerModal = document.getElementById('customerModal')

customerModal.addEventListener('shown.bs.modal', event => {
    const inputElements = event.target.querySelectorAll('.form-input');
    if (inputElements.length > 0) {
        focusFirstValidInput(inputElements);
    }
})
customerModal.addEventListener('hidden.bs.modal', event => {
    inputToNull("form-input")
})
document.getElementById("addBtn").addEventListener("click", function () {
    const data = inputToJson("form-input")

    axios.post("/customerManage", data)
        .then(function (response) {
            alert("저장되었습니다.")
            console.log(response)
            bootstrap.Modal.getInstance(customerModal).hide();
            customerManageTable.replaceData("/customerManage")
        }).catch(function (error) {
        alert(error.response.data.message);
    })
})

document.getElementById("modifyBtn").addEventListener("click", function () {
    const data = inputToJson("form-input")

    axios.put("/customerManage", data)
        .then(function (response) {
            alert("수정되었습니다.")
            bootstrap.Modal.getInstance(customerModal).hide();
            customerManageTable.replaceData("/customerManage")
        }).catch(function (error) {
        console.log(error)
    })
})