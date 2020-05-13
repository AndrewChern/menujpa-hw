import javax.persistence.EntityManager;
import java.util.Scanner;

public class MenuService {

    private static Dish dish;

    private static MenuOfRestaurant menu;

    public MenuService() {
    }

    public void addDish(EntityManager em, Scanner scanner) {

        String name;
        long price;
        long weight;
        long discount;

        System.out.println("Input name of the dish");
        name = scanner.nextLine();

        System.out.println("Input price of the dish");
        price = scanner.nextLong();

        System.out.println("Input weight of the dish");
        weight = scanner.nextLong();

        System.out.println("Input discount of the dish");
        discount = scanner.nextLong();

        try {

            dish = new Dish(name, price, weight, discount);
            em.getTransaction().begin();
            try {
                em.persist(dish);
                em.getTransaction().commit();
            } catch (Exception ex) {
                em.getTransaction().rollback();
                return;
            }
        } finally {
            em.close();
        }
    }

        public void addDishToMenu (EntityManager em, Scanner scanner){

        }

        public void getDishesWithPriceBelow (EntityManager em, Scanner scanner){

        }

        public void getDishesWithDiscount (EntityManager em, Scanner scanner){

        }

        public void getDishesWithPriceLowerThan (EntityManager em, Scanner scanner){

        }
    }
