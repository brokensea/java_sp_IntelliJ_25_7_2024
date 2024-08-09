package de.supercode.dAY29_1_8_2024.eventManagementSystemDatenstrukturen;

import java.time.LocalDate;
import java.util.*;

public class EventManager {
    private Map<LocalDate, Set<Veranstaltung>> veranstaltungsPerTage;
    private Map<String, Map<String, Set<Rolle>>> teilnehmernRollePerVeranstaltung;

    public EventManager() {
        this.veranstaltungsPerTage = new HashMap<>();
        this.teilnehmernRollePerVeranstaltung = new HashMap<>();
    }

    // Fügt eine Veranstaltung hinzu und speichert sie nach Datum
    public void addEvent(Veranstaltung veranstaltung) {
        veranstaltungsPerTage
                .computeIfAbsent(veranstaltung.getDatum(), k -> new HashSet<>())
                .add(veranstaltung);
    }

    public void addEventTeilnehmer(String eventName, Teilnehmer teilnehmer, Rolle rolle) {
        for (Set<Veranstaltung> veranstaltungen : veranstaltungsPerTage.values()) {
            for (Veranstaltung veranstaltung : veranstaltungen) {
                if (veranstaltung.getName().equals(eventName)) {
                    if (teilnehmer.getGeburtsdatum().plusYears(veranstaltung.getAltersfreigabe()).isBefore(LocalDate.now())) {
                        veranstaltung.addTeilnehmer(teilnehmer);
                        teilnehmernRollePerVeranstaltung
                                .computeIfAbsent(eventName, k -> new HashMap<>())
                                .computeIfAbsent(teilnehmer.getName(), k -> new HashSet<>()).add(rolle);
                        teilnehmer.addRolles(rolle);
                    } else {
                        System.out.println("Teilnehmer ist zu jung für diese Veranstaltung.");
                    }
                    return;
                }
            }
        }
        System.out.println("Veranstaltung nicht gefunden.");
    }


    // Entfernt einen Teilnehmer von einer Veranstaltung
    public void removeEventTeilnehmer(String eventName, String teilnehmerName) {
        // Findet die Veranstaltung
        for (Veranstaltung veranstaltung : veranstaltungsPerTage.getOrDefault(LocalDate.now(), Collections.emptySet())) {
            if (veranstaltung.getName().equals(eventName)) {
                // Entfernt den Teilnehmer aus der Veranstaltung
                veranstaltung.getTeilnehmers().remove(teilnehmerName);
                // Entfernt die Rolle des Teilnehmers aus der Rolle-Karte
                teilnehmernRollePerVeranstaltung
                        .getOrDefault(eventName, Collections.emptyMap())
                        .remove(teilnehmerName);
                return;
            }
        }
        System.out.println("Veranstaltung nicht gefunden.");
    }

    // Gibt die Teilnehmer und deren Rollen für eine Veranstaltung aus
    public void getTeilnehmerByEvent(String eventName) {
        Map<String, Set<Rolle>> rolesMap = teilnehmernRollePerVeranstaltung.get(eventName);
        if (rolesMap != null) {
            for (Map.Entry<String, Set<Rolle>> entry : rolesMap.entrySet()) {
                String teinehmerName = entry.getKey();
                Set<Rolle> rolles = entry.getValue();
                System.out.println("Teilnehmer: " + teinehmerName + ", Rollen: " + rolles);
            }
        } else {
            System.out.println("Keine Teilnehmer für diese Veranstaltung.");
        }
    }

    // Gibt alle Veranstaltungen an einem bestimmten Datum zurück
    public Set<Veranstaltung> getEventsByDate(LocalDate date) {
        return veranstaltungsPerTage.getOrDefault(date, Collections.emptySet());
    }


}



/*
if (teilnehmer.getGeburtsdatum().plusYears(veranstaltung.getAltersfreigabe()).isBefore(LocalDate.now())) {
    // 条件满足时执行的操作
}
1. teilnehmer.getGeburtsdatum()
teilnehmer 是一个 Teilnehmer 对象，它表示一个参与者。
getGeburtsdatum() 是 Teilnehmer 类中的一个方法，返回该参与者的出生日期 (LocalDate 类型)。
2. plusYears(veranstaltung.getAltersfreigabe())
veranstaltung 是一个 Veranstaltung 对象，表示一个活动。

getAltersfreigabe() 是 Veranstaltung 类中的一个方法，返回活动的年龄要求（int 类型）。

plusYears(veranstaltung.getAltersfreigabe()):

plusYears 是 LocalDate 类的方法，用于将指定数量的年添加到当前日期。
这里它将活动的年龄要求（以年为单位）添加到参与者的出生日期上。
3. isBefore(LocalDate.now())
LocalDate.now(): 获取当前的日期。

isBefore 是 LocalDate 类的方法，用于检查一个日期是否在另一个日期之前。

isBefore(LocalDate.now()):

这个方法检查通过 plusYears 方法计算出的日期是否在当前日期之前。
4. 整体理解
结合起来，这段代码的作用是检查参与者的年龄是否符合活动的年龄要求：

步骤 1: 从参与者的出生日期中计算出其在活动要求年龄之后的日期。
步骤 2: 检查这个计算出的日期是否在当前日期之前。
如果是，表示参与者在当前日期时已经年满活动要求的年龄。
如果不是，表示参与者尚未达到活动的年龄要求。
示例
假设：

参与者 Alice 的出生日期是 2005年5月15日。
活动 Tech Conference 的年龄要求是 18 年。
计算步骤:

参与者 Alice 的出生日期：2005-05-15。
活动要求的年龄：18 年。
计算出 Alice 应该年满 18 岁的日期：2005-05-15 + 18 年 = 2023-05-15。
当前日期（假设今天是 2024-08-01）。
判断:

2023-05-15 是否在 2024-08-01 之前？
是的，2023-05-15 在 2024-08-01 之前。
所以 if 条件为 true，意味着 Alice 满足年龄要求。

实际应用
这种检查在添加参与者到活动时非常重要，确保参与者的年龄符合活动的年龄限制。例如，如果活动要求参与者必须至少 18 岁，那么在添加参与者时，你可以使用这段代码来验证是否满足要求。*/

/*
teilnehmernRollePerVeranstaltung.computeIfAbsent(eventName, k -> new HashMap<>())
                            .computeIfAbsent(teilnehmer.getName(), k -> new HashSet<>()).add(rolle);

这段代码是用来在一个嵌套的 HashMap 数据结构中更新参与者的角色信息。它使用了 Map 的 computeIfAbsent 方法来简化这个过程。让我们逐步解析每个部分的作用：

代码解析
java
Code kopieren
teilnehmernRollePerVeranstaltung
    .computeIfAbsent(eventName, k -> new HashMap<>())
    .computeIfAbsent(teilnehmer.getName(), k -> new HashSet<>())
    .add(rolle);
1. teilnehmernRollePerVeranstaltung.computeIfAbsent(eventName, k -> new HashMap<>())
teilnehmernRollePerVeranstaltung: 这是一个 Map<String, Map<String, Set<Rolle>>>，它将活动名称 (String) 映射到一个内部 Map。这个内部 Map 的键是参与者的名字 (String)，值是参与者的角色集合 (Set<Rolle>)。

computeIfAbsent(eventName, k -> new HashMap<>()):

eventName: 当前活动的名称，作为外部 Map 的键。
k -> new HashMap<>(): 如果 eventName 对应的值不存在，则使用这个 lambda 表达式创建一个新的 HashMap。这个新的 HashMap 用于存储参与者及其角色集合。
computeIfAbsent: 检查 eventName 是否在 teilnehmernRollePerVeranstaltung 中存在。如果不存在，则插入一个新的 HashMap 作为值，并返回这个新的 HashMap。
2. .computeIfAbsent(teilnehmer.getName(), k -> new HashSet<>())
teilnehmer.getName(): 获取当前参与者的名字，作为内部 Map 的键。

k -> new HashSet<>(): 如果 teilnehmer.getName() 对应的值不存在，则使用这个 lambda 表达式创建一个新的 HashSet。这个新的 HashSet 用于存储该参与者的角色。

computeIfAbsent: 检查 teilnehmer.getName() 是否在内部 Map 中存在。如果不存在，则插入一个新的 HashSet 作为值，并返回这个新的 HashSet。

3. .add(rolle)
rolle: 这是一个 Rolle 枚举值，表示参与者的角色。

add(rolle): 将 rolle 添加到 HashSet 中。这样就更新了参与者的角色集合。

整体理解
这段代码的目的是确保在 teilnehmernRollePerVeranstaltung 这个数据结构中，指定的活动和参与者都存在，并且将指定的角色添加到该参与者的角色集合中。如果某个活动或参与者尚未在数据结构中创建，computeIfAbsent 方法会自动创建相应的结构。

示例
假设我们有以下初始化的 teilnehmernRollePerVeranstaltung：

java
Code kopieren
Map<String, Map<String, Set<Rolle>>> teilnehmernRollePerVeranstaltung = new HashMap<>();
并且我们要为参与者 Alice 添加一个 SPEAKER 角色到活动 "Tech Conference"：

java
Code kopieren
String eventName = "Tech Conference";
Teilnehmer teilnehmer = new Teilnehmer("Alice", LocalDate.of(1995, 6, 10));
Rolle rolle = Rolle.SPEAKER;
执行以下代码：

java
Code kopieren
teilnehmernRollePerVeranstaltung
    .computeIfAbsent(eventName, k -> new HashMap<>())
    .computeIfAbsent(teilnehmer.getName(), k -> new HashSet<>())
    .add(rolle);
操作步骤:

computeIfAbsent(eventName, k -> new HashMap<>()):

检查 eventName ("Tech Conference") 是否存在。如果不存在，则创建一个新的 HashMap 并插入。
computeIfAbsent(teilnehmer.getName(), k -> new HashSet<>()):

在 "Tech Conference" 的 Map 中检查参与者 "Alice" 是否存在。如果不存在，则创建一个新的 HashSet 并插入。
add(rolle):

将 SPEAKER 角色添加到 Alice 的角色集合中。
最终，teilnehmernRollePerVeranstaltung 将包含 "Tech Conference" 作为键，Alice 的角色集合中包含 SPEAKER 角色。*/


