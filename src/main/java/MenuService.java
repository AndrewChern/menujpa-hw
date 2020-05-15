import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
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

        em.getTransaction().begin();

        try {
            Dish dish1 = new Dish(name, price, weight, discount);

            try {
                em.persist(dish1);
                em.getTransaction().commit();
            } catch (Exception ex) {
                em.getTransaction().rollback();
                return;
            }
        } finally {
            em.close();
        }
    }

    public void addDishToMenu(EntityManager em, Scanner scanner) {

        String dishName;
        String menuName;

        System.out.println("Input name of the dish");
        dishName = scanner.nextLine();

        System.out.println("Input name of the menu");
        menuName = scanner.nextLine();

        Query query = em.createNamedQuery("Menu.findByName", MenuOfRestaurant.class);
        query.setParameter("name", menuName);
        MenuOfRestaurant menu = (MenuOfRestaurant) query.getSingleResult();

        Query dishQuery = em.createNamedQuery("Dish.findByName", Dish.class);
        dishQuery.setParameter("name", dishName);
        Dish dish = (Dish) query.getSingleResult();
        dish.setMenuOfRestaurant(menu);
    }

    public void getDishesWithPriceBelow(EntityManager em, Scanner scanner) {

        long minPrice;
        long maxPrice;

        System.out.println("Input mininum price of the dish");
        minPrice = scanner.nextLong();

        System.out.println("Input mininum price of the dish");
        maxPrice = scanner.nextLong();

        Query query = em.createQuery("SELECT d FROM Dish d WHERE d.price BETWEEN :minPrice AND :maxPrice", Dish.class);
        List<Dish> dishList = query.getResultList();

        for (Dish dish : dishList) {
            System.out.println(dish);
        }
    }

    public void getDishesWithDiscount(EntityManager em) {
        Query query = em.createQuery("SELECT d FROM Dish d WHERE d.discount > 0", Dish.class);
        List<Dish> dishList = query.getResultList();
        for (Dish dish : dishList) {
            System.out.println(dish);
        }
    }

    public void getDishesWithWeightLowerThan(EntityManager em, Scanner scanner) {

        List<Dish> dishList = new ArrayList<Dish>();
        long dishesWeightSum = (long) 0.0;
        String dishName;

        while (dishesWeightSum<1000){
            System.out.println("Input name of the dish:");
            dishName = scanner.nextLine();
            Query dishQuery = em.createNamedQuery("Dish.findByName", Dish.class);
            dishQuery.setParameter("name", dishName);
            Dish dishFromRequest = (Dish) dishQuery.getSingleResult();
            dishesWeightSum +=dishFromRequest.getWeight();
            dishList.add(dishFromRequest);
        }

        for (Dish dish : dishList) {
            System.out.println(dish);
        }
    }
}
