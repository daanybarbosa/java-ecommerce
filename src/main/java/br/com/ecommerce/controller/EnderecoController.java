package br.com.ecommerce.controller;

import br.com.ecommerce.models.Endereco;
import br.com.ecommerce.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping("/salvarEndereco") //localhost:8080/enderecos/salvarEndereco
    public ResponseEntity<Endereco> salvarEndereco(@RequestBody Endereco endereco){
        try {
            Endereco enderecoDB = enderecoService.salvar(endereco);
            return ResponseEntity.ok(enderecoDB);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscarCep") //localhost:8080/enderecos/buscarCep?cep=11223345
    public Endereco buscarCep(@RequestParam(value = "cep", required = true) Integer cep){
        Endereco enderecoCep = enderecoService.buscarPorCep(cep);
        return enderecoCep;
    }

    @GetMapping("/buscarPorEstadoECidade") //localhost:8080/enderecos/buscarPorEstadoECidade?estado=sp&cidade=sao paulo
    public List<Endereco> buscarPorEstadoECidade(@RequestParam(value = "estado", required = false) String estado,
                                                 @RequestParam(value = "cidade", required = false) String cidade){
        List<Endereco> enderecos = enderecoService.buscarPorEstadoECidade(estado, cidade);
        return enderecos;
    }

}
