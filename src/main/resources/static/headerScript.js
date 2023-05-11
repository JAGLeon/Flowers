function qs(element) {
    return document.querySelector(element)
}

let $wrapper = qs('.wrapper'),
    $registerLink = qs('.registerLink'),
    $loginLink = qs('.loginLink'),
    $btnPopUpL = qs('.btnLogin-popUp'),
    $btnPopUpR = qs('.btnRegister-popUp'),
    $iconClose = qs('.iconClose');

$registerLink.addEventListener('click', () => {
    $wrapper.classList.add('active');
});

$loginLink.addEventListener('click', () => {
    $wrapper.classList.remove('active');
});

$btnPopUpL.addEventListener('click', () => {
    $wrapper.classList.add('activePopUp');
    $wrapper.classList.remove('active');
});

$btnPopUpR.addEventListener('click', () => {
    $wrapper.classList.add('active');
    $wrapper.classList.add('activePopUp');
});

$iconClose.addEventListener('click', () => {
    $wrapper.classList.remove('activePopUp');
});