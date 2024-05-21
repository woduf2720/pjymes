const fullscreenBtn = document.getElementById("fullscreen-btn");

// 전체 화면 토글 함수
function toggleFullScreen() {
    if (!document.fullscreenElement) {
        document.documentElement.requestFullscreen(); // 전체 화면으로 진입
    } else {
        if (document.exitFullscreen) {
            document.exitFullscreen(); // 전체 화면에서 나가기
        }
    }
}

// 전체 화면 버튼에 클릭 이벤트 리스너 추가
fullscreenBtn.addEventListener("click", toggleFullScreen);

function updateRealTime() {
    const dateTime = luxon.DateTime.now().setLocale("kr");
    document.getElementById("real-time").textContent = dateTime.toLocaleString({
        weekday: "long",
        month: "long",
        day: "2-digit",
        year: "numeric",
        hour: "2-digit",
        minute: "2-digit",
        second: "2-digit",
        hour12: true // 오전/오후 표시
    });
}

const workCompleteBtn = document.getElementById("workCompleteBtn")
workCompleteBtn.addEventListener("click",function(){
    const data = inputToJson("#productionActivity .form-input")
    axios.put("/productionPlan", data)
        .then(function (response) {
            alert("작업이 완료되었습니다.")
            inputToNull("#productionActivity .form-input")
            productionActivityTable.replaceData();
        }).catch(function (error) {
        alert(error.response.data);
    })
})

function workingData(){
    axios.get("/productionPlan/status/1")
        .then(function (response) {
            console.log(response)
            if(response.data.length > 0){
                jsonToInput(response.data[0])
            }
        }).catch(function (error) {
        alert(error.response);
    })
}

function getModalData(data){
    axios.put("/productionPlan", data)
        .then(function (response) {
            console.log(response)
            jsonToInput(response.data)
        }).catch(function (error) {
        alert(error.response.data);
    })
}

const sse = new EventSource("/productionActivity/connect");

sse.addEventListener('notification', (e) => {
    const data = JSON.parse(e.data);
    document.getElementById("productionQuantity").value = data.productionQuantity;
});

var productionActivityTable = new Tabulator("#productionActivityTable", {
    height: "34vh",
    ajaxURL:"/productionPlan/status/2",
    layout:"fitData",
    columns:[
        {title:"순번", field:"rownum", hozAlign: "center", formatter: "rownum"},
        {title:"계획번호", field:"planNo"},
        {title:"수주번호", field:"orderNo"},
        {title:"거래처코드", field:"orderSubCustomerCode"},
        {title:"거래처명", field:"orderSubCustomerName"},
        {title:"품목코드", field:"orderSubItemCode"},
        {title:"품목명", field:"orderSubItemName"},
        {title:"지시수량", field:"orderQuantity"}
    ],
});

document.addEventListener("DOMContentLoaded", function() {
    workingData()
    updateRealTime();
    setInterval(updateRealTime, 1000);
});