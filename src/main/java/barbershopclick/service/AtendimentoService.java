package barbershopclick.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barbershopclick.dto.AtendimentoDto;
import barbershopclick.entity.AtendimentoEntity;
import barbershopclick.enums.HorariosEnum;
import barbershopclick.helper.DataManager;
import barbershopclick.helper.NumberManager;
import barbershopclick.helper.StringManager;
import barbershopclick.helper.csvmanager.CsvGenerator;
import barbershopclick.repository.AtendimentoRepository;
import barbershopclick.vo.AtendimentoProdutoVo;
import barbershopclick.vo.AtendimentoVo;
import barbershopclick.vo.generic.ExportarCsvVo;

@Service
public class AtendimentoService {

	@Autowired
	private AtendimentoRepository repository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private AtendimentoProdutoService atendimentoProdutoService;

	public List<AtendimentoVo> findByFilters(String descricao, String data) throws ValidationException, ParseException {
		List<AtendimentoVo> list = null;

		if (StringManager.getInstance().isNullOrEmpty(data)) {
			list = this.repository.findByFilters(descricao);
		} else {
			DataManager dm = DataManager.getInstance();
			list = this.repository.findByFilters(descricao, dm.getDateTimeInicioDia(data), dm.getDateTimeFimDia(data));
		}

		Integer idMaxItem = 1;
		for (AtendimentoVo atendimentoVo : list) {
			atendimentoVo.setItensVo(this.atendimentoProdutoService.findByIdAtendimento(atendimentoVo.getId()));
			for (AtendimentoProdutoVo produto : atendimentoVo.getItensVo()) {
				idMaxItem = produto.getId();
			}
			atendimentoVo.setIdMaxItem(idMaxItem);
		}

		return list;
	}

	public AtendimentoVo findById(Integer id) throws ValidationException {
		return new AtendimentoVo(
				this.repository.findById(id).orElseThrow(() -> new ValidationException("Atendimento não encontrado.")));
	}

	public AtendimentoEntity save(AtendimentoDto dto) throws ValidationException, ParseException {
		validarDados(dto);

		AtendimentoEntity entity = new AtendimentoEntity();
		if (dto.getId() != null && !dto.getId().equals(0)) {
			entity = this.repository.findById(dto.getId()).get();
		}

		entity.setDescricao(dto.getDescricao());
		if (dto.getIdUsuarioLancamento() != null) {
			entity.setUsuarioLancamento(this.usuarioService.findById(dto.getIdUsuarioLancamento()));
		} else {
			entity.setUsuarioLancamento(null);
		}
		if (dto.getIdUsuarioAtendimento() != null) {
			entity.setUsuarioAtendimento(this.usuarioService.findById(dto.getIdUsuarioAtendimento()));
		} else {
			entity.setUsuarioAtendimento(null);
		}
		if (dto.getIdCliente() != null) {
			entity.setCliente(this.clienteService.findById(dto.getIdCliente()));
		} else {
			entity.setCliente(null);
		}

		entity.setDataAtendimento(DataManager.getInstance().parseDateTime(dto.getDataAtendimento(),
				HorariosEnum.valueOf(dto.getCodigoHora()).getText()));
		entity.setValorTotal(BigDecimal.ZERO);
		entity = this.repository.save(entity);

		if (dto.getItensVo() != null && !dto.getItensVo().isEmpty()) {
			entity.setValorTotal(this.atendimentoProdutoService.save(entity, dto.getItensVo()));
			this.repository.save(entity);
		}

		return entity;
	}
	
	private Date getDataAtendimento(AtendimentoDto dto) throws ParseException, ValidationException {
		return DataManager.getInstance().parseDateTime(dto.getDataAtendimento(),
				HorariosEnum.valueOf(dto.getCodigoHora()).getText());
	}

	private void validarDados(AtendimentoDto dto) throws ValidationException, ParseException {
		if (dto == null) {
			throw new ValidationException("Atentimento inválido.");
		}

		if (StringManager.getInstance().isNullOrEmpty(dto.getDataAtendimento())) {
			throw new ValidationException("Informe uma data.");
		}
		
		if (NumberManager.getInstance().isNullOrZero(dto.getCodigoHora())) {
			throw new ValidationException("Informe uma hora.");
		}
		
		getDataAtendimento(dto);

		if (StringManager.getInstance().isNullOrEmpty(dto.getDescricao())) {
			throw new ValidationException("Informe uma descrição.");
		}

		if (NumberManager.getInstance().isNullOrZero(dto.getCodigoHora())) {
			throw new ValidationException("Informe uma hora.");
		}

		if (NumberManager.getInstance().isNullOrZero(dto.getIdCliente())) {
			throw new ValidationException("Informe um cliente.");
		}

		if (NumberManager.getInstance().isNullOrZero(dto.getIdUsuarioAtendimento())) {
			throw new ValidationException("Informe um funcionário.");
		}

		if (dto.getItensVo() != null && !dto.getItensVo().isEmpty()) {
			this.atendimentoProdutoService.validarDados(dto.getItensVo());
		} else {
			throw new ValidationException("Informe ao menos 1 produto.");
		}
	}

	public ExportarCsvVo generateCsv(String descricao) throws ValidationException, IOException {
		List<AtendimentoVo> listaDados = this.repository.findByFilters(descricao);
		if (listaDados.isEmpty()) {
			throw new ValidationException("Não existem dados para imprimir");
		}
		return new CsvGenerator().generateCSV(listaDados, "\"Relatório Cadastro de Atendimentos\"");
	}

	public void delete(int id) throws ValidationException {
		if (id == 0) {
			throw new ValidationException("Id inválido.");
		}

		this.atendimentoProdutoService.deleteAllProdutos(id);
		
		this.repository.delete(this.repository.findById(id).get());
	}

}
