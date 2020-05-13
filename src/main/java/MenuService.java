import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
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

            String dishName;
            String menuName;

            System.out.println("Input name of the dish");
            dishName = scanner.nextLine();

            System.out.println("Input name of the menu");
            menuName = scanner.nextLine();

        Query query = em.createNamedQuery("Menu.findAll", MenuOfRestaurant.class);
            query.setParameter("name", menuName);
            MenuOfRestaurant menu = (MenuOfRestaurant) query.getSingleResult();

            Query dishQuery = em.createNamedQuery("Menu.findAll", MenuOfRestaurant.class);
            dishQuery.setParameter("name", dishName);
            Dish dish = (Dish) query.getSingleResult();
            dish.setMenuOfRestaurant(menu);
        }

        public void getDishesWithPriceBelow (EntityManager em, Scanner scanner){

        }

        public void getDishesWithDiscount (EntityManager em, Scanner scanner){

        }

        public void getDishesWithPriceLowerThan (EntityManager em, Scanner scanner){

        }
    }
