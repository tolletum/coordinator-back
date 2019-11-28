"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const body_parser_1 = __importDefault(require("body-parser"));
const method_override_1 = __importDefault(require("method-override"));
const cors_1 = __importDefault(require("cors"));
const routes_1 = require("./routes");
const config_1 = require("./config/config");
const dao_1 = require("./dao/dao");
const server = express_1.default();
server.use(body_parser_1.default.urlencoded({ extended: false }));
server.use(body_parser_1.default.json());
server.use(method_override_1.default());
server.use(cors_1.default());
var router = express_1.default.Router();
routes_1.routes(router);
server.use(router);
server.set("name", config_1.config.serverName);
try {
    dao_1.connect();
    server.listen(config_1.config.serverPort, () => {
        console.log(`Server ${server.get("name")} running on port ${config_1.config.serverPort}`);
    });
}
catch (error) {
    console.log(error);
}
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoicnVuU2VydmVyLmpzIiwic291cmNlUm9vdCI6IiIsInNvdXJjZXMiOlsiLi4vc3JjL3J1blNlcnZlci50cyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOzs7OztBQUFBLHNEQUE4QjtBQUM5Qiw4REFBcUM7QUFDckMsc0VBQTZDO0FBQzdDLGdEQUF3QjtBQUN4QixxQ0FBa0M7QUFDbEMsNENBQXlDO0FBQ3pDLG1DQUFvQztBQUVwQyxNQUFNLE1BQU0sR0FBRyxpQkFBTyxFQUFFLENBQUM7QUFFekIsTUFBTSxDQUFDLEdBQUcsQ0FBQyxxQkFBVSxDQUFDLFVBQVUsQ0FBQyxFQUFFLFFBQVEsRUFBRSxLQUFLLEVBQUUsQ0FBQyxDQUFDLENBQUM7QUFDdkQsTUFBTSxDQUFDLEdBQUcsQ0FBQyxxQkFBVSxDQUFDLElBQUksRUFBRSxDQUFDLENBQUM7QUFDOUIsTUFBTSxDQUFDLEdBQUcsQ0FBQyx5QkFBYyxFQUFFLENBQUMsQ0FBQztBQUM3QixNQUFNLENBQUMsR0FBRyxDQUFDLGNBQUksRUFBRSxDQUFDLENBQUM7QUFFbkIsSUFBSSxNQUFNLEdBQUcsaUJBQU8sQ0FBQyxNQUFNLEVBQUUsQ0FBQztBQUU5QixlQUFNLENBQUMsTUFBTSxDQUFDLENBQUM7QUFFZixNQUFNLENBQUMsR0FBRyxDQUFDLE1BQU0sQ0FBQyxDQUFDO0FBRW5CLE1BQU0sQ0FBQyxHQUFHLENBQUMsTUFBTSxFQUFFLGVBQU0sQ0FBQyxVQUFVLENBQUMsQ0FBQztBQUl0QyxJQUFJO0lBQ0YsYUFBTyxFQUFFLENBQUM7SUFDVixNQUFNLENBQUMsTUFBTSxDQUFDLGVBQU0sQ0FBQyxVQUFVLEVBQUUsR0FBRyxFQUFFO1FBQ3BDLE9BQU8sQ0FBQyxHQUFHLENBQ1QsVUFBVSxNQUFNLENBQUMsR0FBRyxDQUFDLE1BQU0sQ0FBQyxvQkFBb0IsZUFBTSxDQUFDLFVBQVUsRUFBRSxDQUNwRSxDQUFDO0lBQ0osQ0FBQyxDQUFDLENBQUM7Q0FDSjtBQUFDLE9BQU8sS0FBSyxFQUFFO0lBQ2QsT0FBTyxDQUFDLEdBQUcsQ0FBQyxLQUFLLENBQUMsQ0FBQztDQUNwQiJ9