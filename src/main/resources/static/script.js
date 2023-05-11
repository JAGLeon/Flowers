let $lookIcon = document.querySelectorAll('.lookIcon');
let $inputPass = document.querySelectorAll('.inputPass');

for (let i = 0; i < $lookIcon.length; i++) {
    $lookIcon[i].addEventListener("click", () => {
        if ($inputPass[i].type == "password") {
            $inputPass[i].type = "text";
            $lookIcon[i].style.opacity = 0.2;
        } else {
            $inputPass[i].type = "password";
            $lookIcon[i].style.opacity = 1;
        }
    })
};

function qs(element) {
    return document.querySelector(element);
};

let $inputName = qs('#name'),
$inputEmail = qs('#email'),
$inputPassword = qs('#password'),
$inputPassword2 = qs('#password2'), 
$formRegister = qs('#formRegister'),
$errors = qs('#errors'),
$iconPerson = qs('#iconPerson'),
$iconEmail = qs('#iconEmail'),
$iconPass = qs('#iconPass'),
$iconPass2 = qs('#iconPass2'),
$cardMsjErrorJs = qs('#cardMsjErrorJs'),
regExAlpha = /^[a-zA-Z\sñáéíóúü ]*$/,
regExEmail = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i,
regExPass = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$/;

$inputName.addEventListener("blur", () => {
    switch (true) {
        case !$inputName.value.trim():
            $errors.innerHTML = "Ingrese su nombre  ";
            $iconPerson.classList.add("is-invalid");
            $inputName.classList.add("is-invalid");
            $cardMsjErrorJs.classList.add("blockError");
            break;
        case $inputName.value.trim().length <= 2:
            $errors.innerHTML = "Mínimo 2 carácteres  ";
            $iconPerson.classList.add("is-invalid");
            $inputName.classList.add("is-invalid");
            $cardMsjErrorJs.classList.add("blockError");
            break;
        case !regExAlpha.test($inputName.value):
            $errors.innerHTML = "Nombre inválido  ";
            $iconPerson.classList.add("is-invalid");
            $inputName.classList.add("is-invalid");
            $cardMsjErrorJs.classList.add("blockError");
            break;
        default: 
            $iconPerson.classList.remove("is-invalid");
            $inputName.classList.remove("is-invalid");
            $cardMsjErrorJs.classList.remove("blockError");
            $errors.innerHTML = "";
            break;
    };
});

$inputEmail.addEventListener("blur", () => {
    switch (true) {
        case !$inputEmail.value.trim():
            $errors.innerHTML = "Campo obligatorio complete con su email";
            $iconEmail.classList.add("is-invalid");
            $inputEmail.classList.add("is-invalid");
            $cardMsjErrorJs.classList.add("blockError");
            break;
        case !regExEmail.test($inputEmail.value):
            $errors.innerHTML = "Email inválido";
            $iconEmail.classList.add("is-invalid");
            $inputEmail.classList.add("is-invalid");
            $cardMsjErrorJs.classList.add("blockError");
            break;
        default: 
            $iconEmail.classList.remove("is-invalid");
            $inputEmail.classList.remove("is-invalid");
            $cardMsjErrorJs.classList.remove("blockError");
            $errors.innerHTML = "";
            break;
    };
});

$inputPassword.addEventListener('blur', function(){
    switch (true) {
        case !$inputPassword.value.trim():
            $errors.innerHTML = 'Campo obligatorio complete con una contraseña';
            $iconPass.classList.add('is-invalid');
            $inputPassword.classList.add('is-invalid');
            $cardMsjErrorJs.classList.add("blockError");
            break;
        case !regExPass.test($inputPassword.value):
            $errors.innerHTML = 'Mínimo 8 carácteres, debe tener mayúscula, minúscula, número';
            $iconPass.classList.add('is-invalid');
            $inputPassword.classList.add('is-invalid');
            $cardMsjErrorJs.classList.add("blockError");
            break;  
        default:
            $iconPass.classList.remove("is-invalid");
            $inputPassword.classList.remove("is-invalid");
            $cardMsjErrorJs.classList.remove("blockError");
            $errors.innerHTML = "";
            break;
    };
});

$inputPassword2.addEventListener('blur', function(){
    switch (true) {
        case !$inputPassword2.value.trim():
            $errors.innerHTML = 'Reingrese la contraseña';
            $iconPass2.classList.add('is-invalid');
            $inputPassword2.classList.add('is-invalid');
            $cardMsjErrorJs.classList.add("blockError");
            break;
        case $inputPassword2.value !== $inputPassword.value:
            $errors.innerHTML = 'Las contraseñas no coinciden';
            $iconPass2.classList.add('is-invalid');
            $inputPassword2.classList.add('is-invalid');
            $cardMsjErrorJs.classList.add("blockError");
            break;
        default:
            $iconPass2.classList.remove('is-invalid');
            $inputPassword2.classList.remove('is-invalid');
            $cardMsjErrorJs.classList.remove("blockError");
            $errors.innerHTML = "";
            break;
    };
});


$formRegister.addEventListener("submit", function(event) {

    event.preventDefault();
    let elementsForm = this.elements;
    let errores = false;

    for (let index = 0; index < elementsForm.length - 1; index++) {
        if(elementsForm[index].value == ""
        && elementsForm[index].type !== "file"
        || elementsForm[index].classList.contains("is-invalid")){
            elementsForm[index].classList.add("is-invalid");
            submitErrors.style.color = "red"
            submitErrors.innerHTML = "Hay errores en el formulario"
            errores = true;
        }
    };

    if(!errores){
        $formRegister.submit()
    };

});





