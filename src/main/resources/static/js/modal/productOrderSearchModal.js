const productOrderSearchModal = document.getElementById('productOrderSearchModal');
const newProductOrderSearchModal = new bootstrap.Modal(productOrderSearchModal);

var originTag = []
productOrderSearchModal.addEventListener('shown.bs.modal', event => {
    event.target.querySelector('.tabulator-tableholder').focus();

    productOrderSearchTable.setData("/productOrder", originTag[0])
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
        {title:"발주번호", field:"orderNo"},
        {title:"거래처명", field:"customerName"},
        {title:"발주일자", field:"orderDate"},
        {title:"납기일자", field:"deliveryDate"},
        {title:"합계금액", field:"price"}
    ],
});

productOrderSearchTable.on("rowDblClick", function(e, row){
    newProductOrderSearchModal.hide();
    getModalData(row.getData())
});