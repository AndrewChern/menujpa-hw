import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATest");
        EntityManager em = emf.createEntityManager();
        MenuService menuService = new MenuService();

        try {
                MenuOfRestaurant menu1 = new MenuOfRestaurant("Menu-1");
                MenuOfRestaurant menu2 = new MenuOfRestaurant("Menu-2");
                Dish dish;
                long gid1, gid2;

                for (int i = 0; i < 3; i++) {
                    dish = new Dish("Name" + i, new Long((i*10)+1), new Long((i+1)*200), new Long(i)) ;
                    menu1.addDish(dish);
                }
                for (int i = 0; i < 2; i++) {
                    dish = new Dish("Name" + (i+5), new Long((i*10)+1), new Long((i+1)*200), new Long(i));
                    menu2.addDish(dish);
                }

                em.getTransaction().begin();
                try {
                    em.persist(menu1);
                    em.persist(menu2);
                    em.getTransaction().commit();

                    System.out.println("New menu id #1: " + (gid1 = menu1.getId()));
                    System.out.println("New menu id #2: " + (gid2 = menu2.getId()));

                    Query query = em.createQuery("SELECT d FROM Dish d", Dish.class);
                    List<Dish> dishList = query.getResultList();

                    for (Dish dish1 : dishList) {
                        System.out.println(dish1);
                    }
                } catch (Exception ex) {
                    em.getTransaction().rollback();
                    return;
                }

            while (true) {
                System.out.println("1: add dish");
                System.out.println("2: add dish to menu");
                System.out.println("3: get dishes with price below");
                System.out.println("4: get dishes with discount");
                System.out.println("5: get dishes with weight lower than");
                System.out.print("-> ");
                String s = scanner.nextLine();
                switch (s) {
                    case "1":
                        menuService.addDish(em,scanner);
                        break;
                    case "2":
                        menuService.addDishToMenu(em,scanner);
                        break;
                    case "3":
                        menuService.getDishesWithPriceBelow(em,scanner);
                        break;
                    case "4":
                        menuService.getDishesWithDiscount(em);
                        break;
                    case "5":
                        menuService.getDishesWithWeightLowerThan(em,scanner);
                        break;

                    default:
                        return;
                }
            }

            } finally {
                scanner.close();
                em.close();
                emf.close();

        }
    }
}
