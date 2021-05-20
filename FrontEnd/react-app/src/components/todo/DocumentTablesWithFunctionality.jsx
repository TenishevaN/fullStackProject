import React, { Component} from 'react'

class DocumentTablesWithFunctionality extends Component {
  constructor(props) {
    super(props)

    this.state = {
      id: ''

    }

    this.upDateItemClicked = this.upDateItemClicked.bind(this)
    this.deleteItemClicked = this.deleteItemClicked.bind(this)

  }

  upDateItemClicked(id){
    this.props.history.push(`/actTable/${id}`) 
 }

 deleteItemClicked(id){
  alert('delete')
 }

  render() {

    return (

      <div >

        <table className="table" style={{ border: "3px double black" }}>
          <thead style={{ border: "3px double black" }}>
            <tr >
              <th>Quantity</th>
              <th>Price</th>
              <th>Measure</th>
              <th>Description</th>
              <th>Amount</th>
              <th>update</th>
              <th>delete</th>
            </tr>
          </thead>
          <tbody>
            {

              this.props.documents_table.map(
                document_table =>

                  <tr key={document_table.id} >
                    <td >{document_table.quantity}</td>
                    <td>{document_table.price}</td>
                    <td>{document_table.measure}</td>
                    <td>{document_table.description}</td>
                    <td>{document_table.quantity * document_table.price}</td>
                    <td><button className="btn btn-success" onClick={() => this.upDateItemClicked(document_table.id)}>Update</button></td>
                    <td><button className="btn btn-warning" onClick={() => this.deleteItemClicked(document_table.id)}>Delete</button></td>
                  </tr>
              )
            }

            <tr key={1000} style={{ border: "3px double black" }}>
              <td>Total</td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
            </tr>

          </tbody>
        </table>
      </div>

    )
  }
}

export default DocumentTablesWithFunctionality