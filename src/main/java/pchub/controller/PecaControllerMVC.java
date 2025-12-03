package pchub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Import corrigido
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pchub.model.Peca; // Import corrigido
import pchub.service.PecaService; // Import corrigido

@Controller
@RequestMapping("/pecas")
public class PecaControllerMVC {

    private final PecaService service;

    public PecaControllerMVC(PecaService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pecas", service.listar());
        return "lista-pecas"; // retorna lista-pecas.html
    }

    @GetMapping("/cadastro")
    public String formCadastro(Model model) {
        model.addAttribute("peca", new Peca());
        return "form-peca"; // retorna form-peca.html
    }

    @PostMapping
    public String salvar(Peca peca) {
        service.salvar(peca);
        return "redirect:/pecas";
    }
}
