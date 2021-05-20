import React, { Component } from 'react'
import moment from 'moment'
import * as Icon from 'react-bootstrap-icons'
import { Container, Row, Col } from 'react-grid-system'
import ScrollArea from 'react-scrollbar'


import DocumentTables from './DocumentTables.js'

class CardContract extends Component {

  

  constructor(props) {

    super(props)
    this.state = {
     
    }

  }

  render() {
  
    return (
      <ScrollArea
        speed={0.8}
        className="area"
        contentClassName="content"
        horizontal={false}
      >
        <div>
          <Container>
          <center>
          <div style={{  height: "40px"}}>
            <h2>
           Contract № {this.props.currentContract.contract_number}
           </h2>
          </div>
          <div>
           {"Duration from "+ moment(this.props.currentContract.data_start).format('DD-MM-YYYY') +" till " + moment(this.props.currentContract.data_end).format('DD-MM-YYYY')}
          </div>
          <div> 
            {"Terms of payment " + this.props.currentContract.terms_of_payment + "  rate "+ this.props.currentContract.rate}
          </div>              
          </center>
          <div style={{  margin: "10px 10px 60px 20px"}}>
          <Row>
                        <Col xs={7} md={11}>
                          {"Customer " + this.props.currentContract.terms_of_payment} 
                        </Col>
                        <Col xs={1}>
                          {"City "  + this.props.currentContract.city.name} 
                        </Col>
            </Row>
            </div>
            <Row>
              <Col xs={8} md={6}>
                <div>
                  <ul>{this.props.acts.map((act) =>

                    <li key={act.id}>
                      <Row>
                        <Col xs={7} md={11}>
                        <div style={{  height: "40px"}}>
                          Act №{act.act_number} from {moment(act.data).format('YYYY-MM-DD')}  comments:{act.comment}
                         </div>
                        </Col>
                        <Col xs={1}>
                          {act.paid && <Icon.Check2All size="30px" color="red" />}
                        </Col>
                      </Row>
                      <DocumentTables documents_table={this.props.acts_table.filter(item => item.id_act === act.id)} />
                    </li>)}</ul>
                </div>
              </Col>


              <Col xs={8} md={6}>
                <div>
                  <ul>{this.props.invoices.map((invoice) =>
                    <li key={invoice.id}>
                      <Row>
                        <Col xs={7} md={11}>
                        <div style={{  height: "40px"}}>
                          Invoice №{invoice.invoice_number} from {moment(invoice.date).format('YYYY-MM-DD')}  comments:{invoice.comment}
                        </div>  
                        </Col>
                        <Col xs={1}>
                          {invoice.paid && <Icon.Check2All size="30px" color="red" />}
                        </Col>
                      </Row>
                      <DocumentTables documents_table={this.props.invoices_table.filter(item => item.id_invoice === invoice.id)} />
                    </li>)}</ul>
                </div>
              </Col>

            </Row>
          </Container>

        </div>
      </ScrollArea>
    )
  }
}
export default CardContract