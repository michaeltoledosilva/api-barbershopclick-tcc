package barbershopclick.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barbershopclick.dto.AgendaDto;
import barbershopclick.entity.AgendaEntity;
import barbershopclick.enums.CortesEnum;
import barbershopclick.enums.HorariosEnum;
import barbershopclick.helper.DataManager;
import barbershopclick.helper.NumberManager;
import barbershopclick.helper.StringManager;
import barbershopclick.helper.csvmanager.CsvGenerator;
import barbershopclick.repository.AgendaRepository;
import barbershopclick.vo.AgendaVo;
import barbershopclick.vo.generic.ComboVo;
import barbershopclick.vo.generic.ExportarCsvVo;

@Service
public class AgendaService {

	@Autowired
	private AgendaRepository repository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ClienteService clienteService;

	public AgendaVo findById(Integer id) throws ValidationException {
		return new AgendaVo(
				this.repository.findById(id).orElseThrow(() -> new ValidationException("Agenda não encontrada")));
	}

	public List<AgendaVo> findByFilters(String descricao, String data) throws ValidationException, ParseException {
		if (StringManager.getInstance().isNullOrEmpty(data)) {
			return this.repository.findByFilters(descricao);
		} else {
			DataManager dm = DataManager.getInstance();
			return this.repository.findByFilters(descricao, dm.getDateTimeInicioDia(data), dm.getDateTimeFimDia(data));
		}
	}

	public AgendaEntity save(AgendaDto dto) throws ValidationException, ParseException {
		validarDados(dto);

		AgendaEntity entity = new AgendaEntity();
		if (dto.getId() != null && !dto.getId().equals(0)) {
			entity = this.repository.findById(dto.getId()).get();
		}

		entity.setDataAgenda(getDataAgenda(dto));
		entity.setTipo(dto.getTipo());
		if (dto.getIdUsuarioAgendado() != null) {
			entity.setUsuarioAgendado(this.usuarioService.findById(dto.getIdUsuarioAgendado()));
		} else {
			entity.setUsuarioAgendado(null);
		}
		if (dto.getIdUsuarioCadastro() != null) {
			entity.setUsuarioCadastro(this.usuarioService.findById(dto.getIdUsuarioCadastro()));
		} else {
			entity.setUsuarioCadastro(null);
		}
		if (dto.getIdCliente() != null) {
			entity.setCliente(this.clienteService.findById(dto.getIdCliente()));
		} else {
			entity.setCliente(null);
		}

		return repository.save(entity);
	}

	private Date getDataAgenda(AgendaDto dto) throws ParseException, ValidationException {
		return DataManager.getInstance().parseDateTime(dto.getDataAgenda(),
				HorariosEnum.valueOf(dto.getCodigoHora()).getText());
	}

	private void validarDados(AgendaDto dto) throws ValidationException, ParseException {
		if (dto == null) {
			throw new ValidationException("Agenda inválida.");
		}

		if (StringManager.getInstance().isNullOrEmpty(dto.getDataAgenda())) {
			throw new ValidationException("Informe uma data.");
		}

		if (NumberManager.getInstance().isNullOrZero(dto.getCodigoHora())) {
			throw new ValidationException("Informe uma hora.");
		}

		if (NumberManager.getInstance().isNullOrZero(dto.getIdCliente())) {
			throw new ValidationException("Informe um cliente.");
		}

		if (NumberManager.getInstance().isNullOrZero(dto.getIdUsuarioAgendado())) {
			throw new ValidationException("Informe um funcionário.");
		}

		if (!dto.getTipo().equals(CortesEnum.CABELO.value()) && !dto.getTipo().equals(CortesEnum.BARBA.value())
				&& !dto.getTipo().equals(CortesEnum.AMBOS.value())) {
			throw new ValidationException("Existem itens com tipo inválido.");
		}

		Date dataAgenda = getDataAgenda(dto);
		AgendaEntity entity = this.repository.findByDataUsuario(dataAgenda, dto.getIdUsuarioAgendado());
		if (entity != null
				&& (NumberManager.getInstance().isNullOrZero(dto.getId()) || !dto.getId().equals(entity.getId()))) {
			throw new ValidationException(
					"Já existe um horário marcado no mesmo selecionado, para o mesmo funcionário.");
		}

		entity = this.repository.findByDataCliente(dataAgenda, dto.getIdCliente());
		if (entity != null
				&& (NumberManager.getInstance().isNullOrZero(dto.getId()) || !dto.getId().equals(entity.getId()))) {
			throw new ValidationException("Já existe um horário marcado no mesmo selecionado, para o mesmo cliente.");
		}
	}

	public ExportarCsvVo generateCsv(String descricao) throws ValidationException, IOException {
		List<AgendaVo> listaDados = this.repository.findByFilters(descricao);
		if (listaDados.isEmpty()) {
			throw new ValidationException("Não existem dados para imprimir");
		}
		return new CsvGenerator().generateCSV(listaDados, "\"Relatório Cadastro de Agenda\"");
	}

	public List<ComboVo> findSchedules() {
		List<HorariosEnum> lst = new ArrayList<>(Arrays.asList(HorariosEnum.values()));
		List<ComboVo> lstVO = new ArrayList<>();

		ComboVo vo = new ComboVo();

		for (HorariosEnum c : lst) {
			vo = new ComboVo();

			vo.setLabel(c.getText());
			vo.setCodigo(c.getValue());

			lstVO.add(vo);
		}

		return lstVO;
	}

	public void delete(int id) throws ValidationException {
		if (id == 0) {
			throw new ValidationException("Id inválido.");
		}

		this.repository.delete(this.repository.findById(id).get());
	}

}
