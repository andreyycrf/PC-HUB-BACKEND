package pchub.controller;

import pchub.model.PCMontado;
import pchub.model.Peca;
import pchub.service.ComponenteDataService;
import pchub.service.MontagemIAService;
import pchub.service.PecaService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MontarPCController {

    private final ComponenteDataService componenteDataService;
    private final PecaService pecaService;
	private MontagemIAService montagemIAService;

    public MontarPCController(ComponenteDataService componenteDataService, PecaService pecaService, MontagemIAService montagemIAService) {
        this.componenteDataService = componenteDataService;
        this.pecaService = pecaService;
        this.montagemIAService = montagemIAService;
    }

    // ... (o GetMapping fica igual) ...

    @PostMapping("/montar")
    public String montarPC(@ModelAttribute("pcMontado") PCMontado pcMontado, Model model) {
        // 1. Convertendo os IDs (String) para Objetos Peca reais
        Peca processador = buscarPecaPorIdString(pcMontado.getProcessador());
        Peca placaVideo = buscarPecaPorIdString(pcMontado.getPlacaVideo());
        Peca memoriaRam = buscarPecaPorIdString(pcMontado.getMemoriaRam());
        Peca placaMae = buscarPecaPorIdString(pcMontado.getPlacaMae());
        Peca armazenamento = buscarPecaPorIdString(pcMontado.getArmazenamento());
        
        // 2. Chamando a IA para analisar os nomes reais
        String analiseDaIA = montagemIAService.gerarAnalise(
            processador, placaVideo, memoriaRam, placaMae, armazenamento, pcMontado.getFonte()
        );

        // 3. Enviando a resposta para a tela
        model.addAttribute("mensagemIA", analiseDaIA);
        
        // Mantém a lista de peças carregada para o usuário não perder o formulário
        model.addAttribute("pecas", pecaService.listar());
        return "montar";
    }

    // Método auxiliar simples para evitar erros se o ID estiver vazio
    private Peca buscarPecaPorIdString(String idStr) {
        if (idStr != null && !idStr.isEmpty()) {
            try {
                Long id = Long.parseLong(idStr);
                return pecaService.buscarPorId(id);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }
}
