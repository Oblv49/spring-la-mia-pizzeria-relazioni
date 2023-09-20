package org.java.lessons.lamiapizzeriacrud.model;

import org.java.lessons.lamiapizzeriacrud.repository.PizzeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Controller
public class DataLoader implements ApplicationRunner {
    @Autowired
    private PizzeRepository pizzeRepository;

    public void run(ApplicationArguments args) {
        // Creazione delle pizze
        if (pizzeRepository.count() == 0) {
            Pizza pizza1 = new Pizza();
            pizza1.setName("Margherita");
            pizza1.setDescription("Pomodoro, mozzarella, basilico");
            pizza1.setImage("https://www.pizzeriagraniantichi.it/templates/yootheme/cache/f3/pizza-margherita_dop-f34c2f99.png");
            pizza1.setPrice(new BigDecimal("8.50"));
            pizzeRepository.save(pizza1);

            Pizza pizza2 = new Pizza();
            pizza2.setName("Diavola");
            pizza2.setDescription("Salsiccia piccante, pomodoro, mozzarella");
            pizza2.setImage("https://www.pizzeriagraniantichi.it/templates/yootheme/cache/f2/pizza-diavola-2-f29995b5.png");
            pizza2.setPrice(new BigDecimal("9.50"));
            pizzeRepository.save(pizza2);

            Pizza pizza3 = new Pizza();
            pizza3.setName("Capricciosa");
            pizza3.setDescription("Funghi, carciofi, prosciutto, pomodoro, mozzarella");
            pizza3.setImage("https://www.pizzaesfizio2.it/wp-content/uploads/2020/09/Tavola-da-disegno-6.png");
            pizza3.setPrice(new BigDecimal("10.50"));
            pizzeRepository.save(pizza3);

            Pizza pizza4 = new Pizza();
            pizza4.setName("Frutti di Mare");
            pizza4.setDescription("Frutti di mare misti, pomodoro, mozzarella");
            pizza4.setImage("https://manitopizzaorder.com/cdn/shop/products/0016_02---Frutti-di-mare.png?v=1657343682");
            pizza4.setPrice(new BigDecimal("12.50"));
            pizzeRepository.save(pizza4);

            Pizza pizza5 = new Pizza();
            pizza5.setName("Bufalina");
            pizza5.setDescription("Pomodoro fresco, mozzarella di bufala, basilico");
            pizza5.setImage("https://www.pizzaesfizio2.it/wp-content/uploads/2020/09/Tavola-da-disegno-18.png");
            pizza5.setPrice(new BigDecimal("9.00"));
            pizzeRepository.save(pizza5);

            Pizza pizza6 = new Pizza();
            pizza6.setName("Wurstel e Patatine");
            pizza6.setDescription("Wurstel, patatine fritte, pomodoro, mozzarella");
            pizza6.setImage("https://pizzarella.ch/wp-content/uploads/2020/07/Pizza-Bambini-Pizzarella-Ticino.jpg");
            pizza6.setPrice(new BigDecimal("9.50"));
            pizzeRepository.save(pizza6);

            Pizza pizza7 = new Pizza();
            pizza7.setName("Quattro Formaggi");
            pizza7.setDescription("Gorgonzola, mozzarella, fontina, parmigiano, pomodoro");
            pizza7.setImage("https://positano.lv/wp-content/uploads/2021/12/Quattro-formaggi-bianca-1.png");
            pizza7.setPrice(new BigDecimal("10.50"));
            pizzeRepository.save(pizza7);

            Pizza pizza8 = new Pizza();
            pizza8.setName("Vegetariana");
            pizza8.setDescription("Melanzane, zucchine, peperoni, pomodoro, mozzarella");
            pizza8.setImage("https://italianspizza.it/wp-content/uploads/2022/06/FAMILY-PIZZA-VEGETARIANA-online-pizza-sconti-eventi-feste-delivery-consegna-a-domicilio-san-colombano-al-lambro-lambrinia-monteleone-lodi-milano-italia.png");
            pizza8.setPrice(new BigDecimal("10.50"));
            pizzeRepository.save(pizza8);

            Pizza pizza9 = new Pizza();
            pizza9.setName("Tonno e Cipolla");
            pizza9.setDescription("Tonno, cipolla, olive nere, pomodoro, mozzarella");
            pizza9.setImage("https://www.pomodorogroup.fr/wp-content/uploads/2022/09/Tonno.png");
            pizza9.setPrice(new BigDecimal("9.50"));
            pizzeRepository.save(pizza9);
        }
    }
}
