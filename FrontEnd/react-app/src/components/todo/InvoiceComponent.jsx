import React, { Component} from 'react'
import moment from 'moment'
import { Formik, Form, Field, FieldArray  } from 'formik';
import InvoiceDataService from '../../api/todo/InvoiceDataService.js'
import AuthenticationService from './AuthenticationService.js'
import ContractDataService from '../../api/todo/ContractDataService.js'
import Select from 'react-select'
import ScrollArea from  'react-scrollbar'
import { Container, Row, Col } from 'react-grid-system'
import TableInvoice from '../tableData/TableInvoice.js'
import * as Icon from 'react-bootstrap-icons'


class InvoiceComponent extends Component {
    constructor(props) {
        super(props)
        
         this.state = {
            id: this.props.match.params.id,
            invoice_number: '',
	        date: moment(new Date()).format('YYYY-MM-DD'),
	        paid: false,
	        sum: 0,
            comment: '',
            invoiceTable: [],
            contract: [],
            contracts: [],
            contract_id: 0,
            contract_number: '', 
            selectedValue: [],
            options: []    
        }

        this.handleChangeContract = this.handleChangeContract.bind(this)
        this.onSubmit = this.onSubmit.bind(this)
        this.fullListContracts = this.fullListContracts.bind(this)
        this.onChangeContract = this.onChangeContract.bind(this)
        this.onClose = this.onClose.bind(this)   
        this.handleInputChangePaid = this.handleInputChangePaid.bind(this)     
    }

      fullListContracts(){
      
        let username = AuthenticationService.getLoggedInUserName()
     
          ContractDataService.retrieveAllContracts(username)
              .then(
                  response => {

                      this.setState({ contracts: response.data })
                  }
              )
      }

        onChangeContract = selectedOption => {
           
           this.setState({contract_id: parseInt(selectedOption.value, 10)})
           this.setState({selectedValue: selectedOption})
     
          };

          onClose(){
            this.props.history.push('/invoices') 
          } 


    handleChangeContract(event){     
    
        this.setState({contract_id: parseInt(event.target.value, 10)})
     
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

            if (this.props.location.state === undefined) {return }
            this.setState({ invoice_number: this.props.location.state.invoice_number })
            this.setState({ date: this.props.location.state.date})
            this.setState({ sum: this.props.location.state.sum })
            this.setState({ invoiceTable: this.props.location.state.invoiceTable })
            this.setState({ contract: this.props.location.state.contract})
            this.setState({ contract_id: this.props.location.state.contract_id })
            this.setState({ contract_number: this.props.location.state.contract_nummer})
            this.setState({ selectedValue: {value: this.props.location.state.contract_id, label: this.props.location.state.contract_nummer}})
            return
        }

   

        let username = AuthenticationService.getLoggedInUserName()

        InvoiceDataService.retrieveInvoice(username, this.state.id)
            .then(response => this.setState({
                invoice_number: response.data.invoice_number,
	            date: moment(response.data.date).format('YYYY-MM-DD'),
	            paid: response.data.paid,
	            sum: response.data.sum,
                comment: response.data.comment,              
                contract_id: response.data.contract.id,
                contract_number: response.data.contract.contract_number,
                selectedValue: {value: response.data.contract.id, label: response.data.contract.contract_number}
            }))
               InvoiceDataService.retrieveInvoiceTable(this.state.id)
            .then(
                response => {
                    this.setState({ invoiceTable: response.data })                        
                }
            )              
    }

     onSubmit(values) {
        let username = AuthenticationService.getLoggedInUserName()
        let invoice = {
            id: this.state.id,
            invoice_number: values.invoice_number,
	        date: values.date,
	        paid: values.paid,
	        sum:  values.sum,
            comment: values.comment
        }
            
      if (invoice.invoice_number===""){

    } else if (this.state.id === "-1") {
     
        InvoiceDataService.createInvoice(username, this.state.contract_id, invoice)
        .then((response) => {         
        
            values.invoiceTable.map((item) => {
                    let invoice_Table ={ 
                    id_invoice: response.data.id,
                    date: response.data.date, 
                    description: item.description,
                    measure: item.measure,
                    price: item.price,
                    quantity: item.quantity
                }
                InvoiceDataService.createInvoiceTable(invoice_Table);     
              });                    

             this.props.history.push('/invoices')
             
         })
               
     
      } else {
             InvoiceDataService.updateInvoice(username, this.state.id, this.state.contract_id, invoice) 
               .then(() => {
              
                  values.invoiceTable.map((item) => {
                    let invoice_Table ={ 
                        id: item.id,
                        id_invoice: item.id_invoice,
                        description: item.description,
                        measure: item.measure,
                        price: item.price,
                        quantity: item.quantity
                    }
                    InvoiceDataService.updateInvoiceTable(invoice_Table);     
                  });
                this.props.history.push('/invoices')
                })
        }                 
    }   

    render() {

       let options = []
        this.state.contracts.map((item) =>
            options.push({ value: item.id, label: item.contract_number })
         )

        let { invoice_number, date, paid, sum, comment, contract_number, invoiceTable} = this.state
     
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
                        initialValues={{ invoice_number, date, paid, sum, comment, contract_number, invoiceTable}}
                        onSubmit={this.onSubmit}
                         enableReinitialize={true}
                    >
                        {
                                (props) => (
                                    <Form>
                                        <div style={{ display: "flex", justifyContent: "flex-end" }}>
                                        <button className = "button-icon" type="submit" id="save-button"><Icon.Save size="30px"  /></button>   
                                        <button className = "button-icon" id="close-button"><Icon.X size="30px" onClick={() => this.onClose()}/></button>    
                                        </div> 
                                        <h1>Invoice</h1>                                       
                                        <Container >
                                         <Row>
                                                <Col xs={2} md={5}>
                                                    <fieldset className="form-group">
                                                        <label>№</label>
                                                        <Field className="form-control" type="text" name="invoice_number" />
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
                                          
                                          
                                            <FieldArray name="invoiceTable" >
                                                {arrayHelpers => (
                                                    <TableInvoice
                                                        name="invoiceTable"
                                                        handleAdd={arrayHelpers.push}
                                                        handleRemove={arrayHelpers.remove}
                                                        id_invoice={this.state.id}

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



export default InvoiceComponent