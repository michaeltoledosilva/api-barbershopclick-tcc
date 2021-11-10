package barbershopclick.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import barbershopclick.entity.CargoEntity;
import barbershopclick.vo.CargoVo;

public interface CargoRepository extends CrudRepository<CargoEntity, Integer> {
	
	@Query("select new barbershopclick.vo.CargoVo(a)"
			  +"  from CargoEntity a"
			  +" where (CASE WHEN :descricao IS NULL OR a.nome LIKE CONCAT('%', :descricao, '%') THEN 1 ELSE 0 END) = 1"
			  +"   and (case when :exibeInativos = 1 OR a.dataDesativacao is null THEN 1 ELSE 0 END) = 1"
			  +" order by a.nome")
	public List<CargoVo> findByFilters(@Param("descricao") String descricao, @Param("exibeInativos") Integer exibeInativos);

	public CargoEntity findByNome(String nome);
}
