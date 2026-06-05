package br.com.fiap.orbifreight.services;

import br.com.fiap.orbifreight.dtos.CargaRequestDTO;
import br.com.fiap.orbifreight.dtos.CargaResponseDTO;
import br.com.fiap.orbifreight.models.Carga;
import br.com.fiap.orbifreight.models.TipoCarga;
import br.com.fiap.orbifreight.repositories.CargaRepository;
import br.com.fiap.orbifreight.repositories.TipoCargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CargaService {

    @Autowired
    private CargaRepository cargaRepository;

    // Nova injeção para validar o relacionamento
    @Autowired
    private TipoCargaRepository tipoCargaRepository;

    public CargaResponseDTO salvar(CargaRequestDTO request) {
        // Vai buscar o TipoCarga à base de dados
        TipoCarga tipo = tipoCargaRepository.findById(request.tipoId())
                .orElseThrow(() -> new RuntimeException("Tipo de Carga não encontrado com o ID: " + request.tipoId()));

        Carga carga = new Carga();
        carga.setTipoCarga(tipo); // Agora usamos o objeto completo!
        carga.setVeiculoId(request.veiculoId());
        carga.setMotoristaId(request.motoristaId());
        carga.setTempMin(request.tempMin());
        carga.setTempMax(request.tempMax());
        carga.setUmidadeMax(request.umidadeMax());
        carga.setStatus(request.status());

        Carga cargaSalva = cargaRepository.save(carga);
        return converterParaDTO(cargaSalva);
    }

    public List<CargaResponseDTO> listarTodas() {
        return cargaRepository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public CargaResponseDTO buscarPorId(Long id) {
        Carga carga = cargaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carga não encontrada com o ID: " + id));
        return converterParaDTO(carga);
    }

    public CargaResponseDTO atualizar(Long id, CargaRequestDTO request) {
        Carga carga = cargaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carga não encontrada com o ID: " + id));

        TipoCarga tipo = tipoCargaRepository.findById(request.tipoId())
                .orElseThrow(() -> new RuntimeException("Tipo de Carga não encontrado com o ID: " + request.tipoId()));

        carga.setTipoCarga(tipo);
        carga.setVeiculoId(request.veiculoId());
        carga.setMotoristaId(request.motoristaId());
        carga.setTempMin(request.tempMin());
        carga.setTempMax(request.tempMax());
        carga.setUmidadeMax(request.umidadeMax());
        carga.setStatus(request.status());

        Carga cargaAtualizada = cargaRepository.save(carga);
        return converterParaDTO(cargaAtualizada);
    }

    public void excluir(Long id) {
        if (!cargaRepository.existsById(id)) {
            throw new RuntimeException("Carga não encontrada com o ID: " + id);
        }
        cargaRepository.deleteById(id);
    }

    private CargaResponseDTO converterParaDTO(Carga carga) {
        return new CargaResponseDTO(
                carga.getId(),
                carga.getTipoCarga().getId(), // Extrai o ID para devolver no DTO
                carga.getVeiculoId(),
                carga.getMotoristaId(),
                carga.getTempMin(),
                carga.getTempMax(),
                carga.getUmidadeMax(),
                carga.getStatus()
        );
    }
}