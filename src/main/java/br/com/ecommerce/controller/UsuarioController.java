package br.com.ecommerce.controller;

import br.com.ecommerce.exceptions.EnderecoException;
import br.com.ecommerce.exceptions.UsuarioException;
import br.com.ecommerce.models.Usuario;
import br.com.ecommerce.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

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
    public Usuario buscarUsuarioPorId(@RequestParam(value = "id", required = true) Long id){
        Usuario usuario = usuarioService.buscarUsuarioPorId(id).orElseGet(null);
        return usuario;
    }

    @GetMapping("/email") //localhost:8080/usuarios/email?email=daniele@teste.com.br
    public Usuario buscarUsuarioPorEmail(@RequestParam(value = "email", required = true) String email){
        Usuario usuarioEmail = usuarioService.buscarUsuarioPorEmail(email);
        return usuarioEmail;
    }








    
}
