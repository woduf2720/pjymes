const itemSearchModal = document.getElementById('itemSearchModal');
const newItemSearchModal = new bootstrap.Modal(itemSearchModal);

var originTag = []
itemSearchModal.addEventListener('shown.bs.modal', event => {
    const firstInput = event.target.querySelector('.form-input');
    if (firstInput) {
        firstInput.focus();
    }
    itemSearchTable.setData("/itemManage", originTag[0])
        .then(function(result){
            console.log(result)
            const rows = itemSearchTable.getRows();
            if(rows.length > 0){
                rows[0].select();
            }
        })
})
const itemSearch = document.getElementById("itemSearch")

if (itemSearch) {
    itemSearch.addEventListener("keypress", function(event) {
        if (event.key === "Enter") {
            itemModalShow(itemSearch.value, itemSearch, "input")
        }
    })
}

function itemModalShow(value, tag, status) {
    originTag = [value, tag, status]
    newItemSearchModal.show();
}

var itemSearchTable = new Tabulator("#itemSearchTable", {
    height: "20rem",
    layout:"fitData",
    keybindings:{
        "selectedRowPrev": "38",
        "selectedRowNext": "40"
    },
    columns:[
        {title:"품목코드", field:"code"},
        {title:"품목명", field:"name"},
    ],
});

itemSearchTable.on("rowClick", function(e, row){
    row.getTable().deselectRow();
    row.select();
});

itemSearchTable.on("rowDblClick", function(e, row){
    newItemSearchModal.hide();
    itemSearchResult(row.getData())
});

function itemSearchResult (data) {
    let tag = originTag[1]
    let stat = originTag[2]
    if(stat === "input") {
        tag.value = data.itemName;
    }else{
        tag.getRow().update({
            "itemCode" : data.code,
            "itemName" : data.name,
            "itemSpecification" : data.specification,
            "unitPrice" : data.unitPrice
        })
    }
}

const itemSearchModalInput = document.getElementById('itemSearchModalInput')

itemSearchModalInput.addEventListener("keypress", function (e) {
    let searchWord = this.value
    if(searchWord.length > 0){
        searchWord = "/"+searchWord
    }
    if(e.key === "Enter"){
        itemSearchTable.setData("/itemManage"+searchWord)
            .then(function(result){
                console.log(result)
                const rows = itemSearchTable.getRows();
                if(rows.length > 0){
                    rows[0].select();
                }

            })
    }
})