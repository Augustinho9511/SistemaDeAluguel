package SistemadeAluguel.service;

import SistemadeAluguel.model.entity.Equipamento;
import SistemadeAluguel.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository equipamentoRespository;


    public Equipamento salvar(Equipamento equipamento) {
        equipamento.setAtivo(true);
        return equipamentoRespository.save(equipamento);
    }

    public void deletar(Long id) {
        Equipamento equipamento = equipamentoRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));

        equipamento.setAtivo(false);
        equipamentoRespository.save(equipamento);
    }

    public List<Equipamento> listarAtivosEDisponiveis() {
        return equipamentoRespository.findByAtivoTrueAndDisponivelTrue();
    }

    public List<Equipamento> listarTudoDoBando() {
        return equipamentoRespository.findAll();
    }
}
