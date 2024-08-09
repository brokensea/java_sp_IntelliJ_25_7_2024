package de.supercode.dAY34_8_8_2024.stream;

import java.util.*;
import java.util.stream.Collectors;

public class Shop {
    private List<Order> orderList;

    public Shop() {
        this.orderList = new ArrayList<>();
    }

    public Order addOrder(Order order) {
        this.orderList.add(order);
        return order;
    }

    public Order removeOrder(Order order) {
        this.orderList.remove(order);
        return order;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    // o1, o2, o3,     o4, ...
    // <c1> <c1> <c1>  <c2>
    public Customer customerWithMostOrder() {
        return orderList
                .stream()
                .collect(Collectors.groupingBy(Order::getCustomer))
                .entrySet().stream()
                .max(Comparator.comparing(entry -> entry.getValue().size()))
                .map(entry -> entry.getKey())
                .orElse(null);
    }

    /* public Customer customerWithHighestLifetimeValue() {
        return orderList.stream()
                .collect(Collectors.groupingBy(Order::getCustomer))  // 按客户分组
                .entrySet().stream()  // 将 Map 转换为 Stream
                .map(entry -> new AbstractMap.SimpleEntry<>(
                        entry.getKey(),
                        entry.getValue().stream()
                                .mapToDouble(Order::totalOrderValue)  // 计算客户的总订单金额
                                .sum()
                ))
                .max(Comparator.comparing(AbstractMap.SimpleEntry::getValue))  // 找到总订单金额最大的客户
                .map(AbstractMap.SimpleEntry::getKey)  // 获取该客户
                .orElse(null);  // 如果没有订单或客户，返回 null
    }*/

    public Customer customerWithHighestLifetimeValue() {
        return orderList
                .stream()
                .collect(Collectors.toMap(Order::getCustomer, Order::totalOrderValue, Float::sum))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    //- Welcher Kunde ist am längsten Dabei ?
    public Customer kundeLaengstenDabei() {
        return orderList.stream()
                .map(Order::getCustomer)
                .min(Comparator.comparing(Customer::getCustomerSince))
                .orElse(null);
    }

    //- Was ist das beliebteste Produkt ?
    public Product beliebtesteProdukt() {
        return orderList.stream()
                .flatMap(order -> order.getProducts().entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,                        // 按产品分组
                        Collectors.summingInt(Map.Entry::getValue) // 累加每个产品的数量
                )).entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    //- Was ist das unbeliebteste Produkt ?
    public Product unbeliebtesteProdukt() {
        return orderList.stream()
                .flatMap(order -> order.getProducts().entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.summingInt(Map.Entry::getValue)
                )).entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    //- Was ist der durchschnittliche Wert einer Bestellung ?
    public float durchschnittlicheWertBestellung(Order order) {
        float totalValue = (float) orderList.stream()
                .mapToDouble(Order::totalOrderValue)
                .sum();
        long orderCount = orderList.size();
        float durchschnittlicheWert = totalValue / orderCount;
        return orderCount > 0 ? durchschnittlicheWert : 0;
    }

    //- Kunden mit Bestellungen über einen bestimmten Wert
    public List<Customer> kundenBestellungenUeberBestimmtenWert(float wert) {
        return orderList.stream()
                .collect(Collectors.groupingBy(Order::getCustomer, Collectors.summingDouble((Order::totalOrderValue))
                )).entrySet().stream()
                .filter(entry -> entry.getValue() > wert)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

    }

    //- Top-Kunden pro Produkt → finde die Top-N Kunden für ein bestimmtes Produkt basierend auf der Anzahl der Bestellungen dieses Produkts
    public List<Customer> topKundenProProdukt(Product product, int n) {
        return orderList.stream()
                .filter(order -> order.getProducts().containsKey(product))  // 先过滤出包含该产品的订单
                .collect(Collectors.groupingBy(
                        Order::getCustomer,  // 按客户分组
                        Collectors.summingInt(order -> order.getProducts().get(product))  // 累加客户购买该产品的数量
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<Customer, Integer>comparingByValue(Comparator.reverseOrder()))  // 按购买数量降序排序
                .limit(n)  // 取前n个客户
                .map(Map.Entry::getKey)  // 提取客户对象
                .collect(Collectors.toList());  // 收集成List
    }

    // Tutor:
    public Customer customerWhoBuysOneProducttheMost(Product product) {
        // o1, o2,                                                            o3....
        // c & Map<Product, Integer> => kazim - [pinzette = 3, rolex =1] kazim - [pinzette = 1]  anna -[pinzette = 8, monitor = 2 ]
        // topkunden(pinzette) --> anna
        // Product --> Totle time bought.
        // for given prodoct: Map<Customer, Integer>
        // o1: kazim ->3
        //o2: kazim ->4
        // o3: kazim ->4, anna -> 8
        // max --> anna

        //Frage ist ;
        // Order {Customer, Map<Product, Integer>} -->Map<Customer, Integer>
        // code:

        return orderList.stream()
                .map(order -> new AbstractMap.SimpleEntry<Customer, Integer>(
                                order.getCustomer(),
                                order.getProducts().containsKey(product) ? order.getProducts().get(product) : 0
                        )
                ).collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingInt(Map.Entry::getValue)))
                .entrySet()
                .stream()
                .peek(System.out::println)
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    // 优化
   /* public Customer customerWhoBuysOneProducttheMost(Product product) {
        return orderList.stream()
                .collect(Collectors.toMap(
                        Order::getCustomer,
                        order -> order.getProducts().getOrDefault(product, 0),
                        Integer::sum // 处理多个订单来自同一客户的情况
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);


    }*/

   /* orderList.stream()
                .collect(Collectors.toMap(
                        Order::getCustomer,
                        Order.getProducts())
                ))*/

/*
    public List<Customer> topKundenProProdukt(Product product, int n) {
        return orderList.stream()
                .flatMap(order -> order.getProducts().entrySet().stream())
                .filter(entry -> entry.getKey().equals(product))
                .collect(Collectors.groupingBy(entry -> orderList.stream()
                        .filter(order -> order.equals(order))
                        .map(Order::getCustomer)
                        .findFirst().orElse(null), Collectors.summingInt(Map.Entry::getValue)
                ))
                .entrySet().stream().sorted(Map.Entry.<Customer, Integer>comparingByValue(Comparator.reverseOrder()))
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }*/

    //- Welche Produktkategorie bringt den meisten Umsatz ?
    public Category kategorieMitHöchstemUmsatz() {
        return orderList.stream()
                .flatMap(order -> order.getProducts().entrySet().stream())
                .collect(Collectors.groupingBy(entry -> entry.getKey().getCategory(),
                        Collectors.summingDouble(entry -> entry.getKey().getPrice() * entry.getValue())))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);


    }

    // Wieviel % der Kunden haben mehrmals gekauft "returning customer Rate"
   /* public float returingCustomeRate() {
        int numberOfCustomersWhoBoughtAtLeastTwice =
                orderList
                        .stream()
                        .map(Order::getCustomer)
                        .collect(Collectors.groupingBy(customer));

        int numberOfCustioners = orderList
                .stream()
                .map(Order::getCustomer)
                .collect(Collectors.toSet())
                .size();
    }*/

    // Tutor:
   /* public List<Customer> topNCustomerPerProduct (Product product, int n) {
        return orderList
                .stream()
                .map(order ->
                        new AbstractMap.SimpleEntry<Customer, Integer>(
                                order.getCustomer(), // key von entry
                                order.getProducts().containsKey(product) ? order.getProducts().get(product) : 0 // value von entry
                        )
                )
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingInt(Map.Entry::getValue)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Customer, Integer>comparingByValue().reversed())
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
    */
    // Top Kunde pro Produkt aka customerWhoBuysOneProductTheMost()
    /*public Customer topCustomerPerProduct (Product product) {
        return orderList
                .stream()
                        // order -> [order.getCustomer(), order.getProducts().get(product)]
                .map(order ->
                    new AbstractMap.SimpleEntry<Customer, Integer>(
                        order.getCustomer(), // key von entry
                        order.getProducts().containsKey(product) ? order.getProducts().get(product) : 0 // value von entry
                    )
                )
                // { <kazim : 7>, <anna : 2>, <kazim : 0>, <hugo : 1>, <kazim : 3 > }
                // grouping -> [kazim: {<kazim : 7>, <kazim : 0>, <kazim : 3 >}, anna: {<anna : 2>}, hugo: { <hugo : 1> } ]
                // summingInt downstream collector -> { kazim: 10, anna: 2, hugo: 1 }
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingInt(Map.Entry::getValue)))
                //        { <kazim: 10>, <anna: 2>, <hugo: 1> }
                .entrySet()
                .stream()
                .peek(System.out::println)
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
}*/

}