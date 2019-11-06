# coordinator-back

Application for Coordinator management in Next Technologies - Backend

## Instalación de postgress con docker para local

docker pull postgres
docker run --name pgcoordinator -p 5432:5432 -e POSTGRES_PASSWORD=123456 -d postgres

## Instalación de administrador de postgres con docker

docker pull dpage/pgadmin4
docker run -p 80:80 \
 --name pgadmin4 \
 --link pgcoordinator \
 -e 'PGADMIN_DEFAULT_EMAIL=user@domain.com' \
 -e 'PGADMIN_DEFAULT_PASSWORD=pass' \
 -d dpage/pgadmin4

NOTA: Acordarse de mirar la dirección IP del contenedor con la base de datos (en nuestro caso 'pgcoordinator')
