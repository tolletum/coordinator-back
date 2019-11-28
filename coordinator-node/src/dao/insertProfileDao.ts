import { knex } from './dao';

const insertProfileDao = async (profile: { description: String; rate: Number }) => {
  const query = knex
    .insert(profile)
    .into('coordinator.profile')
    .returning('*')
    .then(profile => {
      console.log(JSON.stringify(profile));
      return profile;
    })
    .catch(error => {
      console.log(`ERROR al insertar un profile: ${error}`);
      throw error;
    });

  return query;
};

export { insertProfileDao };
