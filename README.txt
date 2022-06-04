1. Скачать и установить Tomcat
2. Установить PostgreSQL
3. Прописать данные базы в application.properties
4. Создать и подключиться к базе данных и выпольнить скрипты schema.sql и data.sql
5. Выполнить команду mvn clean install
6. Установить кодировку UTF-8
7. Настроить конфигурационный файл Tomcat local:
- Выбрать для deploy war:exploded
- Добавить /cinema в application context
- Добавить -Dfile.encoding=UTF-8 в VM options

Endpoints:
cinema/admin/panel/halls - страница создания зала
cinema/admin/panel/films - страница создания фильма
cinema/admin/panel/sessions - страница создания сеанса на фильм в конкретном зале на конкретную дату