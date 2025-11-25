package pchub.service;

import pchub.model.PC;
import pchub.repository.PCRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PCService {

    private final PCRepository pcRepository;

    public PCService(PCRepository pcRepository) {
        this.pcRepository = pcRepository;
    }

    public PC salvar(PC pc) {
        return pcRepository.save(pc);
    }

    public List<PC> listarTodos() {
        return pcRepository.findAll();
    }
}
