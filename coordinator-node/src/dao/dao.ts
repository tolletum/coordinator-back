import Knex from 'knex';

const knex = Knex({
  client: 'pg',
  connection: {
    host: '127.0.0.1',
    port: 5432,
    user: 'postgres',
    password: '123456',
    database: 'postgres',
  },
});

const connect = (): void => {
  knex
    .raw('select 1+1 as result')
    .then(() => {
      console.log('Conexión a BBDD Ok!');
    })
    .catch(error => {
      console.log(`Error al conectar con base de datos: ${error}`);
    });
};

export { connect, knex };
