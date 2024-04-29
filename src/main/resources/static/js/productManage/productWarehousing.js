
const itemTable = new Tabulator("#itemTable", {
    height: "45rem",
    ajaxURL:"/itemManage",
    layout:"fitData",
    selectableRows: true,
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"품목코드", field:"code"},
        {title:"품목명", field:"name"},
        {title:"규격", field:"specification"},
        {title:"분류", field:"category"},
    ],
});

itemTable.on("rowSelectionChanged", function(data, rows, selected, deselected){
    let newData = []
    data.forEach(d => {
        newData.push({
            "itemCode" : d.code,
            "itemName" : d.name,
            "specification" : d.specification,
            "category" : d.category
        })
    })
    lotTable.setData(newData)
});

const lotTable = new Tabulator("#lotTable", {
    height: "45rem",
    layout:"fitData",
    selectableRows: true,
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"품목코드", field:"itemCode"},
        {title:"품목명", field:"itemName"},
        {title:"규격", field:"specification"},
        {title:"분류", field:"category"},
        {title:"수량", field:"quantity", editor : "input"},
    ],
});

document.getElementById("saveBtn").addEventListener("click", function () {

    axios.post("/productWarehousing", lotTable.getData())
        .then(function (response) {
            itemTable.replaceData();
            lotTable.clearData();
            alert("저장되었습니다.")
        }).catch(function (error) {
    })
})