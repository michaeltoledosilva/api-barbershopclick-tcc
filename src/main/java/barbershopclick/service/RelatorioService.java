package barbershopclick.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barbershopclick.entity.ClienteEntity;
import barbershopclick.entity.ProdutoEntity;
import barbershopclick.entity.UsuarioEntity;
import barbershopclick.vo.RelatorioDecimalVo;
import barbershopclick.vo.RelatorioVo;

@Service
public class RelatorioService {

	@Autowired
	private AtendimentoProdutoService atendimentoProdutoService;

	public RelatorioVo findProdutosVendidos() throws ValidationException {

		List<ProdutoEntity> list = this.atendimentoProdutoService.getProdutosVendidos();

		List<Integer> quantidade = new ArrayList<>();
		List<String> descricao = new ArrayList<>();

		for (ProdutoEntity produto : list) {
			quantidade.add(this.atendimentoProdutoService.getNumeroVendas(produto.getId()));
			descricao.add(produto.getNome());
		}

		return new RelatorioVo(quantidade, descricao);
	}

	public RelatorioDecimalVo findValoresProdutos() throws ValidationException {

		List<ProdutoEntity> list = this.atendimentoProdutoService.getProdutosVendidos();

		List<BigDecimal> valor = new ArrayList<>();
		List<String> descricao = new ArrayList<>();

		for (ProdutoEntity produto : list) {
			valor.add(this.atendimentoProdutoService.getValoresVendas(produto.getId()));
			descricao.add(produto.getNome());
		}

		return new RelatorioDecimalVo(valor, descricao);
	}

	public RelatorioVo findAtendimentosClientes() throws ValidationException {

		List<ClienteEntity> list = this.atendimentoProdutoService.getAtendimentosClientes();

		List<Integer> quantidade = new ArrayList<>();
		List<String> descricao = new ArrayList<>();

		for (ClienteEntity cliente : list) {
			quantidade.add(this.atendimentoProdutoService.getNumeroAtendimentosClientes(cliente.getId()));
			descricao.add(cliente.getNome());
		}

		return new RelatorioVo(quantidade, descricao);
	}

	public RelatorioDecimalVo findValoresClientes() throws ValidationException {

		List<ClienteEntity> list = this.atendimentoProdutoService.getAtendimentosClientes();

		List<BigDecimal> valor = new ArrayList<>();
		List<String> descricao = new ArrayList<>();

		for (ClienteEntity cliente : list) {
			valor.add(this.atendimentoProdutoService.getValoresAtendimentosClientes(cliente.getId()));
			descricao.add(cliente.getNome());
		}

		return new RelatorioDecimalVo(valor, descricao);
	}

	public RelatorioVo findAtendimentosFuncionarios() throws ValidationException {

		List<UsuarioEntity> list = this.atendimentoProdutoService.getAtendimentosFuncionarios();

		List<Integer> quantidade = new ArrayList<>();
		List<String> descricao = new ArrayList<>();

		for (UsuarioEntity funcionario : list) {
			quantidade.add(this.atendimentoProdutoService.getNumeroAtendimentosFuncionarios(funcionario.getId()));
			descricao.add(funcionario.getNome());
		}

		return new RelatorioVo(quantidade, descricao);
	}
	
	public RelatorioDecimalVo findValoresFuncionarios() throws ValidationException {

		List<UsuarioEntity> list = this.atendimentoProdutoService.getAtendimentosFuncionarios();

		List<BigDecimal> valor = new ArrayList<>();
		List<String> descricao = new ArrayList<>();

		for (UsuarioEntity funcionario : list) {
			valor.add(this.atendimentoProdutoService.getValoresAtendimentosFuncionarios(funcionario.getId()));
			descricao.add(funcionario.getNome());
		}

		return new RelatorioDecimalVo(valor, descricao);
	}

}
