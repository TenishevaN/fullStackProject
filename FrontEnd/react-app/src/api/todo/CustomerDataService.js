import axios from 'axios'
import { JPA_API_URL } from '../../Constants'

class CustomerDataService {

    retrieveAllCustomers(name) {
      return axios.get(`${JPA_API_URL}/${name}/customers`);     
    }

    retrieveCustomer(name, id) {
        return axios.get(`${JPA_API_URL}/${name}/customers/${id}`);
    }

    deleteCustomer(name, id) {
        return axios.delete(`${JPA_API_URL}/${name}/customers/${id}`);
    }

    updateCustomer(name, id, customer) {
        return axios.put(`${JPA_API_URL}/${name}/customers/${id}`, customer);
    }

    createCustomer(name, customer) {
       return axios.post(`${JPA_API_URL}/${name}/customers/`, customer);
    }

}

export default new CustomerDataService()