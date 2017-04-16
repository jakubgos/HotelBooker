# HotelBooker
Hotel room booking system on the Android platform using Spring framework.

Done:
-none
Backlog:
1.        [S/A] logowanie użytkownika [oauth2]
2.        [S] wystawienie Api umożlwiającego pobieranie listy hotelów (Hotele na sztywno zapisane w bazie danych, wysyłana jest sama informacja o hotelach)
3.        [A] wyświetlenie hotelów na mapie z pozycją w obrębie X Km
4.        [S/A] możliwość sprecyzowania terminu oraz ilości miejsc w pokoju (do wyszukiwania) (domyślnie system szuka pokoju na najbliższą noc dla 2 osób)
5.        [A] wyświetlenie znalezionych wyników jako listView
6.        [S/A] Wyświetlenie informacji o wybranym pokoju (ilość miejsc, standard, usługi hotelowe, udogodnienia itp)
7.        [S/A] obsługa rezerwacji wybranego pokoju (oczekuje na weryfikację, oczekuje na wpłatę, zarezerwowane)
8.        [S/A] Sprawdzenie swoich rezerwacji
9.        [S/A] rejestracja dla użytkownika
10.        [I] przygotowanie bootStrapa pod interferjs użytkownika
11.        [I] logowanie dla obsługi hotelu (oauth2)
11.        [I] możliwość konfiguracji pokojów, terminów, informacji o pokoju (ilość miejsc, standard, usługi hotelowe, udogodnienia, opis, lokalizacja)
12.        [I] możliwość zmiany stanów zamówień klientów
13.        [I] możliwość edytowania informacji o hotelu (opis/lokalizacja)
14.        [A] uumozliwienie konfiguracji promienia wyszukiwania hotelów
15.        [S/A/I] galeria zdjęć Hotelu
16.        [S/A/I} system ocen/opinii pokoju
17.        [I] wykorzystanie netSocketów do aktualizacji danych o zamówieniach
18.        [S/A] notyfikacja MQTT o zmianie stanu rezerwacji
19.        [S] wysyłanie mailów z potwierdzeniem rezerwacji
20.        [A] filtrowanie list/hoteli (np. tylko tam, gdzie basen, czy tylko pokoje w konkretnym hotelu)
21.        [S/A] możliwość dodania hotelu do ulubionych
22.        [S/A/I] wyszukanie hotelu w danym mieście


S - Serwer, A - Android, I - Interface




Założenia:

Założenia Android:
- logowanie/rejestracja dla użytkownika
- po zalogowaniu użytkownik widzi mapę z zaznaczonymi hotelami na mapie w obrębie X km od swojej aktualnej lokalizacji
- domyślnie system szuka pokoju na najbliższą noc dla 2 osób,
- możliwość podania innego terminu / ilosci osób
- możliwość przejrzenia listy hotelów, jako listView
- po kliknięciu na pin na mapie lub element na liście, użytkownik może otworzyć widok hotelu, gdzie może sprawdzić dostępność pokojów w danym terminie
- na osobnym widoku możliwe jest sprawdzenie statusu swoich rezerwacji (oczekuje na weryfikację, oczekuje na wpłatę, zarezerwowane)
- możliwość anulowania rezerwacji
- wyświetlanie informacji o pokoju (ilość miejsc, standard, usługi hotelowe, udogodnienia itp)

Opcjonalne:
- możliwość oceny hotelu
- opinie o hotelu
- notyfikacja MQTT o zmianie stanu rezerwacji
- galeria zdjęć hotelu
- filtrowanie list/hoteli (np. tylko tam gdzie basen)
- możliwość dodania hotelu do ulubionych
- wyszukanie hotelu w danym miescie

Założenia interface webowy
- Interface webowy, zrealizowany za pomocą jsp, Thymeleaf lub angular 2 (jeszcze nie zdecydowałem)
- interface kierowany do recepcji hotelu,
- jeden hotel = jedno konto = kilka pokojów
- możliwość konfiguracji pokojów, terminów, informacji o pokoju (ilość miejsc, standard, usługi hotelowe, udogodnienia, opis, lokalizacja)
- możliwość zmiany stanów zamówień klientów

Opcjonalne:
- wykorzystanie net socketów
- wysyłanie mailów z potwierdzeniem rezerwacji.

Założenia serwer:
- uwierzytelnianie za pomocą oauth2,
- Realizuje powyższe wymagania
- Spring Framework / Spring MVC
- JPA2 (Hibernate), postgress