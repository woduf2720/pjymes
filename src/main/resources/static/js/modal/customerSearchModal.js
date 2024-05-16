const customerSearchModal = document.getElementById('customerSearchModal')
const newCustomerSearchModal = new bootstrap.Modal(customerSearchModal);

var originTag = []
customerSearchModal.addEventListener('shown.bs.modal', event => {
    event.target.querySelector('.tabulator-tableholder').focus();

    customerSearchTable.setData("/customerManage", originTag[0])
        .then(function(result){
            const rows = customerSearchTable.getRows();
            if(rows.length > 0){
                rows[0].select();
            }
        })
})

const customerSearch = document.querySelector(".customerSearch")

if (customerSearch) {
    customerSearch.addEventListener("keypress", function(event) {
        if (event.key === "Enter") {
            customerModalShow(customerSearch.value, customerSearch, "input")
        }
    })
}

function customerModalShow(value, tag, status) {
    originTag = [value, tag, status]
    newCustomerSearchModal.show();
}

var customerSearchTable = new Tabulator("#customerSearchTable", {
    height: "20rem",
    layout:"fitData",
    keybindings:{
        "selectedRowPrev": "38",
        "selectedRowNext": "40"
    },
    columns:[
        {title:"거래처코드", field:"code", headerFilter:"input"},
        {title:"거래처명", field:"name", headerFilter:"input"},
        {title:"분류", field:"categoryName", headerFilter:"input"}
    ],
});

customerSearchTable.on("rowClick", function(e, row){
    row.getTable().deselectRow();
    row.select();
});

customerSearchTable.on("rowDblClick", function(e, row){
    newCustomerSearchModal.hide();
    customerSearchResult(row.getData())
});

function customerSearchResult (data) {
    let tag = originTag[1]
    let stat = originTag[2]
    if(stat === "input") {
        tag.value = data.name;
    }else{
        tag.getRow().update({
            "customerCode" : data.code,
            "customerName" : data.name
        })
    }
}