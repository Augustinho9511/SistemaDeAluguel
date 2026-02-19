package SistemadeAluguel.service;

import SistemadeAluguel.model.entity.Equipamento;
import SistemadeAluguel.model.enums.CategoriaEquipamento;
import SistemadeAluguel.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository equipamentoRepository;


    public Equipamento salvar(Equipamento equipamento) {
        equipamento.setAtivo(true);
        return equipamentoRepository.save(equipamento);
    }

    public void deletar(Long id) {
        Equipamento equipamento = equipamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));

        equipamento.setAtivo(false);
        equipamentoRepository.save(equipamento);
    }

    public List<Equipamento> listarAtivosEDisponiveis() {
        return equipamentoRepository.findByAtivoTrueAndDisponivelTrue();
    }

    public List<Equipamento> listarTudoDoBanco() {
        return equipamentoRepository.findAll();
    }

    public List<Equipamento> buscarPorCategoria(String categoria) {

        try {
            String categoriaFormata = categoria.trim().replace(" ", "_").toUpperCase();
            CategoriaEquipamento catEnum = CategoriaEquipamento.valueOf(categoriaFormata);

            return equipamentoRepository.findByCategoriaAndAtivoTrue(catEnum);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Categoria '" + categoria + "' não encontrada no sistema.");
        }

    }
}
