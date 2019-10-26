package com.talissonMelo.cursomdc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.talissonMelo.cursomdc.domain.Categoria;
import com.talissonMelo.cursomdc.domain.Cidade;
import com.talissonMelo.cursomdc.domain.Cliente;
import com.talissonMelo.cursomdc.domain.Endereco;
import com.talissonMelo.cursomdc.domain.Estado;
import com.talissonMelo.cursomdc.domain.Produto;
import com.talissonMelo.cursomdc.domain.enums.TipoCliente;
import com.talissonMelo.cursomdc.repositories.CategoriaRepository;
import com.talissonMelo.cursomdc.repositories.CidadeRepository;
import com.talissonMelo.cursomdc.repositories.ClienteRepository;
import com.talissonMelo.cursomdc.repositories.EnderecoRepository;
import com.talissonMelo.cursomdc.repositories.EstadoRepository;
import com.talissonMelo.cursomdc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomdcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomdcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Empressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado esta1 = new Estado(null, "Minas Gerais");
		Estado esta2 = new Estado(null, "São Pualo");
		
		Cidade cd1 = new Cidade(null, "Araguari", esta1);
		Cidade cd2 = new Cidade(null, "São Paulo", esta2);
		Cidade cd3 = new Cidade(null, "Campinas",esta2);
				
		esta1.getCidades().addAll(Arrays.asList(cd1));
		esta2.getCidades().addAll(Arrays.asList(cd2,cd3));
		
		estadoRepository.saveAll(Arrays.asList(esta1,esta2));
		cidadeRepository.saveAll(Arrays.asList(cd1,cd2,cd3));
		
		Cliente cli1 = new Cliente(null, "Maria", "maria@gmail.com", "000000000", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("00000000","00000001"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Aptp 303", "Jardim", "00000000", cli1, cd1);
		Endereco end2 = new Endereco(null, "Avenida Flores", "200", "Aptp 203", "Campo", "00000000", cli1, cd2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1,end2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1,end2));
	}

}
