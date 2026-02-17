package SistemadeAluguel.controller;


import SistemadeAluguel.model.entity.Equipamento;
import SistemadeAluguel.repository.EquipamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipamentos")
@RequiredArgsConstructor
public class EquipamentoController {

    private final EquipamentoRepository equipamentoRepository;

    @PostMapping
    public ResponseEntity<Equipamento> criar(@RequestBody Equipamento equipamento) {
        return ResponseEntity.status(201).body(equipamentoRepository.save(equipamento));
    }

}
