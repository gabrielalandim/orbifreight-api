package br.com.fiap.orbifreight.controllers;

import br.com.fiap.orbifreight.dtos.CargaRequestDTO;
import br.com.fiap.orbifreight.dtos.CargaResponseDTO;
import br.com.fiap.orbifreight.services.CargaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/cargas")
@RequiredArgsConstructor
public class CargaController {

    private final CargaService cargaService;

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CargaResponseDTO>> buscarPorId(@PathVariable Long id) {
        CargaResponseDTO carga = cargaService.buscarPorId(id);

        EntityModel<CargaResponseDTO> model = EntityModel.of(carga);
        model.add(linkTo(methodOn(CargaController.class).buscarPorId(id)).withSelfRel());
        model.add(linkTo(methodOn(CargaController.class).listarTodas()).withRel("cargas"));

        return ResponseEntity.ok(model);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<CargaResponseDTO>>> listarTodas() {
        List<EntityModel<CargaResponseDTO>> cargas = cargaService.listarTodas().stream()
                .map(carga -> EntityModel.of(carga,
                        linkTo(methodOn(CargaController.class).buscarPorId(carga.id())).withSelfRel()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(cargas,
                linkTo(methodOn(CargaController.class).listarTodas()).withSelfRel()));
    }

    @PostMapping
    public ResponseEntity<EntityModel<CargaResponseDTO>> salvar(@Valid @RequestBody CargaRequestDTO request) {
        CargaResponseDTO carga = cargaService.salvar(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(carga.id()).toUri();

        EntityModel<CargaResponseDTO> model = EntityModel.of(carga);
        model.add(linkTo(methodOn(CargaController.class).buscarPorId(carga.id())).withSelfRel());

        return ResponseEntity.created(uri).body(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<CargaResponseDTO>> atualizar(@PathVariable Long id, @Valid @RequestBody CargaRequestDTO request) {
        CargaResponseDTO carga = cargaService.atualizar(id, request);

        EntityModel<CargaResponseDTO> model = EntityModel.of(carga);
        model.add(linkTo(methodOn(CargaController.class).buscarPorId(id)).withSelfRel());

        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        cargaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}