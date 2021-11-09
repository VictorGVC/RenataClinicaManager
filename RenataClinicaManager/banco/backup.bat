set PGUSER=postgres
set PGPASSWORD=postgres123
cd banco
pg_dump.exe --host localhost --port 5432 --format custom --blobs --verbose --file "salvo.backup" "bancorenata"

