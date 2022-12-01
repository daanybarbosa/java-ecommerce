package br.com.ecommerce.controller;

import br.com.ecommerce.dto.CarrinhoDto;
import br.com.ecommerce.exceptions.ProdutoException;
import br.com.ecommerce.exceptions.SessaoException;
import br.com.ecommerce.models.Carrinho;
import br.com.ecommerce.models.Sessao;
import br.com.ecommerce.service.CarrinhoService;
import br.com.ecommerce.service.EnderecoService;
import br.com.ecommerce.service.ProdutoService;
import br.com.ecommerce.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private SessaoService sessaoService;

    @PostMapping("/salvar")
    public ResponseEntity<Carrinho> salvar(@RequestBody CarrinhoDto dto, @RequestHeader String token) {
        try {
            if (!sessaoService.sessaoValida(token)){
                throw new SessaoException("Sessao invalida");
            }

            Sessao sessao = sessaoService.consultarSessaoToken(token);
            Carrinho carrinho = carrinhoService.adicionarProdutos(dto.getProdutosId(), sessao);
            return ResponseEntity.ok(carrinho);

        } catch (ProdutoException | SessaoException erro){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
