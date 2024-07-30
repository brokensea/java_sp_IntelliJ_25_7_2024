Arbeiten mit der LinkedList in Java

# Aufgabenstellung

- **Erstellen und Initialisieren**:
    - Erstelle eine neue `LinkedList` namens `students`
    - Füge die folgenden Namen der Reihe nach hinzu: "Alice", "Bob", "Charlie", "Diana", "Eve"
- **Elemente hinzufügen**:
    - Fügen Sie an der ersten Position (Index 0) den Namen "Zara" hinzu
    - Fügen Sie an der letzten Position den Namen "Fred" hinzu
- **Elemente abrufen**:
    - Gebe das erste und das letzte Element der Liste aus
    - Gebe das Element an der dritten Position (Index 2) aus
- **Elemente entfernen**:
    - Entferne das erste Element der Liste
    - Entferne das letzte Element der Liste
    - Entferne das Element an der zweiten Position (Index 1)
- **Überprüfen und Suchen**:
    - Überprüfe, ob der Name "Alice" in der Liste enthalten ist
    - Überprüfe, ob der Name "Eve" in der Liste enthalten ist
    - Gib die Position (Index) des Namens "Charlie" in der Liste aus
- **Iterieren und Anzeigen**:
    - Iteriere über alle Elemente der Liste und gebe sie aus
- **Listenoperationen**:
    - Erstelle eine zweite `LinkedList` namens `newStudents` mit den Namen: "George", "Hannah"
    - Fügen Sie alle Elemente von `newStudents` zur Liste `students` hinzu
    - Sortieren Sie die Liste `students` alphabetisch und geben Sie sie aus
- Implementiere eine Methode, die in einer LinkedList das k-te Element von hinten ausgibt. Überprüfe deine Ausgabe,
  indem du in der Main-Methode einen Beispiel-Aufruf erstellst.

Deine eigene LinkedList

# Aufgabenstellung

Diese Aufgabe umfasst das Erstellen einer benutzerdefinierten `LinkedList`-Klasse, das Hinzufügen von Knoten, das
Entfernen von Knoten und das Implementieren grundlegender Listeneigenschaften. Mit dieser Aufgabe lernst du die
grundlegenden Prinzipien der verketteten Listen verstehen und wie man eine solche Datenstruktur von Grund auf neu
erstellt.

- **Node-Klasse:** Repräsentiert einen einzelnen Knoten in der Liste.
    - sollte zwei Attribute haben: `data` (für den Wert des Knotens) und `next` (für den Verweis auf den nächsten
      Knoten)

- **LinkedList-Klasse:**
    - soll die `Node`-Objekte verwalten
    - **Implementiere die `LinkedList`-Methoden wie folgt**
      (Mache dir vor dem Aufklappen erstmal selber Gedanken dazu, welche Methode benötigt werden):
        - **`add(int data)`**: Fügt einen neuen Knoten am Ende der Liste hinzu.
        - **`add(int index, int data)`**: Fügt einen neuen Knoten an der angegebenen Position hinzu.
        - **`remove(int index)`**: Entfernt den Knoten an der angegebenen Position.
        - **`get(int index)`**: Gibt den Wert des Knotens an der angegebenen Position zurück.
        - **`isEmpty()`**: Gibt `true` zurück, wenn die Liste leer ist.
        - **`printList()`**: Gibt die Werte aller Knoten aus.


- **Bonusaufgabe 1:**
    - **`reverse()`**: Implementiere eine Methode, die die Reihenfolge der Knoten in der Liste umkehrt.

- **Bonusaufgabe 2:**

  Implementiere eine Methode, die in einer LinkedList das k-te Element von hinten ausgibt. Überprüfe deine Ausgabe,
  indem du in der Main-Methode einen Beispiel-Aufruf erstellst.

    - [2, 2, 44, 7, 9, 21, 99, 212] —> Output: 21 mit k = 2
    - [1, 2, 3, 4] —> Output: 1 mit k = 3
    - **Achtung:** berücksichtige, dass k nicht größer als *LinkedList.size()* sein kann

Kollabierte Listen-Operationen

# Aufgabenstellung

**Ziel:** Implementiere eine Methode, die mehrere benachbarte gleiche Elemente in einer `LinkedList` zusammenführt (
kollabiert), sodass nur ein Exemplar eines jeden Elements übrig bleibt.

Implementiere eine Methode `collapseDuplicates(LinkedList<Integer> list)`, die alle benachbarten gleichen Elemente in
der Liste entfernt.

Original List: [1, 1, 2, 2, 3, 1, 1]
Collapsed List: [1, 2, 3, 1]

Analyse von LinkedList & ArrayList

# Aufgabenstellung

Schreibe ein Java-Programm, das die Leistung von `LinkedList` und `ArrayList` unter verschiedenen Bedingungen
vergleicht. Das Programm soll verschiedene Operationen durchführen und die Zeit messen, die für jede Operation benötigt
wird, um ein besseres Verständnis für die Leistungseigenschaften dieser beiden Listentypen zu erlangen.

**Die zu messenden Operationen sind:**

1. **Warm-Up**: Füge eine große Anzahl von Elementen zu beiden Listen hinzu, um eine "Warm-Up"-Phase zu simulieren. Dies
   dient dazu, den JIT-Compiler zu aktivieren und sicherzustellen, dass die Messungen nicht durch Initialisierungszeiten
   beeinflusst werden.
2. **Hinzufügen von Elementen**: Füge eine große Anzahl von Elementen (mind. 1000000) zu beiden Listen hinzu und messe
   die Zeit, die für diese Operation benötigt wird.

   **Beispiel:**

    ```java
    // Hinzufügen von Songs
    System.out.println("Hinzufügen von Songs...");
            
    // LinkedList hinzufügen
    startTime = System.currentTimeMillis();
    for (int i = 0; i < numElements; i++) {
    	playlistLinkedList.add("Song " + i);
    }
    endTime = System.currentTimeMillis();
    System.out.println("LinkedList - Hinzufügen von " + numElements + " Songs: " + (endTime - startTime) + " ms");
    ```

   **Führe alle hier genannten Operationen auf beiden Listen durch:**

3. **Hinzufügen eines Elements am Anfang**: Füge ein Element am Anfang der Liste hinzu und messe die Zeit für diese
   Operation.
4. **Entfernen eines Elements aus der Mitte**: Entferne ein Element aus der Mitte der Liste und messe die Zeit für diese
   Operation.
5. **Zugriff auf ein bestimmtes Element**: Greife auf ein Element in der Mitte der Liste zu und messe die Zeit für diese
   Operation.
6. **Iterieren durch die Liste**: Iteriere durch alle Elemente der Liste und messe die Zeit für diese Operation.
7. **Entfernen des ersten Elements**: Entferne das erste Element der Liste und messe die Zeit für diese Operation.