# Minesweeper

# **Back to 1990**

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/adb1bb61-4066-40e1-ae63-984951b2fa6d/a01433c8-db5a-4fcd-bc69-1d1b440ab277/Untitled.png)

### **Ziel:**

Entwickele ein konsolenbasiertes Minesweeper-Spiel in Java. Das Spiel soll es dem Benutzer ermöglichen, die Größe des
Spielfelds und die Anzahl der Minen zu wählen. Das Spiel endet, wenn der Benutzer entweder **alle Minen erfolgreich
markiert** oder eine **Mine aufdeckt**.

### **Anforderungen:**

<aside>
🎯 Mache dir zuerst selber Gedanken zu den Anforderungen. Die Stichpunkte verraten schon viel bezüglich des Designs und wenn du sie aufklappst noch mehr 😉 ❗

Schaue dir dafür die Beispielaufrufe ganz unten in der Aufgabenstellung an.

Je mehr Gedanken du dir am Anfang selber machst, desto besser. Natürlich kannst du, bevor du anfängst zu programmieren,
deine Anforderungen mit denen der Aufgabe vergleichen.

</aside>

- **Spielstart:**
    - Bitte den Benutzer, die Anzahl der Reihen und Spalten des Spielfelds einzugeben.
    - Bitte den Benutzer, die Anzahl der Minen einzugeben. Diese Zahl darf die Anzahl der Felder auf dem Spielfeld nicht
      überschreiten.
- **Spielfeld:**
    - Erstelle ein Spielfeld, das aus Zellen besteht.
    - Jede Zelle kann eine Mine enthalten oder leer sein.
    - Jede Zelle hat einen Zustand: verdeckt, aufgedeckt oder markiert (flagged).
- **Minenplatzierung:**
    - Platziere die Minen zufällig auf dem Spielfeld.
    - Stelle sicher, dass keine zwei Minen an derselben Stelle platziert werden.
- **Berechnung der angrenzenden Minen:**
    - Für jede nicht-minenhaltige Zelle berechne die Anzahl der angrenzenden Minen und speichere diese Zahl in der
      Zelle.
- **Spielsteuerung:**
    - Der Benutzer kann wählen, eine Zelle aufzudecken oder zu markieren.
    - Eingabeformat: `[Aktion] [Reihe] [Spalte]` (Beispiel: `r 2 3` für Aufdecken der Zelle in Reihe 2, Spalte 3 oder
      `f 2 3` für Markieren/Entmarkieren der Zelle in Reihe 2, Spalte 3).
    - Validiere die Benutzereingabe und stelle sicher, dass sie im erlaubten Bereich liegt.
- **Aufdecken einer Zelle:**
    - Wenn der Benutzer eine Mine aufdeckt, endet das Spiel und alle Minen werden aufgedeckt.
    - Wenn der Benutzer eine leere Zelle aufdeckt, wird die Anzahl der angrenzenden Minen angezeigt.
    - Wenn die aufgedeckte Zelle keine angrenzenden Minen hat, werden automatisch alle benachbarten Zellen aufgedeckt.
- **Markieren/Entmarkieren einer Zelle:**
    - Der Benutzer kann eine Zelle als verdächtige Mine markieren oder eine Markierung entfernen.
    - Markierte Zellen können nicht aufgedeckt werden, es sei denn, die Markierung wird entfernt.
- **Spielende:**
    - Das Spiel endet entweder, wenn der Benutzer eine Mine aufdeckt oder alle nicht-minenhaltigen Zellen aufgedeckt
      wurden.
    - Wenn alle nicht-minenhaltigen Zellen aufgedeckt wurden, gewinnt der Benutzer.
- **Anzeige des Spielfelds:**
    - Zeige das Spielfeld in der Konsole an. Verdeckte Zellen werden mit einem Punkt (`.`) dargestellt, markierte Zellen
      mit einem `F` und aufgedeckte Zellen mit der Anzahl der angrenzenden Minen oder einem Leerzeichen für Zellen ohne
      angrenzende Minen.
    - Bei Spielende wird das vollständige Spielfeld angezeigt, wobei Minen mit einem Stern `(*)` dargestellt werden.
- **Benutzerführung:**
    - Zeige nach jedem Zug das aktuelle Spielfeld an und fordere den Benutzer zur nächsten Aktion auf.
    - Gebe am Spielende eine entsprechende Nachricht aus, ob der Benutzer gewonnen oder verloren hat.

**Zusätzliche Hinweise:**

- Strukturiere deinen Code gut und verwende objektorientierte Prinzipien - wie solls auch anders sein 😉
- Verwende Kommentare, um den Code verständlich zu machen.
- Achte auf eine benutzerfreundliche Ein- und Ausgabe.

## **Beispielaufruf:**

- **Spielfeld Initialisierung:** Nehmen wir ein 5x5-Spielfeld mit 5 Minen an

```java
.....
        .....
        .....
        .....
        .....
```

- die Mienen werden zufällig gesetzt
- in diesem Beispiel sind sie an den Positionen: (1, 2), (2, 2), (3, 3) **- natürlich sieht der Benutzer das nicht !**

```java
.*...
        ...*.
        ..*..
        *....
        ....*
```

- **das Berechnetes Spielfeld sieht wie folgt aus - natürlich sieht der Benutzer auch das nicht !**

```java
1*2 1 1
        1 2 3*1
        1 2*2 1
        *2 1 2 1
        1 1 0 1* 
```

- die Zahlen in einem Minesweeper-Spiel geben an, wie viele Minen sich in den benachbarten Zellen einer bestimmten Zelle
  befinden. Jede Zelle hat bis zu acht Nachbarn (oben, unten, links, rechts und diagonal).

1. **Startzustand:**

```
. . . . .
. . . . .
. . . . .
. . . . .
. . . . .
```

1. **Aufdecken (r 0 0):** Aufdecken der Zelle (0, 0)

```
1 . . . .
. . . . .
. . . . .
. . . . .
. . . . .
```

1. **Markieren (f 0 1):** Markieren der Zelle (0, 1) als Mine

```
1 F . . .
. . . . .
. . . . .
. . . . .
. . . . .
```

1. **Aufdecken (r 0 2):** Aufdecken der Zelle (0, 2)

```
1 F 2 . .
. . . . .
. . . . .
. . . . .
. . . . .
```

1. **Aufdecken (r 1 1):** Aufdecken der Zelle (1, 1)

```r
1 F 2 . .
. 2 . . .
. . . . .
. . . . .
. . . . .
```

1. **Aufdecken (r 2 0):** Aufdecken der Zelle (2, 0)

```
1 F 2 . .
. 2 . . .
2 . . . .
. . . . .
. . . . .
```

1. **Markieren (f 2 2):** Markieren der Zelle (2, 2) als Mine

```
1 F 2 . .
. 2 . . .
2 . F . .
. . . . .
. . . . .
```

1. **Aufdecken (r 4 4):** Aufdecken der Zelle (4, 4) (Mine, Spiel endet)

```
1 F 2 . .
. 2 . . .
2 . F . .
* . . . .
. . . . *
```

1. Ausgabe nach Aufdeckung einer Mine:

```
Sie haben eine Mine aufgedeckt! Spiel vorbei.
1 * 2 1 0
1 2 3 * 1
2 3 * 2 1
* 2 2 2 1
1 1 1 2 *
```

### 👾 Warum Minesweeper ? 👾

- **Problemzerlegung und -lösung:**
    - **Algorithmisches Denken:** Minesweeper erfordert die Implementierung spezifischer Algorithmen zur Platzierung von
      Minen, Berechnung der benachbarten Minen und Spielzustandsprüfung.
    - **Schrittweise Verfeinerung:** Man lernt, wie man komplexe Probleme in kleinere, handhabbare Teile zerlegt und
      schrittweise löst.
- **Zustandsmanagement:** Man lernt, wie man den Zustand eines Spiels speichert und verwaltet, was bei der Entwicklung
  vieler Arten von Software wichtig ist.
- **Eingabe von Daten:** Man lernt, wie man Benutzereingaben liest und verarbeitet.
- **Interaktive Anwendungen:** Die Entwicklung eines interaktiven Spiels vermittelt ein Gefühl dafür, wie Benutzer mit
  Software interagieren.
- **Durchhaltevermögen:** Die Entwicklung eines Spiels kann herausfordernd sein, fördert aber Durchhaltevermögen und
  Problemlösungsfähigkeiten.
- **Kreative Lösungsfindung:** Spieleentwicklung erlaubt es, kreativ zu sein und eigene Ideen umzusetzen.
- **Motivation durch Spaß:** Spiele machen Spaß und können die Motivation steigern, weiter zu programmieren und zu
  lernen.