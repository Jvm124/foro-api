package com.jvm.foro.domain.usuario;

import com.jvm.foro.domain.perfil.Perfil;
import com.jvm.foro.domain.respuesta.Respuesta;
import com.jvm.foro.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    @Column(name = "contrasenia")
    private String contrasenia;


    private Boolean activo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    @OneToMany(mappedBy = "autor")
    private List<Topico> topicos = new ArrayList<>();

    @OneToMany(mappedBy = "autor")
    private List<Respuesta> respuestas = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(perfil.getNombre()));
    }

    @Override
    public String getPassword() {
        return contrasenia;
    }

    @Override
    public String getUsername() {
        return correoElectronico;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Usuario(DatosRegistroUsuario datos, String contraseniaEncriptada, Perfil perfil) {
        this.activo = true;
        this.nombre = datos.nombre();
        this.correoElectronico = datos.correoElectronico();
        this.contrasenia = contraseniaEncriptada;
        this.perfil = perfil;
    }
    public void actualizarInformaciones(DatosActualizarUsuario datos, PasswordEncoder passwordEncoder) {
        if (datos.nombre() != null) {
            this.nombre = datos.nombre();
        }
        if (datos.correoElectronico() != null) {
            this.correoElectronico = datos.correoElectronico();
        }
        if (datos.contrasenia() != null) {
            this.contrasenia = passwordEncoder.encode(datos.contrasenia());
        }
    }

    public void eliminacionLogica() {
        this.activo = false;
    }
}