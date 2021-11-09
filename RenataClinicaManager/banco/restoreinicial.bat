set PGPASSWORD=postgres123
cd banco
pg_restore.exe -c --host localhost --port 5432 --username "postgres" --dbname "bancorenata" --verbose --no-password "backuplimpo.backup"