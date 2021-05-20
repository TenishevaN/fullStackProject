
import React, {Component}  from 'react'
import ActDataService from '../../api/todo/ActDataService.js'
import AuthenticationService from './AuthenticationService.js'
import moment from 'moment'
import * as Icon from 'react-bootstrap-icons'
import Glyphicon from '@strongdm/glyphicon'

class ListActsComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            message: null,
            acts: []
        }
        this.addActClicked = this.addActClicked.bind(this)
        this.upDateActClicked = this.upDateActClicked.bind(this)
        this.deleteActClicked = this.deleteActClicked.bind(this)
        this.refreshActs = this.refreshActs.bind(this)
    }

   

    componentDidMount(){
        this.refreshActs()     
    }

    refreshActs(){

        let username = AuthenticationService.getLoggedInUserName()
       
        ActDataService.retrieveAllActs(username)
        .then(
            response => {
               this.setState({acts : response.data})
            }
         )

    }

    deleteActClicked(id){
        let username = AuthenticationService.getLoggedInUserName()
        ActDataService.deleteAct(username, id)
        .then(
            response => {
                this.setState({message : `Deleted successfully`})
                this.refreshActs()     
              }
        )
    }

    addActClicked(){
        this.props.history.push(`/acts/-1`) 
    }

    upDateActClicked(id){
      this.props.history.push(`/acts/${id}`) 
    }

   
    render(){
      

      return (
          <div>
                         
              {this.state.message && <div className="alert alert-success">{this.state.message}</div>}
              <div className="container">
              <h1>Acts</h1>
              <div style={{ display: "flex", justifyContent: "flex-start" }}>
                      <button className="button-icon" id="add-button"><Icon.PlusCircleDotted size="30px" onClick={() => this.addActClicked()} /></button>
              </div>  
             
              <table className="table">
                  <thead>
                      <tr>
                          <th>Act №</th>
                          <th>Date</th>
                          <th>Customer</th>
                          <th>Contract</th>
                          <th>Total</th>
                          <th>Paid</th>
                          <th>Update</th>
                          <th>Delete</th>
                       
                      </tr>
                  </thead>
                      <tbody>
                          {
                              this.state.acts.map(
                                  act =>

                                      <tr key={act.id}>
                                          <td>{act.act_number}</td>
                                          <td>{moment(act.date).format('YYYY-MM-DD')}</td>
                                          <td>{act.contract.customer.name}</td>
                                          <td>{act.contract.contract_number}</td>
                                          <td>{act.sum}</td>
                                          <td>
                                              {act.paid && <Icon.Check2All size="30px" color="red" />}
                                              {!act.paid && <Icon.Dash size="50px" color="red" />}
                                          </td>
                                          <td><Glyphicon glyph="pencil" onClick={(e) => { this.upDateActClicked(act.id) }} />  </td>
                                          <td><Glyphicon glyph="trash" onClick={(e) => { this.deleteActClicked(act.id) }} /> </td>
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

   export default ListActsComponent