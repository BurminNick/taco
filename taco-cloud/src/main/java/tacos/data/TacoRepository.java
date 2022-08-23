package tacos.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;
import tacos.Taco;

@Repository
public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

}
