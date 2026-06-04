package br.com.fiap.orbifreight.controllers;

import br.com.fiap.orbifreight.dtos.CargaRequestDTO;
import br.com.fiap.orbifreight.dtos.CargaResponseDTO;
import br.com.fiap.orbifreight.services.CargaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cargas") // Define a URL base para este controller
public class CargaController {

    @Autowired
    private CargaService cargaService;

    // POST: Usado para criar uma nova carga
    @PostMapping
    public ResponseEntity<CargaResponseDTO> criar(@Valid @RequestBody CargaRequestDTO request) {
        CargaResponseDTO response = cargaService.salvar(request);
        // Retorna 201 Created quando o registro é inserido com sucesso
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET: Usado para listar todas as cargas
    @GetMapping
    public ResponseEntity<List<CargaResponseDTO>> listar() {
        List<CargaResponseDTO> response = cargaService.listarTodas();
        // Retorna 200 OK com a lista no corpo da requisição
        return ResponseEntity.ok(response);
    }
}