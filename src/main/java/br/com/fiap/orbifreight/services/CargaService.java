package br.com.fiap.orbifreight.services;

import br.com.fiap.orbifreight.dtos.CargaRequestDTO;
import br.com.fiap.orbifreight.dtos.CargaResponseDTO;
import br.com.fiap.orbifreight.models.Carga;
import br.com.fiap.orbifreight.repositories.CargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CargaService {

    @Autowired
    private CargaRepository cargaRepository;

    public CargaResponseDTO salvar(CargaRequestDTO request) {
        // Converte o DTO que chegou na requisição para a Entidade do banco
        Carga carga = new Carga();
        carga.setTipoId(request.tipoId());
        carga.setVeiculoId(request.veiculoId());
        carga.setMotoristaId(request.motoristaId());
        carga.setTempMin(request.tempMin());
        carga.setTempMax(request.tempMax());
        carga.setUmidadeMax(request.umidadeMax());
        carga.setStatus(request.status());

        // Salva no Oracle
        Carga cargaSalva = cargaRepository.save(carga);

        // Devolve como ResponseDTO (agora com o ID preenchido)
        return converterParaDTO(cargaSalva);
    }

    public List<CargaResponseDTO> listarTodas() {
        return cargaRepository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    // Método auxiliar para não repetirmos código
    private CargaResponseDTO converterParaDTO(Carga carga) {
        return new CargaResponseDTO(
                carga.getId(),
                carga.getTipoId(),
                carga.getVeiculoId(),
                carga.getMotoristaId(),
                carga.getTempMin(),
                carga.getTempMax(),
                carga.getUmidadeMax(),
                carga.getStatus()
        );
    }
}