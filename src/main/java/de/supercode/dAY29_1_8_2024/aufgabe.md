Superhelden Comparator

Aufgabenstellung
Ziel:
Eine Liste von Superhero-Objekten sortieren, zuerst nach powerLevel, dann nach speed, und schließlich nach name, falls
die vorherigen Attribute gleich sind.

Schritte:
Erstelle eine SuperheroKlasse mit den Feldern name, powerLevel und speed. Erstelle einen Category-ENUM mit den Werten
HERO und VILLAIN. Füge deiner SuperHero Klasse ein Category-Attribut hinzu.
Implementiere drei Comparator Instanzen: eine zum Sortieren nach powerlevel, eine zum Sortieren nach speed, und eine zum
Sortieren nach name.
Erstelle eine Liste von Superhero Objekten und sortiere sie mit deinen Comparatoren. Kombiniere deine Comparatoren auch
untereinander.
Verwenden eine forEach-Schleife und einen Lambda-Ausdruck, um die sortierte Liste der Superhelden auszugeben.

for-each → Lambda Ausdruck

Aufgabenstellung

Nutze für folgende Aufgaben eine forEach mit Lambda-Ausdrücken
Experimentiere mit verschiedenen Listen und Lambda-Ausdrücken, um ein besseres Verständnis zu erlangen
Aufgabe: Zeichenketten-Konkatenation:
Gegeben ist eine Liste von Zeichenketten. Konkateniere alle Zeichenketten in der Liste zu einer einzigen Zeichenkette
und gebe das Ergebnis aus.
Aufgabe: Quadratzahlen:
Gegeben ist eine Liste von ganzen Zahlen. Berechnen Sie das Quadrat jeder Zahl und geben Sie die Quadratzahlen aus
Länge der Zeichenketten:
Gegeben ist eine Liste von Zeichenketten. Gebe die Länge jeder Zeichenkette in der Liste aus.

Event-Management-System - kombinierte Datenstrukturen

✅ Aufgabenstellung
Du sollst ein System erstellen, das Veranstaltungen und die Teilnehmer der Veranstaltungen verwaltet.
Jede Veranstaltung hat folgende Attribute:
Name der Veranstaltung
Datum der Veranstaltung
Altersfreigabe
Teilnehmer (als Liste von PersonObjekten)
Jeder Teilnehmer hat folgende Attribute:
Name des Teilnehmers
Geburtsdatum
Das Herzstück deiner Software: Klasse EventManager
Hat zwei HashMaps als Attribute:
eine HashMap zum Speichern von Veranstaltungen nach Datum → pro Datum gibt es eine oder mehrere Veranstaltungen
eine verschachtelte HashMap zur Verwaltung von Teilnehmern und deren Rolle für eine entsprechende Veranstaltung. Die
verschachtelte HashMap sollte so strukturiert sein:
Äußere HashMap: Schlüssel ist der Name der Veranstaltung, Wert ist eine innere HashMap.
Innere HashMap: Schlüssel ist der Name des Teilnehmers, Wert ist ein Set<Group> von Rollen des Teilnehmers.
Die Rolle soll als ENUM definiert werden: SPEAKER, STARGAST, ZUSCHAUER, VOLUNTEER
Vorteile:
Effizienz bei der Suche: Eine HashMap bietet eine durchschnittliche Zeitkomplexität von O(1) für Suchoperationen (d.h.,
die Suche ist nicht abhängig von der Größe des Suchbereichs - Zeitkomplexität werden wir noch besprechen). Dies ist
besonders vorteilhaft, wenn man viele Veranstaltungen hat und schnell auf Ereignisse für bestimmte Daten zugreifen
müssen.
Direkter Zugriff: Durch die Speicherung von Veranstaltungen nach Datum kann man direkt und effizient alle
Veranstaltungen an einem bestimmten Datum abrufen, ohne jede Veranstaltung auf das Datum überprüfen zu müssen.
Effiziente Verwaltung und Abfrage: Es ist einfacher, spezifische Informationen über Teilnehmer und ihre Gruppen zu
verwalten und abzurufen, als eine tief verschachtelte Struktur in einer einzigen Liste zu haben.
Methoden der EventManager Klasse:
addEvent: Fügt eine Veranstaltung hinzu und speichert sie nach Datum.
addEventTeilnehmer: Fügt einen Teilnehmer einer Veranstaltung hinzu, prüft ob sein Alter für die Veranstaltung passt,
fügt ihm Rollen für die Veranstaltung hinzu.
removeEventTeilnehmer: Entfernt einen Teilnehmer von einer Veranstaltung.
getTeilnehmerByEvent: Gibt die Teilnehmer und deren Rollen für eine Veranstaltung aus.
getEventsByDate: Gibt alle Veranstaltungen an einem bestimmten Datum zurück.
Comparator:
EventDateComparator: Erstelle einen Comparator, der Veranstaltungen nach ihrem Datum sortiert. Die Veranstaltungen
sollen in aufsteigender Reihenfolge des Datums sortiert werden.
Bonus: RoleComparator für die Rollen der Teilnehmer einer Veranstaltung→ wir nehmen an, dass eine Person mehrere Rollen
haben kann und nur die höchste Rolle zählt:
Dafür braucht dein Rolle-Enum einen Rank:
public enum Rolle {
STARGAST(1),      
SPEAKER(2),       
ORGANIZER(3),
ZUSCHAUER(4),
VOLUNTEER(5);

    private final int rank;

    Rolle(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

}