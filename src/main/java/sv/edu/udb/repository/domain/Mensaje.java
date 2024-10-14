package sv.edu.udb.repository.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mensaje {
    private String icono;
    private String title;
}
