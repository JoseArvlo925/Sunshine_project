package sv.edu.udb.repository.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sv.edu.udb.repository.domain.Cliente;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "orden")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cli", nullable = false)
    private Cliente idCli;

    @Column(name = "fecha_ped")
    private Instant fechaPed;

    @Column(name = "total", precision = 8, scale = 2)
    private BigDecimal total;

    @Column(name = "estado", length = 25)
    private String estado;

}