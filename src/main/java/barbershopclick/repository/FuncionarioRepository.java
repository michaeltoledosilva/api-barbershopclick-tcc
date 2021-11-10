package barbershopclick.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import barbershopclick.entity.CargoEntity;
import barbershopclick.entity.FuncionarioEntity;
import barbershopclick.entity.UsuarioEntity;
import barbershopclick.vo.FuncionarioVo;

public interface FuncionarioRepository extends CrudRepository<FuncionarioEntity, Integer> {
	
	@Query("select new barbershopclick.vo.FuncionarioVo(a)"
			  +"  from FuncionarioEntity a"
			  +" where (CASE WHEN :descricao IS NULL OR a.nome LIKE CONCAT('%', :descricao, '%') THEN 1 ELSE 0 END) = 1"
			  +"   and (case when :exibeInativos = 1 OR a.dataDesativacao is null THEN 1 ELSE 0 END) = 1"
			  +" order by a.nome")
	public List<FuncionarioVo> findByFilters(@Param("descricao") String descricao, @Param("exibeInativos") Integer exibeInativos);

	public FuncionarioEntity findByNome(String nome);
	
	@Query("select new barbershopclick.vo.FuncionarioVo(a)"
			  +"  from FuncionarioEntity a"
			  +" where a.cargo = :cargo")
	public List<FuncionarioVo> findByCargo(@Param("cargo") CargoEntity cargo);
	
	@Query("select new barbershopclick.vo.FuncionarioVo(a)"
			  +"  from FuncionarioEntity a"
			  +" where a.usuario = :usuario")
	public List<FuncionarioVo> findByUsuario(@Param("usuario") UsuarioEntity cusuarioargo);

}