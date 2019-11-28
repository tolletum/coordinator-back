import express from 'express';
import bodyParser from 'body-parser';
import methodOverride from 'method-override';
import cors from 'cors';
import { routes } from './routes';
import { config } from './config/config';
import { connect } from './dao/dao';

const server = express();

server.use(bodyParser.urlencoded({ extended: false }));
server.use(bodyParser.json());
server.use(methodOverride());
server.use(cors());

var router = express.Router();

routes(router);

server.use(router);

server.set('name', config.serverName);

// Validamos conexion con bbdd

try {
  connect();
  server.listen(config.serverPort, () => {
    console.log(`Server ${server.get('name')} running on port ${config.serverPort}`);
  });
} catch (error) {
  console.log(error);
}
