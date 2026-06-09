package br.com.fiap.orbifreight.services;

import br.com.fiap.orbifreight.dtos.DashboardResponseDTO;
import br.com.fiap.orbifreight.repositories.AlertaRepository;
import br.com.fiap.orbifreight.repositories.CargaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final CargaRepository cargaRepository;
    private final AlertaRepository alertaRepository;

    public DashboardResponseDTO obterEstatisticas() {
        return new DashboardResponseDTO(
                (int) cargaRepository.count(),
                (int) cargaRepository.countByStatus("ATIVA"),
                (int) alertaRepository.countByStatus("ABERTO"),
                (int) alertaRepository.countByNivelAndStatus("CRITICO", "ABERTO")
        );
    }
}