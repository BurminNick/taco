package tacos;

import javax.persistence.*;

import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC, force=true)
@Table(name="ingredient")
public class Ingredient {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private Type type;
    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }



}
