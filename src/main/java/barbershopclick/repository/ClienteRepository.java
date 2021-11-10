package barbershopclick.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import barbershopclick.entity.ClienteEntity;
import barbershopclick.vo.ClienteVo;

public interface ClienteRepository extends CrudRepository<ClienteEntity, Integer> {
	
	@Query("select new barbershopclick.vo.ClienteVo(a)"
			  +"  from ClienteEntity a"
			  +" where (CASE WHEN :descricao IS NULL OR a.nome LIKE CONCAT('%', :descricao, '%') THEN 1 ELSE 0 END) = 1"
			  +"   and (case when :exibeInativos = 1 OR a.dataDesativacao is null THEN 1 ELSE 0 END) = 1"
			  +" order by a.nome")
	public List<ClienteVo> findByFilters(@Param("descricao") String descricao, @Param("exibeInativos") Integer exibeInativos);

	public ClienteEntity findByNome(String nome);
}
