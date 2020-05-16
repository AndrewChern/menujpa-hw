import javax.persistence.*;

@Entity
@Table(name="Dish")
@NamedQueries({
    @NamedQuery(name="Dish.findAll", query = "SELECT d FROM Dish d"),
    @NamedQuery(name="Dish.findByName", query = "SELECT d FROM Dish d WHERE d.name = :name")
})
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="price")
    private Long price;

    @Column(name="weight")
    private Long weight;

    @Column(name="discount")
    private Long discount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="menu_id")
    private MenuOfRestaurant menuOfRestaurant;

    public Dish (){
    }

    public Dish(String name, long price, long weight, long discount) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.discount = discount;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }

    public MenuOfRestaurant getMenuOfRestaurant() {
        return menuOfRestaurant;
    }

    public void setMenuOfRestaurant(MenuOfRestaurant menuOfRestaurant) {
        this.menuOfRestaurant = menuOfRestaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", discount=" + discount +
                '}';
    }
}
