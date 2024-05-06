var memberTable = new Tabulator("#memberTable", {
    height: "45rem",
    ajaxURL:"/member",
    layout:"fitData",
    selectableRows: "1",
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"id", field:"mid"},
        {title:"사용자명", field:"mname"},
        {title:"사용자타입", field:"roleName"},
        {title:"사용유무", field:"active", hozAlign: "center", formatter:"tickCross"}
    ],
});

let modalTitle = document.querySelector("#memberModal .modal-title");
let modalMid = document.querySelector("#mid");

document.getElementById("addedModalBtn").addEventListener("click", function () {
    console.log(memberTable.getData())
    modalTitle.textContent = "사용자 추가"
    modalMid.readOnly = false
    document.getElementById("roleId").selectedIndex = 0;
    document.getElementById("active").checked = true

    document.querySelectorAll('.addedModal').forEach(function(element) {
        element.classList.remove('d-none');
    });
    document.querySelectorAll('.modifiedModal').forEach(function(element) {
        element.classList.add('d-none');
    });
})

const memberModal = document.getElementById('memberModal')

document.getElementById("modifiedModalBtn").addEventListener("click", function (e) {
    const rows = memberTable.getRows("selected");
    if(rows.length === 0){
        return alert("수정할 사용자를 선택해주세요");
    }else{
        modalTitle.textContent = "사용자 수정"
        modalMid.readOnly = true
        new bootstrap.Modal(memberModal).show()
        document.getElementById('mid').value = rows[0].getData().mid;
        document.getElementById('mname').value = rows[0].getData().mname;
        document.getElementById('roleId').value = rows[0].getData().roleId;
        document.getElementById('active').checked = rows[0].getData().active;

        document.querySelectorAll('.addedModal').forEach(function(element) {
            element.classList.add('d-none');
        });
        document.querySelectorAll('.modifiedModal').forEach(function(element) {
            element.classList.remove('d-none');
        });
    }
})

memberModal.addEventListener('shown.bs.modal', event => {
    const inputElements = event.target.querySelectorAll('.form-input');
    if (inputElements.length > 0) {
        focusFirstValidInput(inputElements);
    }
})
document.getElementById("addMemberBtn").addEventListener("click", function () {
    const data = inputToJson("form-input")

    axios.post("/member", data)
        .then(function (response) {
            console.log(response)
            bootstrap.Modal.getInstance(memberModal).hide();
            memberTable.replaceData("/member")
        }).catch(function (error) {
        console.log(error)
    })
})

document.getElementById("modifyMemberBtn").addEventListener("click", function () {
    console.log("멤버 수정")
    const data = inputToJson("form-input")

    axios.put("/member", data)
        .then(function (response) {
            console.log(response)
            bootstrap.Modal.getInstance(memberModal).hide();
            memberTable.replaceData("/member")
        }).catch(function (error) {
        console.log(error)
    })
})

memberModal.addEventListener('hidden.bs.modal', event => {
    inputToNull("form-input")
})

document.getElementById("pwReset").addEventListener("click", function () {
    let data = inputToJson("form-input")
    data.mpw = "1111"
    axios.put("/member/password", data)
        .then(function (response) {
            console.log(response)
            bootstrap.Modal.getInstance(memberModal).hide();
            memberTable.replaceData("/member")
        }).catch(function (error) {
        console.log(error)
    })
})