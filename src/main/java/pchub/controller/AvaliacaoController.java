package pchub.controller;

import pchub.model.AvaliacaoSetup;
import pchub.model.ResultadoAvaliacao;
import pchub.service.AvaliacaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @GetMapping("/avaliar")
    public String showAvaliacaoForm(Model model) {
        model.addAttribute("avaliacaoSetup", new AvaliacaoSetup());
        return "avaliar";
    }


}
