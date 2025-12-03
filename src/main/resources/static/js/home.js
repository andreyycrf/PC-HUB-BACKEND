document.addEventListener('DOMContentLoaded', () => {
    // Animação de contagem para as estatísticas
    const stats = document.querySelectorAll('.stat-number');
    const duration = 2000; // 2 segundos

    const animateCount = (element) => {
        const target = parseInt(element.getAttribute('data-count'));
        let start = 0;
        const increment = target / (duration / 16); // 60fps

        const updateCount = () => {
            start += increment;
            if (start < target) {
                element.textContent = Math.ceil(start).toLocaleString('pt-BR');
                requestAnimationFrame(updateCount);
            } else {
                element.textContent = target.toLocaleString('pt-BR');
            }
        };
        updateCount();
    };

    stats.forEach(stat => {
        animateCount(stat);
    });

    // Funcionalidade do menu mobile
    const navToggle = document.querySelector('.nav-toggle');
    const navMenu = document.querySelector('.nav-menu'); // Não existe no HTML fornecido, mas é boa prática ter

    if (navToggle) {
        navToggle.addEventListener('click', () => {
            const isExpanded = navToggle.getAttribute('aria-expanded') === 'true' || false;
            navToggle.setAttribute('aria-expanded', !isExpanded);
            // Se o menu de navegação fosse implementado, ele seria exibido/ocultado aqui
            // Ex: navMenu.classList.toggle('open');
        });
    }
});
