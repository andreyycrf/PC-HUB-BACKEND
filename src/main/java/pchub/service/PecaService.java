package pchub.service;

import org.springframework.stereotype.Service;
import pchub.model.Peca;
import pchub.repository.PecaRepository;

import java.util.List;

@Service
public class PecaService {

    private final PecaRepository pecaRepository;

    // Injeção de dependência via construtor (melhor prática)
    public PecaService(PecaRepository pecaRepository) {
        this.pecaRepository = pecaRepository;
    }

    public List<Peca> listar() {
        return pecaRepository.findAll();
    }

    public void salvar(Peca peca) {
        pecaRepository.save(peca);
    }

    public Peca buscarPorId(Long id) {
        return pecaRepository.findById(id).orElse(null);
    }
    
    // Mantendo outros métodos para o caso de serem necessários no futuro
    // public Peca update(Long id, Peca novaPeca) { ... }
    // public void delete(Long id) { ... }
}
