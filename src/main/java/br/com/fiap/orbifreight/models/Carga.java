package br.com.fiap.orbifreight.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CARGA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Os relacionamentos oficiais (@ManyToOne) faremos depois,
    // por enquanto deixamos os IDs diretos para facilitar o CRUD inicial.
    @Column(name = "tipo_id", nullable = false)
    private Long tipoId;

    @Column(name = "veiculo_id", nullable = false)
    private Long veiculoId;

    @Column(name = "motorista_id", nullable = false)
    private Long motoristaId;

    @Column(name = "temp_min", nullable = false)
    private Double tempMin;

    @Column(name = "temp_max", nullable = false)
    private Double tempMax;

    @Column(name = "umidade_max", nullable = false)
    private Double umidadeMax;

    @Column(name = "status", nullable = false, length = 50)
    private String status;
}