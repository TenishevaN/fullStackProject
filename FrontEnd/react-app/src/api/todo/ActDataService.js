import axios from 'axios'
import { JPA_API_URL } from '../../Constants'

class ActDataService {

    retrieveAllActs(name) {
      return axios.get(`${JPA_API_URL}/${name}/acts`);
     
    }


    retrieveAct(name, id) {
        return axios.get(`${JPA_API_URL}/${name}/acts/${id}`);
    }
     
    deleteAct(name, id) {
        return axios.delete(`${JPA_API_URL}/${name}/acts/${id}`);
    }

    updateAct(name, id, contract_id, act) {
      
        return axios.put(`${JPA_API_URL}/${name}/acts/${id}/${contract_id}`, act);
    }

    createAct(name, act) {

       return axios.post(`${JPA_API_URL}/${name}/acts/`, act);
    }

    retrieveActTable(id){
        return axios.get(`${JPA_API_URL}/actTableByActId/${id}`);
    }  
    
    retrieveAllContractActsTable(id){
        return axios.get(`${JPA_API_URL}/actTableByContractId/${id}`);
    }  

   

    createActTable(actTable) {
        return axios.post(`${JPA_API_URL}/createActTable/`, actTable);
     }

     updateActTable(actTable) {
        return axios.put(`${JPA_API_URL}/updateActTable/`, actTable);
     }

     deleteActTable(id) {
        return axios.delete(`${JPA_API_URL}/deleteActTable/${id}`);
    }
       
}

export default new ActDataService()