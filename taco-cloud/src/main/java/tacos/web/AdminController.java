package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tacos.Order;
import tacos.Taco;
import tacos.User;
import tacos.data.OrderRepository;
import tacos.data.TacoRepository;
import tacos.data.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@SessionAttributes("order")
@Slf4j
public class AdminController {

    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private TacoRepository tacoRepo;
    @Autowired
    private UserRepository userRepo;

    public AdminController(OrderRepository orderRepo, TacoRepository tacoRepo, UserRepository userRepo) {
        this.orderRepo = orderRepo;
        this.tacoRepo = tacoRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/all-orders")
    public String showOrders(Model model){
        List<Order> allOrders = (List<Order>) orderRepo.findAll();
        model.addAttribute("allOrders", allOrders);
        return "all-orders";
    }

    @GetMapping("/all-tacos")
    public String showTacos(Model model){
        List<Taco> allTacos = (List<Taco>) tacoRepo.findAll();
        model.addAttribute("allTacos", allTacos);
        return "all-tacos";
    }


    @GetMapping("/all-users")
    public String showUsers(Model model){
        List<User> allUsers = (List<User>) userRepo.findAll();
        model.addAttribute("allUsers", allUsers);
        return "all-users";
    }

    @PostMapping("/all-orders/deleteAll")
    public String deleteAllOrders(){

        log.info("   --- Deleting all orders");
        orderRepo.deleteAll();

        return "all-orders";

    }

    @PostMapping("/all-tacos/deleteAll")
    public String deleteAllTacos(){

        log.info("   --- Deleting all tacos");
        tacoRepo.deleteAll();

        return "all-tacos";

    }

    @PostMapping("/all-users/deleteAll")
    public String deleteAllUsers(){

        log.info("   --- Deleting all users");
        userRepo.deleteAll();

        return "all-users";

    }


    @DeleteMapping("/all-orders/{id}")
    public String deleteOrder(@PathVariable("id") Long id){

        log.info("   --- Deleting order");
       orderRepo.deleteById(id);

        return "home";

    }

    @DeleteMapping("/all-tacos/{id}")
    public String deleteTaco(@PathVariable("id") Long id){

        log.info("   --- Deleting taco");
        tacoRepo.deleteById(id);

        return "home";

    }

    @DeleteMapping("/all-users/{id}")
    public String deleteUser(@PathVariable("id") Long id){

        log.info("   --- Deleting user");
        userRepo.deleteById(id);

        return "home";

    }
}
