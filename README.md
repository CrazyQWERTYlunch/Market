# Начало проекта

1. Для пуша в ветку необходимо находиться на ветке с етм же именем
2. Для того чтобы запушить изменения с локального репозитория в удалённый 
```bash
git push origin dev
```
3. Изменения проходят через пайплан (сейчас стоит только то, что проект собирается) и если он пройдён можно сделать мердж

### Для того, чтобы поднять локальный сервер нужно:

1. Перед запуском сервера необходимо поменять параметры в файле по пути src/main/resources/database.properties, а именно: jdbc.url, jdbc.username и jdbc.password
2. Запустить Main-файл (или в терминале выполнить команду ```./gradlew bootRun```)
3. Перейти в браузере по ссылке: http://localhost:8080/catalog/ (где вместо "/catalog" вписать нужное)