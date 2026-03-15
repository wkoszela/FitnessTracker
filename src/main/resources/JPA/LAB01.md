Laboratorium I - Organizacja | Relacje w JPA

1. Zsynchronizuj swoje repozytorium z repozytorium prowadzącego (Sync Fork bądź Pull z repozytorium prowadzącego). Można
   wykonać to bezpośrednio w GitHubie lub lokalnie.
2. Zapoznaj sie ze strukturą projektu - nazwy pakietów, encje, enumy. Zwróć uwagę na application.properties w resources.
   Zapoznaj sie z ustawieniami aplikacji (przede wszystkim JPA).
3. Uruchom aktualną konfiguracje 'FitnessTracker' bądź kliknij prawym ma FitnessTracker i wybierz opcje RUN.
   Obserwuj logi konsoli, zwlaszcza logi Hibernate'a
4. Po starcie aplikacji (w logach widoczny ostatni wpis "Started FitnessTracker in XXXX seconds (JVM running for
   X.XX)") uruchom przeglądarke i przejdź do adresu http://localhost:8080/h2/ (UWAGA: Link może być nie aktualny, zwróć
   uwagę co jest podane w application.properties) Pojawi sie poniższa strona logowania.
   Wpisz odpowiednie username i password jeśli nie zostało uzupelnione automatycznie (sprawdź ustawienia w pliku
   application.properties - url, login, hasło) i połącz sie z bazą H2 ![](H2_console.png)
5. Po zalogowaniu sie do bazy H2 pojawi sie poniższy interfejs graficzny z listingiem wszystkich tabel (po lewej) oraz
   miejscem na wprowadzania komend sql ![](H2_console_tables.png)
6. W pom.xml zmień nazwę wersji aplikacji na własną przy zachowaniu zasady '1.2.0-NumerIndeksu1-NumerIndeksu2-SNAPSHOT'
7. Wypushuj zmiany do repozytorium.

8. Jeśli zmieniasz nazwy tabel, pamiętaj o wywołaniu komendy "drop all objects" - inaczej Hibernate utworzy na nowo
   tylko znane mu aktualnie tabele, zostawiając poprzednie nietknięte, co może być mocno mylące.
9. Zmień tymczasowo port aplikacji na 8091 (w application.properties) i uruchom aplikację ponownie.
10. Wypushuj zmiany do repozytorium.
11. Zapoznaj się z poniższym schematem relacyjnym bazy danych. Korzystając z wiedzy przekazanej na wykładzie, literatury
    oraz internetu zbuduj tabele HealthMetrcis.

Zgłoś prowadzącemu chęć oddania zadania do oceny.

Ocena 3.0

- Zadania do punktu 7 włącznie

Ocena 5.0

- Zadania do punktu 11 włącznie
