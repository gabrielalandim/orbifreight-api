package br.com.fiap.orbifreight.controllers;

import br.com.fiap.orbifreight.dtos.LeituraIoTRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/iot")
@RequiredArgsConstructor
@Tag(name = "IoT", description = "Endpoints para recebimento de telemetria de sensores")
public class IoTController {

    @PostMapping("/leituras")
    @Operation(summary = "Recebe leitura de sensor IoT", description = "Processa os dados enviados pelos sensores das cargas em trânsito.")
    public ResponseEntity<String> receberLeitura(@Valid @RequestBody LeituraIoTRequest payload) {

        // Aqui você integraria com o seu Service para persistir no banco ou gerar um alerta
        System.out.println("🌡 ALERTA IOT RECEBIDO!");
        System.out.println("Carga: " + payload.carga_id());
        System.out.println("Temperatura: " + payload.temperatura() + "°C");
        System.out.println("Risco: " + payload.risco());
        System.out.println("-------------------------");

        return ResponseEntity.ok("Dados recebidos com sucesso pelo backend!");
    }
}