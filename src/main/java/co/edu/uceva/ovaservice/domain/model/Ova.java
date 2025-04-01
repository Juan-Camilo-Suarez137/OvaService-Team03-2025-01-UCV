package co.edu.uceva.ovaservice.domain.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Ova {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message ="No puede estar vacio")
    @Size(min=2, max=20,message="El tamaño tiene que estar entre 2 y 20")
    @Column(nullable = false)
    private String nombre;

    @Size(max = 255, message = "La descripción no puede tener más de 255 caracteres")
    private String descripcion;

    @Min(value = 0, message = "El ova no puede ser negativo")
    @NotNull(message = "El ova es obligatorio")
    @Column(nullable = false)
    private long idCurso;


}
