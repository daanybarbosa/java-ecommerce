package br.com.ecommerce.service;

import br.com.ecommerce.exceptions.ProdutoException;
import br.com.ecommerce.models.*;
import br.com.ecommerce.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private EnderecoService enderecoService;



    public Carrinho adicionarProdutos(List<Long> ids, Sessao sessao) throws ProdutoException {
        Carrinho carrinho = consultarSeCarrinhoExiste(sessao.getUsuario());

        for (Long id : ids){
            Produto produto = produtoService.buscarProdutoPorId(id).orElseThrow(() -> new ProdutoException("Produto inexistente!"));

            if (produto != null){
                carrinho.getProdutos().add(produto);
            } else {
                throw new ProdutoException("Estoque insuficiente");
            }
        }

        carrinho.setQtdeProduto(ids.size());
        carrinho.setFrete(new BigDecimal(10.0));
        carrinho.getValorTotalProduto();
        carrinho.getValorTotalCarrinho();

        return carrinhoRepository.save(carrinho);
    }

    public Carrinho consultarSeCarrinhoExiste(Usuario usuario){
        Carrinho carrinho = carrinhoRepository.findByUsuarioId(usuario.getId());

        if (carrinho != null){
            carrinhoRepository.save(carrinho);
            return carrinho;
        }

        carrinho = new Carrinho();
        carrinho.setUsuario(usuario);
        carrinho.setEndereco(usuario.getEndereco());

        return carrinhoRepository.save(carrinho);
    }
}
