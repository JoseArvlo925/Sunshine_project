package sv.edu.udb.repository.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente", schema = "sunshine_ecommerce")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Cliente.findByID", query = "SELECT c FROM Cliente c WHERE c.id = :id"),
        @NamedQuery(name = "Cliente.Auth", query = "SELECT c FROM Cliente c WHERE c.correo = :correo AND c.contrasenia = :contrasenia"),
        @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
        @NamedQuery(name = "Cliente.countEmail", query = "SELECT COUNT(c) FROM Cliente c where correo = :correo")
})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //->Indica que el atributo se genera
    @Column(name = "id_cli", nullable = false)
    private Integer id;

    @Column(name = "nombres", nullable = false, length = 200)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 200)
    private String apellidos;

    @Column(name = "telefono", length = 50)
    private String telefono;

    @Column(name = "correo", nullable = false, length = 100)
    private String correo;

    @Column(name = "contrasenia", nullable = false, length = 50)
    private String contrasenia;

    @Column( name = "tipo", nullable = false, length = 25)
    private String tipo;
}