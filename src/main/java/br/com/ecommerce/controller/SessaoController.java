package br.com.ecommerce.controller;

import br.com.ecommerce.dto.LoginDto;
import br.com.ecommerce.exceptions.SessaoException;
import br.com.ecommerce.models.Sessao;
import br.com.ecommerce.service.SessaoService;
import br.com.ecommerce.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sessao")
public class SessaoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SessaoService sessaoService;

    @PostMapping("/validarLogin") //localhost:8080/login/validarLogin
    public ResponseEntity<LoginDto> validarLogin(@RequestBody LoginDto loginDto){
        try {
            Sessao sessao = sessaoService.ativarSessao(loginDto);
            loginDto.setToken(sessao.getToken());
            return ResponseEntity.ok(loginDto);
        } catch (SessaoException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/validarToken") //localhost:8080/sessao/validarToken?token=c360a158-bf56-45e5-a374-83413d809ce0
    public Sessao validarPorToken(@RequestParam(value = "token", required = true) String token){
        Sessao sessao = sessaoService.consultarSessaoToken(token);
        return sessao;
    }
}
