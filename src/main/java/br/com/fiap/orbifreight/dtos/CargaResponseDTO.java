package br.com.fiap.orbifreight.dtos;

public record CargaResponseDTO(
        Long id,
        Long tipoId,
        Long veiculoId,
        Long motoristaId,
        Double tempMin,
        Double tempMax,
        Double umidadeMax,
        String status
) {}
