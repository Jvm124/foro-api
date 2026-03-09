package com.jvm.foro.domain.respuesta;

import com.jvm.foro.domain.topico.Topico;
import com.jvm.foro.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private LocalDateTime fechaCreacion;

    private Boolean solucion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;


    public Respuesta(DatosRegistroRespuesta datos, Usuario autor, Topico topico) {
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.solucion = false;
        this.topico = topico;
        this.autor = autor;
    }


    public void actualizarInformaciones(DatosActualizarRespuesta datos) {
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
        if (datos.solucion() != null) {
            this.solucion = datos.solucion();
        }
    }
}
