package barbershopclick.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import barbershopclick.entity.PerfilEntity;
import barbershopclick.vo.PerfilVo;

public interface PerfilRepository extends CrudRepository<PerfilEntity, Integer> {
	
	@Query("select new barbershopclick.vo.PerfilVo(a)"
			  +"  from PerfilEntity a"
			  +" where (CASE WHEN :descricao IS NULL OR a.nome LIKE CONCAT('%', :descricao, '%') THEN 1 ELSE 0 END) = 1"
			  +"   and (case when :exibeInativos = 1 OR a.dataDesativacao is null THEN 1 ELSE 0 END) = 1"
			  +" order by a.nome")
	public List<PerfilVo> findByFilters(@Param("descricao") String descricao, @Param("exibeInativos") Integer exibeInativos);

	public PerfilEntity findByNome(String nome);

}