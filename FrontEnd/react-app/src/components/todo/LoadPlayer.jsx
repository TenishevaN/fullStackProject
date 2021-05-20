import moment from 'moment'
import AuthenticationService from './AuthenticationService.js'
import InvoiceDataService from '../../api/todo/InvoiceDataService.js'

 const  loadPlayer = async ({ id }, { signal }) => {
      
    let username = AuthenticationService.getLoggedInUserName()
    let new_invoice = {
       
        invoice_number: this.state.act_number,
        data: moment(new Date()).format('YYYY-MM-DD'),
        paid: false,
        sum: this.state.sum,
        comment: ""
    }

    const res = await fetch(InvoiceDataService.createInvoice(username, this.state.contract_id, new_invoice), { signal })
    if (!res.ok) throw new Error(res.statusText)
    return res.json()
  }

  export default loadPlayer