package barbershopclick.service;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barbershopclick.dto.CorteClienteDto;
import barbershopclick.entity.ClienteEntity;
import barbershopclick.entity.CorteClienteEntity;
import barbershopclick.enums.CortesEnum;
import barbershopclick.helper.StringManager;
import barbershopclick.helper.csvmanager.CsvGenerator;
import barbershopclick.repository.CorteClienteRepository;
import barbershopclick.vo.CorteClienteVo;
import barbershopclick.vo.generic.ExportarCsvVo;

@Service
public class CorteClienteService {

	@Autowired
	private CorteClienteRepository repository;

	public CorteClienteVo findById(Integer id) throws ValidationException {
		return new CorteClienteVo(this.repository.findById(id)
				.orElseThrow(() -> new ValidationException("Corte do Cliente não encontrado.")));
	}

	public List<CorteClienteVo> findByFilters(String descricao) throws ValidationException {
		return this.repository.findByFilters(descricao);
	}

	public List<CorteClienteVo> findByIdCliente(Integer idCliente) throws ValidationException {
		return this.repository.findByIdCliente(idCliente);
	}

	public void save(ClienteEntity entityCliente, List<CorteClienteDto> itensVo) throws ValidationException {
		deleteAllCortes(entityCliente);

		for (CorteClienteDto corte : itensVo) {
			CorteClienteEntity entity = new CorteClienteEntity();

			entity.setCliente(entityCliente);
			entity.setDescricao(corte.getDescricao());
			entity.setTipo(corte.getTipo());

			this.repository.save(entity);
		}
	}

	public void validarDados(List<CorteClienteDto> itensVo) throws ValidationException {
		for (CorteClienteDto corte : itensVo) {
			if (StringManager.getInstance().isNullOrEmpty(corte.getDescricao())) {
				throw new ValidationException("Existem itens sem descrição.");
			}

			if (!corte.getTipo().equals(CortesEnum.CABELO.value())
					&& !corte.getTipo().equals(CortesEnum.BARBA.value())) {
				throw new ValidationException("Existem itens com tipo inválido.");
			}
		}
	}

	private void deleteAllCortes(ClienteEntity entityCliente) {
		List<CorteClienteEntity> list = this.repository.findEntityByIdCliente(entityCliente.getId());
		for (CorteClienteEntity corte : list) {
			this.repository.delete(corte);
		}
	}

	public ExportarCsvVo generateCsv(Integer idCliente) throws ValidationException, IOException {
		List<CorteClienteVo> listaDados = this.repository.findByIdCliente(idCliente);
		if (listaDados.isEmpty()) {
			throw new ValidationException("Não existem dados para imprimir");
		}
		return new CsvGenerator().generateCSV(listaDados, "\"Relatório Cadastro de Cortes do Cliente\"");
	}

}
