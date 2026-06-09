package br.com.fiap.orbifreight.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PontoParadaId implements Serializable {

    @Column(name = "carga_id")
    private Long cargaId;

    @Column(name = "ordem_parada")
    private Integer ordemParada;
}