Laboratorium 02 - Relacje w JPA - stan na 27.03.2026

**Uwaga - Przed przystąpieniem do zadania proszę o wykonanie zadania LAB01 (poprzednie zajęcia).**

1. Zsynchronizuj swoje repozytorium z repozytorium prowadzącego (Sync Fork bądź Pull z repozytorium prowadzącego). Można
   wykonać to bezpośrednio w GitHubie lub lokalnie.
2. Dodaj plik [`github-ci-cd.yml`](.github/workflows/github-ci-cd.yml) z katalogu
   `resources/JPA/LAB02/github-ci-cd.yml`(src/main/resources/general/github-ci-cd.yml).
   Plik powinien zostać umieszczony pod `.github/workflows/github-ci-cd.yml`
3. Scommituj zmiany i wyślij je do swojego repozytorium. Zweryfikuj czy projekt poprawnie się buduje na Github (zakładka
   Actions)
4. Przenieś klasę DatabaseSchemaTest.java z folderu resources/JPA/LAB02/DatabaseSchemaTest do katalogu testowego
   `src/test/java/pl/wsb/fitnesstracker`.
5. Scommituj zmiany i wyślij je do swojego repozytorium.
6. Zweryfikuj czy projekt poprawnie się buduje na Github. Test nie powinny poprawnie przechodzić
7. Zapoznaj się z poniższym schematem relacyjnym bazy danych. Korzystając z wiedzy przekazanej na wykładzie, literatury
   oraz internetu, uzupełnij brakujące tabele i relacje w aktualnej definicji encji (zwróć uwagę także na nazwy tabel
   oraz brakujące kolumny).
   Określ nullowalność oraz nazwy kolumn. Ustaw relacje tak, aby przynajmniej raz była wykorzystana relacja
   jednostronna (OneToOne)
   oraz ManyToOne. **Pracujemy nad 4 Encjami User, Trainings, Statistics, HealthMetrics. Zaczynamy od tabeli Training**

   ![](db_schema.png)
8. Do projektu dodaj profilu loadInitialData (application properties). Zweryfikuj czy dane testowe w trakcie startu
   aplikacji poprawnie się ładują do bazy danych.
9. Wprowadzone zmiany prześlij na swoje repozytorium. Testy powinny poprawnie się wykonać lokalnie oraz platformie
   Github.
10. Zgłoś prowadzącemu wykonanie zadanie, celem uzyskania oceny.

11. (*) Opcjonalnie można przygotować plik data.sql z danymi (komendy INSERT) w odpowiedniej kolejności, potwierdzając
    poprawność modelu.
    Przydadzą się na kolejnych zajęciach.

Kryterium Akceptacji na ocenę 5:

- [ ] Poprawnie zdefiniowane 4 encje JPA (tabele, kolumny, relacje, nullowalność)
- [ ] Poprawnie działające testy jednostkowe (DatabaseSchemaTest) - również na Github Actions
- [ ] GitHub Actions z poprawnym buildem projektu po kroku 8.
- [ ] Przesłanie zmian na na swoje repozytorium.

Kryterium Akceptacji na ocenę 4:

- [ ] Poprawnie zdefiniowane 2 encje JPA (tabele, kolumny, relacje, nullowalność)
- [ ] Poprawnie działające testy jednostkowe związane z encjami (DatabaseSchemaTest) - również na Github Actions
- [ ] Przesłanie zmian na na swoje repozytorium.

Kryterium Akceptacji na ocenę 3:

- [ ] Poprawnie zdefiniowana 1 encja JPA (tabele, kolumny, relacje, nullowalność)
- [ ] Zsynchronizowane repozytorium z repozytorium prowadzącego
- [ ] GitHub Actions z poprawnym buildem projektu po kroku 5.
- [ ] Przesłanie zmian na swoje repozytorium.