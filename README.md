# Процедура запуска тестов.

## Для запуска авто-тестов необходимо установить следующие программы:

1. IDEA;
2. Google Chrome;
3. Docker

После установки вышеуказанного ПО в терминале из каталога склонированного репозитория
необходимо (для запуска приложения на базе данных MySQL):
* выполнить `docker compose up`;
* дождаться полного запуска/выполнения команды (загрузки и запуска контейнеров и запуска сервисов);
* выполнить команду `java -jar .\artifacts\aqa-shop.jar -P:jdbc.url=mysql://localhost:3306/app -P:jdbc.user=app -P:jdbc.password=pass`;
* дождаться полного запуска/выполнения команды;
* запустить тесты в файлах `OrderTest.java` и `CreditTest.java` для тестирования (либо выполнить команду в терминале `.\gredlew test`);


Для запуска приложения на базе данных PostgreSQL необходимо:
* в файле `SqlQuery.java` в строке 36 заменить "jdbc:mysql://localhost:3306/app" на "jdbc:postgresql://localhost:5432/app";
* выполнить команду в терминале `docker compose up`;
* дождаться полного запуска/выполнения команды (загрузки и запуска контейнеров и запуска сервисов);
* выполнить команду `java -jar .\artifacts\aqa-shop.jar -P:jdbc.url=postgresql://localhost:5432/app -P:jdbc.user=app -P:jdbc.password=pass`;
* дождаться полного запуска/выполнения команды;
* запустить тесты в файлах `OrderTest.java` и `CreditTest.java` для тестирования (либо выполнить команду в терминале `.\gredlew test`);

-P:jdbc.url=jdbc:postgresql://localhost:5432/app -P:jdbc.user=app -P:jdbc.password=pass