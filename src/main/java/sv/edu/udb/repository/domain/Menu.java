package sv.edu.udb.repository.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @Column(name = "id_tipo", nullable = false)
    private Integer id;

    @Column(name = "tipo", length = 50)
    private String tipo;

}