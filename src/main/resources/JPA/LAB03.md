Laboratorium III - Pierwsze repozytorium JPA i proste zapytania - stan na 19.04.2026 16:15

**Uwaga - Przed przystąpieniem do zadania należy mieć ukończone LAB01 oraz LAB02.**

W LAB02 zajmowaliśmy się **encjami** — czyli klasami Javy, które odpowiadają tabelom
w bazie. Dzisiaj po raz pierwszy dokładamy do tego **repozytorium** — czyli miejsce,
przez które aplikacja **czyta i zapisuje** dane. Plan zajęć (1.5h):

1. Dodanie dwóch nowych encji do modelu (~20 min).
2. Utworzenie pierwszego `JpaRepository` (~20 min).
3. Napisanie dwóch prostych zapytań: jedno w JPQL, jedno w natywnym SQL (~40 min).
4. Commit, push, weryfikacja GitHub Actions (~10 min).

---

## 0. Przygotowanie

1. Zsynchronizuj swój projekt z projektem prowadzącego (Sync Fork / Pull).
2. Zweryfikuj, że projekt buduje się lokalnie (uruchom `clean install` z
   **wbudowanego w IntelliJ widoku Maven** — panel *Maven* po prawej stronie
   IDE → `Lifecycle` → dwuklik na `clean`, potem `install`) oraz na GitHubie
   (zakładka Actions). Pipeline powinien być zielony.
3. Nadpisz workflow i dociągnij nowy test:
    - **Nadpisz** `.github/workflows/github-ci-cd.yml` wersją z
      `src/main/resources/JPA/LAB03/github-ci-cd.yml`. Nowy workflow ma **dwa
      niezależne stage'e testowe** odpowiadające laboratoriom:
        - `lab02` — `DatabaseSchemaTest` (encje z LAB02),
        - `lab03-entities` — `Lab03EntitiesTest` (STAGE 1, encje LAB03).

      Stage'e są niezależne — niepowodzenie jednego **nie przerywa** pozostałych,
      więc na GitHubie widzisz pełny obraz, które laboratorium już jest zielone,
      a które jeszcze wymaga pracy. Pipeline uruchamia się **na wszystkich branchach**.
    - **Przekopiuj** plik testowy `Lab03EntitiesTest.java` z
      `src/main/resources/JPA/LAB03/` do `src/test/java/pl/wsb/fitnesstracker/`.
      Ten test sprawdza, że nowe tabele (`event`, `user_event`, `workout_session`)
      powstały po dodaniu encji z sekcji 1.

---

## 1. Nowe encje w modelu

W LAB02 zrobiliśmy encje: `User`, `Training`, `Statistics`, `HealthMetrics`.
Dzisiaj dokładamy jeszcze **cztery**, żeby mieć co pytać w zapytaniach:

| Encja            | Po co?                                                                             |
|------------------|------------------------------------------------------------------------------------|
| `WorkoutSession` | Log jednego treningu — współrzędne GPS startu/końca i wysokość (`altitude`).       |
| `Event`          | Wydarzenie sportowe (np. maraton) — ma `name`, `startDate`, `location`.            |
| `UserEvent`      | Rejestracja użytkownika na Event — łączy `User` z `Event` + `registrationDate`.    |
| `Achievement`    | Osiągnięcie zdobyte przez użytkownika (np. "Pierwsze 10 km") — `name`, `earnedAt`. |

**Relacje (używamy tylko `@ManyToOne` — jeden nowy typ relacji):**

- `WorkoutSession` → `Training`: `@ManyToOne` (jeden trening może mieć wiele sesji logu,
  np. gdy użytkownik zrobił przerwę i wznowił).
- `UserEvent` → `User`: `@ManyToOne` (jeden użytkownik może mieć wiele rejestracji).
- `UserEvent` → `Event`: `@ManyToOne` (na jeden event zapisuje się wielu użytkowników).
- `Achievement` → `User`: `@ManyToOne` (jeden użytkownik może zdobyć wiele osiągnięć).

> **Wskazówka 1:** klasa `WorkoutSession` już jest w projekcie (`pl.wsb.fitnesstracker.workoutsession`),
> ale to na razie zwykły POJO. Twoim zadaniem jest dodać `@Entity`, poprawne `@Id`
> (`Long` + `@GeneratedValue`) i zamienić pole `trainingId` na relację `@ManyToOne` do
> `Training`. Pole `timestamp` zmień z `String` na `LocalDateTime`.
>
> **Wskazówka 2:** pakiet `pl.wsb.fitnesstracker.achievement` już istnieje, ale jest
> pusty (samo `package-info.java`). Utwórz w nim klasę `Achievement` od zera: `@Entity`,
> `@Id` typu `Long` + `@GeneratedValue`, pola `name` (String), `earnedAt`
> (`LocalDateTime`) oraz relacja `@ManyToOne` do `User`.

Uzupełnij schemat (`db_schema.png`).

![](db_schema.png)

---

## 2. Czym jest `JpaRepository`?

`JpaRepository` to interfejs z biblioteki Spring Data. Wystarczy stworzyć
**własny interfejs**, który po nim dziedziczy, a Spring sam w tle dostarczy
implementację z gotowymi metodami: `save`, `findById`, `findAll`, `deleteById`.

Minimalny przykład — nic nie musimy pisać w środku, żeby mieć podstawowe CRUD:

```java
package pl.wsb.fitnesstracker.event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
```

> `<Event, Long>` oznacza: to repozytorium zarządza encją `Event`, której klucz
> główny (`@Id`) jest typu `Long`.

---

## 3. Pierwsze własne zapytanie — JPQL (`@Query`)

Wyobraź sobie, że na stronie głównej chcemy pokazać **listę nadchodzących eventów**
(czyli tych, których `startDate` jest w przyszłości). W JPQL piszemy zapytanie
**posługując się nazwami klas i pól Javy** (a nie tabel i kolumn bazy).

```java
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.startDate > :now ORDER BY e.startDate")
    List<Event> findUpcoming(@Param("now") LocalDate now);
}
```

Co warto zauważyć:

- `Event` to **nazwa klasy**, nie tabeli.
- `:now` to **parametr** — wartość wstawiamy przez `@Param("now")`, nigdy nie
  sklejamy stringów (SQL injection!).
- Metoda nazywa się dowolnie — w odróżnieniu od "derived queries" (`findByName…`)
  tutaj liczy się treść `@Query`, nie nazwa metody.

**Użycie w teście** (albo tymczasowo w `InitialDataLoader`):

```java
List<Event> events = eventRepository.findUpcoming(LocalDate.now());
System.out.println("Nadchodzące eventy: " + events.size());
```

---

## 4. ZADANIE — Zapytania w natywnym SQL

**Zadanie obowiązkowe na ocenę 5**: napisz **co najmniej jedno** zapytanie w natywnym
SQL (`nativeQuery = true`) w odpowiednim repozytorium. Poniżej znajdziesz listę
propozycji — do wyboru.

Czasem łatwiej (albo wygodniej) napisać zwykłe zapytanie SQL — wtedy dopisujemy
`nativeQuery = true`. **Najważniejsza różnica:** w natywnym SQL używamy **nazw
tabel i kolumn z bazy** (np. `user_event`, `event_id`), a **nie** nazw klas i pól
Javy (np. `UserEvent`, `eventId`).

Porównanie:

| JPQL (`@Query`)              | Natywny SQL (`nativeQuery = true`)         |
|------------------------------|--------------------------------------------|
| `FROM Event e`               | `FROM event` (nazwa tabeli)                |
| `e.startDate`                | `start_date` (nazwa kolumny)               |
| działa na każdej bazie       | może być zależny od dialektu bazy          |

### Przykład prowadzącego (wzorzec do kopiowania)

Ile osób zapisało się na dany event? (`UserEventRepository`)

```java
@Query(
    value = "SELECT COUNT(*) FROM user_event WHERE event_id = :eventId",
    nativeQuery = true
)
long countParticipants(@Param("eventId") Long eventId);
```

### Zapytania do wyboru (wybierz minimum 1)

**1) Użytkownicy o podanej domenie e-mail** (`UserRepository`).

**2) Suma kilometrów przebytych przez danego użytkownika** (`TrainingRepository`).

**3) Nazwy eventów wraz z liczbą zapisanych osób** (`EventRepository`).

### Jak zweryfikować wynik?

Wywołaj wybraną metodę w `InitialDataLoader` albo w prostym teście:

```java
System.out.println("Łącznie treningów: " + trainingRepository.countAllTrainings());
```

---

## 5. Commit & push

1. Upewnij się, że `clean install` z **wbudowanego w IntelliJ widoku Maven**
   (panel *Maven* → `Lifecycle`) przechodzi lokalnie.
2. Scommituj zmiany i wyślij na swoje repo — testy powinny być zielone na GitHub Actions.
3. Zgłoś prowadzącemu wykonanie zadania.

---

## Kryteria akceptacji

**Ocena 5:**
- Nowe encje `WorkoutSession`, `Event`, `UserEvent`, `Achievement` zdefiniowane razem z relacjami `@ManyToOne`.
- `DatabaseSchemaTest` przechodzi lokalnie i na GitHub Actions.
- Dodane **jedno `JpaRepository`** z co najmniej **dwoma zapytaniami `@Query`**:
  - **minimum 1 zapytanie w natywnym SQL** (`nativeQuery = true`) — zgodnie z sekcją 4,
  - oraz 1 zapytanie JPQL (zgodnie z sekcją 3).
- Zielony build na GitHub Actions po wypchnięciu zmian.

**Ocena 4:**
- Poprawnie zdefiniowane 3 z 4 nowych encji wraz z relacjami.
- `JpaRepository` utworzone (może być bez własnego zapytania, sam interfejs).
- Zielony `DatabaseSchemaTest` dla tych encji.

**Ocena 3:**
- Zsynchronizowane repozytorium z repozytorium prowadzącego.
- Poprawnie zdefiniowana 1 nowa encja (kolumny, relacja, nullowalność).
- Zielony build na GitHub Actions.
