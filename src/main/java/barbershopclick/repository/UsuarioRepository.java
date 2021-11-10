package barbershopclick.repository;

import barbershopclick.entity.PerfilEntity;
import barbershopclick.entity.UsuarioEntity;
import barbershopclick.vo.UsuarioVo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Integer> {

	@Query("select new barbershopclick.vo.UsuarioVo(a)"
			  +"  from UsuarioEntity a"
			  +" where (CASE WHEN :descricao IS NULL OR a.nome LIKE CONCAT('%', :descricao, '%') THEN 1 ELSE 0 END) = 1"
			  +"   and (case when :exibeInativos = 1 OR a.dataDesativacao is null THEN 1 ELSE 0 END) = 1"
			  + "  and a.perfil.id <> 0"
			  +" order by a.nome")
	public List<UsuarioVo> findByFilters(@Param("descricao") String descricao, @Param("exibeInativos") Integer exibeInativos);
	
	public UsuarioEntity findBylogin(String login);
	
	@Query("select new barbershopclick.vo.UsuarioVo(a)"
			  +"  from UsuarioEntity a"
			  +" where a.perfil = :perfil")
	public List<UsuarioVo> findByPerfil(@Param("perfil") PerfilEntity perfil);

}
