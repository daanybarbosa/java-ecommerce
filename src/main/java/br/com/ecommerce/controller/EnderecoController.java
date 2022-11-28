package br.com.ecommerce.controller;

import br.com.ecommerce.exceptions.EnderecoException;
import br.com.ecommerce.exceptions.SessaoException;
import br.com.ecommerce.models.Endereco;
import br.com.ecommerce.service.EnderecoService;
import br.com.ecommerce.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private SessaoService sessaoService;

    @PostMapping("/salvarEndereco") //localhost:8080/enderecos/salvarEndereco
    public ResponseEntity<Endereco> salvarEndereco(@RequestBody Endereco endereco, @RequestHeader String token){
        try {
            if (!sessaoService.sessaoValida(token)){
                throw new SessaoException("Sessao invalida");
            }
            Endereco enderecoDB = enderecoService.salvar(endereco);
            return ResponseEntity.ok(enderecoDB);
        } catch (EnderecoException | SessaoException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/buscarCep") //localhost:8080/enderecos/buscarCep?cep=11223345
    public ResponseEntity<Endereco> buscarCep(@RequestParam(value = "cep", required = true) Integer cep, @RequestHeader String token){
        try {
           if (!sessaoService.sessaoValida(token)){
               throw new SessaoException("Sessao invalida");
           }
            Endereco enderecoCep = enderecoService.buscarPorCep(cep);
            return ResponseEntity.ok(enderecoCep);
        } catch (SessaoException erro) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @GetMapping("/buscarPorEstadoECidade") //localhost:8080/enderecos/buscarPorEstadoECidade?estado=sp&cidade=sao paulo
    public ResponseEntity<List<Endereco>> buscarPorEstadoECidade(@RequestParam(value = "estado", required = false) String estado,
                                                                 @RequestParam(value = "cidade", required = false) String cidade,
                                                                 @RequestHeader String token){
        try {
            if (!sessaoService.sessaoValida(token)){
                throw new SessaoException("Sessao invalida");
            }
            List<Endereco> enderecos = enderecoService.buscarPorEstadoECidade(estado, cidade);
            return ResponseEntity.ok(enderecos);
        } catch (SessaoException erro){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
