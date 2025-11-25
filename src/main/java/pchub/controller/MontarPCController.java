package pchub.controller;

import pchub.model.PCMontado;
import pchub.model.Peca;
import pchub.service.ComponenteDataService;
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

    public MontarPCController(ComponenteDataService componenteDataService, PecaService pecaService) {
        this.componenteDataService = componenteDataService;
        this.pecaService = pecaService;
    }

    @GetMapping("/montar")
    public String showMontarPCForm(Model model) {
        model.addAttribute("pcMontado", new PCMontado());
        model.addAttribute("pecas", pecaService.listar()); // Adiciona todas as peças para o frontend
        return "montar";
    }

    @PostMapping("/montar")
    public String montarPC(@ModelAttribute("pcMontado") PCMontado pcMontado, Model model) {
        // Lógica de montagem e validação de compatibilidade (a ser implementada)
        // Por enquanto, apenas redireciona para a mesma página com uma mensagem de sucesso
        model.addAttribute("mensagem", "PC montado com sucesso! (Funcionalidade de validação a ser implementada)");
        model.addAttribute("pecas", pecaService.listar());
        return "montar";
    }

    @GetMapping("/api/pecas/compativeis")
    @ResponseBody
    public List<Peca> getPecasCompativeis(@RequestParam("tipo") String tipoPeca, @RequestParam(value = "pecaBaseId", required = false) Long pecaBaseId) {
        Peca pecaBase = null;
        if (pecaBaseId != null) {
            pecaBase = pecaService.buscarPorId(pecaBaseId);
        }
        return componenteDataService.filtrarPecasCompativeis(tipoPeca, pecaBase);
    }
}
