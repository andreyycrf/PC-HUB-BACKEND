package pchub.controller;

import pchub.model.PC;
import pchub.service.PCService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pc")
public class PCController {

    private final PCService pcService;

    public PCController(PCService pcService) {
        this.pcService = pcService;
    }

    @GetMapping("/cadastro")
    public String showRegistrationForm(Model model) {
        model.addAttribute("pc", new PC());
        return "cadastro-pc";
    }

    @PostMapping("/cadastro")
    public String registerPC(@ModelAttribute("pc") PC pc, Model model) {
        try {
            pcService.salvar(pc);
            return "redirect:/pc/lista?success";
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao cadastrar PC. Tente novamente.");
            return "cadastro-pc";
        }
    }

    @GetMapping("/lista")
    public String listPCs(Model model) {
        model.addAttribute("pcs", pcService.listarTodos());
        return "lista-pc";
    }
}
