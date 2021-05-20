import axios from 'axios'
import { JPA_API_URL } from '../../Constants'

class InvoiceDataService {

    retrieveAllInvoices(name) {
      return axios.get(`${JPA_API_URL}/${name}/invoices`);
     
    }


    retrieveInvoice(name, id) {
        return axios.get(`${JPA_API_URL}/${name}/invoices/${id}`);
    }
     
    deleteInvoice(name, id) {
        return axios.delete(`${JPA_API_URL}/${name}/invoices/${id}`);
    }

    updateInvoice(name, id, contract_id, invoice) {
      
        return axios.put(`${JPA_API_URL}/${name}/invoices/${id}/${contract_id}`, invoice);
    }

    createInvoice(name, contract_id, invoice) {
       return axios.post(`${JPA_API_URL}/${name}/invoices/${contract_id}`, invoice);
    }

    retrieveAllContractInvoices(name, id){
        return axios.get(`${JPA_API_URL}/${name}/contractInvoices/${id}`);
    }    

    retrieveInvoiceTable(id){
        return axios.get(`${JPA_API_URL}/invoiceTableByInvoiceId/${id}`);
    }  
    
    retrieveAllContractInvoicesTable(id){
        return axios.get(`${JPA_API_URL}/invoiceTableByContractId/${id}`);
    }     

    createInvoiceTable(invoiceTable) {
        return axios.post(`${JPA_API_URL}/createInvoiceTable`, invoiceTable);
     }

     updateInvoiceTable(invoiceTable) {
        return axios.put(`${JPA_API_URL}/updateInvoiceTable/`, invoiceTable);
     }

     deleteInvoiceTable(id) {
        return axios.delete(`${JPA_API_URL}/deleteInvoiceTable/${id}`);
    }

    printInvoice(name, id) {
        return axios.put(`${JPA_API_URL}/${name}/printInvoice/${id}`);
    }
      
}

export default new InvoiceDataService()