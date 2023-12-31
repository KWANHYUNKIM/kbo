document.addEventListener('DOMContentLoaded', function() {
    const myMenuContent = document.querySelector('.my-menu-content');

    const handleMyMenu = (event) => {
        myMenuContent.classList.toggle('is-active');
        adjustDropdownPosition();
    };

    const adjustDropdownPosition = () => {
        const myMenuTrigger = document.querySelector('.dropdown-button');
        const triggerRect = myMenuTrigger.getBoundingClientRect();
        const windowHeight = window.innerHeight;

        // Adjust the position based on the window height
        if (windowHeight - triggerRect.bottom < myMenuContent.clientHeight) {
            myMenuContent.style.top = 'auto';
            myMenuContent.style.bottom = '100%';
        } else {
            myMenuContent.style.top = '100%';
            myMenuContent.style.bottom = 'auto';
        }
    };

    const init = () => {
        window.addEventListener('click', (event) => {
            if (!event.target.matches('.dropdown-button') && myMenuContent.classList.contains('is-active')) {
                myMenuContent.classList.remove('is-active');
            }
        });

        document.querySelector('.dropdown-button').addEventListener('click', handleMyMenu);
    };

    init();
});