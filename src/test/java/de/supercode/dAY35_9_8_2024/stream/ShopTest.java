package de.supercode.dAY35_9_8_2024.stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(Lifecycle.PER_CLASS)
class ShopTest {

    private Shop shop;
    private Customer kazim;
    private Customer anna;
    private Product pinzette;
    private Product rolex;

    @BeforeAll
    void setup() {
        shop = new Shop();

        LocalDateTime kursbeginn = LocalDateTime.of(2024, Month.MAY, 21, 8, 45);
        Instant kursbeginnInstant = kursbeginn.toInstant(ZoneOffset.UTC);

        kazim = new Customer("Kazim", "Sakip", "k.s@gmail.com", Instant.now());
        Customer c2 = new Customer("Viktor", "Steiner", "viktors@gmail.com", kursbeginnInstant);
        anna = new Customer("Anna", "Patschen", "annapatschen@gmail.com", Instant.now());

        Product uhr = new Product("Uhr", "hilft dabei die Zeit zu lesen", 150.0f, "U139030", Category.SCHMUCK);
        rolex = new Product("Rolex Uhr", "hilft dabei die Zeit zu lesen", 18000.0f, "U144430", Category.SCHMUCK);
        Product soundMachine = new Product("Sound Machine", "Macht viele geräusche auf Tastenklick", 9.0f, "SM239", Category.TECH);
        pinzette = new Product("Pinzette", "Pinzette mit integrierte LED", 2.49f, "P800", Category.KOSMETIK);
        Product monitor = new Product("Monitor Groß", "Monitor mit vielen kleinen Pixeln", 250.0f, "LG239340e", Category.TECH);
        Product keyboard = new Product("Keyboard", "Keyboard mit vielen Tasten", 100.0f, "K239340e", Category.TECH);
        Product perfume = new Product("Perfume", "Riecht echt gut hmmmn (100ml)", 100.0f, "P239340e", Category.KOSMETIK);

        Order o1 = new Order(kazim);
        Order o2 = new Order(kazim);
        Order o3 = new Order(kazim);

        Order o4 = new Order(c2);

        Order o5 = new Order(anna);
        Order o6 = new Order(anna);

        o1.addProduct(uhr, 1);
        o1.addProduct(rolex, 1);
        o1.addProduct(pinzette, 2);
        o1.setHasPaid(true);
        o1.setOrderDate(Instant.now());
        shop.addOrder(o1);

        o2.addProduct(monitor, 1);
        o2.setHasPaid(false);
        o2.setOrderDate(Instant.now());
        shop.addOrder(o2);

        o3.addProduct(soundMachine, 1);
        o3.setHasPaid(true);
        o3.setOrderDate(Instant.now());
        shop.addOrder(o3);

        o4.addProduct(monitor, 12);
        o4.addProduct(keyboard, 12);
        o4.setHasPaid(true);
        o4.setOrderDate(kursbeginnInstant);
        shop.addOrder(o4);

        o5.addProduct(perfume, 1);
        o5.addProduct(pinzette, 2);
        o5.addProduct(rolex, 2);
        o5.setHasPaid(true);
        o5.setOrderDate(Instant.now());
        shop.addOrder(o5);

        o6.addProduct(pinzette, 1);
        o6.setHasPaid(true);
        o6.setOrderDate(Instant.now());
        shop.addOrder(o6);
    }

    @Test
    void totalOrderValue() {
        assertEquals(18154.98f, shop.getOrderList().get(0).totalOrderValue());
    }

    @Test
    void customerWithMostOrders() {
        assertEquals(kazim, shop.customerWithMostOrder());
    }


    @Test
    void customerWithHighestLifetimeValue() {
        assertEquals(anna, shop.customerWithHighestLifetimeValue());
    }

    @Test
    void topCustomerByProductPinzetteIsAnna() {
        assertEquals(anna, shop.topCustomerPerProduct(pinzette));
    }

    @Test
    void topCustomerByProductRolexIsKazim() {
        assertEquals(anna, shop.topCustomerPerProduct(rolex));
    }

    @Test
    void topCategoryByRevenue() {
        assertEquals(Category.SCHMUCK, shop.topCategoryByRevenue());
    }

    @Test
    void returningCustomerRate() {
        assertEquals(2.0 / 3.0, shop.returningCustomerRate());
    }


}