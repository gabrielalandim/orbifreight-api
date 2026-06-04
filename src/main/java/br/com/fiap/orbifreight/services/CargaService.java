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

    // Criar nova carga (POST)
    public CargaResponseDTO salvar(CargaRequestDTO request) {
        Carga carga = new Carga();
        carga.setTipoId(request.tipoId());
        carga.setVeiculoId(request.veiculoId());
        carga.setMotoristaId(request.motoristaId());
        carga.setTempMin(request.tempMin());
        carga.setTempMax(request.tempMax());
        carga.setUmidadeMax(request.umidadeMax());
        carga.setStatus(request.status());

        Carga cargaSalva = cargaRepository.save(carga);
        return converterParaDTO(cargaSalva);
    }

    // Listar todas as cargas (GET)
    public List<CargaResponseDTO> listarTodas() {
        return cargaRepository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    // Buscar uma carga específica pelo ID (GET)
    public CargaResponseDTO buscarPorId(Long id) {
        Carga carga = cargaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carga não encontrada com o ID: " + id));
        return converterParaDTO(carga);
    }

    // Atualizar os dados de uma carga existente (PUT)
    public CargaResponseDTO atualizar(Long id, CargaRequestDTO request) {
        Carga carga = cargaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carga não encontrada com o ID: " + id));

        carga.setTipoId(request.tipoId());
        carga.setVeiculoId(request.veiculoId());
        carga.setMotoristaId(request.motoristaId());
        carga.setTempMin(request.tempMin());
        carga.setTempMax(request.tempMax());
        carga.setUmidadeMax(request.umidadeMax());
        carga.setStatus(request.status());

        Carga cargaAtualizada = cargaRepository.save(carga);
        return converterParaDTO(cargaAtualizada);
    }

    // Eliminar uma carga (DELETE)
    public void excluir(Long id) {
        if (!cargaRepository.existsById(id)) {
            throw new RuntimeException("Carga não encontrada com o ID: " + id);
        }
        cargaRepository.deleteById(id);
    }

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