1. Anforderungsanalyse
   Kernfunktionen

1.1 Todo erstellen
Der Benutzer soll in der Lage sein, eine neue Todo-Aufgabe zu erstellen. Diese Aufgabe soll grundlegende
Informationen
wie eine Beschreibung, eine Priorität und einen Erledigungsstatus enthalten.

1.2. Todos abrufen
Der Benutzer kann alle erstellten Todos einsehen. Außerdem kann er durch Angabe einer spezifischen ID eine bestimmte

1.3. Todo-Aufgabe abrufen.
Der Benutzer soll auch in der Lage sein, nur die offenen (nicht erledigten) Todos abzurufen.

1.4. Todos aktualisieren
Der Benutzer soll eine bestimmte Todo-Aufgabe aktualisieren können, indem er die Beschreibung, Priorität und den
Erledigungsstatus ändert.

1.5. Todos löschen
Der Benutzer soll eine bestimmte Todo-Aufgabe löschen können.

Erweiterte Funktionen

1.1. Todos filtern
Der Benutzer soll die Möglichkeit haben, alle noch offenen Todos abzurufen.
Es soll möglich sein, Todos nach ihrer Priorität zu filtern.

1.2. Paginierung und Sortierung
Der Benutzer soll eine paginierte Liste der Todos abrufen können, die nach Erstellungsdatum, Priorität oder anderen
Feldern sortiert ist.

1.3. Benutzerinteraktion
API-Antwortformat: Die API sollte Daten im JSON-Format zurückgeben, welche die Details der Todos und den
Ergebnisstatus (Erfolg oder Misserfolg) der Operationen enthalten.

2. Datenmodell-Design

Todo Entität
java:
public class Todo {
private Long id; // Einzigartige Kennung für die Todo-Aufgabe
private String beschreibung; // Beschreibung der Aufgabe
private boolean erledigt; // Status, ob die Aufgabe erledigt ist
private int prio; // Priorität (je höher die Zahl, desto wichtiger die Aufgabe)
private LocalDateTime createdAt; // Erstellungszeitpunkt der Aufgabe
private LocalDateTime updatedAt; // Zeitpunkt der letzten Aktualisierung
}

Felder im Detail
id: Eine eindeutige Kennung, die von der Datenbank automatisch generiert wird (in der Regel eine fortlaufende ID).
beschreibung: Ein Textfeld, das den Inhalt der Aufgabe beschreibt.
erledigt: Ein boolescher Wert, der angibt, ob die Aufgabe abgeschlossen ist.
prio: Ein ganzzahliger Wert, der die Wichtigkeit der Aufgabe angibt; höhere Werte bedeuten höhere Priorität.
createdAt und updatedAt: Diese Felder speichern den Zeitpunkt der Erstellung und der letzten Änderung der Aufgabe.

Beziehungen und Einschränkungen
Einzigartigkeit: Das id-Feld muss einzigartig sein, damit jede Todo-Aufgabe eindeutig identifiziert werden kann.
Nicht-Null Einschränkung: Felder wie beschreibung und prio dürfen nicht leer sein.
Standardwerte: Das Feld erledigt hat standardmäßig den Wert false, was bedeutet, dass eine neue Aufgabe als nicht
erledigt markiert wird.
Zeitstempelverwaltung: Die Felder createdAt und updatedAt sollten automatisch generiert und bei jeder Aktualisierung der
Aufgabe entsprechend aktualisiert werden.

3. API-Schnittstellen-Design

3.1 Todo erstellen
HTTP-Methode: POST
URI: /todos
Request-Body: Enthält Felder wie beschreibung, prio und optional erledigt.
Antwort: Das erstellte Todo-Objekt, einschließlich der id.

3.2 Todos abrufen
HTTP-Methode: GET
URI: /todos
Abfrageparameter: erledigt (optional) um nur nicht erledigte Todos anzuzeigen, prio (optional) um nach Priorität zu
filtern.
Antwort: Eine Liste von Todo-Objekten.

3.3 Ein bestimmtes Todo abrufen
HTTP-Methode: GET
URI: /todos/{id}
Antwort: Ein einzelnes Todo-Objekt.

3.4 Todo aktualisieren
HTTP-Methode: PUT
URI: /todos/{id}
Request-Body: Die aktualisierten Felder wie beschreibung, prio, erledigt.
Antwort: Das aktualisierte Todo-Objekt.

3.5
Todo löschen
HTTP-Methode: DELETE
URI: /todos/{id}
Antwort: Eine Nachricht über den Erfolg oder Misserfolg der Operation.

API-Schnittstellenbeispiele

1. Erstellen eines neuen Todos
   POST /todos
   Request Body:
   {
   "beschreibung": "Einkaufen gehen",
   "erledigt": false,
   "prio": 1
   }

Antwort:
{
"id": 1,
"beschreibung": "Einkaufen gehen",
"erledigt": false,
"prio": 1,
"createdAt": "2024-08-20T15:30:00",
"updatedAt": "2024-08-20T15:30:00"
}

2. Alle Todos abrufen
   GET /todos
   Antwort:
   [
   {
   "id": 1,
   "beschreibung": "Einkaufen gehen",
   "erledigt": false,
   "prio": 1,
   "createdAt": "2024-08-20T15:30:00",
   "updatedAt": "2024-08-20T15:30:00"
   },
   {
   "id": 2,
   "beschreibung": "Hausaufgaben machen",
   "erledigt": true,
   "prio": 2,
   "createdAt": "2024-08-19T10:00:00",
   "updatedAt": "2024-08-19T10:30:00"
   }
   ]
3. Offene Todos mit Query-Parametern abrufen
   GET /todos?erledigt=false
   Antwort:
   [
   {
   "id": 1,
   "beschreibung": "Einkaufen gehen",
   "erledigt": false,
   "prio": 1,
   "createdAt": "2024-08-20T15:30:00",
   "updatedAt": "2024-08-20T15:30:00"
   }
   ]

4. Einzelnes Todo abrufen
   GET /todos/1
   Antwort:
   {
   "id": 1,
   "beschreibung": "Einkaufen gehen",
   "erledigt": false,
   "prio": 1,
   "createdAt": "2024-08-20T15:30:00",
   "updatedAt": "2024-08-20T15:30:00"
   }

5. Ein Todo aktualisieren
   PUT /todos/1
   Request Body:
   {
   "beschreibung": "Einkaufen gehen und kochen",
   "erledigt": true,
   "prio": 1
   }
   Antwort:
   {
   "id": 1,
   "beschreibung": "Einkaufen gehen und kochen",
   "erledigt": true,
   "prio": 1,
   "createdAt": "2024-08-20T15:30:00",
   "updatedAt": "2024-08-20T16:00:00"
   }

6. Ein Todo löschen
   DELETE /todos/1
   Antwort:
   {
   "message": "Todo mit ID 1 erfolgreich gelöscht."
   }

