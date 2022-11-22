package br.com.ecommerce;

import br.com.ecommerce.models.Endereco;
import br.com.ecommerce.models.Usuario;
import br.com.ecommerce.repository.UsuarioRepository;
import br.com.ecommerce.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootTest
public class UsuarioTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Test
    public void usuarioCadastrado(){
        String data = "06/06/1990";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataAniversario = LocalDate.parse(data, formatter);

        Usuario usuario1 = new Usuario();
        usuario1.setNome("Daniele");
        usuario1.setSobrenome("Silva");
        usuario1.setCpf("123123123");
        usuario1.setTelefone(1122334455);
        usuario1.setDataNascimento(dataAniversario);
        usuario1.setSexo("feminino");
        usuario1.setEmail("daniele@teste.com.br");
        usuario1.setSenha("12345");

        Endereco endereco1 = new Endereco();
        endereco1.setCep(12345);
        endereco1.setEndereco("Rua 1");
        endereco1.setNumero("1");
        endereco1.setComplemento("casa 1");
        endereco1.setBairro("jd 1");
        endereco1.setCidade("cidade 1");
        endereco1.setEstado("estado 1");

        usuario1.setEndereco(endereco1);

        usuarioService.salvar(usuario1);
    }

//alterar senha
    //@Test
    //public void ListarUsuarios(){
        //List<Usuario> listaUsuario = usuarioRepository.findAll();

   // }
}
