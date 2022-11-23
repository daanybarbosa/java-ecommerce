package br.com.ecommerce.service;

import br.com.ecommerce.exceptions.ProdutoException;
import br.com.ecommerce.models.Produto;
import br.com.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Optional<Produto> buscarProdutoPorId(Long id){
         Optional<Produto> produtoDB = produtoRepository.findById(id);
         return produtoDB;
    }

    public Optional<Produto> buscarProdutoPorNome(String nomeDoProduto){
        Optional<Produto> produtoName = produtoRepository.findByNomeDoProduto(nomeDoProduto);
        return produtoName;
    }

    //id e a qtde
    public Produto atualizarQtde(Long id, Integer qtdeProduto) throws ProdutoException {

        Optional<Produto> produtoOpt = buscarProdutoPorId(id);
        validaProduto(produtoOpt);

        if (produtoOpt.isPresent()){
            Produto produto = produtoOpt.get();
            produto.setQtdeProduto(qtdeProduto); //atualizar qtde
            produtoRepository.save(produto);
        }
        return null;
    }

    public void validaProduto(Optional<Produto> produto) throws ProdutoException {

        if (produto.get().getNomeDoProduto() == null | produto.get().getNomeDoProduto().length() <= 0){
            throw new ProdutoException("Nome do produto deve ser preenchido");
        }

        if (produto.get().getModelo() == null | produto.get().getModelo().length() <= 0){
            throw new ProdutoException("Modelo do produto deve ser preenchido");
        }

        if (produto.get().getFabricante() == null | produto.get().getFabricante().length() <= 0){
            throw new ProdutoException("Fabricante deve ser preenchido");
        }

        if (produto.get().getCategoria() == null){
            throw new ProdutoException("Categoria deve ser preenchido");
        }

        if (produto.get().getValorUnitario() == null){
            throw new ProdutoException("Valor unitário deve ser preenchido");
        }

        if (produto.get().getQtdeProduto() == null){
            throw new ProdutoException("Quantidade do produto deve ser preenchiida");
        }

        if (produto.get().getDescricaoDoProduto() == null | produto.get().getDescricaoDoProduto().length() <= 0){
            throw new ProdutoException("Descrição do produto deve ser preenchida");
        }

        if (produto.get().getInformacoesTecnicas() == null | produto.get().getInformacoesTecnicas().length() <= 0){
            throw new ProdutoException("Informações Técnicas deve ser preenchido");
        }
    }


}
