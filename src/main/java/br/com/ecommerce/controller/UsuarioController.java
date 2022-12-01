package br.com.ecommerce.controller;

import br.com.ecommerce.exceptions.EnderecoException;
import br.com.ecommerce.exceptions.UsuarioException;
import br.com.ecommerce.models.Usuario;
import br.com.ecommerce.service.SessaoService;
import br.com.ecommerce.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SessaoService sessaoService;

    @PostMapping("/salvar")
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario){
        try {
            Usuario usuarioDB = usuarioService.salvar(usuario);
            return ResponseEntity.ok(usuarioDB);
        } catch (UsuarioException | EnderecoException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/id") //localhost:8080/usuarios/id?id=4
    public ResponseEntity buscarUsuarioPorId(@RequestParam(value = "id", required = true) Long id, @RequestHeader String token){
        try {
            if (!sessaoService.sessaoValida(token)) {
                throw new UsuarioException("Sessao invalida");
            }
            Usuario usuario = usuarioService.buscarUsuarioPorId(id).orElseGet(null);
            return ResponseEntity.ok(usuario);
        } catch (UsuarioException erro){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/email") //localhost:8080/usuarios/email?email=dani@teste.com.br&senha=12345
    public ResponseEntity buscarUsuarioPorEmailESenha(@RequestParam(value = "email", required = true) String email,
                                                      @RequestParam(value = "senha", required = true) String senha,
                                                      @RequestHeader String token){
        try {
            if (!sessaoService.sessaoValida(token)){
                throw new UsuarioException("Sessao invalida");
            }
            Usuario emailESenha = usuarioService.buscarUsuarioPorEmailESenha(email, senha);
            return ResponseEntity.ok(emailESenha);
        } catch (UsuarioException erro){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/endereco")
    public ResponseEntity buscarEnderecoUsuario(@RequestParam(value = "cep", required = true) Integer cep, @RequestHeader String token){
        try {
            if (!sessaoService.sessaoValida(token)){
                throw new UsuarioException("Sessao invalida");
            }
            Usuario endereco = usuarioService.buscarEndereco(cep);
            return ResponseEntity.ok(endereco);
        } catch (UsuarioException erro){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
