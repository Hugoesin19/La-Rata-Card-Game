document.addEventListener('DOMContentLoaded', () => {

    const AVATAR_COUNT = 15;

    const logoSection = document.getElementById('logo-section');
    const buttonsSection = document.getElementById('buttons-section');

    const profileAvatar = document.getElementById('profile-avatar');
    const profileUsername = document.getElementById('profile-username');

    const optionsBtn = document.getElementById('options-btn');
    const closeOptionsBtn = document.getElementById('close-options-btn');
    const optionsModal = document.getElementById('options-modal');

    const lobbyBtn = document.getElementById('lobby-btn');
    const closeLobbyBtn = document.getElementById('close-lobby-btn');
    const lobbyModal = document.getElementById('lobby-modal');

    const configBtn = document.getElementById('config-btn');
    const closeConfigBtn = document.getElementById('close-config-btn');
    const configModal = document.getElementById('config-modal');
    const avatarSelector = document.getElementById('avatar-selector');
    const currentAvatarPreview = document.getElementById('current-avatar-preview');
    const currentUsernameDisplay = document.getElementById('current-username-display');

    const createGameBtn = document.getElementById('create-game-btn');
    const joinGameBtn = document.getElementById('join-game-btn');
    const lobbyCodeDisplay = document.getElementById('lobby-code-display');
    const lobbyCodeText = document.getElementById('lobby-code-text');
    const lobbyCodeInput = document.getElementById('lobby-code-input');


    const loadUserSettings = () => {
        const storedUsername = localStorage.getItem('laRataUsername') || 'Jugador Invitado';
        const storedAvatarId = localStorage.getItem('laRataAvatarId') || 'default';

        profileUsername.textContent = storedUsername;
        profileAvatar.src = `assets/characters/${storedAvatarId}.png`;

        currentUsernameDisplay.textContent = storedUsername;
        currentAvatarPreview.src = `assets/characters/${storedAvatarId}.png`;
    };

    const saveNewAvatar = (avatarId) => {
        localStorage.setItem('laRataAvatarId', avatarId);
        const avatarSrc = `assets/characters/${avatarId}.png`;
        profileAvatar.src = avatarSrc;
        currentAvatarPreview.src = avatarSrc;
    };

    const createAvatarGrid = () => {
        avatarSelector.innerHTML = '';
        const currentAvatarId = localStorage.getItem('laRataAvatarId') || 'default';

        for (let i = 1; i <= AVATAR_COUNT; i++) {
            const avatarId = `char_${i}`;
            const img = document.createElement('img');
            img.src = `assets/characters/${avatarId}.png`;
            img.alt = `Avatar ${i}`;
            img.classList.add('avatar-option');
            img.dataset.avatarId = avatarId;

            if (avatarId === currentAvatarId) {
                img.classList.add('selected');
            }

            img.addEventListener('click', (e) => {
                document.querySelectorAll('.avatar-option').forEach(opt => opt.classList.remove('selected'));
                e.target.classList.add('selected');
                saveNewAvatar(avatarId);
            });

            avatarSelector.appendChild(img);
        }
    };


    const openModal = (modalElement) => {
        logoSection.classList.add('hidden');
        buttonsSection.classList.add('hidden');
        modalElement.classList.remove('hidden');
    };

    const closeModal = (modalElement) => {
        modalElement.classList.add('hidden');
        logoSection.classList.remove('hidden');
        buttonsSection.classList.remove('hidden');
    };


    if (configBtn) {
        configBtn.addEventListener('click', () => {
            createAvatarGrid();
            openModal(configModal);
        });
    }

    if (closeConfigBtn) {
        closeConfigBtn.addEventListener('click', () => {
            closeModal(configModal);
        });
    }

    if (optionsBtn) {
        optionsBtn.addEventListener('click', () => {
            openModal(optionsModal);
        });
    }
    if (closeOptionsBtn) {
        closeOptionsBtn.addEventListener('click', () => {
            closeModal(optionsModal);
        });
    }

    if (lobbyBtn) {
        lobbyBtn.addEventListener('click', () => {
            openModal(lobbyModal);
        });
    }
    if (closeLobbyBtn) {
        closeLobbyBtn.addEventListener('click', () => {
            closeModal(lobbyModal);
        });
    }

    if (createGameBtn) {
        createGameBtn.addEventListener('click', () => {
            const fakeCode = (Math.random().toString(36).substring(2, 7)).toUpperCase();
            lobbyCodeText.textContent = fakeCode;
            lobbyCodeDisplay.classList.remove('hidden');
        });
    }

    if (joinGameBtn) {
        joinGameBtn.addEventListener('click', () => {
            const code = lobbyCodeInput.value;
            if (code.length === 5) {
                alert('¡Uniendo a la partida ' + code + '!');
            } else {
                alert('¡El código debe tener 5 caracteres!');
            }
        });
    }

    loadUserSettings();
});