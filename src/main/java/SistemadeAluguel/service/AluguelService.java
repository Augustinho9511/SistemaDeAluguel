package SistemadeAluguel.service;

import SistemadeAluguel.model.dto.AluguelRequestDTO;
import SistemadeAluguel.model.dto.AluguelResponseDTO;
import SistemadeAluguel.model.entity.Aluguel;
import SistemadeAluguel.repository.AluguelRepository;
import SistemadeAluguel.repository.ClienteRepository;
import SistemadeAluguel.repository.EquipamentoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;


@Service
public class AluguelService {

    private final AluguelRepository aluguelRepository;
    private final EquipamentoRepository equipamentoRepository;
    private final ClienteRepository clienteRepository;

    public AluguelService(AluguelRepository aluguelRepository, EquipamentoRepository equipamentoRepository, ClienteRepository clienteRepository) {
        this.aluguelRepository = aluguelRepository;
        this.equipamentoRepository = equipamentoRepository;
        this.clienteRepository = clienteRepository;
    }

    public AluguelResponseDTO alugar(AluguelRequestDTO request) {

        var cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        var equipamento = equipamentoRepository.findById(request.getEquipamentoId())
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));

        if (!equipamento.isDisponivel()) {
            throw new RuntimeException("Equipamento já está alugado por outra pessoa!");
        }

        equipamento.setDisponivel(false);
        equipamentoRepository.save(equipamento);

        LocalDate dataPrevista = LocalDate.now().plusDays(request.getQuantidadeDias());
        BigDecimal valorTotal = equipamento.getValorDiaria().multiply(BigDecimal.valueOf(request.getQuantidadeDias()));

        Aluguel novoAluguel = new Aluguel();
        novoAluguel.setCliente(cliente);
        novoAluguel.setEquipamento(equipamento);
        novoAluguel.setDataInicio(LocalDate.now());
        novoAluguel.setDataPrevista(dataPrevista);
        novoAluguel.setValorTotal(valorTotal);

        aluguelRepository.save(novoAluguel);

        AluguelResponseDTO response = new AluguelResponseDTO();
        response.setId(novoAluguel.getId());
        response.setNomeCliente(cliente.getNome());
        response.setNomeEquipamento(equipamento.getNome());
        response.setDatapPrevista(novoAluguel.getDataPrevista());
        response.setValorTotal(novoAluguel.getValorTotal());

        return response;
    }
}