"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const dao_1 = require("./dao");
const insertProfileDao = async (profile) => {
    const query = dao_1.knex
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
exports.insertProfileDao = insertProfileDao;
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiaW5zZXJ0UHJvZmlsZURhby5qcyIsInNvdXJjZVJvb3QiOiIiLCJzb3VyY2VzIjpbIi4uLy4uL3NyYy9kYW8vaW5zZXJ0UHJvZmlsZURhby50cyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOztBQUFBLCtCQUE2QjtBQUU3QixNQUFNLGdCQUFnQixHQUFHLEtBQUssRUFBRSxPQUE4QyxFQUFFLEVBQUU7SUFDaEYsTUFBTSxLQUFLLEdBQUcsVUFBSTtTQUNmLE1BQU0sQ0FBQyxPQUFPLENBQUM7U0FDZixJQUFJLENBQUMscUJBQXFCLENBQUM7U0FDM0IsU0FBUyxDQUFDLEdBQUcsQ0FBQztTQUNkLElBQUksQ0FBQyxPQUFPLENBQUMsRUFBRTtRQUNkLE9BQU8sQ0FBQyxHQUFHLENBQUMsSUFBSSxDQUFDLFNBQVMsQ0FBQyxPQUFPLENBQUMsQ0FBQyxDQUFDO1FBQ3JDLE9BQU8sT0FBTyxDQUFDO0lBQ2pCLENBQUMsQ0FBQztTQUNELEtBQUssQ0FBQyxLQUFLLENBQUMsRUFBRTtRQUNiLE9BQU8sQ0FBQyxHQUFHLENBQUMsaUNBQWlDLEtBQUssRUFBRSxDQUFDLENBQUM7UUFDdEQsTUFBTSxLQUFLLENBQUM7SUFDZCxDQUFDLENBQUMsQ0FBQztJQUVMLE9BQU8sS0FBSyxDQUFDO0FBQ2YsQ0FBQyxDQUFDO0FBRU8sNENBQWdCIn0=