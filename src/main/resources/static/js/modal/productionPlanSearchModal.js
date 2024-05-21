const productionPlanSearchModal = document.getElementById('productionPlanSearchModal');
const newProductionPlanSearchModal = new bootstrap.Modal(productionPlanSearchModal);

productionPlanSearchModal.addEventListener('shown.bs.modal', event => {
    event.target.querySelector('.tabulator-tableholder').focus();

    productionPlanSearchTable.setData("/productionPlan/status/0")
        .then(function(){
            const rows = productionPlanSearchTable.getRows();
            if(rows.length > 0){
                rows[0].select();
            }
        })
})

const productionPlanSearchTable = new Tabulator("#productionPlanSearchTable", {
    height: "20rem",
    layout:"fitData",
    keybindings:{
        "selectedRowPrev": "38",
        "selectedRowNext": "40"
    },
    selectRows: 1,
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"계획번호", field:"planNo"},
        {title:"수주번호", field:"orderNo"},
        {title:"거래처코드", field:"orderSubCustomerCode"},
        {title:"거래처명", field:"orderSubCustomerName"},
        {title:"품목코드", field:"orderSubItemCode"},
        {title:"품목코드", field:"orderSubItemName"},
        {title:"규격", field:"orderSubItemSpecification"},
        {title:"분류", field:"orderSubItemCategoryName"},
        {title:"지시일", field:"orderDate"},
        {title:"마감일", field:"dueDate"},
        {title:"지시수량", field:"quantity", hozAlign: "right"}
    ],
});

productionPlanSearchTable.on("rowClick", function(e, row){
    row.getTable().deselectRow();
    row.select();
});

productionPlanSearchTable.on("rowDblClick", function(e, row){
    newProductionPlanSearchModal.hide();
    getModalData(row.getData())
});