import React, { Component} from 'react'
import moment from 'moment'
import { Formik, Form, Field} from 'formik';
import AuthenticationService from './AuthenticationService.js'
import CityDataService from '../../api/todo/CityDataService.js'
import CustomerDataService from '../../api/todo/CustomerDataService.js'
import ContractDataService from '../../api/todo/ContractDataService.js'
import ScrollArea from  'react-scrollbar'
import { Container, Row, Col } from 'react-grid-system'
import * as Icon from 'react-bootstrap-icons'
import Select from 'react-select'


class ContractComponent extends Component {
    constructor(props) {
        super(props)

         this.state = {
            id: this.props.match.params.id,
            sum: 0,
            comment: '',
            contract_number: '',
            data_start: moment(new Date()).format('YYYY-MM-DD'),
            data_end: moment(new Date()).format('YYYY-MM-DD'),
            rate: '',
            terms_of_payment: '',
            city_id: 0,
            selectedValue: [],
            city_options: [],
            cities:[],
            customer_id: 0,
            selectedCustomerValue: [],
            customers:[],
            customer_options: []

        }

        this.onSubmit = this.onSubmit.bind(this)
        this.onClose = this.onClose.bind(this)  
        this.onChangeCity = this.onChangeCity.bind(this) 
        this.fullListCities = this.fullListCities.bind(this)  
        this.onChangeCustomer = this.onChangeCustomer.bind(this)  
        this.fullListCustomers = this.fullListCustomers.bind(this)   
    }

           onClose(){
            this.props.history.push('/contracts') 
          } 

          fullListCities(){
      
            CityDataService.retrieveAllCities()
                  .then(
                      response => {    
                          this.setState({ cities: response.data })
                     }
                  )
          }

          fullListCustomers(){
            let username = AuthenticationService.getLoggedInUserName()
            CustomerDataService.retrieveAllCustomers(username)
                  .then(
                      response => {
                        this.setState({ customers: response.data })
                     }
                  )
          }

           onChangeCity = selectedOption => {
            this.setState({city_id: parseInt(selectedOption.value, 10)});
            this.setState({selectedValue: selectedOption})
          }

          onChangeCustomer =  selectedOption => {
            this.setState({customer_id: parseInt(selectedOption.value, 10)});
            this.setState({selectedCustomerValue: selectedOption})
          }


    componentDidMount() {
      
        this.fullListCities()  
        this.fullListCustomers()    

        if (this.state.id === '-1') {
            return
        }

        let username = AuthenticationService.getLoggedInUserName()

        ContractDataService.retrieveContract(username, this.state.id)
            .then(response => this.setState({
                contract_number: response.data.contract_number,
	            data_start: moment(response.data.data_start).format('YYYY-MM-DD'),
                data_end: moment(response.data.data_end).format('YYYY-MM-DD'),
	            comment: response.data.comment,
                rate: response.data.rate,
                terms_of_payment: response.data.terms_of_payment,
                city_id: response.data.city.id,
                customer_id: response.data.customer.id,
                selectedValue: {value: response.data.id_city, label: response.data.city.name},
                selectedCustomerValue: {value: response.data.id_customer, label: response.data.customer.name}
            }))
         
    }

     onSubmit(values) {
        let username = AuthenticationService.getLoggedInUserName()
        let contract = {
            id: this.state.id,
            contract_number: values.contract_number,
	        data_start: values.data_start,
            data_end: values.data_end,
	        comment: values.comment,
            rate: values.rate,
            terms_of_payment: values.terms_of_payment
        }

      if (contract.contract_number===""){

      } else if  (this.state.id === -1) {
            ContractDataService.createContract(username, parseInt(this.state.city_id, 10), this.state.customer_id, contract)
                .then(() => this.props.history.push('/contracts'))
        } else {
            ContractDataService.updateContract(username, this.state.id, parseInt(this.state.city_id, 10), this.state.customer_id, contract)
               .then(() => this.props.history.push('/contracts'))
        }                 
    }

 
    render() {        
        let { contract_number, data_start, data_end, comment, rate, terms_of_payment} = this.state

        let city_options = []
        this.state.cities.map((item) =>         
            city_options.push({ value: item.id, label: item.name })
         )

         let customer_options = []
         this.state.customers.map((item) =>         
             customer_options.push({ value: item.id, label: item.name })
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
                        initialValues={{ contract_number, data_start, data_end, comment, rate, terms_of_payment}}
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
                                        <h1>Contract</h1>                                       
                                        <Container >
                                         <Row>
                                                <Col xs={2} md={5}>
                                                    <fieldset className="form-group">
                                                        <label>Contract №</label>
                                                        <Field className="form-control" type="text" name="contract_number" />
                                                    </fieldset>
                                                </Col>
                                                <Col xs={3} md={3}>
                                                    <fieldset className="form-group">
                                                        <label>data from</label>
                                                        <Field className="form-control" type="text" name="data_start" />
                                                    </fieldset>
                                                </Col>
                                                <Col xs={3} md={3}>
                                                    <fieldset className="form-group">
                                                        <label>data to
                                                        <Field className="form-control" type="text" name="data_end" />
                                                        </label>
                                                    </fieldset>
                                                </Col>
                                        </Row>
                                        <Row>
                                                <Col xs={4} md={5}>
                                                    <fieldset className="form-group">
                                                        <label>Customer</label>
                                                        <Select options={customer_options} onChange={this.onChangeCustomer} value={this.state.selectedCustomerValue} />
                                                    </fieldset>
                                                </Col>
                                                <Col xs={4} md={5}>
                                                    <fieldset className="form-group">
                                                        <label>City</label>
                                                        <Select options={city_options} onChange={this.onChangeCity} value={this.state.selectedValue} />
                                                    </fieldset>
                                                </Col>
                                            </Row>  
                                                            
                                            <Row>
                                                <Col xs={4} md={6}>
                                                    <fieldset className="form-group">
                                                        <label>Terms of payment</label>
                                                        <Field className="form-control" type="text" name="terms_of_payment" />
                                                    </fieldset>
                                                </Col>
                                                <Col xs={4} md={5}>
                                                    <fieldset className="form-group">
                                                        <label>Rate</label>
                                                        <Field className="form-control" type="text" name="rate" />
                                                    </fieldset>
                                                </Col>
                                            </Row>

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



export default ContractComponent