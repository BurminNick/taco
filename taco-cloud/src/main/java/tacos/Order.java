package tacos;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="taco_order")
//@Table(name="Taco_Order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="placedAt")
    private Date placedAt;

    @NotBlank(message="Name is required")
    @Column(name="deliveryName")
    private String deliveryName;

    @NotBlank(message="Street is required")
    @Column(name="deliveryStreet")
    private String deliveryStreet;


    @NotBlank(message="City is required")
    @Column(name="deliveryCity")
    private String deliveryCity;


    @NotBlank(message="State is required")
    @Column(name="deliveryState")
    private String deliveryState;

    @NotBlank(message="Zip code is required")
    @Column(name="deliveryZip")
    private String deliveryZip;

    @CreditCardNumber(message="Not a valid credit card number")
    @Column(name="ccNumber")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    @Column(name="ccExpiration")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    @Column(name="ccCVV")
    private String ccCVV;

    //@ManyToMany(targetEntity=Taco.class)

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    //@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "taco_order_tacos", joinColumns = @JoinColumn(name="order_id"), inverseJoinColumns = @JoinColumn(name="tacos_id"))
    private List<Taco> tacos = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id")
    private User user;


    public void addDesign(Taco design) {
        this.tacos.add(design);
    }

    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }

}
