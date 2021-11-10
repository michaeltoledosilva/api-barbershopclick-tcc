package barbershopclick.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barbershopclick.dto.AtendimentoProdutoDto;
import barbershopclick.entity.AtendimentoEntity;
import barbershopclick.entity.AtendimentoProdutoEntity;
import barbershopclick.entity.ClienteEntity;
import barbershopclick.entity.ProdutoEntity;
import barbershopclick.entity.UsuarioEntity;
import barbershopclick.helper.NumberManager;
import barbershopclick.helper.csvmanager.CsvGenerator;
import barbershopclick.repository.AtendimentoProdutoRepository;
import barbershopclick.vo.AtendimentoProdutoVo;
import barbershopclick.vo.generic.ExportarCsvVo;

@Service
public class AtendimentoProdutoService {

	@Autowired
	private AtendimentoProdutoRepository repository;

	@Autowired
	private ProdutoService produtoService;

	public AtendimentoProdutoEntity findById(Integer id) throws ValidationException {
		return this.repository.findById(id)
				.orElseThrow(() -> new ValidationException("Produto do Atendimento não encontrado."));
	}

	public List<AtendimentoProdutoVo> findByFilters(String descricao) throws ValidationException {
		return this.repository.findByFilters(descricao);
	}

	public List<AtendimentoProdutoVo> findByIdAtendimento(Integer idAtendimento) throws ValidationException {
		return this.repository.findByIdAtendimento(idAtendimento);
	}

	public BigDecimal save(AtendimentoEntity atendimentoEntity, List<AtendimentoProdutoDto> itensVo)
			throws ValidationException {
		if (itensVo == null) {
			new ValidationException("Produto do Atentimento inválido.");
		}

		deleteAllProdutos(atendimentoEntity.getId());

		BigDecimal somaProdutos = BigDecimal.ZERO;
		for (AtendimentoProdutoDto produto : itensVo) {
			AtendimentoProdutoEntity entity = new AtendimentoProdutoEntity();

			ProdutoEntity produtoEntity = this.produtoService.findEntityById(produto.getIdProduto());
			entity.setProduto(produtoEntity);
			entity.setValor(produtoEntity.getValor());
			entity.setCliente(atendimentoEntity.getCliente());
			entity.setAtendimento(atendimentoEntity);
			somaProdutos = somaProdutos.add(produtoEntity.getValor());

			this.repository.save(entity);
		}

		return somaProdutos;
	}

	public void deleteAllProdutos(Integer id) {
		List<AtendimentoProdutoEntity> list = this.repository.findEntityByIdAtendimento(id);
		for (AtendimentoProdutoEntity produto : list) {
			this.repository.delete(produto);
		}
	}

	public ExportarCsvVo generateCsv(Integer idAtendimento) throws ValidationException, IOException {
		List<AtendimentoProdutoVo> listaDados = this.repository.findByIdAtendimento(idAtendimento);
		if (listaDados.isEmpty()) {
			throw new ValidationException("Não existem dados para imprimir");
		}
		return new CsvGenerator().generateCSV(listaDados, "\"Relatório Cadastro de Produto do Atendimento\"");
	}

	public void validarDados(List<AtendimentoProdutoDto> itensVo) throws ValidationException {
		for (AtendimentoProdutoDto produto : itensVo) {
			if (NumberManager.getInstance().isNullOrZero(produto.getIdProduto())) {
				throw new ValidationException("Existem itens sem informar o produto.");
			}
		}

	}

	public List<ProdutoEntity> getProdutosVendidos() {
		return this.repository.getProdutosVendidos();
	}
	
	public Integer getNumeroVendas(Integer idProduto) {
		return this.repository.getNumeroVendas(idProduto);
	}
	 
	public BigDecimal getValoresVendas(Integer idProduto) {
		return this.repository.getValoresVendas(idProduto);
	}
	
	public List<ClienteEntity> getAtendimentosClientes() {
		return this.repository.getAtendimentosClientes();
	}
	
	public Integer getNumeroAtendimentosClientes(Integer idCliente) {
		return this.repository.getNumeroAtendimentosClientes(idCliente);
	}
	 
	public BigDecimal getValoresAtendimentosClientes(Integer idCliente) {
		return this.repository.getValoresAtendimentosClientes(idCliente);
	}
	
	public List<UsuarioEntity> getAtendimentosFuncionarios() {
		return this.repository.getAtendimentosFuncionarios();
	}
	
	public Integer getNumeroAtendimentosFuncionarios(Integer idCliente) {
		return this.repository.getNumeroAtendimentosFuncionarios(idCliente);
	}
	 
	public BigDecimal getValoresAtendimentosFuncionarios(Integer idCliente) {
		return this.repository.getValoresAtendimentosFuncionarios(idCliente);
	}

}
