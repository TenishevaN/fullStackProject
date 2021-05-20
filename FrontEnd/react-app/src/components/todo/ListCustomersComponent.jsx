
import React, {Component}  from 'react'
import CustomerDataService from '../../api/todo/CustomerDataService.js'
import AuthenticationService from './AuthenticationService.js'
import * as Icon from 'react-bootstrap-icons'
import Glyphicon from '@strongdm/glyphicon'

class ListCustomersComponent extends Component {

    constructor(props){
        super(props)
        this.state = {
            customers: [],
            message: null
        }
        this.addCustomerClicked = this.addCustomerClicked.bind(this)
        this.upDateCustomerClicked = this.upDateCustomerClicked.bind(this)
        this.deleteCustomerClicked = this.deleteCustomerClicked.bind(this)
        this.refreshCustomers = this.refreshCustomers.bind(this)
    }

    componentDidMount(){
        this.refreshCustomers()     
    }

    refreshCustomers(){

        let username = AuthenticationService.getLoggedInUserName()
       
        CustomerDataService.retrieveAllCustomers(username)
        .then(
            response => {
               this.setState({customers : response.data})
            }
         )

    }

    deleteCustomerClicked(id){
        let username = AuthenticationService.getLoggedInUserName()
        CustomerDataService.deleteCustomer(username, id)
        .then(
            response => {
                this.setState({message : `Deleted successfully`})
                this.refreshCustomers()     
              }
        )
    }

    addCustomerClicked(){
        this.props.history.push(`/customers/-1`) 
    }

    upDateCustomerClicked(id){
        this.props.history.push(`/customers/${id}`) 
    }

    render(){
      return (
          <div>
              <h1>List Customers</h1>
              {this.state.message && <div className="alert alert-success">{this.state.message}</div>}
              <div className="container">
              <div style={{ display: "flex", justifyContent: "flex-start" }}>
                      <button className="button-icon" id="add-button"><Icon.PlusCircleDotted size="30px" onClick={() => this.addCustomerClicked()} /></button>
               </div>       
              <table className="table">
                  <thead>
                      <tr>
                          <th>name</th>
                          <th>update</th>
                          <th>delete</th>
                      </tr>
                  </thead>
                  <tbody>
                      {
                          this.state.customers.map(
                              customer =>
                                  <tr key={customer.id}> 
                                      <td>{customer.name}</td>
                                      <td><Glyphicon glyph="pencil" onClick={(e) => { this.upDateCustomerClicked(customer.id) }} />  </td>
                                      <td><Glyphicon glyph="trash" onClick={(e) => { this.deleteCustomerClicked(customer.id) }} /> </td>
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

export default ListCustomersComponent

