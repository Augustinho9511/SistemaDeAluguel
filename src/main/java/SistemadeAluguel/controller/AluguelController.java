package SistemadeAluguel.controller;


import SistemadeAluguel.model.dto.AluguelRequestDTO;
import SistemadeAluguel.model.dto.AluguelResponseDTO;
import SistemadeAluguel.service.AluguelService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {

    private final AluguelService aluguelService;

    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    @PostMapping
    public ResponseEntity<AluguelResponseDTO> criarAluguel(@RequestBody @Valid AluguelRequestDTO request) {
        var response = aluguelService.alugar(request);
        return ResponseEntity.status(201).body(response);
    }

}
