package SistemadeAluguel.controller;


import SistemadeAluguel.model.entity.Equipamento;
import SistemadeAluguel.model.enums.CategoriaEquipamento;
import SistemadeAluguel.service.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {


    @Autowired
    private EquipamentoService equipamentoService;

    @PostMapping
    public ResponseEntity<Equipamento> criar(@RequestBody Equipamento equipamento) {
        Equipamento novo = equipamentoService.salvar(equipamento);
        return ResponseEntity.status(201).body(novo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        equipamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/disponiveis")
    public List<Equipamento> listarDisponiveis() {
        return equipamentoService.listarAtivosEDisponiveis();
    }

    @GetMapping("/filtro")
    public List<Equipamento> filtrarPorCategoria(@RequestParam String categoria) {
        CategoriaEquipamento catEnum = CategoriaEquipamento.valueOf(categoria.toUpperCase());
        return equipamentoService.buscarPorCategoria(categoria);
    }
}
