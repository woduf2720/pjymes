const pwChangeModal = document.getElementById('pwChangeModal')
const newPwChangeModal = new bootstrap.Modal(pwChangeModal);

pwChangeModal.addEventListener('shown.bs.modal', event => {
    const firstInput = event.target.querySelector('.form-input');
    if (firstInput) {
        firstInput.focus();
    }
})
document.getElementById("pwChangeBtn").addEventListener("click", function () {
    const data = inputToJson(".form-input")

    var newPassword = data.mpw;
    var confirmPassword = data.confirmPassword;

    // 현재 비밀번호, 새 비밀번호, 비밀번호 확인 필드가 비어 있는지 확인
    if (newPassword.trim() === '' || confirmPassword.trim() === '') {
        alert("모든 필드를 입력하세요.");
        return false;
    }

    // 새 비밀번호와 비밀번호 확인이 일치하는지 확인
    if (newPassword !== confirmPassword) {
        alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        return false;
    }

    axios.put("/member/password", data)
        .then(function (response) {
            newPwChangeModal.hide();
            alert("변경되었습니다.")
        }).catch(function (error) {
    })
})