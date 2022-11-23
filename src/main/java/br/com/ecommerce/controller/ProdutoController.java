package br.com.ecommerce.controller;

import br.com.ecommerce.models.Produto;
import br.com.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/salvar") //localhost:8080/produtos/salvar
    public Produto salvarProduto(@RequestBody Produto produto){
        Produto produtoDB = produtoService.salvarProduto(produto);
        return produtoDB;
    }

    @GetMapping("/id") //localhost:8080/produtos/id?id=19
    public Produto buscarProdutoPorId(@RequestParam(value = "id", required = true) Long id){
        Produto produtoId = produtoService.buscarProdutoPorId(id).orElseGet(null);
        return produtoId;
    }

    @GetMapping("/nome") //localhost:8080/produtos/nome?nomeDoProduto=Smart TV
    public Produto buscarProdutoPorNome(@RequestParam(value = "nomeDoProduto", required = true) String nomeDoProduto){
        Produto produtoName = produtoService.buscarProdutoPorNome(nomeDoProduto).orElseGet(null);
        return produtoName;
    }

    @PutMapping("/atualizarQtde")
    public ResponseEntity<Produto> atualizarQtdeProduto(@RequestParam(value = "id", required = true) Long id,
                                                        @RequestParam(value = "qtdeProduto", required = false) Integer qtdeProduto){
        try {
            Produto produto = produtoService.atualizarQtde(id, qtdeProduto);
            return ResponseEntity.ok(produto);
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }



    }

}
