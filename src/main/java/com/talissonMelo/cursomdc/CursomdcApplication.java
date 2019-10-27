package com.talissonMelo.cursomdc;

import java.text.SimpleDateFormat;
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
import com.talissonMelo.cursomdc.domain.ItemPedido;
import com.talissonMelo.cursomdc.domain.Pagamento;
import com.talissonMelo.cursomdc.domain.PagamentoBoleto;
import com.talissonMelo.cursomdc.domain.PagamentoCartao;
import com.talissonMelo.cursomdc.domain.Pedido;
import com.talissonMelo.cursomdc.domain.Produto;
import com.talissonMelo.cursomdc.domain.enums.EstadoPagamento;
import com.talissonMelo.cursomdc.domain.enums.TipoCliente;
import com.talissonMelo.cursomdc.repositories.CategoriaRepository;
import com.talissonMelo.cursomdc.repositories.CidadeRepository;
import com.talissonMelo.cursomdc.repositories.ClienteRepository;
import com.talissonMelo.cursomdc.repositories.EnderecoRepository;
import com.talissonMelo.cursomdc.repositories.EstadoRepository;
import com.talissonMelo.cursomdc.repositories.ItemPedidoRepository;
import com.talissonMelo.cursomdc.repositories.PagamentoRepository;
import com.talissonMelo.cursomdc.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("03/03/2018 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("03/10/2017 03:32"), cli1, end2);
		
		Pagamento pag1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("21/10/2019 24: 00"), null);
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1,pag2));
		
		ItemPedido ite1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.00);
		ItemPedido ite2 = new ItemPedido(ped1, p3, 0.0, 2, 80.00);
		ItemPedido ite3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ite1,ite2));
		ped2.getItens().addAll(Arrays.asList(ite3));
		
		p1.getItens().addAll(Arrays.asList(ite1));
		p2.getItens().addAll(Arrays.asList(ite3));;
		p3.getItens().addAll(Arrays.asList(ite2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ite1,ite2,ite3));
	}

}
