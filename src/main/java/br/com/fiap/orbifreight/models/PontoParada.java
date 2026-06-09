package br.com.fiap.orbifreight.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "PONTO_PARADA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PontoParada {

    @EmbeddedId
    private PontoParadaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cargaId") // Diz ao JPA que a coluna carga_id da chave composta é esta relação
    @JoinColumn(name = "carga_id")
    private Carga carga;

    @Column(name = "localidade", nullable = false, length = 150)
    private String localidade;

    @Column(name = "data_chegada")
    private LocalDateTime dataChegada;
}
