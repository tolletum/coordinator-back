"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const dao_1 = require("./dao");
const listProfilesDao = async () => {
    const query = dao_1.knex
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
exports.listProfilesDao = listProfilesDao;
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoibGlzdFByb2ZpbGVzRGFvLmpzIiwic291cmNlUm9vdCI6IiIsInNvdXJjZXMiOlsiLi4vLi4vc3JjL2Rhby9saXN0UHJvZmlsZXNEYW8udHMiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7QUFBQSwrQkFBNkI7QUFFN0IsTUFBTSxlQUFlLEdBQUcsS0FBSyxJQUFJLEVBQUU7SUFDakMsTUFBTSxLQUFLLEdBQUcsVUFBSTtTQUNmLE1BQU0sRUFBRTtTQUNSLElBQUksQ0FBQyxxQkFBcUIsQ0FBQztTQUMzQixJQUFJLENBQUMsSUFBSSxDQUFDLEVBQUU7UUFDWCxPQUFPLENBQUMsR0FBRyxDQUFDLElBQUksQ0FBQyxTQUFTLENBQUMsSUFBSSxDQUFDLENBQUMsQ0FBQztRQUNsQyxPQUFPLElBQUksQ0FBQztJQUNkLENBQUMsQ0FBQztTQUNELEtBQUssQ0FBQyxLQUFLLENBQUMsRUFBRTtRQUNiLE9BQU8sQ0FBQyxHQUFHLENBQUMsNENBQTRDLEtBQUssRUFBRSxDQUFDLENBQUM7UUFDakUsTUFBTSxLQUFLLENBQUM7SUFDZCxDQUFDLENBQUMsQ0FBQztJQUVMLE9BQU8sS0FBSyxDQUFDO0FBQ2YsQ0FBQyxDQUFDO0FBRU8sMENBQWUifQ==