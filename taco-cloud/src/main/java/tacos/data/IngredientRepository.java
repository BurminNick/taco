package tacos.data;


import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import tacos.Ingredient;

import java.util.List;
@Repository
@CrossOrigin(origins="http://tacocloud:8080")
public interface IngredientRepository extends CrudRepository<Ingredient, String>{

}
