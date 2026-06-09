package br.com.fiap.orbifreight.services;

import br.com.fiap.orbifreight.dtos.AlertaRequestDTO;
import br.com.fiap.orbifreight.dtos.AlertaResponseDTO;
import br.com.fiap.orbifreight.exceptions.ResourceNotFoundException;
import br.com.fiap.orbifreight.models.Alerta;
import br.com.fiap.orbifreight.models.Carga;
import br.com.fiap.orbifreight.repositories.AlertaRepository;
import br.com.fiap.orbifreight.repositories.CargaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertaService {

    private final AlertaRepository alertaRepository;
    private final CargaRepository cargaRepository;

    @Transactional
    public AlertaResponseDTO salvar(AlertaRequestDTO request) {
        Carga carga = cargaRepository.findById(request.cargaId())
                .orElseThrow(() -> new ResourceNotFoundException("Carga não encontrada: " + request.cargaId()));

        Alerta alerta = new Alerta();
        preencherDados(alerta, request, carga);
        return converterParaDTO(alertaRepository.save(alerta));
    }

    public List<AlertaResponseDTO> listarTodos() {
        return alertaRepository.findAll().stream().map(this::converterParaDTO).toList();
    }

    public AlertaResponseDTO buscarPorId(Long id) {
        return alertaRepository.findById(id)
                .map(this::converterParaDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Alerta não encontrado: " + id));
    }

    @Transactional
    public AlertaResponseDTO atualizar(Long id, AlertaRequestDTO request) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alerta não encontrado: " + id));
        Carga carga = cargaRepository.findById(request.cargaId())
                .orElseThrow(() -> new ResourceNotFoundException("Carga não encontrada: " + request.cargaId()));

        preencherDados(alerta, request, carga);
        return converterParaDTO(alertaRepository.save(alerta));
    }

    @Transactional
    public void excluir(Long id) {
        if (!alertaRepository.existsById(id)) throw new ResourceNotFoundException("Alerta não encontrado: " + id);
        alertaRepository.deleteById(id);
    }

    private void preencherDados(Alerta alerta, AlertaRequestDTO request, Carga carga) {
        alerta.setCarga(carga);
        alerta.setTitulo(request.titulo());
        alerta.setDescricao(request.descricao());
        alerta.setNivel(request.nivel());
        alerta.setStatus(request.status());
    }

    private AlertaResponseDTO converterParaDTO(Alerta alerta) {
        return new AlertaResponseDTO(
                alerta.getId(), alerta.getCarga().getId(), alerta.getTitulo(),
                alerta.getDescricao(), alerta.getNivel(), alerta.getStatus(), alerta.getDataCriacao()
        );
    }
}