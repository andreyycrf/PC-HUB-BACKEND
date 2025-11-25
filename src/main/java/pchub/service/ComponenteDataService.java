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

    /**
     * Filtra as peças compatíveis com base em uma peça já selecionada.
     * Esta é uma implementação simplificada para demonstração.
     * A lógica real de compatibilidade (ex: socket, chipset, tipo de RAM)
     * deve ser implementada aqui.
     *
     * @param tipoPeca O tipo de peça a ser filtrada (ex: "Processador", "Placa-Mãe").
     * @param pecaBase A peça já selecionada para comparação (pode ser null).
     * @return Lista de peças compatíveis.
     */
    public List<Peca> filtrarPecasCompativeis(String tipoPeca, Peca pecaBase) {
        List<Peca> todasPecas = pecaService.listar();

        // 1. Filtrar pelo tipo de peça
        List<Peca> pecasDoTipo = todasPecas.stream()
                .filter(p -> p.getTipo() != null && p.getTipo().equalsIgnoreCase(tipoPeca))
                .collect(Collectors.toList());

        if (pecaBase == null) {
            return pecasDoTipo;
        }

        // 2. Aplicar lógica de compatibilidade (Exemplo: Socket)
        // Se a peça base for um Processador, filtrar Placas-Mãe com o mesmo socket.
        if (pecaBase.getTipo() != null && pecaBase.getTipo().equalsIgnoreCase("Processador") && tipoPeca.equalsIgnoreCase("Placa-Mãe")) {
            return pecasDoTipo.stream()
                    .filter(p -> p.getSocket() != null && p.getSocket().equalsIgnoreCase(pecaBase.getSocket()))
                    .collect(Collectors.toList());
        }

        // Se a peça base for uma Placa-Mãe, filtrar Processadores com o mesmo socket.
        if (pecaBase.getTipo() != null && pecaBase.getTipo().equalsIgnoreCase("Placa-Mãe") && tipoPeca.equalsIgnoreCase("Processador")) {
            return pecasDoTipo.stream()
                    .filter(p -> p.getSocket() != null && p.getSocket().equalsIgnoreCase(pecaBase.getSocket()))
                    .collect(Collectors.toList());
        }

        // Lógica para RAM, Placa de Vídeo, etc. deve ser adicionada aqui.
        // Por enquanto, retorna todas as peças do tipo se a lógica de compatibilidade não for clara.
        return pecasDoTipo;
    }
}
