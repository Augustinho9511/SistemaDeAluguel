package SistemadeAluguel.controller;


import SistemadeAluguel.model.dto.AluguelRequestDTO;
import SistemadeAluguel.model.dto.AluguelResponseDTO;
import SistemadeAluguel.model.entity.Aluguel;
import SistemadeAluguel.repository.AluguelRepository;
import SistemadeAluguel.service.AluguelService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {

    private final AluguelService aluguelService;
    private final AluguelRepository aluguelRepository;

    public AluguelController(AluguelService aluguelService, AluguelRepository aluguelRepository) {
        this.aluguelService = aluguelService;
        this.aluguelRepository = aluguelRepository;
    }

    @GetMapping
    public ResponseEntity<List<Aluguel>> listarTodos() {
        return ResponseEntity.ok(aluguelRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<AluguelResponseDTO> criarAluguel(@RequestBody @Valid AluguelRequestDTO request) {
        var response = aluguelService.alugar(request);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}/devolucao")
    public ResponseEntity<String> devolverEquipamento(@PathVariable Long id) {
        String mensagem = aluguelService.devolver(id);
        return ResponseEntity.ok(mensagem);
    }


}
