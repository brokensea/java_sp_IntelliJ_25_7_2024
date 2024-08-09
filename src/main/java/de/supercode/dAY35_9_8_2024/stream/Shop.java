package de.supercode.dAY35_9_8_2024.stream;

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

    public double returningCustomerRateMorePerformant() {
        Map<Boolean, Long> returningCustomerPartition = orderList
                .stream()
                .map(Order::getCustomer)
                .collect(Collectors.groupingBy(
                        (customer) -> customer,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .collect(Collectors.partitioningBy((entry) -> entry.getValue() >= 2, Collectors.counting()));

        long numberOfCustomersWhoBoughtAtLeastTwice = returningCustomerPartition.get(true);
        long numberOfCustomers = returningCustomerPartition.get(true) + returningCustomerPartition.get(false);

        if (numberOfCustomers == 0) throw new IllegalStateException("No customers at all (can not divide by zero)");
        return numberOfCustomersWhoBoughtAtLeastTwice / (double) numberOfCustomers;
    }

    // Wieviel % der Kunden haben mehrmals gekauft "returning customer rate"
    public double returningCustomerRate() {
        long numberOfCustomersWhoBoughtAtLeastTwice = orderList
                .stream()
                .map(Order::getCustomer)
                .collect(Collectors.groupingBy(
                        (customer) -> customer,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .filter((entry) -> entry.getValue() >= 2)
                .count();
        int numberOfCustomers = orderList
                .stream()
                .map(Order::getCustomer)
                .collect(Collectors.toSet())
                .size();
        if (numberOfCustomers == 0) throw new IllegalStateException("No customers at all (can not divide by zero)");
        return numberOfCustomersWhoBoughtAtLeastTwice / (double) numberOfCustomers;
    }

    public Category topCategoryByRevenue() {
        //        o1,                            o2, ...                o3
        // kazim - [pinzette=3,      rolex=1]         kazim - [pinzette=1]     anna - [pinzette=8,            monitor=2]
        //          price, category    price, category            price, category         price, category     price, category
        Optional<Category> foundCategory = orderList
                .stream()
                .flatMap(order -> order.getProducts().entrySet().stream())
                .map(orderProductEntry -> new AbstractMap.SimpleEntry<Category, Double>(
                        orderProductEntry.getKey().getCategory(), // product dieser bestellung
                        orderProductEntry.getKey().getPrice() * orderProductEntry.getValue().doubleValue() // umsatz durch product dieser bestellung
                ))
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingDouble(Map.Entry::getValue)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
        if (foundCategory.isEmpty()) throw new IllegalStateException("No top Category found");
        else return foundCategory.get();
    }

    public List<Customer> topNCustomerPerProduct(Product product, int n) {
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

    // Top Kunde pro Produkt aka customerWhoBuysOneProductTheMost()
    public Customer topCustomerPerProduct(Product product) {
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

        //        o1,                            o2, ...                o3
        // kazim - [pinzette=3, rolex=1]    kazim - [pinzette=1]     anna - [pinzette=8, monitor=2]
        // topCustomerPerProduct(pinzette) ----> anna
        // topCustomerPerProduct(rolex) ----> kazim
        // topCustomerPerProduct(monitor) ----> anna

        // for given product: Map<Customer, Integer>
        // product = pinzette
        // o1: kazim -> 3
        // o2: kazim -> 4
        // o3: kazim -> 4, anna -> 8
        // max --> anna

        // for given product: Map<Customer, Integer>
        // product = rolex
        // o1: kazim --> 1
        // o2: kazim --> 1
        // o3: kazim --> 1
        // max --> kazim

        // Order { Customer, Map<Product, Integer> } --> Map<Customer, Integer>
        //

    }

    public Customer customerWithMostOrder() {
        return orderList
                .stream()
                .collect(Collectors.groupingBy(Order::getCustomer))
                .entrySet().stream()
                .max(Comparator.comparing(entry -> entry.getValue().size()))
                .map(entry -> entry.getKey())
                .orElse(null);
    }


    // o1,    o2,    o3,     o4, ...
    // <c1>   <c1>   <c1>    <c2>
    // TOV    TOV    TOV     TOV
    public Customer customerWithHighestLifetimeValueBloated() {
        return orderList
                .stream()
                .collect(Collectors.groupingBy(Order::getCustomer))
                .entrySet()
                .stream()
                .map(entry ->
                        new AbstractMap.SimpleEntry<Customer, Double>(
                                entry.getKey(),
                                entry.getValue()
                                        .stream()
                                        .mapToDouble(Order::totalOrderValue)
                                        .sum()
                        )
                )
                .max(Comparator.comparing(e -> e.getValue()))
                .map(e -> e.getKey())
                .orElse(null);
    }

    public Customer customerWithHighestLifetimeValue() {
        return orderList
                .stream()
                .collect(Collectors.toMap(
                                order -> order.getCustomer(),
                                order -> order.totalOrderValue(),
                                (order1TotalValue, order2TotalValue) -> order1TotalValue + order2TotalValue
                        )
                )
//                .collect(Collectors.toMap(Order::getCustomer, Order::totalOrderValue, Float::sum))
                .entrySet().stream()
                .max(Comparator.comparing(e -> e.getValue().doubleValue()))
//                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

//    public Product productOrderedByMostCustomersUgly() {
//        return orderList
//                .stream()
//                .collect(Collectors.toMap(
//                        (order) -> order.getProducts().keySet(),
//                        Order::getCustomer
//                ))
//                .entrySet().stream()
//                .reduce(
//                        new HashMap<Product, List<Customer>>(),
//                        (productToCustomersMap, entry) -> {
//                            Set<Product> products = entry.getKey();
//                            Customer customer = entry.getValue();
//
//                            products.stream().forEach(product -> {
//                                if(!productToCustomersMap.containsKey(product)) productToCustomersMap.put(product, new ArrayList<>());
//                                productToCustomersMap.get(product).add(customer);
//                            });
//
//                            return productToCustomersMap;
//                        },
//                        (hmap1, hmap2) -> {
//                            hmap1.putAll(hmap2);
//                            return hmap1;
//                        }
//                )
//                .entrySet().stream()
//                .max(Comparator.comparingInt((entry) -> entry.getValue().size()))
//                .map(entry -> entry.getKey())
//                .orElse(null);
//    }
//    public Product productOrderedByMostCustomers() {
//        class Tupel<X, Y> {
//            public X x;
//            public Y y;
//            public Tupel(X x, Y y) {
//                this.x = x;
//                this.y = y;
//            }
//
//            public X getX() {return x;}
//            public Y getY() {return y;}
//        }
//        return orderList
//                .stream()
//                .flatMap(order -> order.getProducts().keySet().stream().map( product -> new Tupel<Product, Order>(product, order)))
//                .collect(Collectors.groupingBy((t) -> t.getX(), (t) -> t))
//    }
//
//    public Product productOrderedByMostCustomers() {
//        return orderList
//                .stream()
//                .flatMap(order -> order
//                        .getProducts()
//                        .keySet().stream()
//                        .collect(Collectors.toMap(product -> product, (product) -> order.getCustomer()))
//                        .entrySet()
//                        .stream()
//                )
//                .collect(Collectors.groupingBy((entry) -> entry.getKey())
////                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue(), ))
//
//                .
////                .entrySet().stream()
////                .max(Comparator.comparingInt((productWithCustomerInfo) -> productWithCustomerInfo.getValue().size()))
////                .map(e -> e.getValue())
////                .orElse(null);
//    }

    //  Day 35 Stream weiter

    // Was ist der durschnittliche Wert einer Bestellung in einem gegebnen Zeitraum ?
    // Was ist der gesamte Shopumsatz in einem gegebene Zeitraum ?
    // Wieviel % der Kunden haben mehrmals gekauf "returning customer rate"
    // Durschnittliche Anzahl von Produkten pro Bestellung
    // Produkt dass am Meisten von unterschiedlichen Kunden bestellt wurde...

    // Umsatzstärkster Tag...
}