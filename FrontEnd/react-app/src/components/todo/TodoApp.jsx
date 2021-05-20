import React, {Component}  from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import AuthenticatedRoute from './AuthenticatedRoute.jsx'
import LoginComponent from './LoginComponent.jsx'
import HeaderComponent from './HeaderComponent.jsx'
import FooterComponent from './FooterComponent.jsx'
import LogoutComponent from  './LogoutComponent.jsx'
import ErrorComponent from './ErrorComponent.jsx'
import DashboardComponent from './DashboardComponent.jsx'
import ListCustomersComponent from './ListCustomersComponent.jsx'
import CustomerComponent from './CustomerComponent.jsx'
import ListContractsComponent from './ListContractsComponent.jsx'
import ContractComponent from './ContractComponent.jsx'
import ListActsComponent from './ListActsComponent.jsx'
import ActComponent from './ActComponent.jsx' 
import InvoiceComponent from './InvoiceComponent.jsx' 
import ListInvoicesComponent from './ListInvoicesComponent.jsx' 
import ListPaymentOrdersComponent from './ListPaymentOrdersComponent.jsx' 
import PaymentOrderComponent from './PaymentOrderComponent.jsx'
import ListFundsReceiptComponent from './ListFundsReceiptComponent.jsx'
import FundsReceiptComponent from './FundsReceiptComponent.jsx'


class TodoApp extends Component{
    render(){
        return(
            <div className="TodoApp">
                <Router>
                    <>
                    <HeaderComponent/>
                    <Switch>
                    <Route path="/" exact component={LoginComponent} />
                    <Route path="/login" component={LoginComponent} />
                    <AuthenticatedRoute path="/dashboard" component={DashboardComponent}/> 
                    <AuthenticatedRoute path="/logout" component={LogoutComponent}/> 
                    <AuthenticatedRoute path="/acts/:id" component={ActComponent}/>          
                    <AuthenticatedRoute path="/acts" component={ListActsComponent}/>  
                    <AuthenticatedRoute path="/invoices/:id" component={InvoiceComponent}/>          
                    <AuthenticatedRoute path="/invoices" component={ListInvoicesComponent}/> 
                    <AuthenticatedRoute path="/paymentOrders/:id" component={PaymentOrderComponent}/>          
                    <AuthenticatedRoute path="/paymentOrders" component={ListPaymentOrdersComponent}/> 
                    <AuthenticatedRoute path="/fundsReceipts/:id" component={FundsReceiptComponent}/>          
                    <AuthenticatedRoute path="/fundsReceipts" component={ListFundsReceiptComponent}/>                                     
                    <AuthenticatedRoute path="/customers/:id" component={CustomerComponent}/>                  
                    <AuthenticatedRoute path="/customers" component={ListCustomersComponent}/> 
                    <AuthenticatedRoute path="/contracts/:id" component={ContractComponent}/>          
                    <AuthenticatedRoute path="/contracts" component={ListContractsComponent}/>   
                            
                    <Route component={ErrorComponent}/>
                    </Switch>
                    <FooterComponent/>
                    </>
                </Router>
            </div>
        )
    }
}


export default TodoApp