document.addEventListener('DOMContentLoaded', () => {

    const loginForm = document.getElementById('login-form');
    const registerForm = document.getElementById('register-form');

    const toRegisterLink = document.getElementById('to-register-link');
    const toLoginLink = document.getElementById('to-login-link');

    const loginBtn = document.getElementById('login-btn');
    const registerBtn = document.getElementById('register-btn');


    toRegisterLink.addEventListener('click', (e) => {
        e.preventDefault();
        loginForm.classList.add('hidden');
        registerForm.classList.remove('hidden');
    });


    toLoginLink.addEventListener('click', (e) => {
        e.preventDefault();
        registerForm.classList.add('hidden');
        loginForm.classList.remove('hidden');
    });

    loginBtn.addEventListener('click', () => {
        console.log('Iniciando sesión...');
        window.location.href = 'index.html';
    });


    registerBtn.addEventListener('click', () => {
        console.log('Registrando usuario...');
        alert('¡Cuenta creada! Ya puedes iniciar sesión.');
        registerForm.classList.add('hidden');
        loginForm.classList.remove('hidden');
    });

});