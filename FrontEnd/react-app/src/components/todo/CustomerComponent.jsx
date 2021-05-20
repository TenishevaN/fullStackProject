import React, { Component } from 'react'
import { Formik, Form, Field } from 'formik';
import CustomerDataService from '../../api/todo/CustomerDataService.js'
import AuthenticationService from './AuthenticationService.js'
import ScrollArea from 'react-scrollbar'
import * as Icon from 'react-bootstrap-icons'
import { Container, Row, Col } from 'react-grid-system'


class PaymentOrderComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            name: '',
	        inn: '',
	        props: '',
            comment: ''
        }

        this.onSubmit = this.onSubmit.bind(this)
        this.onClose = this.onClose.bind(this)
    }


    componentDidMount() {


        if (this.state.id === '-1') {
            return
        }

        let username = AuthenticationService.getLoggedInUserName()

        CustomerDataService.retrieveCustomer(username, this.state.id)
            .then(response => this.setState({
                name: response.data.name,
                inn: response.data.inn,
                props: response.data.props,
                comment: response.data.comment              
            }))

    }

    onSubmit(values) {
        let username = AuthenticationService.getLoggedInUserName()
        let customer = {
            id: this.state.id,
            name: values.name,
            inn: values.inn,
            props: values.props,         
            comment: values.comment
        }
        
         if (this.state.id === -1) {
            CustomerDataService.createCustomer(username, customer)
                .then(() => this.props.history.push('/customers'))
        } else {
            CustomerDataService.updateCustomer(username, this.state.id, customer)
                .then(() => this.props.history.push('/customers'))
        }
    }


    onClose(){
        this.props.history.push('/customers') 
      } 

    render() {

        let { name, inn, props, comment} = this.state

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
                            initialValues={{name, inn, props, comment }}
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
                                        <h1>Customer</h1>
                                        <Container >
                                            <Row>
                                                <Col xs={2} md={5}>
                                                    <fieldset className="form-group">
                                                        <label>Name</label>
                                                        <Field className="form-control" type="text" name="name" />
                                                    </fieldset>
                                                </Col>
                                                <Col xs={5} md={6}>
                                                    <fieldset className="form-group">
                                                        <label>INN</label>
                                                        <Field className="form-control" type="text" name="inn" />
                                                    </fieldset>
                                                </Col>
                                             </Row>
                                            <Row>
                                                <Col xs={8} md={11}>
                                                <fieldset className="form-group">
                                                        <label>Props</label>
                                                        <Field className="form-control" type="text" name="props" />
                                                    </fieldset>
                                                </Col>
                                            </Row>
                                          
                                            <Row>
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