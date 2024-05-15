function inputToJson(className) {
    const inputElements = document.querySelectorAll(className);
    const jsonData = {};

    inputElements.forEach(input => {
        const id = input.id;
        const value = input.type === 'checkbox' ? input.checked : input.value;
        jsonData[id] = value;
    });

    return jsonData;
}

function inputToNull(className) {
    const inputElements = document.querySelectorAll(className);

    inputElements.forEach(input => {
        if (input.type === 'checkbox') {
            input.checked = false;
        } else {
            input.value = null;
        }
    });
}

function focusFirstValidInput(inputElements) {
    // 첫 번째 readonly나 disabled가 아닌 input 태그를 찾음
    let nextFocusElement = null;
    for (let i = 0; i < inputElements.length; i++) {
        const input = inputElements[i];
        const isReadOnly = input.readOnly || input.disabled;
        const isHidden = window.getComputedStyle(input).display === 'none';

        if (!isReadOnly && !isHidden && input.type !== 'hidden') {
            nextFocusElement = input;
            break;
        }
    }

    // 찾은 요소에 포커스 설정
    if (nextFocusElement) {
        nextFocusElement.focus();
    }
}

Tabulator.extendModule("keybindings", "actions", {
    "selectedRowPrev":function(){ //delete selected rows
        const row = this.table.getSelectedRows()[0];
        const prevRow = row.getPrevRow();
        if(prevRow){
            row.getTable().deselectRow();
            prevRow.select()
        }
    },
    "selectedRowNext":function(){ //delete selected rows
        const row = this.table.getSelectedRows()[0];
        const nextRow = row.getNextRow ();
        if(nextRow){
            row.getTable().deselectRow();
            nextRow.select()
        }
    },
});

function handleEnterKey(event) {
    if (event.key === "Enter"){
        const targetModal = event.target.closest(".modal")
        const isModal = targetModal.classList.contains("modal");
        if(isModal){
            const kind = event.target.parentNode.id;
            let tempTable = Tabulator.findTable("#"+kind)[0];
            let tempData = tempTable.getData("selected")[0]
            if(kind === "customerSearchTable"){
                customerSearchResult(tempData)
            }else if(kind === "itemSearchTable"){
                itemSearchResult(tempData)
            }else if(kind === "productOrderSearchTable"){
                getModalData(tempData)
            }
            bootstrap.Modal.getInstance(targetModal).hide()
        }
    }
}

document.addEventListener("keydown", handleEnterKey);

document.addEventListener("DOMContentLoaded", function() {
    document.querySelectorAll('.todayDate').forEach(function(element) {
        element.value = luxon.DateTime.local().toFormat('yyyy-MM-dd')
    });
    document.querySelectorAll('.tomorrowDate').forEach(function(element) {
        element.value = luxon.DateTime.local().plus({ days: 1 }).toFormat('yyyy-MM-dd')
    });
});