import React, { Component } from 'react'
import moment from 'moment'
import { Formik, Form, Field} from 'formik';
import FundsReceiptDataService from '../../api/todo/FundsReceiptDataService.js'
import InvoiceDataService from '../../api/todo/InvoiceDataService.js'
import AuthenticationService from './AuthenticationService.js'
import ScrollArea from 'react-scrollbar'
import * as Icon from 'react-bootstrap-icons'
import { Container, Row, Col } from 'react-grid-system'
import Select from 'react-select'


class FundsReceiptComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            date: '',
            sum: '',
            comment: '',
            invoice_id: 0,
            selectedInvoiceValue: [],
            invoices:[],
            invoice_options: []
        }

        this.onSubmit = this.onSubmit.bind(this)
        this.onClose = this.onClose.bind(this)
        this.fullListInvoices = this.fullListInvoices.bind(this)  
        this.onChangeInvoice = this.onChangeInvoice.bind(this)  
    }

    onChangeInvoice =  selectedOption => {
        this.setState({invoice_id: selectedOption.value});
        this.setState({selectedInvoiceValue: selectedOption})
      }

    componentDidMount() {


        this.fullListInvoices()    

        if (this.state.id === '-1') {
            return
        }

        let username = AuthenticationService.getLoggedInUserName()

        FundsReceiptDataService.retrieveFundsReceipt(username, this.state.id)
            .then(response => this.setState({
                
                date: moment(response.data.date).format('YYYY-MM-DD'),
                sum: response.data.sum,
                comment: response.data.comment,
                invoice_id: response.data.invoice.id,
                selectedInvoiceValue:  {value: response.data.invoice.id, label: 'invoice №' + response.data.invoice.invoice_number +' contract №'+ response.data.invoice.contract.contract_number + ' from ' + moment(response.data.invoice.contract.data_start).format('YYYY-MM-DD')}
     }))

    }

    fullListInvoices(){
        let username = AuthenticationService.getLoggedInUserName()
        InvoiceDataService.retrieveAllInvoices(username)
              .then(
                  response => {
                    this.setState({ invoices: response.data })
                 }
              )
      }

    onSubmit(values) {
        let username = AuthenticationService.getLoggedInUserName()
        let fundsReceipt = {
            id: this.state.id,
            date: values.date,
            sum: values.sum,
            comment: values.comment
        }
        
        if (fundsReceipt.sum === "") {

        } else if (this.state.id === -1) {
            FundsReceiptDataService.createFundsReceipt(username, this.state.invoice_id, fundsReceipt)
                .then(() => this.props.history.push('/fundsReceipts'))
        } else {
            FundsReceiptDataService.updateFundsReceipt(username, this.state.id, this.state.invoice_id, fundsReceipt)
                .then(() => this.props.history.push('/fundsReceipts'))
        }
    }


    onClose(){
        this.props.history.push('/fundsReceipts') 
      } 

    render() {

        let { date, paid, sum, comment, description, operation, rate, to_pay } = this.state

        let invoice_options = []
        this.state.invoices.map((item) =>         
            invoice_options.push({ value: item.id, label: 'invoice №' + item.invoice_number +' contract №'+ item.contract.contract_number + ' from ' + moment(item.contract.data_start).format('YYYY-MM-DD')})
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
                            initialValues={{ date, paid, sum, comment, description, operation, rate, to_pay }}
                            onSubmit={this.onSubmit}
                            enableReinitialize={true}
                        >
                            {
                                (props) => (
                                    <Form>
                                        <div style={{ display: "flex", justifyContent: "flex-end" }}>
                                            <button className="button-icon" type="submit" id="save-button"><Icon.Save size="30px" /></button>
                                            <button className="button-icon" id="close-button"><Icon.X size="30px" onClick={() => this.onClose()} /></button>
                                        </div>
                                        <h1>Funds receipt</h1>
                                        <Container >
                                            <Row>
                                               
                                                <Col xs={4} md={5}>
                                                    <fieldset className="form-group">
                                                        <label>Date</label>
                                                        <Field className="form-control" type="text" name="date" />
                                                    </fieldset>
                                                </Col>
                                                <Col xs={4} md={6}>
                                                <fieldset className="form-group">
                                                        <label>Invoice</label>
                                                        <Select options={invoice_options} onChange={this.onChangeInvoice} value={this.state.selectedInvoiceValue} />
                                                    </fieldset>
                                                </Col>                                          
                                            </Row>
                                            <Row>
                                                                                       
                                                <Col xs={4} md={5}>
                                                    <fieldset className="form-group">
                                                        <label>Sum</label>
                                                        <Field className="form-control" type="text" name="sum" />
                                                    </fieldset>
                                                </Col>
                                                <Col xs={4} md={6}>
                                                    <fieldset className="form-group">
                                                        <label>Comment</label>
                                                        <Field className="form-control" type="text" name="comment" />
                                                    </fieldset>
                                                </Col>
                                             
                                            </Row>
                                                                        
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



export default FundsReceiptComponent