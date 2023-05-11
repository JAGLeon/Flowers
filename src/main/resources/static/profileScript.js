
const inputFile = document.getElementById('iconUser');
const img = document.getElementById('imgUser');
const divImg = document.querySelector('.containerImgProfile');
let inputPassProfile = document.getElementById('inputPass');
let lookIconProfile = document.getElementById('lookIcon');


inputFile.addEventListener('change', e => {
    if (e.target.files[0]) {
        const reader = new FileReader();
        reader.onload = function (e) {
            divImg.style.display = "block";
            img.src = e.target.result;
        }
        reader.readAsDataURL(e.target.files[0])
    } else {
        divImg.style.display = "none";
    }
});

lookIconProfile.addEventListener("click", () => {
        if (inputPassProfile.type == "password") {
            inputPassProfile.type = "text";
            lookIconProfile.style.opacity = 0.2;
        } else {
            inputPassProfile.type = "password";
            lookIconProfile.style.opacity = 1;
        }
})