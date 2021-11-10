package barbershopclick.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barbershopclick.dto.ClienteDto;
import barbershopclick.entity.ClienteEntity;
import barbershopclick.helper.NumberManager;
import barbershopclick.helper.StringManager;
import barbershopclick.helper.csvmanager.CsvGenerator;
import barbershopclick.repository.ClienteRepository;
import barbershopclick.vo.ClienteVo;
import barbershopclick.vo.CorteClienteVo;
import barbershopclick.vo.generic.ExportarCsvVo;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private CorteClienteService corteClienteService;

	public List<ClienteVo> findByFilters(String descricao, Boolean listarInativos) throws ValidationException {
		List<ClienteVo> list = this.repository.findByFilters(descricao,
				(listarInativos == null ? false : listarInativos) ? 1 : 0);

		Integer idMaxItem = 1;
		for (ClienteVo clienteVo : list) {
			clienteVo.setItensVo(this.corteClienteService.findByIdCliente(clienteVo.getId()));
			for (CorteClienteVo corte : clienteVo.getItensVo()) {
				idMaxItem = corte.getId();
			}
			clienteVo.setIdMaxItem(idMaxItem);
		}

		return list;
	}

	public ClienteEntity findById(Integer id) throws ValidationException {
		return this.repository.findById(id).orElseThrow(() -> new ValidationException("Perfil não encontrado."));
	}

	public ClienteEntity save(ClienteDto dto) throws ValidationException {
		validarDados(dto);

		ClienteEntity entity = new ClienteEntity();
		if (dto.getId() != null && !dto.getId().equals(0)) {
			entity = this.repository.findById(dto.getId()).get();
		} else {
			entity.setDataCadastro(new Date());
		}

		entity.setNome(dto.getNome());
		entity.setDataDesativacao(dto.getDataDesativacao() == null ? null : new Date());
		entity.setTelefone(dto.getTelefone());

		this.repository.save(entity);

		if (dto.getItensVo() != null && !dto.getItensVo().isEmpty()) {
			this.corteClienteService.save(entity, dto.getItensVo());
		}

		return entity;
	}

	private void validarDados(ClienteDto dto) throws ValidationException {
		if (dto == null) {
			throw new ValidationException("Cliente inválido.");
		}
		if (StringManager.getInstance().isNullOrEmpty(dto.getNome())) {
			throw new ValidationException("Informe um nome.");
		}

		ClienteEntity entity = this.repository.findByNome(dto.getNome());
		if (entity != null
				&& (NumberManager.getInstance().isNullOrZero(dto.getId()) || !dto.getId().equals(entity.getId()))) {
			throw new ValidationException("Informe um nome que ainda não exista.");
		}

		if (StringManager.getInstance().isNullOrEmpty(dto.getTelefone()) || dto.getTelefone().length() < 10
				|| dto.getTelefone().length() > 11) {
			throw new ValidationException("Informe um telefone válido.");
		}

		if (dto.getItensVo() != null && !dto.getItensVo().isEmpty()) {
			this.corteClienteService.validarDados(dto.getItensVo());
		}
	}

	public ClienteVo disableOrEnableById(Integer id) throws ValidationException {
		ClienteEntity entity = this.repository.findById(id).get();
		entity.setDataDesativacao(entity.getDataDesativacao() == null ? new Date() : null);
		return new ClienteVo(this.repository.save(entity));
	}

	public ExportarCsvVo generateCsv(String descricao, Boolean listarInativos) throws ValidationException, IOException {
		List<ClienteVo> listaDados = this.repository.findByFilters(descricao,
				Boolean.TRUE.equals(listarInativos) ? 1 : 0);
		if (listaDados.isEmpty()) {
			throw new ValidationException("Não existem dados para imprimir");
		}
		return new CsvGenerator().generateCSV(listaDados, "\"Relatório Cadastro de Cliente\"");
	}

}
