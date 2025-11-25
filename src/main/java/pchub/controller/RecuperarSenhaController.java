package pchub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecuperarSenhaController {

    @GetMapping("/recuperar_senha")
    public String showRecuperarSenhaForm() {
        return "recuperar_senha";
    }
}
