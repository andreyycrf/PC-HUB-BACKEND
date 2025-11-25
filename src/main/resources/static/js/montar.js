document.addEventListener("DOMContentLoaded", function() {
    const btnMontar = document.querySelector(".btn-montar");
    btnMontar.addEventListener("click", function() {
        const componentes = {
            processador: document.querySelector("select[name='processador']").value,
            placaMae: document.querySelector("select[name='placa-mae']").value,
            memoria: document.querySelector("select[name='memoria']").value,
            armazenamento: document.querySelector("select[name='armazenamento']").value,
            fonte: document.querySelector("select[name='fonte']").value
        };
        console.log(componentes);
    });
});

