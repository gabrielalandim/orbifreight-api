package br.com.fiap.orbifreight.controllers;

import br.com.fiap.orbifreight.dtos.DashboardResponseDTO;
import br.com.fiap.orbifreight.services.DashboardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Endpoints para visualização de estatísticas do sistema")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<EntityModel<DashboardResponseDTO>> getDashboard() {
        DashboardResponseDTO estatisticas = dashboardService.obterEstatisticas();

        EntityModel<DashboardResponseDTO> model = EntityModel.of(estatisticas);

        // Links de navegação para as entidades relacionadas ao dashboard
        model.add(linkTo(methodOn(DashboardController.class).getDashboard()).withSelfRel());
        model.add(linkTo(methodOn(CargaController.class).listarTodas()).withRel("cargas"));
        model.add(linkTo(methodOn(AlertaController.class).listar()).withRel("alertas"));

        return ResponseEntity.ok(model);
    }
}