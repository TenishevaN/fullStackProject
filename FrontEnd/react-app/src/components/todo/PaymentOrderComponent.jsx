import React, { Component } from 'react'
import moment from 'moment'
import { Formik, Form, Field } from 'formik';
import PaymentOrderDataService from '../../api/todo/PaymentOrderDataService.js'
import AuthenticationService from './AuthenticationService.js'
import ScrollArea from 'react-scrollbar'
import * as Icon from 'react-bootstrap-icons'
import { Container, Row, Col } from 'react-grid-system'
import '../../App.css'


class PaymentOrderComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            date: '',
            operation: '',
            paid: '',
            sum: '',
            rate: '',
            to_pay: '',
            comment: '',
            description: ''
        }

        this.onSubmit = this.onSubmit.bind(this)
        this.onClose = this.onClose.bind(this)
        this.handleInputChangePaid = this.handleInputChangePaid.bind(this)
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

        if (this.state.id === '-1') {
            return
        }

        let username = AuthenticationService.getLoggedInUserName()

        PaymentOrderDataService.retrievePaymentOrder(username, this.state.id)
            .then(response => this.setState({
                date: moment(response.data.date).format('YYYY-MM-DD'),
                paid: response.data.paid,
                sum: response.data.sum,
                comment: response.data.comment,
                operation: response.data.operation,
                rate: response.data.rate,
                to_pay: response.data.to_pay,
                description: response.data.description
            }))

    }

    onSubmit(values) {
        let username = AuthenticationService.getLoggedInUserName()
        let paymentOrder = {
            id: this.state.id,
            date: values.date,
            paid: values.paid,
            sum: values.sum,
            comment: values.comment,
            operation: values.operation,
            rate: values.rate,
            to_pay: values.to_pay,
            description: values.description
        }
        
        if (paymentOrder.sum === "") {

        } else if (this.state.id === -1) {
            PaymentOrderDataService.createPaymentOrder(username, paymentOrder)
                .then(() => this.props.history.push('/paymentOrders'))
        } else {
            PaymentOrderDataService.updatePaymentOrder(username, this.state.id, paymentOrder)
                .then(() => this.props.history.push('/paymentOrders'))
        }
    }


    onClose(){
        this.props.history.push('/paymentOrders') 
      } 

    render() {

        let { date, paid, sum, comment, description, operation, rate, to_pay } = this.state

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
                                        <h1>Payment order</h1>
                                        <Container >
                                            <Row>
                                                <Col xs={2} md={5}>
                                                    <fieldset className="form-group">
                                                        <label>№</label>
                                                        <Field className="form-control" type="text" name="id" />
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
                                                        <label>Operation</label>
                                                        <Field className="form-control" type="text" name="operation" />
                                                    </fieldset>
                                                </Col>
                                                <Col xs={4} md={6}>
                                                    <fieldset className="form-group">
                                                        <label>Sum</label>
                                                        <Field className="form-control" type="text" name="sum" />
                                                    </fieldset>
                                                </Col>
                                            </Row>
                                            <Row>
                                                <Col xs={4} md={5}>
                                                <fieldset className="form-group">
                                                        <label>To pay</label>
                                                        <Field className="form-control" type="text" name="to_pay" />
                                                    </fieldset>
                                                </Col>
                                                <Col xs={4} md={6}>
                                                    <fieldset className="form-group">
                                                        <label>Rate</label>
                                                        <Field className="form-control" type="text" name="rate" />
                                                    </fieldset>
                                                </Col>
                                            </Row>
                                            <Row>
                                                <Col xs={4} md={5}>
                                                <fieldset className="form-group">
                                                        <label>Description</label>
                                                        <Field className="form-control" type="text" name="description" />
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



export default PaymentOrderComponent