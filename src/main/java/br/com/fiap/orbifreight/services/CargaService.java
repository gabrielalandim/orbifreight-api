package br.com.fiap.orbifreight.services;

import br.com.fiap.orbifreight.dtos.CargaRequestDTO;
import br.com.fiap.orbifreight.dtos.CargaResponseDTO;
import br.com.fiap.orbifreight.exceptions.ResourceNotFoundException;
import br.com.fiap.orbifreight.models.Carga;
import br.com.fiap.orbifreight.models.TipoCarga;
import br.com.fiap.orbifreight.repositories.CargaRepository;
import br.com.fiap.orbifreight.repositories.TipoCargaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CargaService {

    private final CargaRepository cargaRepository;
    private final TipoCargaRepository tipoCargaRepository;

    @Transactional
    public CargaResponseDTO salvar(CargaRequestDTO request) {
        TipoCarga tipo = tipoCargaRepository.findById(request.tipoId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de Carga não encontrado: " + request.tipoId()));

        Carga carga = new Carga();
        preencherDados(carga, request, tipo);

        return converterParaDTO(cargaRepository.save(carga));
    }

    public List<CargaResponseDTO> listarTodas() {
        return cargaRepository.findAll().stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public CargaResponseDTO buscarPorId(Long id) {
        return cargaRepository.findById(id)
                .map(this::converterParaDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Carga não encontrada com o ID: " + id));
    }

    @Transactional
    public CargaResponseDTO atualizar(Long id, CargaRequestDTO request) {
        Carga carga = cargaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carga não encontrada com o ID: " + id));

        TipoCarga tipo = tipoCargaRepository.findById(request.tipoId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de Carga não encontrado: " + request.tipoId()));

        preencherDados(carga, request, tipo);
        return converterParaDTO(cargaRepository.save(carga));
    }

    @Transactional
    public void excluir(Long id) {
        if (!cargaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Carga não encontrada com o ID: " + id);
        }
        cargaRepository.deleteById(id);
    }

    private void preencherDados(Carga carga, CargaRequestDTO request, TipoCarga tipo) {
        carga.setTipoCarga(tipo);
        carga.setVeiculoId(request.veiculoId());
        carga.setMotoristaId(request.motoristaId());
        carga.setPlacaVeiculo(request.placaVeiculo());
        carga.setOrigem(request.origem());
        carga.setDestino(request.destino());
        carga.setTempMin(request.tempMin());
        carga.setTempMax(request.tempMax());
        carga.setUmidadeMax(request.umidadeMax());
        carga.setStatus(request.status());
    }

    private CargaResponseDTO converterParaDTO(Carga carga) {
        return new CargaResponseDTO(
                carga.getId(),
                carga.getTipoCarga().getId(),
                carga.getVeiculoId(),
                carga.getMotoristaId(),
                carga.getPlacaVeiculo(),
                carga.getOrigem(),
                carga.getDestino(),
                carga.getTempMin(),
                carga.getTempMax(),
                carga.getUmidadeMax(),
                carga.getStatus()
        );
    }
}