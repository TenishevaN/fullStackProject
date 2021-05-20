
import React, {Component}  from 'react'
import PaymentOrderDataService from '../../api/todo/PaymentOrderDataService.js'
import AuthenticationService from './AuthenticationService.js'
import moment from 'moment'
import * as Icon from 'react-bootstrap-icons'
import Glyphicon from '@strongdm/glyphicon'

class ListPaymentOrdersComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            message: null,
            paymentOrders: []
        }
        this.addPaymentOrderClicked = this.addPaymentOrderClicked.bind(this)
        this.upDatePaymentOrderClicked = this.upDatePaymentOrderClicked.bind(this)
        this.deletePaymentOrderClicked = this.deletePaymentOrderClicked.bind(this)
        this.refreshPaymentOrders = this.refreshPaymentOrders.bind(this)
    }

   

    componentDidMount(){
        this.refreshPaymentOrders()     
    }

    refreshPaymentOrders(){

        let username = AuthenticationService.getLoggedInUserName()
       
        PaymentOrderDataService.retrieveAllPaymentOrders(username)
        .then(
            response => {
               this.setState({paymentOrders : response.data})
            }
         )

    }

    deletePaymentOrderClicked(id){
        let username = AuthenticationService.getLoggedInUserName()
        PaymentOrderDataService.deletePaymentOrder(username, id)
        .then(
            response => {
                this.setState({message : `Deleted successfully`})
                this.refreshPaymentOrders()     
              }
        )
    }

    addPaymentOrderClicked(){
        this.props.history.push(`/paymentOrders/-1`) 
    }

    upDatePaymentOrderClicked(id){
      this.props.history.push(`/paymentOrders/${id}`) 
    }

   
    render(){
      

      return (
          <div>
                         
              {this.state.message && <div className="alert alert-success">{this.state.message}</div>}
              <div className="container">
              <h1>Payment orders</h1>
              <div style={{ display: "flex", justifyContent: "flex-start" }}>
                      <button className="button-icon" id="add-button"><Icon.PlusCircleDotted size="30px" onClick={() => this.addPaymentOrderClicked()} /></button>
               </div>   
             
              <table className="table">
                  <thead>
                      <tr>
                          <th>Payment order №</th>
                          <th>Date</th>
                          <th>Operation</th>
                          <th>Sum</th>
                          <th>Description</th>
                          <th>Paid</th>
                          <th>Update</th>
                          <th>Delete</th>
                       
                      </tr>
                  </thead>
                      <tbody>
                          {
                              this.state.paymentOrders.map(
                                paymentOrder =>

                                      <tr key={paymentOrder.id}>
                                          <td>{paymentOrder.id}</td>
                                          <td>{moment(paymentOrder.date).format('YYYY-MM-DD')}</td>
                                          <td>{paymentOrder.operation}</td>
                                          <td>{paymentOrder.sum}</td>
                                          <td>{paymentOrder.description}</td>
                                          <td>
                                              {paymentOrder.paid && <Icon.Check2All size="30px" color="red" />}
                                              {!paymentOrder.paid && <Icon.Dash size="50px" color="red" />}
                                          </td>
                                          <td><Glyphicon glyph="pencil" onClick={(e) => { this.upDatePaymentOrderClicked(paymentOrder.id) }} />  </td>
                                          <td><Glyphicon glyph="trash" onClick={(e) => { this.deletePaymentOrderClicked(paymentOrder.id) }} /> </td>
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

   export default ListPaymentOrdersComponent