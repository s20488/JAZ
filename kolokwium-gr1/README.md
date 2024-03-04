# JAZ2023_k2_1

## Instrukcja uruchomienia (via IntelliJ IDEA)

### 1. Skonfiguruj bazę danych
**Database -> New -> Data Source -> HSQLDB**
![idea64_dyiuNBQayK](https://github.com/s20488/JAZ/assets/56721899/629a8778-2ddf-481c-b037-a2cb82aaee10)

### 2. Uruchom lokalną bazę danych
Wpisz poniższe polecenie w konsoli w środowisku:

```bash
java -cp "...\hsqldb\lib\hsqldb.jar" org.hsqldb.server.Server --database.0 "file:workdb" --dbname.0 workdb --port 9002
```

### 3. Wyślij żądanie HTTP
Użyj metod GET, POST, PUT i DELETE za pośrednictwem (na przykład) Postman do http://localhost:8080/api/v1/person:

```bash
{
    "id": "1",
    "first_Name": "Adam",
    "last_Name": "Bret"
}
```
![chrome_E4x1jeoRH9](https://github.com/s20488/JAZ/assets/56721899/1df04645-e919-46c0-aeb7-0a600ba6e830)

P.S. Mapa projektu znajduje się w pliku **kolokwium - gr3.pdf** (projekt nie zawiera implementacji updaters)
