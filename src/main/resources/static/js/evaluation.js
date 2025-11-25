document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('setupForm');
    const resultsSection = document.getElementById('evaluationResults');
    const resultsContainer = document.getElementById('evaluationResult');
    const evaluateBtn = document.getElementById('evaluateBtn');

    if (!form) return;

    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        setLoadingState(true);

        const formData = getFormData();

        try {
            const response = await fetch('/api/avaliar', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });

            if (!response.ok) {
                throw new Error(`Erro do servidor: ${response.statusText}`);
            }

            const evaluation = await response.json();
            displayResults(evaluation);
            showResultsSection();

        } catch (error) {
            showNotification('Erro ao avaliar setup. Tente novamente.', 'error');
            console.error('Evaluation error:', error);
        } finally {
            setLoadingState(false);
        }
    });

    function getFormData() {
        const data = {};
        const formElements = form.elements;
        for (let element of formElements) {
            const fieldName = element.getAttribute('name');
            if (fieldName) {
                data[fieldName] = element.value.trim();
            }
        }
        return data;
    }

    function displayResults(evaluation) {
        const score = evaluation.notaGeral * 10;
        const scoreClass = score >= 80 ? 'excellent' : score >= 60 ? 'good' : score >= 40 ? 'warning' : 'critical';

        resultsContainer.innerHTML = `
            <div class="result-header">
                <div class="result-score ${scoreClass}">${evaluation.notaGeral}/10</div>
                <h3 class="result-title">${evaluation.titulo}</h3>
                <p class="result-description">${evaluation.descricao}</p>
            </div>

            <div class="result-grid">
                ${evaluation.notasComponentes.map(nota => `
                    <div class="result-category">
                        <div class="category-header">
                            <span class="category-icon">${getCategoryIcon(nota.nome)}</span>
                            <h4 class="category-title">${nota.nome}</h4>
                            <span class="category-status status-${getNotaStatus(nota.nota)}">${nota.nota}/10</span>
                        </div>
                        <p>${nota.detalhe}</p>
                    </div>
                `).join('')}
            </div>

            ${evaluation.recomendacoes && evaluation.recomendacoes.length > 0 ? `
                <div class="recommendations">
                    <h4>💡 Recomendações de Melhoria</h4>
                    <ul>
                        ${evaluation.recomendacoes.map(rec => `<li>${rec}</li>`).join('')}
                    </ul>
                </div>
            ` : ''}
            
            <div class="compatibility-issues">
                <h4>✅ Compatibilidade: ${evaluation.compatibilidade}</h4>
                <h4>⚡ Desempenho: ${evaluation.desempenho}</h4>
            </div>
        `;

        setTimeout(() => {
            resultsSection.classList.add('show');
        }, 100);
    }

    function getNotaStatus(nota) {
        if (nota >= 8) return 'excellent';
        if (nota >= 6) return 'good';
        if (nota >= 4) return 'warning';
        return 'critical';
    }

    function getCategoryIcon(categoryName) {
        const icons = {
            'Processador': '🔧',
            'Placa de Vídeo': '🎮',
            'Memória RAM': '🧠',
            'Armazenamento': '💾',
            'Fonte': '🔋'
        };
        return icons[categoryName] || '⚙️';
    }

    function showResultsSection() {
        resultsSection.hidden = false;
        resultsSection.scrollIntoView({ behavior: 'smooth' });
    }

    function setLoadingState(loading) {
        evaluateBtn.disabled = loading;
        if (loading) {
            evaluateBtn.classList.add('loading');
            evaluateBtn.innerHTML = `<span class="btn-icon" aria-hidden="true">📊</span> Analisando... <span class="loading-spinner" aria-hidden="true"></span>`;
        } else {
            evaluateBtn.classList.remove('loading');
            evaluateBtn.innerHTML = `<span class="btn-icon" aria-hidden="true">📊</span> Analisar Setup <span class="loading-spinner" aria-hidden="true"></span>`;
        }
    }

    function showNotification(message, type = 'info') {
        const notification = document.createElement('div');
        notification.className = `notification notification-${type}`;
        notification.textContent = message;
        document.body.appendChild(notification);
        setTimeout(() => notification.remove(), 5000);
    }
});
