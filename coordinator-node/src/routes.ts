import { Router } from 'express';
import { listProfilesDao } from './dao/listProfilesDao';
import { insertProfileDao } from './dao/insertProfileDao';

const routes = (server: Router) => {
  server.get('/coordinators/profiles/', async (req, res) => {
    const listProfiles = await listProfilesDao();
    console.log(`Devolvemos la salida: ${JSON.stringify(listProfiles)}`);
    res.send(listProfiles);
  });

  server.post('/coordinators/profiles/', async (req, res) => {
    console.log(JSON.stringify(req.body));
    res.send(insertProfileDao(req.body));
  });
};

export { routes };
