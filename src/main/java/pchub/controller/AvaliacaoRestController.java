package pchub.controller;

import pchub.model.AvaliacaoSetup;
import pchub.model.ResultadoAvaliacao;
import pchub.service.AvaliacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AvaliacaoRestController {

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoRestController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping("/avaliar")
    public ResponseEntity<ResultadoAvaliacao> avaliarSetup(@RequestBody AvaliacaoSetup setup) {
        try {
            ResultadoAvaliacao resultado = avaliacaoService.avaliar(setup);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            // Logar o erro para diagnóstico
            System.err.println("Erro ao processar avaliação: " + e.getMessage());
            e.printStackTrace();
            // Retorna um erro 500 (Internal Server Error)
            return ResponseEntity.internalServerError().build();
        }
    }
}
