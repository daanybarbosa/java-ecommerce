package br.com.ecommerce.controller;

import br.com.ecommerce.exceptions.ProdutoException;
import br.com.ecommerce.exceptions.SessaoException;
import br.com.ecommerce.models.Produto;
import br.com.ecommerce.service.ProdutoService;
import br.com.ecommerce.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private SessaoService sessaoService;

    @PostMapping("/salvar") //localhost:8080/produtos/salvar
    public ResponseEntity salvarProduto(@RequestBody Produto produto, @RequestHeader String token){
        try {
            if (!sessaoService.sessaoValida(token)){
                throw new SessaoException("Sessao invalida");
            }
            Produto produtoDB = produtoService.salvarProduto(produto);
            return ResponseEntity.ok(produto);
        } catch (SessaoException erro) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/id") //localhost:8080/produtos/id?id=19
    public ResponseEntity buscarProdutoPorId(@RequestParam(value = "id", required = true) Long id, @RequestHeader String token){
        try {
            if (!sessaoService.sessaoValida(token)){
                throw new SessaoException("Sessao invalida");
            }
            Produto produtoId = produtoService.buscarProdutoPorId(id).orElseGet(null);
            return ResponseEntity.ok(produtoId);
        } catch (SessaoException erro){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @GetMapping("/nome") //localhost:8080/produtos/nome?nomeDoProduto=Smart TV
    public ResponseEntity buscarProdutoPorNome(@RequestParam(value = "nomeDoProduto", required = true) String nomeDoProduto,
                                        @RequestHeader String token){
        try {
            if (!sessaoService.sessaoValida(token)){
                throw new SessaoException("Sessao invalida");
            }
            Produto produtoName = produtoService.buscarProdutoPorNome(nomeDoProduto).orElseGet(null);
            return ResponseEntity.ok(produtoName);
        } catch (SessaoException erro){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @PutMapping("/atualizarQtde") //localhost:8080/produtos/atualizarQtde?id=1&qtdeProduto=25
    public ResponseEntity<Produto> atualizarQtdeProduto(@RequestParam(value = "id", required = true) Long id,
                                                        @RequestParam(value = "qtdeProduto", required = false) Integer qtdeProduto,
                                                        @RequestHeader String token){
        try {
            if (!sessaoService.sessaoValida(token)){
                throw new SessaoException("Sessao invalida");
            }
            Produto produto = produtoService.atualizarQtde(id, qtdeProduto);
            return ResponseEntity.ok(produto);
        } catch(SessaoException | ProdutoException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
