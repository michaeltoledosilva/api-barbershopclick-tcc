package barbershopclick.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barbershopclick.dto.FuncionarioDto;
import barbershopclick.entity.CargoEntity;
import barbershopclick.entity.FuncionarioEntity;
import barbershopclick.entity.UsuarioEntity;
import barbershopclick.helper.NumberManager;
import barbershopclick.helper.StringManager;
import barbershopclick.helper.csvmanager.CsvGenerator;
import barbershopclick.repository.FuncionarioRepository;
import barbershopclick.vo.FuncionarioVo;
import barbershopclick.vo.generic.ExportarCsvVo;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;

	@Autowired
	private CargoService cargoService;

	@Autowired
	private UsuarioService usuarioService;

	public FuncionarioVo findById(Integer id) throws ValidationException {
		return new FuncionarioVo(
				repository.findById(id).orElseThrow(() -> new ValidationException("Funcionário não encontrado.")));
	}

	public List<FuncionarioVo> findByFilters(String descricao, Boolean listarInativos) throws ValidationException {
		return this.repository.findByFilters(descricao, (listarInativos == null ? false : listarInativos) ? 1 : 0);
	}

	public FuncionarioEntity save(FuncionarioDto dto) throws ValidationException {
		validarDados(dto);

		FuncionarioEntity entity = new FuncionarioEntity();
		if (dto.getId() != null && !dto.getId().equals(0)) {
			entity = this.repository.findById(dto.getId()).get();
		} else {
			entity.setDataCadastro(new Date());
		}

		entity.setNome(dto.getNome());
		entity.setTelefone(dto.getTelefone());
		entity.setEmail(dto.getEmail());
		entity.setDataDesativacao(dto.getDataDesativacao() == null ? null : new Date());
		entity.setCargo(this.cargoService.findById(dto.getCargo()));
		entity.setUsuario(this.usuarioService.findById(dto.getUsuario()));

		return repository.save(entity);
	}

	private void validarDados(FuncionarioDto dto) throws ValidationException {
		if (dto == null) {
			throw new ValidationException("Funcionário inválido.");
		}

		if (StringManager.getInstance().isNullOrEmpty(dto.getNome())) {
			throw new ValidationException("Informe um nome.");
		}

		FuncionarioEntity entity = this.repository.findByNome(dto.getNome());
		if (entity != null
				&& (NumberManager.getInstance().isNullOrZero(dto.getId()) || !dto.getId().equals(entity.getId()))) {
			throw new ValidationException("Informe um nome que ainda não exista.");
		}

		if (StringManager.getInstance().isNullOrEmpty(dto.getNome())) {
			throw new ValidationException("Informe um e-mail.");
		}

		if (StringManager.getInstance().isNullOrEmpty(dto.getTelefone()) || dto.getTelefone().length() < 10
				|| dto.getTelefone().length() > 11) {
			throw new ValidationException("Informe um telefone válido.");
		}

		if (NumberManager.getInstance().isNullOrZero(dto.getCargo())) {
			throw new ValidationException("Informe um cargo.");
		}

		if (NumberManager.getInstance().isNullOrZero(dto.getUsuario())) {
			throw new ValidationException("Informe um usuário.");
		}
	}

	public FuncionarioVo disableOrEnableById(Integer id) throws ValidationException {
		FuncionarioEntity entity = this.repository.findById(id).get();
		entity.setDataDesativacao(entity.getDataDesativacao() == null ? new Date() : null);
		return new FuncionarioVo(this.repository.save(entity));
	}

	public ExportarCsvVo generateCsv(String descricao, Boolean listarInativos) throws ValidationException, IOException {
		List<FuncionarioVo> listaDados = this.repository.findByFilters(descricao,
				Boolean.TRUE.equals(listarInativos) ? 1 : 0);
		if (listaDados.isEmpty()) {
			throw new ValidationException("Não existem dados para imprimir");
		}
		return new CsvGenerator().generateCSV(listaDados, "\"Relatório Cadastro de Funcionário\"");
	}

	public boolean existeCargoVinculado(CargoEntity entity) {
		return !this.repository.findByCargo(entity).isEmpty();
	}

	public boolean existeUsuarioVinculado(UsuarioEntity entity) {
		return !this.repository.findByUsuario(entity).isEmpty();
	}
}
