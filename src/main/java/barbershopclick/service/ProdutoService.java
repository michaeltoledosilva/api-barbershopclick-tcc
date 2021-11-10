package barbershopclick.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barbershopclick.dto.ProdutoDto;
import barbershopclick.entity.ProdutoEntity;
import barbershopclick.helper.NumberManager;
import barbershopclick.helper.StringManager;
import barbershopclick.helper.csvmanager.CsvGenerator;
import barbershopclick.repository.ProdutoRepository;
import barbershopclick.vo.ProdutoVo;
import barbershopclick.vo.generic.ExportarCsvVo;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public ProdutoVo findById(Integer id) throws ValidationException, ParseException {
		return new ProdutoVo(findEntityById(id));
	}

	public ProdutoEntity findEntityById(Integer id) throws ValidationException {
		return this.repository.findById(id).orElseThrow(() -> new ValidationException("Produto não encontrado."));
	}

	public List<ProdutoVo> findByFilters(String descricao, Boolean listarInativos) throws ValidationException {
		return this.repository.findByFilters(descricao, (listarInativos == null ? false : listarInativos) ? 1 : 0);
	}

	public ProdutoEntity save(ProdutoDto dto) throws ValidationException {
		validarDados(dto);

		ProdutoEntity entity = new ProdutoEntity();
		if (dto.getId() != null && !dto.getId().equals(0)) {
			entity = this.repository.findById(dto.getId()).get();
		} else {
			entity.setDataCadastro(new Date());
		}

		entity.setNome(dto.getNome());
		entity.setValor(dto.getValor());
		entity.setDataDesativacao(dto.getDataDesativacao() == null ? null : new Date());

		return repository.save(entity);
	}

	private void validarDados(ProdutoDto dto) throws ValidationException {
		if (dto == null) {
			throw new ValidationException("Produto inválido.");
		}
		
		if (StringManager.getInstance().isNullOrEmpty(dto.getNome())) {
			throw new ValidationException("Informe o nome.");
		}
		
		if (dto.getValor() == null || dto.getValor().equals(BigDecimal.ZERO)) {
			throw new ValidationException("Informe um valor maior que zero.");
		}
		
		ProdutoEntity entity = this.repository.findByNome(dto.getNome());
		if (entity != null
				&& (NumberManager.getInstance().isNullOrZero(dto.getId()) || !dto.getId().equals(entity.getId()))) {
			throw new ValidationException("Informe um nome que ainda não exista.");
		}
	}

	public ProdutoVo disableOrEnableById(Integer id) throws ValidationException, ParseException {
		ProdutoEntity entity = this.repository.findById(id).get();
		entity.setDataDesativacao(entity.getDataDesativacao() == null ? new Date() : null);
		return new ProdutoVo(this.repository.save(entity));
	}

	public ExportarCsvVo generateCsv(String descricao, Boolean listarInativos) throws ValidationException, IOException {
		List<ProdutoVo> listaDados = this.repository.findByFilters(descricao,
				Boolean.TRUE.equals(listarInativos) ? 1 : 0);
		if (listaDados.isEmpty()) {
			throw new ValidationException("Não existem dados para imprimir");
		}
		return new CsvGenerator().generateCSV(listaDados, "\"Relatório Cadastro de Produto\"");
	}

}
