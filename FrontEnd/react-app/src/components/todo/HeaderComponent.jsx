import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import AuthenticationService from './AuthenticationService.js'
import { withRouter } from 'react-router'
import { LinkContainer } from 'react-router-bootstrap'
import Nav from 'react-bootstrap/Nav'
import { Navbar } from 'react-bootstrap'
import NavDropdown from 'react-bootstrap/NavDropdown'


class HeaderComponent extends Component {
    render() {

        const isUserLoggedIn = AuthenticationService.isUserLoggedIn();


        return (
            <header>
                <Navbar className="navbar navbar-expand-md navbar-dark bg-dark" expand="lg">
                   <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="mr-auto">
                        {isUserLoggedIn && <LinkContainer to='/dashboard'>
                                <Nav.Link>
                                    <i className='fas fa-shopping-cart'></i> Dashboard
                                </Nav.Link>
                            </LinkContainer>}                          
                            {isUserLoggedIn && <LinkContainer to='/customers'>
                                <Nav.Link>
                                    <i className='fas fa-shopping-cart'></i> Customers
                                </Nav.Link>
                            </LinkContainer>}
                            {isUserLoggedIn && <LinkContainer to='/contracts'>
                                <Nav.Link>
                                    <i className='fas fa-shopping-cart'></i> Contracts
                                </Nav.Link>
                            </LinkContainer>}
                            {isUserLoggedIn && <NavDropdown title="Financial documents" id="basic-nav-dropdown">
                                <LinkContainer to='/acts'>
                                    <NavDropdown.Item>Acts</NavDropdown.Item>
                                </LinkContainer>
                                <LinkContainer to='/invoices'>
                                    <NavDropdown.Item>Invoices</NavDropdown.Item>
                                </LinkContainer>
                                <NavDropdown.Divider />
                                <LinkContainer to='/fundsReceipts'>
                                    <NavDropdown.Item>Funds receipts</NavDropdown.Item>
                                </LinkContainer>
                                <LinkContainer to='/paymentOrders'>
                                    <NavDropdown.Item>Payment orders</NavDropdown.Item>
                                </LinkContainer>
                             </NavDropdown>
                            }

                        </Nav>
                    </Navbar.Collapse>
                    <ul className="navbar-nav navbar-collapse justify-content-end">
                        {!isUserLoggedIn && <li><Link className="nav-link" to="/login">Login</Link></li>}
                        {isUserLoggedIn && <li><Link className="nav-link" to="/logout" onClick={AuthenticationService.logout}>Logout</Link></li>}
                    </ul>

                </Navbar>
            </header>

        )
    }
}


export default withRouter(HeaderComponent)