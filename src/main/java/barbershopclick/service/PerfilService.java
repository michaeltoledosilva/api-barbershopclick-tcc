package barbershopclick.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barbershopclick.dto.PerfilDto;
import barbershopclick.entity.PerfilEntity;
import barbershopclick.helper.NumberManager;
import barbershopclick.helper.StringManager;
import barbershopclick.helper.csvmanager.CsvGenerator;
import barbershopclick.repository.PerfilRepository;
import barbershopclick.vo.PerfilVo;
import barbershopclick.vo.generic.ExportarCsvVo;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository repository;

	@Autowired
	private UsuarioService usuarioService;

	public PerfilEntity findById(Integer id) throws ValidationException {
		return this.repository.findById(id).orElseThrow(() -> new ValidationException("Perfil não encontrado."));
	}

	public List<PerfilVo> findByFilters(String descricao, Boolean listarInativos) throws ValidationException {
		return this.repository.findByFilters(descricao, (listarInativos == null ? false : listarInativos) ? 1 : 0);
	}

	public PerfilEntity save(PerfilDto dto) throws ValidationException {
		validarDados(dto);

		PerfilEntity entity = new PerfilEntity();
		if ((dto.getId() != null && !dto.getId().equals(0))
				|| (dto.getId().equals(0) && dto.getNome().equals("Perfil Cadastro Google"))) {
			entity = this.repository.findById(dto.getId()).get();
		} else {
			entity.setDataCadastro(new Date());
		}

		entity.setNome(dto.getNome());
		entity.setDataDesativacao(dto.getDataDesativacao() == null ? null : new Date());
		entity.setAcessaAgenda(parseBoolean(dto.isAcessaAgenda()));
		entity.setAcessaAtendimento(parseBoolean(dto.isAcessaAtendimento()));
		entity.setAcessaCargos(parseBoolean(dto.isAcessaCargos()));
		entity.setAcessaClientes(parseBoolean(dto.isAcessaClientes()));
		entity.setAcessaFuncionarios(parseBoolean(dto.isAcessaFuncionarios()));
		entity.setAcessaPerfil(parseBoolean(dto.isAcessaPerfil()));
		entity.setAcessaProdutos(parseBoolean(dto.isAcessaProdutos()));
		entity.setAcessaUsuarios(parseBoolean(dto.isAcessaUsuarios()));

		return repository.save(entity);
	}

	private void validarDados(PerfilDto dto) throws ValidationException {
		if (dto == null) {
			throw new ValidationException("Perfil inválido.");
		}

		if (StringManager.getInstance().isNullOrEmpty(dto.getNome())) {
			throw new ValidationException("Informe um nome.");
		}

		PerfilEntity entity = this.repository.findByNome(dto.getNome());
		if (entity != null
				&& (NumberManager.getInstance().isNullOrZero(dto.getId()) || !dto.getId().equals(entity.getId()))
				&& !dto.getNome().equals("Perfil Cadastro Google")) {
			throw new ValidationException("Informe um nome que ainda não exista.");
		}
	}

	private String parseBoolean(boolean valor) {
		return valor ? "1" : "0";
	}

	public PerfilVo disableOrEnableById(Integer id) throws ValidationException {
		PerfilEntity entity = this.repository.findById(id).get();

		if (id.equals(0)) {
			throw new ValidationException(
					"Deve existir um perfil ativo para cadastro de usuários com login Google, por isso o 'Perfil Cadastro Google' não pode ser desativado.");
		}

		if (!this.usuarioService.existePerfilVinculado(entity)) {
			entity.setDataDesativacao(entity.getDataDesativacao() == null ? new Date() : null);
			return new PerfilVo(this.repository.save(entity));
		} else {
			throw new ValidationException("Não será possivel desativar. Perfil está vinculado a algum usuário.");
		}
	}

	public ExportarCsvVo generateCsv(String descricao, Boolean listarInativos) throws ValidationException, IOException {
		List<PerfilVo> listaDados = this.repository.findByFilters(descricao,
				Boolean.TRUE.equals(listarInativos) ? 1 : 0);
		if (listaDados.isEmpty()) {
			throw new ValidationException("Não existem dados para imprimir");
		}
		return new CsvGenerator().generateCSV(listaDados, "\"Relatório Cadastro de Perfil\"");
	}

}
