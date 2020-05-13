import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="MenuOfRestaurant")
@NamedQueries({
        @NamedQuery(name="Menu.findAll", query = "SELECT m FROM MenuOfRestaurant m"),
        @NamedQuery(name="Menu.findByName", query = "SELECT m FROM MenuOfRestaurant m WHERE m.name = :name")
})
public class MenuOfRestaurant {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "menuOfRestaurant", cascade = CascadeType.ALL)
    private List<Dish> dishes = new ArrayList<>();

    public MenuOfRestaurant(){

    }

    public MenuOfRestaurant(String name) {
        this.name = name;
    }

    public void addDish (Dish dish){
        dish.setMenuOfRestaurant(this);
        dishes.add(dish);
    }

    public List<Dish> getDishes() {
        return java.util.Collections.unmodifiableList(dishes);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MenuOfRestaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
