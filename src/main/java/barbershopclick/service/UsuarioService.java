package barbershopclick.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barbershopclick.dto.UsuarioDto;
import barbershopclick.entity.PerfilEntity;
import barbershopclick.entity.UsuarioEntity;
import barbershopclick.helper.StringManager;
import barbershopclick.helper.csvmanager.CsvGenerator;
import barbershopclick.repository.UsuarioRepository;
import barbershopclick.vo.UsuarioVo;
import barbershopclick.vo.generic.ExportarCsvVo;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private PerfilService perfilService;

	@Autowired
	private FuncionarioService funcionarioService;

	public UsuarioEntity findById(Integer id) throws ValidationException {
		return this.repository.findById(id).orElseThrow(() -> new ValidationException("Usuário não encontrado"));
	}

	public List<UsuarioVo> findByFilters(String descricao, Boolean listarInativos) throws ValidationException {
		return this.repository.findByFilters(descricao, (listarInativos == null ? false : listarInativos) ? 1 : 0);
	}

	public UsuarioVo loginValidation(String nome, String login, String senha, String isGoogle)
			throws ValidationException {
		UsuarioEntity entity = null;
		
		entity = this.repository.findBylogin(login);
		if (entity == null || login.isEmpty() || senha.isEmpty() || !senha.equals(entity.getSenha())) {
			if (Boolean.parseBoolean(isGoogle)) {
				entity = cadastraUserGoogle(nome, login, senha);
			} else {
				throw new ValidationException("Usuário ou senha inválidos.");
			}
		}
		
		if (entity.getDataDesativacao() != null)  {
            throw new ValidationException("O usuário informado está desativado no sistema.");
        }
		
		return new UsuarioVo(entity);
	}

	public UsuarioEntity save(UsuarioDto dto) throws ValidationException {
		UsuarioEntity entity = new UsuarioEntity();

		validarDados(dto);

		if ((dto.getId() != null && !dto.getId().equals(0))) {
			entity = this.repository.findById(dto.getId()).get();
		} else {
			entity.setDataCadastro(new Date());
		}
		entity.setNome(dto.getNome());
		entity.setSenha(dto.getSenha());
		entity.setLogin(dto.getLogin());
		entity.setPerfil(this.perfilService.findById(dto.getIdPerfil()));

		return repository.save(entity);
	}

	public UsuarioVo disableOrEnableById(Integer id) throws ValidationException {
		UsuarioEntity entity = this.repository.findById(id).get();
		if (!this.funcionarioService.existeUsuarioVinculado(entity)) {
			entity.setDataDesativacao(entity.getDataDesativacao() == null ? new Date() : null);
			return new UsuarioVo(this.repository.save(entity));
		} else {
			throw new ValidationException("Não será possivel desativar. Usuário está vinculado a algum funcionário.");
		}
	}

	public ExportarCsvVo generateCsv(String descricao, Boolean listarInativos) throws ValidationException, IOException {
		List<UsuarioVo> listaDados = this.repository.findByFilters(descricao,
				Boolean.TRUE.equals(listarInativos) ? 1 : 0);
		if (listaDados.isEmpty()) {
			throw new ValidationException("Não existem dados para imprimir");
		}
		return new CsvGenerator().generateCSV(listaDados, "\"Relatório Cadastro de Usuário\"");
	}

	private boolean validarDados(UsuarioDto dto) throws ValidationException {
		StringManager sm = new StringManager();
		if (dto == null) {
			throw new ValidationException("Usuários inválido.");
		}
		if (sm.isNullOrEmpty(dto.getNome())) {
			throw new ValidationException("Informe um nome.");
		}

		if (sm.isNullOrEmpty(dto.getLogin())) {
			throw new ValidationException("Informe um login.");
		}

		if (sm.isNullOrEmpty(dto.getSenha())) {
			throw new ValidationException("Informe uma senha.");
		}

		if (dto.getIdPerfil() == null) {
			throw new ValidationException("Informe um perfil.");
		}

		return true;
	}

	public boolean existePerfilVinculado(PerfilEntity perfilEntity) {
		return !this.repository.findByPerfil(perfilEntity).isEmpty();
	}

	public UsuarioEntity cadastraUserGoogle(String nome, String login, String senha) throws ValidationException {
		UsuarioEntity entity = new UsuarioEntity();

		entity.setDataCadastro(new Date());
		entity.setNome(nome);
		entity.setSenha(senha);
		entity.setLogin(login);
		entity.setPerfil(this.perfilService.findById(0));

		return this.repository.save(entity);
	}

}
