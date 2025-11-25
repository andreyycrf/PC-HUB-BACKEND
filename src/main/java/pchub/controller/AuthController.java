package pchub.controller;

import pchub.model.Usuario;
import pchub.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/usuario/cadastro")
    public String showRegistrationForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro-usuario";
    }

    @PostMapping("/usuario/cadastro")
    public String registerUser(@ModelAttribute("usuario") Usuario usuario, Model model) {
        try {
            usuarioService.salvar(usuario);
            return "redirect:/usuario/cadastro?success";
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao cadastrar usuário. Tente novamente.");
            return "cadastro-usuario";
        }
    }
}
