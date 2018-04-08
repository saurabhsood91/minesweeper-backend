Service to store and retrieve a list of scores of Minesweeper

### Technologies Used:
- Java 1.8
- Spark Framework
- Postgres
- Docker

### Running with Docker

#### Build
- `docker-compose -f stack.yml build`
- `docker-compose -f stack.yml up -d`

#### Set up the DB
- `docker-compose -f stack.yml run -e PGPASSWORD=example db psql -h db -f /docker-entrypoint-initdb.d/01_create_db.sql -U postgres`
- `docker-compose -f stack.yml run -e PGPASSWORD=example db psql -h db -f /docker-entrypoint-initdb.d/02_init.sql -U postgres minesweeper`