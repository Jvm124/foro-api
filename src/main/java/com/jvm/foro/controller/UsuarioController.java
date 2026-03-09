package com.jvm.foro.controller;

import com.jvm.foro.domain.curso.*;
import com.jvm.foro.domain.usuario.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RegistroDeUsuarios registro;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalleUsuario> registrar(@RequestBody @Valid DatosRegistroUsuario datosUsuario, UriComponentsBuilder uriBuilder){
        var detalleUsuario = registro.registrar(datosUsuario);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(detalleUsuario.id()).toUri();
        return ResponseEntity.created(uri).body(detalleUsuario);
    }

    @GetMapping //@PageableDefault(size=10, sort={"titulo"})
    public ResponseEntity<Page<DatosListaUsuario>> listar(Pageable paginacion){
        var page = usuarioRepository.findAllByActivoTrue(paginacion).map(DatosListaUsuario::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var usuario = usuarioOptional.get();
        return ResponseEntity.ok(new DatosDetalleUsuario(usuario));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizarUsuario datos) {
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.actualizarInformaciones(datos, passwordEncoder);
        return ResponseEntity.ok(new DatosDetalleUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.eliminacionLogica();
        return ResponseEntity.noContent().build();
    }

}


