
import React, {Component}  from 'react'
import InvoiceDataService from '../../api/todo/InvoiceDataService.js'
import AuthenticationService from './AuthenticationService.js'
import moment from 'moment'
import * as Icon from 'react-bootstrap-icons'
import Glyphicon from '@strongdm/glyphicon'


class ListInvoicesComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            message: null,
            invoices: []
        }
        this.addInvoiceClicked = this.addInvoiceClicked.bind(this)
        this.upDateInvoiceClicked = this.upDateInvoiceClicked.bind(this)
        this.deleteInvoiceClicked = this.deleteInvoiceClicked.bind(this)
        this.refreshInvoices = this.refreshInvoices.bind(this)
        this.printInvoiceClicked = this.printInvoiceClicked.bind(this)
    }

   

    componentDidMount(){
        this.refreshInvoices()     
    }

    refreshInvoices(){

        let username = AuthenticationService.getLoggedInUserName()
        InvoiceDataService.retrieveAllInvoices(username)
        .then(
            response => {
               this.setState({invoices : response.data})
            }
         )

    }

    deleteInvoiceClicked(id){
        let username = AuthenticationService.getLoggedInUserName()
        InvoiceDataService.deleteInvoice(username, id)
        .then(
            response => {
                this.setState({message : `Deleted successfully`})
                this.refreshInvoices()     
              }
        )
    }

    printInvoiceClicked(id){
     
       let username = AuthenticationService.getLoggedInUserName()
       InvoiceDataService.printInvoice(username, id)
       .then(
           response => {
               this.setState({message : `print successful`})
        }
       ) 
       
      
      
    }

    addInvoiceClicked(){
        this.props.history.push(`/invoices/-1`) 
    }

    upDateInvoiceClicked(id){
     
      this.props.history.push(`/invoices/${id}`) 
    }

   
    render(){     

      return (
          <div>
              <h1>Invoices</h1>
             
              {this.state.message && <div className="alert alert-success">{this.state.message}</div>}
              <div className="container">
              <div style={{ display: "flex", justifyContent: "flex-start" }}>
                      <button className="button-icon" id="add-button"><Icon.PlusCircleDotted size="30px" onClick={() => this.addInvoiceClicked()} /></button>
                </div>  
              <table className="table">
                  <thead>
                      <tr>
                          <th>Invoice №</th>
                          <th>Date</th>
                          <th>Customer</th>
                          <th>Contract</th>
                          <th>Total</th>
                          <th>Paid</th>
                          <th>Update</th>
                          <th>Print</th>
                          <th>Delete</th>                     
                      </tr>
                  </thead>
                  <tbody>
                      {
                          this.state.invoices.map(
                              invoice =>                                 
                                  <tr key={invoice.id}> 
                                      <td>{invoice.invoice_number}</td>
                                      <td>{ moment(invoice.date).format('YYYY-MM-DD')}</td> 
                                      <td>{invoice.contract.customer.name}</td>
                                      <td>{invoice.contract.contract_number}</td>
                                      <td>{invoice.sum}</td>                                    
                                      <td>
                                          {invoice.paid && <Icon.Check2All size="30px" color="red" />}
                                          {!invoice.paid && <Icon.Dash size="50px" color="red" />}
                                      </td>                                     
                                      <td><Glyphicon glyph="pencil" onClick={(e) => { this.upDateInvoiceClicked(invoice.id) }}/>  </td>
                                      <td><Glyphicon glyph="print" onClick={(e) => { this.printInvoiceClicked(parseInt(invoice.id, 10)) }} /> </td>
                                      <td><Glyphicon glyph="trash" onClick={(e) => { this.deleteInvoiceClicked(invoice.id) }} /> </td>
                                  </tr>
                          )
                      }

                  </tbody>
              </table>
             
              </div>
          </div>
      )
    }
   }

   export default ListInvoicesComponent