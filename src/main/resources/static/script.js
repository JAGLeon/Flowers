function qs(element) {
    return document.querySelector(element)
}

let wrapper = qs('.wrapper'),
    registerLink = qs('.registerLink'),
    loginLink = qs('.loginLink'),
    btnPopUpL = qs('.btnLogin-popUp'),
    btnPopUpR = qs('.btnRegister-popUp'),
    iconClose = qs('.iconClose');

let lookIcon = document.querySelectorAll('.lookIcon');
let inputPass = document.querySelectorAll('.inputPass');


registerLink.addEventListener('click', () => {
    wrapper.classList.add('active');
});

loginLink.addEventListener('click', () => {
    wrapper.classList.remove('active');
});

btnPopUpL.addEventListener('click', () => {
    wrapper.classList.add('activePopUp');
    wrapper.classList.remove('active');
});

btnPopUpR.addEventListener('click', () => {
    wrapper.classList.add('active');
    wrapper.classList.add('activePopUp');
});

iconClose.addEventListener('click', () => {
    wrapper.classList.remove('activePopUp');
});

for (let i = 0; i < lookIcon.length; i++) {
    lookIcon[i].addEventListener("click", () => {
        if (inputPass[i].type == "password") {
            inputPass[i].type = "text";
            lookIcon[i].style.opacity = 0.2;
        } else {
            inputPass[i].type = "password";
            lookIcon[i].style.opacity = 1;
        }
    })
};
