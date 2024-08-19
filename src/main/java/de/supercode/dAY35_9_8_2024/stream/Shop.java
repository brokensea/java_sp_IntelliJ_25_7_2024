package de.supercode.dAY35_9_8_2024.stream;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public double durschnittlicheWertBestellungInEinemZeitraum(Instant startDate, Instant endDate) {
        return orderList.stream()
                .filter(order -> !order.getOrderDate().isBefore(startDate)
                        && !order.getOrderDate().isAfter(endDate))
                // 过滤在指定时间段内的订单
                .mapToDouble(Order::totalOrderValue)
                // Order geschrieben  totalOrderValue return double
                .average()
                .orElse(0.0);

    }

    // Was ist der gesamte Shopumsatz in einem gegebene Zeitraum ?
    public double gesamteShopumsatzInEinemZeitraum(Instant startDate, Instant endDate) {
        return orderList.stream()
                .filter(order -> !order.getOrderDate().isBefore(startDate)
                        && !order.getOrderDate().isAfter(endDate))
                // Bestellungen innerhalb eines bestimmten Zeitraums filtern
//                .map(order -> order.totalOrderValue())
                .mapToDouble(Order::totalOrderValue)
                // map totalOrderValue zu Double
                .sum();
    }

    // Wieviel % der Kunden haben mehrmals gekauf "returning customer rate"
    public double wievielPerzentKundenSindReturningCustomer() {
        // Sammeln alle Kunden
        Set<Customer> allCustomers = orderList.stream()
                .map(Order::getCustomer)
                .collect(Collectors.toSet());
        // Zählen die Anzahl der Einkäufe jedes Kunden
        Map<Customer, Long> customerShopingCount = orderList.stream()
                .map(Order::getCustomer)
                .collect(Collectors
                        .groupingBy(customer -> customer, Collectors.counting()));
        // Zählen die Anzahl der Stammkunden
        long returningCustomer = customerShopingCount
                .values()
                .stream()
                .filter(count -> count > 1)
                .count();
        // die Gesamtzahl der Kunden
        long totalCustomerCount = allCustomers.size();
        // Prozent
        return totalCustomerCount == 0 ? 0.0 : (double) returningCustomer / totalCustomerCount * 100;
    }

    // Durschnittliche Anzahl von Produkten pro Bestellung
    public double durschnittlicheProduktenProBestellung() {
        long anzahl = orderList.stream()
                .mapToLong(order -> order.getProducts().entrySet().size())
                .sum();
        long orderAnzahl = orderList.stream().map(order -> order).count();
        double durchnittliche = (double) anzahl / orderAnzahl;
        return durchnittliche;
    }

    /*public double durchschnittlicheProduktenProBestellung(List<Order> orderList) {
        // 使用一次遍历同时计算总的产品数量和订单总数
        long[] result = orderList.stream()
                .mapToLong(order -> order.getProducts().size())
                .collect(() -> new long[] {0, 0},
                         (a, b) -> { a[0] += b; a[1]++; },
                         (a, b) -> { a[0] += b[0]; a[1] += b[1]; });

        long totalProducts = result[0];
        long totalOrders = result[1];

        // 处理空订单列表的情况
        return totalOrders == 0 ? 0.0 : (double) totalProducts / totalOrders;
    }*/

    // oder
    public double durschnittlicheProduktenProBestellung2() {
        return orderList.stream()
                .mapToInt(order -> order.getProducts().size())
                .average()
                .orElse(0.0);
    }


  /*  public double durschnittlicheProduktenProBestellung() {
        long anzahl = orderList.stream()
                .mapToLong(order -> order.getProducts().entrySet().size())
                .sum();
        long orderAnzahl = orderList.stream().map(order -> order).count();
        double durchnittliche = (double) anzahl / orderAnzahl;
        return durchnittliche;
    }*/

    // Produkt dass am Meisten von unterschiedlichen Kunden bestellt wurde...
   /* public Product produktAmMeistenVonUnterschiedlichenKundenBestellt() {
        return orderList.stream()
                .flatMap(order -> order.getProducts().keySet().stream()
                        .map(product ->
                                new AbstractMap.SimpleEntry<>(product, order.getCustomer())))
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toSet())
                )).entrySet().stream()
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue().size()))
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
    }*/
    /*Idee:
    要解决“找到被最多不同客户订购的产品”的问题，思路可以分为以下几个步骤：
1. 遍历订单列表
遍历所有订单，获取每个订单中的产品以及下单的客户信息。
2. 记录每个产品的独立客户数量
对于每个产品，记录有多少不同的客户下单购买了这个产品。可以通过使用 Map<Product, Set<Customer>> 来实现，其中 Product 是产品，Set<Customer> 用于保存下单购买该产品的所有不同客户。
3. 计算每个产品的独立客户数量
对于每个产品，计算其被多少个不同客户订购过（即 Set<Customer> 的大小）。
4. 找到独立客户数量最多的产品
遍历所有产品，找出那个被最多不同客户订购的产品。
5. 返回结果
返回这个被最多不同客户订购的产品。如果有多个产品的客户数量相同，可以选择其中一个作为结果返回。如果没有产品被订购，返回 null。
代码实现的思路
代码实现则体现了上述思路：

通过 flatMap 展开所有订单中的产品，并同时记录下单客户。
使用 collect 和 groupingBy 对每个产品按客户进行分组，并使用 toSet 来去重，确保每个客户只被计数一次。
再次遍历这个分组结果，计算每个产品的不同客户数量。
使用 max 找到被最多不同客户订购的产品，并返回。
这个方法的核心在于如何有效地统计每个产品的独立客户数量，并从中找出最大值。
     * */

    public Product produktAmMeistenVonUnterschiedlichenKundenBestellt() {
        // 1. 映射每个订单中的产品和相应的客户，形成产品与客户的映射
        Stream<Map.Entry<Product, Customer>> produktKundePaare =
                orderList.stream().flatMap(order -> order
                        .getProducts()
                        .keySet()
                        .stream()
                        .map(product ->
                                new AbstractMap
                                        .SimpleEntry<>(product, order.getCustomer()))
                );
        // 2. 根据产品分组，并将客户放入一个 Set 中（去重）
        Map<Product, Set<Customer>> produktKundenMap =
                produktKundePaare
                        .collect(Collectors
                                .groupingBy(Map.Entry::getKey
                                        , Collectors.mapping(Map.Entry::getValue
                                                , Collectors.toSet())));
        // 3. 计算每个产品被不同客户订购的数量
        Stream<Map.Entry<Product, Integer>> produktKundenAnzahl =
                produktKundenMap.entrySet().stream()
                        .map(entry -> new AbstractMap.SimpleEntry<>(
                                entry.getKey()
                                , entry.getValue().size()));
        // 4. 找到被最多不同客户订购的产品
        return produktKundenAnzahl
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
    }


    // Umsatzstärkster Tag...
    public LocalDate umsatzstaerksterTag() {
        return orderList.stream()
                .collect(Collectors.groupingBy(
                        order -> toLocalDate(order.getOrderDate()),
                        Collectors.summingDouble(Order::totalOrderValue)
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    //  Instant to LocalDate
    private LocalDate toLocalDate(Instant instant) {
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
/*
.map(product ->
                                new AbstractMap
                                        .SimpleEntry<>(product, order.getCustomer()))
使用 new AbstractMap.SimpleEntry<>(product, order.getCustomer()) 是为了创建一个键值对（key-value pair），其中键是产品 product，值是下单的客户 order.getCustomer()。

为什么要用 AbstractMap.SimpleEntry
键值对表示法:

在这段代码中，product 和 order.getCustomer() 需要被成对存储并处理。为了方便将产品与对应的客户配对，使用键值对（Map.Entry）是一种常见的方式。
AbstractMap.SimpleEntry 的作用:

AbstractMap.SimpleEntry<K,V> 是 Java 中 Map.Entry<K,V> 接口的一个简单实现，用来存储一个键和值的配对。这个类很轻量，并且已经包含了 getKey() 和 getValue() 等基本的方法，可以很方便地创建和操作键值对。
为什么使用它:

由于需要在后续的流操作中对产品和客户进行分组，使用 Map.Entry 作为中间形式，可以更容易地将产品和客户关联起来，并在 Collectors.groupingBy() 中按产品对客户进行分组。
AbstractMap.SimpleEntry 提供了一个现成的、简单的方法来创建 Map.Entry 实例，而无需手动创建一个新的类或使用其他复杂的方法。
代码段的具体含义
java
Code kopieren
.map(product ->
    new AbstractMap.SimpleEntry<>(product, order.getCustomer())
)
这里的 .map(product -> ...) 是对每个产品执行的映射操作。具体来说：

product 是流中的一个元素（产品）。
order.getCustomer() 获取当前订单中的客户。
new AbstractMap.SimpleEntry<>(product, order.getCustomer()) 创建了一个 Map.Entry<Product, Customer>，即一个包含产品和客户信息的键值对。

*/
/*
Collectors.mapping()

这个Java代码片段定义了一个名为 mapping 的静态方法，用于创建一个新的 Collector。这个 Collector 会首先将输入元素通过一个映射函数（mapper）转换为另一个类型的元素，然后将转换后的元素收集到下游的 Collector 中。下面逐步解释这个方法的作用和如何理解它。

1. Collector 和 mapping 方法的作用
Collector：Collector 是 Java Stream API 中的一个接口，定义了如何将流中的元素收集成一个结果（比如 List、Set 或 Map）。常见的 Collector 实现有 Collectors.toList()、Collectors.toSet() 等。

mapping 方法：mapping 是一个静态方法，用于将一个接受类型 U 元素的 Collector 适配为一个接受类型 T 元素的 Collector。适配的方式是通过将输入的 T 类型元素先转换为 U 类型，然后将结果传递给原始的 Collector。

2. 方法参数解析
<T, U, A, R>：这是四个泛型参数：

T：输入流中元素的类型。
U：映射后的类型，也就是下游 Collector 接受的元素类型。
A：下游 Collector 的累积类型（通常是中间结果的容器类型）。
R：最终收集结果的类型。
mapper：这是一个 Function<? super T, ? extends U>，用于将类型 T 的元素转换为类型 U 的元素。

downstream：这是一个 Collector<? super U, A, R>，用于处理映射后的 U 类型元素，并最终产生结果 R。

3. 方法实现
BiConsumer<A, ? super U> downstreamAccumulator = downstream.accumulator();：获取下游 Collector 的累积器函数（accumulator），它定义了如何将元素 U 添加到累积器 A 中。

return new CollectorImpl<>(...)：返回一个新的 Collector，该 Collector 将：

使用 downstream.supplier() 作为结果的初始化方法。
使用自定义的累积器 (r, t) -> downstreamAccumulator.accept(r, mapper.apply(t)) 来处理流中的每个元素。它的工作流程是：先将元素 t 转换为 U 类型（通过 mapper.apply(t)），然后将结果传递给下游的累积器。
使用下游的组合器 downstream.combiner() 来合并两个部分结果。
使用下游的终结器 downstream.finisher() 来生成最终结果。
保留下游 Collector 的特性（characteristics）。
4. 理解与例子
这个 mapping 方法最常用于在 groupingBy、partitioningBy 等多级归约操作中。假设我们有一组 Person 对象，想要根据城市收集每个城市中人的姓氏集合，代码可以像示例中的那样使用：

java
Code kopieren
Map<City, Set<String>> lastNamesByCity = people.stream().collect(
    groupingBy(Person::getCity, mapping(Person::getLastName, toSet()))
);
这里：

groupingBy(Person::getCity) 根据城市对人分组。
mapping(Person::getLastName, toSet()) 首先将每个人映射为他们的姓氏，然后收集成一个 Set。
总结
mapping 方法的主要功能是将输入元素通过 mapper 函数转换为另一个类型，然后将这些转换后的元素传递给下游的 Collector 进行收集。这使得我们可以灵活地在流处理过程中进行元素的转换和归约操作。
*/
/*

new AbstractMap.SimpleEntry<> 里SimpleEntry怎么理解
        SimpleEntry 是 Java 中 AbstractMap 的一个静态内部类，专门用于表示键值对（key-value pair）。它实现了 Map.Entry<K, V> 接口，提供了一个简单的、不可变的键值对对象。

Map.Entry<K, V> 接口
在 Java 的 Map 接口中，键值对是通过 Map.Entry<K, V> 接口表示的。Map.Entry 定义了基本的方法，比如 getKey() 和 getValue()，用来访问键和值。

AbstractMap.SimpleEntry<K, V> 类
SimpleEntry<K, V> 是 AbstractMap 类中的一个静态内部类，具体实现了 Map.Entry<K, V> 接口。
它提供了一种简单的方法来存储和操作键值对，而不需要编写单独的实现类。
为什么要使用 SimpleEntry
使用 SimpleEntry 的主要原因是：

简洁：SimpleEntry 是一个现成的实现，能够让你轻松地创建和使用键值对，而不需要自己实现 Map.Entry 接口。
可变性：SimpleEntry 允许在创建之后修改键和值（尽管在流处理的场景中通常不会修改），这使得它更为灵活。
如何理解 new AbstractMap.SimpleEntry<>(...)
当你看到 new AbstractMap.SimpleEntry<>(key, value) 时，这实际上是在创建一个包含键和值的键值对对象。这个键值对可以像普通对象一样传递、存储，并且通过 getKey() 和 getValue() 方法来访问键和值。

示例
假设你在某个地方需要创建一个表示人名和年龄的键值对，可以这样写：

java
Code kopieren
Map.Entry<String, Integer> personEntry = new AbstractMap.SimpleEntry<>("Alice", 30);
在这个例子中：

"Alice" 是键，表示名字。
30 是值，表示年龄。
personEntry 是一个 SimpleEntry 对象，代表这个名字和年龄的配对。
总结
AbstractMap.SimpleEntry<K, V> 提供了一个简单且直接的方式来创建和操作键值对。它常用于需要快速创建键值对对象的场景，特别是在流处理、集合操作或者需要将键值对传递到某个方法时。
*/

/*
怎么判断什么时候能在原来的stream上进行操作得到结果,什么时候必须new 一个新object
        在处理 Java Streams 时，决定是否需要创建新对象（如使用 new 关键字）通常取决于以下几个因素：

是否需要改变数据结构:

如果你需要改变流中的元素的类型或数据结构（例如，将一个 Map.Entry<Product, Set<Customer>> 转换为 Map.Entry<Product, Integer>），通常需要创建一个新对象。
在你的例子中，entry.getValue() 是一个 Set<Customer>，而 entry.getValue().size() 是一个 Integer。为了将这两个值组合在一起，你需要一个新的 Map.Entry<Product, Integer>，因此使用了 new AbstractMap.SimpleEntry<>()。
元素本身是否发生了变化:

如果你只是对元素进行简单的操作，并且不需要改变其类型或结构，比如对某个字段进行计算、过滤或者直接获取值，那么通常可以直接在流上进行操作而不需要创建新对象。
例如，如果你只是想对 entry.getValue().size() 做某些运算并返回一个 Integer，你可以直接操作而不需要创建新的对象。
是否需要将多个值组合到一起:

当你需要将多个值组合成一个新结构时，比如将产品和不同客户的数量组合到一个 Map.Entry<Product, Integer> 中，就需要创建一个新的对象来存储这个组合的结果。
在流处理过程中，如果一个步骤的输出需要和另一个值组合成一个新的对象，通常就需要使用 new 来创建该对象。
判断依据
保持原类型: 如果你在流上操作的结果仍然与原始元素类型一致，你通常可以继续在原来的流上操作，而无需创建新对象。例如：

java
Code kopieren
// 如果你只是需要处理产品而不改变其类型
produktKundenMap.entrySet().stream()
    .map(entry -> entry.getKey().doSomething())
    .collect(Collectors.toList());
类型转换或组合: 如果操作会导致类型的变化或需要将多个值组合成一个新对象，通常就需要创建一个新对象。例如：

java
Code kopieren
produktKundenMap.entrySet().stream()
    .map(entry -> new AbstractMap.SimpleEntry<>(
        entry.getKey(),
        entry.getValue().size()
    ))
    .collect(Collectors.toList());
具体示例
无需创建新对象的情况:

如果你只需要从 entry 中获取键并对它执行一些操作，可以直接返回操作结果，而不需要创建新对象：

java
Code kopieren
produktKundenMap.entrySet().stream()
    .map(entry -> entry.getKey().getSomeProperty())  // 不创建新对象
    .collect(Collectors.toList());
需要创建新对象的情况:

如果你需要将产品和其客户的数量组合成一个新的键值对，那么必须创建一个新对象：

java
Code kopieren
produktKundenMap.entrySet().stream()
    .map(entry -> new AbstractMap.SimpleEntry<>(
        entry.getKey(),  // 产品
        entry.getValue().size()  // 不同客户的数量
    ))
    .collect(Collectors.toList());
总结
保持类型: 不改变元素类型时，不需要 new。
类型转换或组合: 当需要组合多个值或转换类型时，使用 new 创建新对象。
这种思路有助于判断何时可以直接操作流中的元素，何时需要创建新的对象来存储结果。*/



/*
    // 2. 根据产品分组，并将客户放入一个 Set 中（去重）
        Map<Product, Set<Customer>> produktKundenMap =
                produktKundePaare
                        .collect(Collectors
                                .groupingBy(Map.Entry::getKey
                                        , Collectors.mapping(Map.Entry::getValue
                                                , Collectors.toSet())));
        像这样几个 Collectors 嵌套在一起怎么理解,每个Opertration 的 Input 是什么 执行顺序是什么先执行 toSet 还是 gropingBy



在这段代码中，Collectors.groupingBy 和 Collectors.mapping 是嵌套使用的，因此理解它们的执行顺序和每个操作的输入非常重要。我们来详细分析一下这段代码。

代码结构分析
java
Code kopieren
Map<Product, Set<Customer>> produktKundenMap =
    produktKundePaare
        .collect(Collectors.groupingBy(
            Map.Entry::getKey,
            Collectors.mapping(Map.Entry::getValue, Collectors.toSet())
        ));
操作的执行顺序和输入解释
1. groupingBy 的输入和执行
输入：Stream<Map.Entry<Product, Customer>>，这是一个包含Product作为键和Customer作为值的Map.Entry流。
执行：groupingBy首先根据Map.Entry::getKey对流中的元素进行分组，即根据Product进行分组。分组后的结果是一个Map<Product, List<Map.Entry<Product, Customer>>>，但这里还没有完成。
2. mapping 的输入和执行
输入：每个分组后得到的List<Map.Entry<Product, Customer>>。也就是说，对于每个Product键（分组键），mapping处理的是属于该键的所有Map.Entry元素。
执行：mapping(Map.Entry::getValue, Collectors.toSet())表示对每个Map.Entry<Product, Customer>中的Customer值进行映射，并将这些值收集到一个Set<Customer>中。
3. toSet 的输入和执行
输入：每个Customer对象，这些对象是从Map.Entry中通过Map.Entry::getValue提取出来的。
执行：toSet()收集器将所有提取出来的Customer放入一个Set中，实现去重功能。
整体执行顺序
groupingBy：

第一步：groupingBy开始执行，它遍历整个流，并根据Map.Entry的键（Product）进行分组。这一步创建了一个Map<Product, List<Map.Entry<Product, Customer>>>，但这些List会在接下来的步骤中被进一步处理。
mapping：

第二步：对于每一个Product分组（即Map<Product, List<Map.Entry<Product, Customer>>中的每个键值对），mapping操作会提取出每个Map.Entry中的Customer（Map.Entry::getValue），并将这些Customer传递给Collectors.toSet()进行收集。
toSet：

第三步：toSet收集器将每个分组中的Customer对象收集到一个Set中，确保去重。最终结果是Map<Product, Set<Customer>>，每个Product键对应的值是一个去重后的Customer集合。
结论
执行顺序：先执行groupingBy，再执行mapping，最后执行toSet。
每个操作的输入：
groupingBy的输入是整个Stream<Map.Entry<Product, Customer>>。
mapping的输入是List<Map.Entry<Product, Customer>>，这是在分组后得到的值。
toSet的输入是Customer对象，它们是通过mapping从Map.Entry中提取出来的。
这段代码最终生成了一个Map<Product, Set<Customer>>，其中每个Product键对应的是一个去重后的Customer集合。

*/
/*
是的，groupingBy 和 mapping 的输入和输出类型确实是不同的。我们来详细讨论它们的输入和输出类型。

1. groupingBy 的输入和输出类型
groupingBy 的输入类型：
输入：groupingBy的输入是一个Stream<T>，其中T是流中元素的类型。
在你的例子中，Stream<T>的T类型是Map.Entry<Product, Customer>。
groupingBy 的输出类型：
输出：groupingBy的输出是一个Map<K, V>，其中：
K 是分组的键的类型，即通过分类函数（classifier）返回的类型。在这里，K是Product（Map.Entry::getKey的返回类型）。
V 是分组后每个键对应的值的类型，通常是一个集合或列表。在使用简单的groupingBy时，V的默认类型是List<T>，但在结合其他收集器时，V的类型取决于所用的下游收集器。
在你的代码中：

T = Map.Entry<Product, Customer>
K = Product
V = Set<Customer>（由于你在groupingBy中使用了Collectors.mapping，并且将其结果收集到了一个Set中）。
所以，groupingBy的输出是一个Map<Product, Set<Customer>>。

2. mapping 的输入和输出类型
mapping 的输入类型：
输入：mapping的输入是一个Stream<T>，这里的T是分组后的每个组内的元素类型。
在你的例子中，mapping操作在每个分组内部运行，它接收的输入流的元素类型仍然是Map.Entry<Product, Customer>。
mapping 的输出类型：
输出：mapping的输出取决于下游收集器的类型。
在你的例子中，mapping的作用是将Map.Entry<Product, Customer>映射为Customer，然后将这些Customer对象通过下游收集器Collectors.toSet()收集到一个Set中。
所以，mapping的输出是Set<Customer>。

3. 代码中的类型总结
java
Code kopieren
Map<Product, Set<Customer>> produktKundenMap =
    produktKundePaare
        .collect(Collectors.groupingBy(
            Map.Entry::getKey,
            Collectors.mapping(Map.Entry::getValue, Collectors.toSet())
        ));
produktKundePaare的类型：Stream<Map.Entry<Product, Customer>>
groupingBy的输入类型：Stream<Map.Entry<Product, Customer>>
groupingBy的输出类型：Map<Product, Set<Customer>>
mapping的输入类型：Stream<Map.Entry<Product, Customer>>（对每个分组执行）
mapping的输出类型：Set<Customer>
总结
groupingBy 接收一个流（Stream<T>）并返回一个映射（Map<K, V>），其中K是分组键的类型，V是分组后的结果容器的类型。
mapping 接收一个流（Stream<T>）并返回一个新的收集器结果，结果类型取决于下游收集器。在你的例子中，mapping从流中提取Customer并将它们收集为Set<Customer>。
这种组合使用使得你可以灵活地对流进行分组并对每个分组的内容进行进一步处理。
*/
