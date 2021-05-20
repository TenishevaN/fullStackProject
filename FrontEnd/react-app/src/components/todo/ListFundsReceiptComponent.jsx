
import React, {Component}  from 'react'
import FundsReceiptDataService from '../../api/todo/FundsReceiptDataService.js'
import AuthenticationService from './AuthenticationService.js'
import moment from 'moment'
import * as Icon from 'react-bootstrap-icons'
import Glyphicon from '@strongdm/glyphicon'

class ListFundsReceiptsComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            message: null,
            fundsReceipts: []
        }
        this.addFundsReceiptClicked = this.addFundsReceiptClicked.bind(this)
        this.upDateFundsReceiptClicked = this.upDateFundsReceiptClicked.bind(this)
        this.deleteFundsReceiptClicked = this.deleteFundsReceiptClicked.bind(this)
        this.refreshFundsReceipts = this.refreshFundsReceipts.bind(this)
    }

   

    componentDidMount(){
        this.refreshFundsReceipts()     
    }

    refreshFundsReceipts(){

        let username = AuthenticationService.getLoggedInUserName()
       
        FundsReceiptDataService.retrieveAllFundsReceipts(username)
        .then(
            response => {
               this.setState({fundsReceipts : response.data})
            }
         )

    }

    deleteFundsReceiptClicked(id){
        let username = AuthenticationService.getLoggedInUserName()
        FundsReceiptDataService.deleteFundsReceipt(username, id)
        .then(
            response => {
                this.setState({message : `Deleted successfully`})
                this.refreshFundsReceipts()     
              }
        )
    }

    addFundsReceiptClicked(){
        this.props.history.push(`/fundsReceipts/-1`) 
    }

    upDateFundsReceiptClicked(id){
      this.props.history.push(`/fundsReceipts/${id}`) 
    }

   
    render(){
      

      return (
          <div>
                         
              {this.state.message && <div className="alert alert-success">{this.state.message}</div>}
              <div className="container">
              <h1>Funds receipts</h1>
              <div style={{ display: "flex", justifyContent: "flex-start" }}>
                      <button className="button-icon" id="add-button"><Icon.PlusCircleDotted size="30px" onClick={() => this.addFundsReceiptClicked()} /></button>
               </div>   
             
              <table className="table">
                  <thead>
                      <tr>
                          <th>№</th>
                          <th>Date</th>
                          <th>Sum</th>
                          <th>Comment</th>
                          <th>Update</th>
                          <th>Delete</th>
                       
                      </tr>
                  </thead>
                      <tbody>
                          {
                              this.state.fundsReceipts.map(
                                fundsReceipt =>

                                      <tr key={fundsReceipt.id}>
                                          <td>{fundsReceipt.id}</td>
                                          <td>{moment(fundsReceipt.date).format('YYYY-MM-DD')}</td>
                                          <td>{fundsReceipt.sum}</td>
                                          <td>{fundsReceipt.comment}</td>                                      
                                          <td><Glyphicon glyph="pencil" onClick={(e) => { this.upDateFundsReceiptClicked(fundsReceipt.id) }} />  </td>
                                          <td><Glyphicon glyph="trash" onClick={(e) => { this.deleteFundsReceiptClicked(fundsReceipt.id) }} /> </td>
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

   export default ListFundsReceiptsComponent