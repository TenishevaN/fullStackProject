
import React, {Component}  from 'react'
import ContractDataService from '../../api/todo/ContractDataService.js'
import ActDataService from  '../../api/todo/ActDataService.js'
import InvoiceDataService from  '../../api/todo/InvoiceDataService.js'
import AuthenticationService from './AuthenticationService.js'
import moment from 'moment'
import CardContract from './CardContract.js'
import ReactModal from 'react-modal'
import * as Icon from 'react-bootstrap-icons'


class ListContractsComponent extends Component {

    constructor(props){
        super(props)

        this.state = {
            contracts: [],
            currentContract: [],
            currentCustomer: [],
            acts: [],
            acts_table: [],
            invoices: [],
            invoices_table: [],
            showCardContract: false,
            message: null
        }
        this.addContractClicked = this.addContractClicked.bind(this)
        this.openContractClicked = this.openContractClicked.bind(this)
        this.upDateContractClicked = this.upDateContractClicked.bind(this)
        this.deleteContractClicked = this.deleteContractClicked.bind(this)
        this.refreshContracts = this.refreshContracts.bind(this)
    }

    componentDidMount(){
        this.refreshContracts()     
    }

    refreshContracts(){

        let username = AuthenticationService.getLoggedInUserName()
       
        ContractDataService.retrieveAllContracts(username)
        .then(
            response => {
               this.setState({contracts : response.data})
            }
         )

    }

    deleteContractClicked(id){
        let username = AuthenticationService.getLoggedInUserName()
        ContractDataService.deleteContract(username, id)
        .then(
            response => {
                this.setState({message : `Deleted successfully`})
                this.refreshContracts()     
              }
            
                 
        )
    }

    addContractClicked(){
        this.props.history.push(`/contracts/-1`) 
    }

    openContractClicked(id, contract) {
         
        this.setState({ currentContract: contract }) 
        this.setState({ currentCustomer: contract.customer }) 
        let username = AuthenticationService.getLoggedInUserName()
        ContractDataService.retrieveAllContractActs(username, id)
            .then(
                response => {
                    this.setState({ acts: response.data })
                  
                }
            )

        ActDataService.retrieveAllContractActsTable(id)
            .then(
                response => {
                    this.setState({ acts_table: response.data })
                   
                }
            )

            InvoiceDataService.retrieveAllContractInvoices(username, id)
            .then(
                response => {
                    this.setState({ invoices: response.data })
                  
                }
            )

         InvoiceDataService.retrieveAllContractInvoicesTable(id)
            .then(
                response => {
                    this.setState({ invoices_table: response.data })
                   
                }
            )    

        this.setState({ showCardContract: true })
    }

    upDateContractClicked(id){
        this.props.history.push(`/contracts/${id}`) 
    }

    render(){
      return (
           <div>
                <ReactModal 
               isOpen={this.state.showCardContract}
               ariaHideApp={false}
               >
                   <CardContract currentContract={this.state.currentContract}  currentCustomer={this.state.currentCustomer} acts ={this.state.acts}  acts_table={this.state.acts_table} invoices ={this.state.invoices} invoices_table={this.state.invoices_table}/>
                   <button onClick={() => { this.setState({ showCardContract: false }) }}>Ok</button>
               </ReactModal>
              <h1>Contracts</h1>
              {this.state.message && <div className="alert alert-success">{this.state.message}</div>}
              <div className="container">
              <div style={{ display: "flex", justifyContent: "flex-start" }}>
                      <button className="button-icon" id="add-button"><Icon.PlusCircleDotted size="30px" onClick={() => this.addContractClicked()} /></button>
               </div> 
              <table className="table">
                  <thead>
                      <tr>
                          <th>number</th>
                          <th>duration</th>
                          <th>customer</th>
                          <th>city</th>
                          <th>payment terms</th>
                          <th>rate</th>
                          <th>open</th>
                          <th>update</th>
                          <th>delete</th>
                      </tr>
                  </thead>
                  <tbody>
                      {
                          this.state.contracts.map(
                              contract =>
                                      <tr key={contract.id}> 
                                      <td>{contract.contract_number}</td>
                                      <td>{moment(contract.data_start).format('YYYY-MM-DD') +" till " + moment(contract.data_end).format('YYYY-MM-DD')}</td>
                                      <td>{contract.customer.name}</td>
                                      <td>{contract.city.name}</td>
                                      <td>{contract.terms_of_payment}</td>
                                      <td>{contract.rate}</td>
                                      <td><button className="btn btn-success" onClick={() => this.openContractClicked(contract.id, contract)}>Open</button></td>
                                      <td><button className="btn btn-success" onClick={() => this.upDateContractClicked(contract.id)}>Update</button></td>
                                      <td><button className="btn btn-warning" onClick={() => this.deleteContractClicked(contract.id)}>Delete</button></td>
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

export default ListContractsComponent