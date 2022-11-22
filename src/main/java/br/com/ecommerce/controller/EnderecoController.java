package br.com.ecommerce.controller;

import br.com.ecommerce.models.Endereco;
import br.com.ecommerce.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping("/salvarEndereco")
    public ResponseEntity<Endereco> salvarEndereco(@RequestBody Endereco endereco){
        try {
            Endereco enderecoDB = enderecoService.salvar(endereco);
            return ResponseEntity.ok(enderecoDB);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscarCep")
    public Endereco buscarCep(@RequestParam(value = "cep", required = true) Integer cep){
        Endereco enderecoCep = enderecoService.buscarPorCep(cep);
        return enderecoCep;
    }

    /*
    @GetMapping("/buscarPorEstadoECidade/{estado}/{cidade}")
    public Endereco buscarPorEstadoECidade(@RequestParam(value = "estado", required = false) String estado, @RequestParam(value = "cidade", required = false) String cidade){
        Endereco endEstadoECidade = enderecoService.buscarPorEstadoECidade(estado, cidade);
        return endEstadoECidade;
    }

    @GetMapping("/buscarPorEstadoECidade/{estado}/{cidade}")
    public Endereco buscarPorEstadoECidade(@PathVariable String estado, @PathVariable String cidade){
        Endereco endEstadoECidade = enderecoService.buscarPorEstadoECidade(estado, cidade);
        return endEstadoECidade;
    }*/
}
