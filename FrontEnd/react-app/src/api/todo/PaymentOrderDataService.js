import axios from 'axios'
import { JPA_API_URL } from '../../Constants'

class PaymentOrderDataService {

    retrieveAllPaymentOrders(name) {
        return axios.get(`${JPA_API_URL}/${name}/paymentOrders`);
       
      }

    retrievePaymentOrder(name, id) {
        return axios.get(`${JPA_API_URL}/${name}/paymentOrders/${id}`);
    }
     
    deletePaymentOrder(name, id) {
        return axios.delete(`${JPA_API_URL}/${name}/paymentOrders/${id}`);
    }

    updatePaymentOrder(name, id,  paymentOrder) {
      
        return axios.put(`${JPA_API_URL}/${name}/paymentOrders/${id}`, paymentOrder);
    }

    createPaymentOrder(name, paymentOrder) {

       return axios.post(`${JPA_API_URL}/${name}/paymentOrders/`, paymentOrder);
    }
      
}

export default new PaymentOrderDataService()