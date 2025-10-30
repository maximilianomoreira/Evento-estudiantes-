const form = document.getElementById('form-register');
const name = document.getElementById('name');
const email = document.getElementById('email');
const password = document.getElementById('password');
const confirmPassword = document.getElementById('confirmPassword');
const errorGlobal = document.getElementById('error-global');

form.addEventListener('submit', e => {
    errorGlobal.textContent = '';

    const n = name.value.trim();
    const mail = email.value.trim();
    const pass = password.value.trim();
    const pass2 = confirmPassword.value.trim();

    if (!n || !mail || !pass || !pass2) {
        e.preventDefault();
        errorGlobal.textContent = 'Completa todos los campos.';
        return;
    }

    const formato = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!formato.test(mail)) {
        e.preventDefault();
        errorGlobal.textContent = 'Ingresa un correo valido.';
        return;
    }

    if (pass.length < 3) {
        e.preventDefault();
        errorGlobal.textContent = 'La contraseña debe tener al menos 3 caracteres.';
        return;
    }

    if (pass !== pass2) {
        e.preventDefault();
        errorGlobal.textContent = 'Las contraseñas no coinciden.';
    }
});