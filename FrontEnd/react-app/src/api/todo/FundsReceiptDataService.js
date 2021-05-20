import axios from 'axios'
import { JPA_API_URL } from '../../Constants'

class FundsReceiptDataService {

    retrieveAllFundsReceipts(name) {
        return axios.get(`${JPA_API_URL}/${name}/fundsReceipts`);
       
      }

    retrieveFundsReceipt(name, id) {
        return axios.get(`${JPA_API_URL}/${name}/fundsReceipts/${id}`);
    }
     
    deleteFundsReceipt(name, id) {
        return axios.delete(`${JPA_API_URL}/${name}/fundsReceipts/${id}`);
    }

    updateFundsReceipt(name, id,  invoice_id, fundsReceipt) {
        return axios.put(`${JPA_API_URL}/${name}/fundsReceipts/${id}/${invoice_id}`, fundsReceipt);
    }

    createFundsReceipt(name, invoice_id, fundsReceipt) {
       return axios.post(`${JPA_API_URL}/${name}/fundsReceipts/${invoice_id}`, fundsReceipt);
    }
      
}

export default new FundsReceiptDataService()