# CRUD приложение предоставляющее REST api

Запустите CustomerService локально
Конфигурация базы данных:
    Имя базы данных: usersDemo
    Пользователь: root
    Пароль:
При необходимости изменения внести в application.properties

Получите доступ к сервису, используя следующие REST-запросы:

Получить данные о пользователе
    GET: http://localhost:8080/api/users/{id} - где {id} номер в базе данныъ 
Добавить данные о польхователе
    POST: http://localhost:8080/api/users/ Формат данныйх в формате JSON: {"email":"e@mail.ru", "name":"name"}
Изменить данные пользователя
    POST: http://localhost:8080/api/users/{id} Где {id} номер в базе данных, тело запроса в вормате JSON: {"email":"e@mail.ru", "name":"name"}
Удалить пользователя
    DELETE: http://localhost:8080/api/users/{id} - удалить клиента с идентификатором {id}
