package sv.edu.udb.repository.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orden")
@NamedQueries({
        @NamedQuery(name = "Orden.findByCustomer", query = "SELECT o FROM Orden o WHERE o.idCli = :id_cli")
})
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cli", nullable = false)
    private Cliente idCli;

    @Column(name = "fecha_ped")
    private LocalDate fechaPed;

    @Column(name = "total", precision = 8, scale = 2)
    private BigDecimal total;

    @Column(name = "estado", length = 25)
    private String estado;

    @OneToMany(mappedBy = "idOrden") //->Se mapea con el nombre del atributo en la clase, NO el nombre de la tabla
    private List<DetalleOrden> detalleList;
}