package br.com.fiap.orbifreight.services;

import br.com.fiap.orbifreight.dtos.TipoCargaRequestDTO;
import br.com.fiap.orbifreight.dtos.TipoCargaResponseDTO;
import br.com.fiap.orbifreight.exceptions.ResourceNotFoundException;
import br.com.fiap.orbifreight.models.TipoCarga;
import br.com.fiap.orbifreight.repositories.TipoCargaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoCargaService {

    private final TipoCargaRepository tipoCargaRepository;

    @Transactional
    public TipoCargaResponseDTO salvar(TipoCargaRequestDTO request) {
        TipoCarga tipo = new TipoCarga();
        preencherDados(tipo, request);
        return converterParaDTO(tipoCargaRepository.save(tipo));
    }

    public List<TipoCargaResponseDTO> listarTodos() {
        return tipoCargaRepository.findAll().stream().map(this::converterParaDTO).toList();
    }

    public TipoCargaResponseDTO buscarPorId(Long id) {
        return tipoCargaRepository.findById(id)
                .map(this::converterParaDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de Carga não encontrado com o ID: " + id));
    }

    @Transactional
    public TipoCargaResponseDTO atualizar(Long id, TipoCargaRequestDTO request) {
        TipoCarga tipo = tipoCargaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de Carga não encontrado com o ID: " + id));
        preencherDados(tipo, request);
        return converterParaDTO(tipoCargaRepository.save(tipo));
    }

    @Transactional
    public void excluir(Long id) {
        if (!tipoCargaRepository.existsById(id)) throw new ResourceNotFoundException("Tipo de Carga não encontrado com o ID: " + id);
        tipoCargaRepository.deleteById(id);
    }

    private void preencherDados(TipoCarga tipo, TipoCargaRequestDTO request) {
        tipo.setNome(request.nome());
        tipo.setTempMin(request.tempMin());
        tipo.setTempMax(request.tempMax());
        tipo.setUmidadeMax(request.umidadeMax());
        tipo.setPrazoMaxHoras(request.prazoMaxHoras());
    }

    private TipoCargaResponseDTO converterParaDTO(TipoCarga tipo) {
        return new TipoCargaResponseDTO(
                tipo.getId(), tipo.getNome(), tipo.getTempMin(),
                tipo.getTempMax(), tipo.getUmidadeMax(), tipo.getPrazoMaxHoras()
        );
    }
}