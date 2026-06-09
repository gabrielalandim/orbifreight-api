package br.com.fiap.orbifreight.controllers;

import org.springframework.http.HttpStatus;
import br.com.fiap.orbifreight.config.TokenService;
import br.com.fiap.orbifreight.dtos.LoginRequestDTO;
import br.com.fiap.orbifreight.dtos.LoginResponseDTO;
import br.com.fiap.orbifreight.dtos.RegisterRequestDTO;
import br.com.fiap.orbifreight.models.Usuario;
import br.com.fiap.orbifreight.repositories.UsuarioRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Endpoints para login e registro de usuários")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository repository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var usuario = (Usuario) auth.getPrincipal();
        var token = tokenService.gerarToken(usuario);

        return ResponseEntity.ok(new LoginResponseDTO(token, usuario.getId(), usuario.getNome()));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequestDTO data) {
        if (this.repository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = passwordEncoder.encode(data.senha());
        Usuario novoUsuario = new Usuario(null, data.nome(), data.email(), encryptedPassword, data.cargo());

        this.repository.save(novoUsuario);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}