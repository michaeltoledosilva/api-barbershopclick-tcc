package barbershopclick.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barbershopclick.dto.CargoDto;
import barbershopclick.entity.CargoEntity;
import barbershopclick.helper.NumberManager;
import barbershopclick.helper.StringManager;
import barbershopclick.helper.csvmanager.CsvGenerator;
import barbershopclick.repository.CargoRepository;
import barbershopclick.vo.CargoVo;
import barbershopclick.vo.generic.ExportarCsvVo;

@Service
public class CargoService {

	@Autowired
	private CargoRepository repository;

	@Autowired
	private FuncionarioService funcionarioService;

	public CargoEntity findById(Integer id) throws ValidationException {
		return this.repository.findById(id).orElseThrow(() -> new ValidationException("Cargo não encontrado."));
	}

	public List<CargoVo> findByFilters(String descricao, Boolean listarInativos) throws ValidationException {
		return this.repository.findByFilters(descricao, (listarInativos == null ? false : listarInativos) ? 1 : 0);
	}

	public CargoEntity save(CargoDto dto) throws ValidationException {
		validarDados(dto);

		CargoEntity entity = new CargoEntity();
		if (dto.getId() != null && !dto.getId().equals(0)) {
			entity = this.repository.findById(dto.getId()).get();
		} else {
			entity.setDataCadastro(new Date());
		}

		entity.setNome(dto.getNome());
		entity.setDataDesativacao(dto.getDataDesativacao() == null ? null : new Date());

		return repository.save(entity);
	}

	private void validarDados(CargoDto dto) throws ValidationException {
		if (dto == null) {
			throw new ValidationException("Cargo inválido.");
		}
		if (StringManager.getInstance().isNullOrEmpty(dto.getNome())) {
			throw new ValidationException("Informe um nome.");
		}

		CargoEntity entity = this.repository.findByNome(dto.getNome());
		if (entity != null
				&& (NumberManager.getInstance().isNullOrZero(dto.getId()) || !dto.getId().equals(entity.getId()))) {
			throw new ValidationException("Informe um nome que ainda não exista.");
		}
	}

	public CargoVo disableOrEnableById(Integer id) throws ValidationException {
		CargoEntity entity = this.repository.findById(id).get();
		if (!this.funcionarioService.existeCargoVinculado(entity)) {
			entity.setDataDesativacao(entity.getDataDesativacao() == null ? new Date() : null);
			return new CargoVo(this.repository.save(entity));
		} else {
			throw new ValidationException("Não será possivel desativar. Cargo está vinculado a algum funcionário.");
		}
	}

	public ExportarCsvVo generateCsv(String descricao, Boolean listarInativos) throws ValidationException, IOException {
		List<CargoVo> listaDados = this.repository.findByFilters(descricao,
				Boolean.TRUE.equals(listarInativos) ? 1 : 0);
		if (listaDados.isEmpty()) {
			throw new ValidationException("Não existem dados para imprimir");
		}
		return new CsvGenerator().generateCSV(listaDados, "\"Relatório Cadastro de Cargo\"");
	}
}
