package sv.edu.udb.repository.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "platillo")
@NamedQueries({
        @NamedQuery(name = "Platillo.findAll", query = "SELECT p FROM Platillo p"),
        @NamedQuery(name = "Platillo.findById", query = "SELECT p FROM Platillo p WHERE p.id = :id"),
        @NamedQuery(name = "Platillo.desayunos", query = "SELECT p from Platillo p WHERE p.idTipo.id = 1"),
        @NamedQuery(name = "Platillo.almuerzos", query = "SELECT p from Platillo p WHERE p.idTipo.id = 2"),
        @NamedQuery(name = "Platillo.cenas", query = "SELECT p from Platillo p WHERE p.idTipo.id = 3")
})
public class Platillo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plat", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "precio", nullable = false, precision = 8, scale = 2)
    private BigDecimal precio;

    @Column(name = "imagen", length = 200)
    private String imagen;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipo", nullable = false)
    private Menu idTipo;

}