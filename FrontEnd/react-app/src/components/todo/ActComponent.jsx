import React, { Component } from 'react'
import moment from 'moment'
import { Formik, Form, Field, FieldArray } from 'formik';
import ActDataService from '../../api/todo/ActDataService.js'
import AuthenticationService from './AuthenticationService.js'
import ContractDataService from '../../api/todo/ContractDataService.js'
import Select from 'react-select'
import ScrollArea from 'react-scrollbar'
import { Container, Row, Col } from 'react-grid-system'
import TableAct from '../tableData/TableAct.js'
import * as Icon from 'react-bootstrap-icons'

class ActComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            act_number: '',
            date: moment(new Date()).format('YYYY-MM-DD'),
            paid: false,
            sum: 0,
            comment: '',
            actTable: [],
            contract: [],
            contracts: [],
            contract_id: 0,
            contract_number: '',
            selectedValue: [],
            options: []
        }

        this.onSubmit = this.onSubmit.bind(this)
        this.fullListContracts = this.fullListContracts.bind(this)
        this.onChangeContract = this.onChangeContract.bind(this)
        this.onClose = this.onClose.bind(this)
        this.onCreateInvoice = this.onCreateInvoice.bind(this)
        this.handleInputChangePaid = this.handleInputChangePaid.bind(this)

       }


    onCreateInvoice(){  
       this.props.history.push(`/invoices/-1`, {invoice_number: this.state.act_number, sum: this.state.sum, invoiceTable: this.state.actTable, contract: this.state.contract, contract_id: this.state.contract_id, date: this.state.date, contract_nummer: this.state.contract_number}) 
    }
 
    
    fullListContracts() {

        let username = AuthenticationService.getLoggedInUserName()

        ContractDataService.retrieveAllContracts(username)
            .then(
                response => {

                    this.setState({ contracts: response.data })
                }
            )
    }

    onChangeContract = selectedOption => {

        this.setState({ contract_id: parseInt(selectedOption.value, 10) })
        this.setState({ selectedValue: selectedOption })

    };

    onClose() {
        this.props.history.push('/acts')
    }

    handleInputChangePaid(event) {
        const target = event.target
        const value = target.type === 'checkbox' ? target.checked : target.value
        const name = target.name
    
        this.setState({
          [name]: value
        });
      }

    
    componentDidMount() {
    
        this.fullListContracts()


        if (this.state.id === '-1') {
            return
        }

        let username = AuthenticationService.getLoggedInUserName()

        ActDataService.retrieveAct(username, this.state.id)
            .then(response => this.setState({
                act_number: response.data.act_number,
                date: moment(response.data.date).format('YYYY-MM-DD'),
                paid: response.data.paid,
                sum: response.data.sum,
                comment: response.data.comment,
                contract: response.data.contract,
                contract_id: response.data.contract.id,
                contract_number: response.data.contract.contract_number,
                selectedValue: { value: response.data.contract.id, label: response.data.contract.contract_number }
            }))

        ActDataService.retrieveActTable(this.state.id)
            .then(
                response => {
                    this.setState({ actTable: response.data })
                }
            )          

    }

    onSubmit(values) {
        let username = AuthenticationService.getLoggedInUserName()
        let act = {
            id: this.state.id,
            act_number: values.act_number,
            date: values.date,
            paid: values.paid,
            sum: values.sum,
            comment: values.comment
        }

        if (act.act_number === "") {

        } else if (this.state.id === "-1") {
            ActDataService.createAct(username, act)
                  .then((response) => {         
                  
                      values.actTable.map((item) => {
                          let act_Table ={ 
                              id_act: response.data.id,
                              description: item.description,
                              measure: item.measure,
                              price: item.price,
                              quantity: item.quantity
                          }
                          ActDataService.createActTable(act_Table);     
                        });                    
          
                        this.props.history.push('/acts')
                       
                   })


        } else {
            ActDataService.updateAct(username, this.state.id, this.state.contract_id, act)
            .then((response) => {         
                  
                values.actTable.map((item) => {
                    let act_Table ={ 
                        id: item.id,
                        id_act: item.id_act,
                        description: item.description,
                        measure: item.measure,
                        price: item.price,
                        quantity: item.quantity
                    }
                    ActDataService.updateActTable(act_Table);     
                  });                    
    
                  this.props.history.push('/acts')
                 
             })
        }
    }



    render() {

        let { act_number, date, paid, sum, comment, contract_number, actTable } = this.state

        let options = []
        this.state.contracts.map((item) =>
            options.push({ value: item.id, label: item.contract_number })
        )

        return (
            <div>
                <ScrollArea
                    speed={0.8}
                    className="area"
                    contentClassName="content"
                    horizontal={false}
                >
                    <div className="container">
                        <div></div>
                        <Formik
                            initialValues={{ act_number, date, paid, sum, comment, contract_number, actTable }}
                            onSubmit={this.onSubmit}
                            enableReinitialize={true}
                        >
                            {
                                (props) => (
                                    <Form>
                                        <div style={{ display: "flex", justifyContent: "flex-end" }}>
                                            <button className="button-icon" type="submit" id="save-button"><Icon.Save size="30px" /></button>
                                            <button className="button-icon" id="close-button"><Icon.CalendarPlus size="30px" onClick={() => this.onCreateInvoice()} /></button>
                                            <button className="button-icon" id="close-button"><Icon.X size="30px" onClick={() => this.onClose()} /></button>
                                        </div>
                                        <h1>Act</h1>
                                        <Container >
                                            <Row>
                                                <Col xs={2} md={5}>
                                                    <fieldset className="form-group">
                                                        <label>№</label>
                                                        <Field className="form-control" type="text" name="act_number" />
                                                    </fieldset>
                                                </Col>
                                                <Col xs={5} md={6}>
                                                    <fieldset className="form-group">
                                                        <label>Date</label>
                                                        <Field className="form-control" type="text" name="date" />
                                                    </fieldset>
                                                </Col>
                                                <Col xs={1}>
                                                    <fieldset className="form-group">
                                                    <label>Paid                                                      
                                                        <Field className="check-box" type="checkbox" name="paid"  checked={this.state.paid}  onChange={this.handleInputChangePaid}/>
                                                    </label>
                                                    </fieldset>
                                                </Col>
                                            </Row>
                                            <Row>
                                                <Col xs={4} md={5}>
                                                    <fieldset className="form-group">
                                                        <label>Contract</label>
                                                        <Select options={options} onChange={this.onChangeContract} value={this.state.selectedValue} />
                                                    </fieldset>
                                                </Col>
                                                <Col xs={4} md={6}>
                                                    <fieldset className="form-group">
                                                        <label>Total</label>
                                                        <Field className="form-control" type="text" name="sum" />
                                                    </fieldset>
                                                </Col>
                                            </Row>

                                           
                                            <FieldArray name="actTable" >
                                          
                                                {arrayHelpers => (
                                                    <TableAct
                                                        name="actTable"
                                                        handleAdd={arrayHelpers.push}
                                                        handleRemove={arrayHelpers.remove}
                                                        id_act={this.state.id}
                                                        documentType="act"
                                                        
                                                    />
                                                )}
                                         
                                            </FieldArray>
                                       

                                            <fieldset className="form-group">
                                                <label>Comments</label>
                                                <Field className="form-control" type="text" name="comment" />
                                            </fieldset>

                                        </Container>

                                    </Form>
                                )
                            }
                        </Formik>

                    </div>
                </ScrollArea>
            </div>

        )
    }
}



export default ActComponent