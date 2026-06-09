package br.com.fiap.orbifreight.controllers;

import br.com.fiap.orbifreight.dtos.TipoCargaRequestDTO;
import br.com.fiap.orbifreight.dtos.TipoCargaResponseDTO;
import br.com.fiap.orbifreight.services.TipoCargaService;
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
@RequestMapping("/tipos-carga")
@RequiredArgsConstructor
public class TipoCargaController {

    private final TipoCargaService tipoCargaService;

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<TipoCargaResponseDTO>> buscarPorId(@PathVariable Long id) {
        TipoCargaResponseDTO tipo = tipoCargaService.buscarPorId(id);
        EntityModel<TipoCargaResponseDTO> model = EntityModel.of(tipo);
        model.add(linkTo(methodOn(TipoCargaController.class).buscarPorId(id)).withSelfRel());
        model.add(linkTo(methodOn(TipoCargaController.class).listar()).withRel("tipos-carga"));
        return ResponseEntity.ok(model);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<TipoCargaResponseDTO>>> listar() {
        var tipos = tipoCargaService.listarTodos().stream()
                .map(tipo -> EntityModel.of(tipo,
                        linkTo(methodOn(TipoCargaController.class).buscarPorId(tipo.id())).withSelfRel()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(tipos,
                linkTo(methodOn(TipoCargaController.class).listar()).withSelfRel()));
    }

    @PostMapping
    public ResponseEntity<EntityModel<TipoCargaResponseDTO>> criar(@Valid @RequestBody TipoCargaRequestDTO request) {
        TipoCargaResponseDTO tipo = tipoCargaService.salvar(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tipo.id()).toUri();
        EntityModel<TipoCargaResponseDTO> model = EntityModel.of(tipo);
        model.add(linkTo(methodOn(TipoCargaController.class).buscarPorId(tipo.id())).withSelfRel());
        return ResponseEntity.created(uri).body(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<TipoCargaResponseDTO>> atualizar(@PathVariable Long id, @Valid @RequestBody TipoCargaRequestDTO request) {
        TipoCargaResponseDTO tipo = tipoCargaService.atualizar(id, request);
        EntityModel<TipoCargaResponseDTO> model = EntityModel.of(tipo);
        model.add(linkTo(methodOn(TipoCargaController.class).buscarPorId(id)).withSelfRel());
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        tipoCargaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}