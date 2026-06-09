package br.com.fiap.orbifreight.controllers;

import br.com.fiap.orbifreight.dtos.AlertaRequestDTO;
import br.com.fiap.orbifreight.dtos.AlertaResponseDTO;
import br.com.fiap.orbifreight.services.AlertaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/alertas")
@RequiredArgsConstructor
public class AlertaController {

    private final AlertaService alertaService;

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<AlertaResponseDTO>> buscarPorId(@PathVariable Long id) {
        AlertaResponseDTO alerta = alertaService.buscarPorId(id);
        EntityModel<AlertaResponseDTO> model = EntityModel.of(alerta);
        model.add(linkTo(methodOn(AlertaController.class).buscarPorId(id)).withSelfRel());
        model.add(linkTo(methodOn(AlertaController.class).listar()).withRel("alertas"));
        return ResponseEntity.ok(model);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<AlertaResponseDTO>>> listar() {
        var alertas = alertaService.listarTodos().stream()
                .map(alerta -> EntityModel.of(alerta,
                        linkTo(methodOn(AlertaController.class).buscarPorId(alerta.id())).withSelfRel()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(alertas,
                linkTo(methodOn(AlertaController.class).listar()).withSelfRel()));
    }

    @PostMapping
    public ResponseEntity<EntityModel<AlertaResponseDTO>> criar(@Valid @RequestBody AlertaRequestDTO request) {
        AlertaResponseDTO alerta = alertaService.salvar(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(alerta.id()).toUri();
        EntityModel<AlertaResponseDTO> model = EntityModel.of(alerta);
        model.add(linkTo(methodOn(AlertaController.class).buscarPorId(alerta.id())).withSelfRel());
        return ResponseEntity.created(uri).body(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<AlertaResponseDTO>> atualizar(@PathVariable Long id, @Valid @RequestBody AlertaRequestDTO request) {
        AlertaResponseDTO alerta = alertaService.atualizar(id, request);
        EntityModel<AlertaResponseDTO> model = EntityModel.of(alerta);
        model.add(linkTo(methodOn(AlertaController.class).buscarPorId(id)).withSelfRel());
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        alertaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}