package pchub.service;

import org.springframework.stereotype.Service;
import pchub.model.Peca;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComponenteDataService {

    private final PecaService pecaService;

    public ComponenteDataService(PecaService pecaService) {
        this.pecaService = pecaService;
    }

    public List<Peca> filtrarPecasCompativeis(String tipoDesejado, Peca pecaBase) {
        // 1. Busca todas as peças do tipo desejado (ex: todas as Placas-Mãe)
        List<Peca> todasPecas = pecaService.listar().stream()
                .filter(p -> p.getTipo().equalsIgnoreCase(tipoDesejado))
                .collect(Collectors.toList());

        // 2. Se não houver uma peça base selecionada (ex: usuário ainda não escolheu processador),
        // retorna tudo.
        if (pecaBase == null) {
            return todasPecas;
        }

        // 3. REGRA DE OURO: Filtragem por Socket
        // Se a peça base for um Processador e estamos buscando Placa-Mãe (ou vice-versa)
        if (pecaBase.getSocket() != null) {
            return todasPecas.stream()
                    .filter(p -> p.getSocket() != null && 
                                 p.getSocket().equalsIgnoreCase(pecaBase.getSocket()))
                    .collect(Collectors.toList());
        }

        return todasPecas;
    }
}
