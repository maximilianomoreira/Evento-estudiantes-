document.addEventListener("DOMContentLoaded", () => {    const form = document.getElementById('form-login');
    const email = document.getElementById('email');
    const password = document.getElementById('password');
    const errorGlobal = document.getElementById('error-global');

    form.addEventListener('submit', e => {
        errorGlobal.textContent = '';

        const mail = email.value.trim();
        const pass = password.value.trim();

        if (!mail || !pass) {
            e.preventDefault();
            errorGlobal.textContent = 'Completa todos los campos.';
            return;
        }

        const formato = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!formato.test(mail)) {
            e.preventDefault();
            errorGlobal.textContent = 'Ingresa un correo valido.';
        }
    });
});