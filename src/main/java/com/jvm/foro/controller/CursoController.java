package com.jvm.foro.controller;

import com.jvm.foro.domain.curso.*;

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
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private RegistroDeCursos registro;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalleCurso> registrar(@RequestBody @Valid DatosRegistroCurso datosCurso, UriComponentsBuilder uriBuilder){
        var detalleCurso = registro.registrar(datosCurso);
        var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(detalleCurso.id()).toUri();
        return ResponseEntity.created(uri).body(detalleCurso);
    }

    @GetMapping //@PageableDefault(size=10, sort={"titulo"})
    public ResponseEntity<Page<DatosListaCurso>> listar(Pageable paginacion){
        var page = cursoRepository.findAllByActivoTrue(paginacion).map(DatosListaCurso::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var cursoOptional = cursoRepository.findById(id);

        if (cursoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var curso = cursoOptional.get();
        return ResponseEntity.ok(new DatosDetalleCurso(curso));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizarCurso datos) {
        var curso = cursoRepository.getReferenceById(id);
        curso.actualizarInformaciones(datos);
        return ResponseEntity.ok(new DatosDetalleCurso(curso));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id){
        var topico = cursoRepository.getReferenceById(id);
        topico.eliminacionLogica();
        return ResponseEntity.noContent().build();
    }

}
