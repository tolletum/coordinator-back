"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const listProfilesDao_1 = require("./dao/listProfilesDao");
const insertProfileDao_1 = require("./dao/insertProfileDao");
const routes = (server) => {
    server.get('/coordinators/profiles/', async (req, res) => {
        const listProfiles = await listProfilesDao_1.listProfilesDao();
        console.log(`Devolvemos la salida: ${JSON.stringify(listProfiles)}`);
        res.send(listProfiles);
    });
    server.post('/coordinators/profiles/', async (req, res) => {
        console.log(JSON.stringify(req.body));
        res.send(insertProfileDao_1.insertProfileDao(req.body));
    });
};
exports.routes = routes;
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoicm91dGVzLmpzIiwic291cmNlUm9vdCI6IiIsInNvdXJjZXMiOlsiLi4vc3JjL3JvdXRlcy50cyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOztBQUNBLDJEQUF3RDtBQUN4RCw2REFBMEQ7QUFFMUQsTUFBTSxNQUFNLEdBQUcsQ0FBQyxNQUFjLEVBQUUsRUFBRTtJQUNoQyxNQUFNLENBQUMsR0FBRyxDQUFDLHlCQUF5QixFQUFFLEtBQUssRUFBRSxHQUFHLEVBQUUsR0FBRyxFQUFFLEVBQUU7UUFDdkQsTUFBTSxZQUFZLEdBQUcsTUFBTSxpQ0FBZSxFQUFFLENBQUM7UUFDN0MsT0FBTyxDQUFDLEdBQUcsQ0FBQyx5QkFBeUIsSUFBSSxDQUFDLFNBQVMsQ0FBQyxZQUFZLENBQUMsRUFBRSxDQUFDLENBQUM7UUFDckUsR0FBRyxDQUFDLElBQUksQ0FBQyxZQUFZLENBQUMsQ0FBQztJQUN6QixDQUFDLENBQUMsQ0FBQztJQUVILE1BQU0sQ0FBQyxJQUFJLENBQUMseUJBQXlCLEVBQUUsS0FBSyxFQUFFLEdBQUcsRUFBRSxHQUFHLEVBQUUsRUFBRTtRQUN4RCxPQUFPLENBQUMsR0FBRyxDQUFDLElBQUksQ0FBQyxTQUFTLENBQUMsR0FBRyxDQUFDLElBQUksQ0FBQyxDQUFDLENBQUM7UUFDdEMsR0FBRyxDQUFDLElBQUksQ0FBQyxtQ0FBZ0IsQ0FBQyxHQUFHLENBQUMsSUFBSSxDQUFDLENBQUMsQ0FBQztJQUN2QyxDQUFDLENBQUMsQ0FBQztBQUNMLENBQUMsQ0FBQztBQUVPLHdCQUFNIn0=