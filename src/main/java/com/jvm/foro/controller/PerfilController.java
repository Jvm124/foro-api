package com.jvm.foro.controller;


import com.jvm.foro.domain.perfil.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/perfiles")
@SecurityRequirement(name = "bearer-key")
public class PerfilController {
    @Autowired
    private RegistroDePerfiles registro;

    @Autowired
    private PerfilRepository perfilRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetallePerfil> registrar(@RequestBody @Valid DatosRegistroPerfil datosPerfil, UriComponentsBuilder uriBuilder){
        var detallePerfil = registro.registrar(datosPerfil);
        var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(detallePerfil.id()).toUri();
        return ResponseEntity.created(uri).body(detallePerfil);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaPerfil>> listar(Pageable paginacion){
        var page = perfilRepository.findAll(paginacion).map (DatosListaPerfil::new);
        return ResponseEntity.ok(page);
    }
    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var perfilOptional = perfilRepository.findById(id);

        if (perfilOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var perfil = perfilOptional.get();
        return ResponseEntity.ok(new DatosDetallePerfil(perfil));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizarPerfil datos) {
        var perfil = perfilRepository.getReferenceById(id);
        perfil.actualizarInformaciones(datos);
        return ResponseEntity.ok(new DatosDetallePerfil(perfil));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id){
        perfilRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
