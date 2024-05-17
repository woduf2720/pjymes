const productOrderSearchModal = document.getElementById('productOrderSearchModal');
const newProductOrderSearchModal = new bootstrap.Modal(productOrderSearchModal);

productOrderSearchModal.addEventListener('shown.bs.modal', event => {
    event.target.querySelector('.tabulator-tableholder').focus();

    productOrderSearchTable.setData("/productOrder/orderSub/uncompleted")
        .then(function(result){
            console.log(result)
            const rows = productOrderSearchTable.getRows();
            if(rows.length > 0){
                rows[0].select();
            }
        })
})

const productOrderSearchTable = new Tabulator("#productOrderSearchTable", {
    height: "20rem",
    layout:"fitData",
    keybindings:{
        "selectedRowPrev": "38",
        "selectedRowNext": "40"
    },
    selectRows: 1,
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"발주번호", field:"orderNo"},
        {title:"거래처명", field:"orderMasterCustomerName"},
        {title:"발주일자", field:"orderMasterOrderDate"},
        {title:"납기일자", field:"orderMasterDeliveryDate"},
        {title:"품목코드", field:"itemCode"},
        {title:"품목명", field:"itemName"},
        {title:"규격", field:"itemSpecification"},
        {title:"수주수량", field:"quantity", hozAlign: "right"},
        {title:"출고수량", field:"deliveryQuantity", hozAlign: "right"}
    ],
});

productOrderSearchTable.on("rowClick", function(e, row){
    row.getTable().deselectRow();
    row.select();
});

productOrderSearchTable.on("rowDblClick", function(e, row){
    newProductOrderSearchModal.hide();
    getModalData(row.getData())
});