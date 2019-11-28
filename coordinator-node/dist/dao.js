"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const knex_1 = __importDefault(require("knex"));
const dao = knex_1.default({
    client: "pg",
    connection: {
        host: "127.0.0.1",
        port: 5432,
        user: "postgres",
        password: "123456",
        database: "postgres"
    }
});
const connect = () => {
    dao
        .raw("select 1+1 as result")
        .then(() => {
        console.log("Conexión a BBDD Ok!");
    })
        .catch(error => {
        console.log(`Error al conectar con base de datos: ${error}`);
    });
};
exports.connect = connect;
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiZGFvLmpzIiwic291cmNlUm9vdCI6IiIsInNvdXJjZXMiOlsiLi4vc3JjL2Rhby50cyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOzs7OztBQUFBLGdEQUF3QjtBQUV4QixNQUFNLEdBQUcsR0FBRyxjQUFJLENBQUM7SUFDZixNQUFNLEVBQUUsSUFBSTtJQUNaLFVBQVUsRUFBRTtRQUNWLElBQUksRUFBRSxXQUFXO1FBQ2pCLElBQUksRUFBRSxJQUFJO1FBQ1YsSUFBSSxFQUFFLFVBQVU7UUFDaEIsUUFBUSxFQUFFLFFBQVE7UUFDbEIsUUFBUSxFQUFFLFVBQVU7S0FDckI7Q0FDRixDQUFDLENBQUM7QUFFSCxNQUFNLE9BQU8sR0FBRyxHQUFTLEVBQUU7SUFDekIsR0FBRztTQUNBLEdBQUcsQ0FBQyxzQkFBc0IsQ0FBQztTQUMzQixJQUFJLENBQUMsR0FBRyxFQUFFO1FBQ1QsT0FBTyxDQUFDLEdBQUcsQ0FBQyxxQkFBcUIsQ0FBQyxDQUFDO0lBQ3JDLENBQUMsQ0FBQztTQUNELEtBQUssQ0FBQyxLQUFLLENBQUMsRUFBRTtRQUNiLE9BQU8sQ0FBQyxHQUFHLENBQUMsd0NBQXdDLEtBQUssRUFBRSxDQUFDLENBQUM7SUFDL0QsQ0FBQyxDQUFDLENBQUM7QUFDUCxDQUFDLENBQUM7QUFFTywwQkFBTyJ9