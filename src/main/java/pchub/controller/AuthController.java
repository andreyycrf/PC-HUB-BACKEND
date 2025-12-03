package pchub.controller;

import jakarta.servlet.http.HttpSession;
import pchub.model.Usuario;
import pchub.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // ===== LOGIN =====
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute("usuario") Usuario usuario,
                              Model model,
                              HttpSession session) {
        try {
            Usuario logado = usuarioService.login(usuario.getEmail(), usuario.getSenha());

            // salva o usuário na sessão
            session.setAttribute("usuarioLogado", logado);

            return "redirect:/?loginSuccess";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }


    // ===== CADASTRO =====
    @GetMapping("/usuario/cadastro")
    public String cadastroForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro-usuario";
    }

    @PostMapping("/usuario/cadastro")
    public String cadastroSubmit(@ModelAttribute("usuario") Usuario usuario,
                                 Model model) {
        try {
            usuarioService.cadastrar(usuario);
            return "redirect:/usuario/cadastro?success";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "cadastro-usuario";
        }
    }
}
