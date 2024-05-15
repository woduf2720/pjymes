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


document.addEventListener("DOMContentLoaded", function() {
    document.querySelectorAll('.todayDate').forEach(function(element) {
        element.value = luxon.DateTime.local().toFormat('yyyy-MM-dd')
    });
    document.querySelectorAll('.tomorrowDate').forEach(function(element) {
        element.value = luxon.DateTime.local().plus({ days: 1 }).toFormat('yyyy-MM-dd')
    });
});