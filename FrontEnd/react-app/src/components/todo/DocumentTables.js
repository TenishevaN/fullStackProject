import React from 'react'

const DocumentTables = (props) => (
 
  <div > 

    <table className="table" style={{border: "3px double black"}}>
      <thead style={{border: "3px double black"}}>
        <tr >
          <th>Description</th>
          <th>Quantity</th>
          <th>Price</th>
          <th>Measure</th>
          <th>Amount</th>
        </tr>
      </thead>
      <tbody>
        {           
       
        props.documents_table.map(
            document_table =>
             
              <tr key={document_table.id} >
                <td>{document_table.description}</td>
                <td >{document_table.quantity}</td>
                <td>{document_table.price}</td>
                <td>{document_table.measure}</td>
                <td>{document_table.quantity * document_table.price}</td>
            
              </tr>
          )        
        }
     
        <tr key={1000} style={{border: "3px double black"}}>
          <td>Total</td>
          <td>{ (props.documents_table.length !==0 ) ? (props.documents_table.map(ele => (ele.quantity)).reduce((prev, curr)=> prev + curr)) : 0}</td>
          <td>{(props.documents_table.length !==0 ) ? (props.documents_table.map(ele => (ele.price)).reduce((prev, curr)=> prev + curr)) : 0}</td>
          <td></td>
          <td>{(props.documents_table.length !==0 ) ? (props.documents_table.map(ele => (ele.price* ele.quantity)).reduce((prev, curr)=> prev + curr)) : 0}</td>
        </tr>

      </tbody>
    </table>
    {
      
    }
   </div>

);

export default DocumentTables;