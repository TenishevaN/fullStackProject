import axios from 'axios'
import { JPA_API_URL } from '../../Constants'

class ContractDataService {

    retrieveAllContracts(name) {
      return axios.get(`${JPA_API_URL}/${name}/contracts`);
     
    }

    retrieveContract(name, id) {
        return axios.get(`${JPA_API_URL}/${name}/contracts/${id}`);
    }

    deleteContract(name, id) {
                             
        return axios.delete(`${JPA_API_URL}/${name}/contracts/${id}`);
    }

   
    updateContract(name, id, city_id, customer_id, contract) {
       return axios.put(`${JPA_API_URL}/${name}/contracts/${id}/${city_id}/${customer_id}`, contract);
    }

    createContract(name,  city_id, customer_id, contract) {

       return axios.post(`${JPA_API_URL}/${name}/contracts/${city_id}/${customer_id}`, contract);
    }

    retrieveContractModal(name, id) {
        return axios.get(`${JPA_API_URL}/${name}/contractsmodal/${id}`);
    }

    retrieveAllContractActs(name, id){
        return axios.get(`${JPA_API_URL}/${name}/contractActs/${id}`);
    }     
 
}

export default new ContractDataService()