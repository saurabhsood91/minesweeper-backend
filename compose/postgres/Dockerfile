FROM postgres:9.6
ADD sql/000_create_db.sql /docker-entrypoint-initdb.d/01_create_db.sql
ADD sql/001_create_tables.sql /docker-entrypoint-initdb.d/02_init.sql