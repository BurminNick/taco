package tacos;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
//import org.springframework.data.rest.core.annotation.RestResource;

@Data
@Entity
//@RestResource(rel="tacos", path="tacos")
@Table(name="taco")
public class Taco {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="createdAt")
    private Date createdAt;

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    @Column(name="name")
    private String name;

    //@ManyToMany(targetEntity=Ingredient.class)
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "taco_ingredients", joinColumns = @JoinColumn(name="taco"), inverseJoinColumns = @JoinColumn(name="ingredient"))
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

    /*@Column(name="taste")
    @Enumerated(EnumType.STRING)
    private Taste taste;

    public enum Taste{
        HOT, ORIGINAL
    }*/

}