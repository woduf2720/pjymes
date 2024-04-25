var rightsTable = new Tabulator("#rightsTable", {
    height: "45rem",
    ajaxURL:"/commonCode/1",
    layout:"fitData",
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"사용자 타입", field:"name"}
    ],
});

rightsTable.on("rowClick", function(e, row){
    row.getTable().deselectRow();
    row.select();
});

rightsTable.on("rowSelected", function(row){
    menuRightsTable.setData("/menuRights/"+row.getData().id);
});

var menuRightsTable = new Tabulator("#menuRightsTable", {
    height: "45rem",
    layout:"fitData",
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"메뉴명", field:"menuName"},
        {title:"사용유무", field:"hasAccess", hozAlign: "center", formatter:"tickCross", editor: "tickCross"},
    ],
});

menuRightsTable.on("cellEdited", function(cell){
    const rowData = cell.getRow().getData();

    axios.put("/menuRights", rowData)
        .then(function (response) {
            console.log(response)
        }).catch(function (error) {
        console.log(error)
    })
});