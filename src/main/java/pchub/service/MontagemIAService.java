package pchub.service;

import org.springframework.stereotype.Service;
import pchub.model.Peca;

@Service
public class MontagemIAService {

    public String gerarAnalise(Peca processador, Peca gpu, Peca ram, Peca mobo, Peca storage, String fonte) {
        StringBuilder analise = new StringBuilder();
        int pontuacao = 10;
        
        // --- 1. Verificação de Fonte (Lógica Simples) ---
        if (gpu != null && fonte != null) {
            String nomeGpu = gpu.getNome().toUpperCase();
            // Tenta extrair o número da fonte (ex: "500w" -> 500)
            int wattsFonte = extrairWatts(fonte);

            if ((nomeGpu.contains("RTX 4090") || nomeGpu.contains("RTX 3090")) && wattsFonte < 850) {
                analise.append("⚠️ <strong>Alerta de Energia:</strong> Sua placa de vídeo é muito potente (" + gpu.getNome() + ") para essa fonte. Recomendamos pelo menos 850W.<br>");
                pontuacao -= 3;
            } else if ((nomeGpu.contains("RTX 40") || nomeGpu.contains("RX 7")) && wattsFonte < 650) {
                 analise.append("⚠️ <strong>Atenção na Fonte:</strong> Para a linha atual de placas, uma fonte de " + fonte + " pode ficar no limite. Considere 650W ou mais.<br>");
                 pontuacao -= 1;
            } else {
                analise.append("✅ <strong>Energia:</strong> A fonte parece adequada para a configuração.<br>");
            }
        }

        // --- 2. Verificação de Gargalo (CPU vs GPU) ---
        if (processador != null && gpu != null) {
            String nomeCpu = processador.getNome().toUpperCase();
            String nomeGpu = gpu.getNome().toUpperCase();

            // Exemplo: CPU fraca com GPU muito forte
            boolean cpuBasica = nomeCpu.contains("I3") || nomeCpu.contains("RYZEN 3");
            boolean gpuForte = nomeGpu.contains("RTX 40") || nomeGpu.contains("RTX 30") || nomeGpu.contains("RX 68") || nomeGpu.contains("RX 69");

            if (cpuBasica && gpuForte) {
                analise.append("⚠️ <strong>Gargalo Detectado:</strong> O processador (" + processador.getNome() + ") pode limitar o desempenho da sua placa de vídeo. Um i5/Ryzen 5 seria melhor.<br>");
                pontuacao -= 2;
            } else {
                analise.append("✅ <strong>Equilíbrio:</strong> A combinação de CPU e GPU parece bem balanceada.<br>");
            }
        }

        // --- 3. Dicas Gerais ---
        if (ram != null) {
            if (ram.getNome().contains("4 GB") || ram.getNome().contains("8 GB")) {
                analise.append("💡 <strong>Dica de Memória:</strong> Hoje em dia, 16GB é o ideal para jogos modernos.<br>");
            }
        }

        // --- Conclusão ---
        analise.append("<br><strong>Nota Final da Build: " + pontuacao + "/10</strong>");
        
        return analise.toString();
    }

    // Método auxiliar para transformar "500w" em 500
    private int extrairWatts(String fonteTexto) {
        try {
            String numero = fonteTexto.replaceAll("[^0-9]", ""); // Remove tudo que não é número
            return Integer.parseInt(numero);
        } catch (Exception e) {
            return 500; // Valor padrão se der erro
        }
    }
}
