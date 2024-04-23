var rightsTable = new Tabulator("#rightsTable", {
    height: "45rem",
    ajaxURL:"/commonCode/sub",
    ajaxParams: {"majorCode" : "01"},
    layout:"fitData",
    selectableRows: "1",
    columns:[
        {title:"사용자 코드", field:"commonCodeId"},
        {title:"사용자 타입", field:"commonCodeName"}
    ],
});

rightsTable.on("rowClick", function(e, row){
    row.getTable().deselectRow();
    row.select();
});

rightsTable.on("rowSelected", function(row){
    menuRightsTable.setData("/menuRights/"+row.getData().commonCodeId);
});

var menuRightsTable = new Tabulator("#menuRightsTable", {
    height: "45rem",
    layout:"fitData",
    columns:[
        {title:"메뉴코드", field:"menuId"},
        {title:"메뉴명", field:"menuName"},
        {title:"사용유무", field:"useStatus", hozAlign: "center", formatter:"tickCross", editor: "tickCross"},
    ],
});

menuRightsTable.on("cellEdited", function(cell){
    const rowData = cell.getRow().getData();
    console.log(rowData);

    axios.put("/menuRights", rowData)
        .then(function (response) {
            console.log(response)
        }).catch(function (error) {
        console.log(error)
    })
});