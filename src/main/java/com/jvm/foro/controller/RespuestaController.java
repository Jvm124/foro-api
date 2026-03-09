package com.jvm.foro.controller;

import com.jvm.foro.domain.respuesta.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {
    @Autowired
    private RepuestaRepository respuestaRepository;

    @Autowired
    private RegistroDeRespuestas registro;
    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalleRespuesta> registrar(@RequestBody @Valid DatosRegistroRespuesta datosRespuesta, UriComponentsBuilder uriBuilder){
        var detalleRespuesta = registro.registrar(datosRespuesta);
        var uri = uriBuilder.path("/respuestas/{id}").buildAndExpand(detalleRespuesta.id()).toUri();
        return ResponseEntity.created(uri).body(detalleRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaRespuesta>> listar(@PageableDefault(size=10, sort={"titulo"}) Pageable paginacion){
        var page = respuestaRepository.findAll(paginacion).map(DatosListaRespuesta::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var respuestaOptional = respuestaRepository.findById(id);

        if (respuestaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var respuesta = respuestaOptional.get();
        return ResponseEntity.ok(new DatosDetalleRespuesta(respuesta));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizarRespuesta datos) {
        var respuesta = respuestaRepository.getReferenceById(id);
        respuesta.actualizarInformaciones(datos);
        return ResponseEntity.ok(new DatosDetalleRespuesta(respuesta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id){
        respuestaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }





}



