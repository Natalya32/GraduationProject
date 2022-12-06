# Процедура запуска тестов.

## Для запуска авто-тестов необходимо установить следующие программы:

1. IDEA;
2. Google Chrome;
3. Docker

После установки вышеуказанного ПО в терминале из каталога склонированного репозитория
необходимо (для запуска приложения на базе данных MySQL):
* выполнить `docker compose up`;
* дождаться полного запуска/выполнения команды (загрузки и запуска контейнеров и запуска сервисов);
* выполнить команду `java -Durl=jdbc:mysql://localhost:3306/app -Dusername=app -Dpassword=pas -jar .\artifacts\aqa-shop.jar`;
* дождаться полного запуска/выполнения команды;
* запустить тесты выполнив команду в терминале `./gradlew test -Durl=mysql://localhost:3306/app -Dusername=app -Dpassword=pass`);


Для запуска приложения на базе данных PostgreSQL необходимо:
* выполнить команду в терминале `docker compose up`;
* дождаться полного запуска/выполнения команды (загрузки и запуска контейнеров и запуска сервисов);
* выполнить команду `java -Durl=jdbc:postgresql://localhost:5432/app -Dusername=app -Dpassword=pas -jar .\artifacts\aqa-shop.jar`;
* дождаться полного запуска/выполнения команды;
* запустить тесты выполнив команду в терминале `./gradlew test -Durl=postgresql://localhost:5432/app -Dusername=app -Dpassword=pass`);
