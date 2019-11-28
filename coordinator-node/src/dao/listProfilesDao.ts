import { knex } from './dao';

const listProfilesDao = async () => {
  const query = knex
    .select()
    .from('coordinator.profile')
    .then(rows => {
      console.log(JSON.stringify(rows));
      return rows;
    })
    .catch(error => {
      console.log(`ERROR al recuperar la lista de profiles: ${error}`);
      throw error;
    });

  return query;
};

export { listProfilesDao };
