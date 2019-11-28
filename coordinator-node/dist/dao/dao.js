"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const knex_1 = __importDefault(require("knex"));
const knex = knex_1.default({
    client: 'pg',
    connection: {
        host: '127.0.0.1',
        port: 5432,
        user: 'postgres',
        password: '123456',
        database: 'postgres',
    },
});
exports.knex = knex;
const connect = () => {
    knex
        .raw('select 1+1 as result')
        .then(() => {
        console.log('Conexión a BBDD Ok!');
    })
        .catch(error => {
        console.log(`Error al conectar con base de datos: ${error}`);
    });
};
exports.connect = connect;
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiZGFvLmpzIiwic291cmNlUm9vdCI6IiIsInNvdXJjZXMiOlsiLi4vLi4vc3JjL2Rhby9kYW8udHMiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7QUFBQSxnREFBd0I7QUFFeEIsTUFBTSxJQUFJLEdBQUcsY0FBSSxDQUFDO0lBQ2hCLE1BQU0sRUFBRSxJQUFJO0lBQ1osVUFBVSxFQUFFO1FBQ1YsSUFBSSxFQUFFLFdBQVc7UUFDakIsSUFBSSxFQUFFLElBQUk7UUFDVixJQUFJLEVBQUUsVUFBVTtRQUNoQixRQUFRLEVBQUUsUUFBUTtRQUNsQixRQUFRLEVBQUUsVUFBVTtLQUNyQjtDQUNGLENBQUMsQ0FBQztBQWFlLG9CQUFJO0FBWHRCLE1BQU0sT0FBTyxHQUFHLEdBQVMsRUFBRTtJQUN6QixJQUFJO1NBQ0QsR0FBRyxDQUFDLHNCQUFzQixDQUFDO1NBQzNCLElBQUksQ0FBQyxHQUFHLEVBQUU7UUFDVCxPQUFPLENBQUMsR0FBRyxDQUFDLHFCQUFxQixDQUFDLENBQUM7SUFDckMsQ0FBQyxDQUFDO1NBQ0QsS0FBSyxDQUFDLEtBQUssQ0FBQyxFQUFFO1FBQ2IsT0FBTyxDQUFDLEdBQUcsQ0FBQyx3Q0FBd0MsS0FBSyxFQUFFLENBQUMsQ0FBQztJQUMvRCxDQUFDLENBQUMsQ0FBQztBQUNQLENBQUMsQ0FBQztBQUVPLDBCQUFPIn0=