package com.jvm.foro.domain.perfil;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "perfiles")
@Entity(name = "Perfil")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Perfil(DatosRegistroPerfil datos) {
        this.nombre = datos.nombre();
    }

    public void actualizarInformaciones(DatosActualizarPerfil datos) {
        if(datos.nombre() != null) {
            this.nombre = datos.nombre();
        }
    }
}
