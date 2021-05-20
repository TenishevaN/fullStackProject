import React, { useCallback, useState, useMemo, useEffect } from "react";
import { useFormikContext, getIn } from "formik";
import Table from "./Table";
import Input from "./Input";
import ActDataService from '../../api/todo/ActDataService.js';
import Glyphicon from '@strongdm/glyphicon'

const EMPTY_ARR = [];

function TableAct({ name, handleAdd, handleRemove, id_act}) {
  const { values } = useFormikContext();

  // from all the form values we only need the "friends" part.
  // we use getIn and not values[name] for the case when name is a path like `social.facebook`
  const formikSlice = getIn(values, name) || EMPTY_ARR;
  const [tableRows, setTableRows] = useState(formikSlice);

  
  // we need this so the table updates after the timeout expires
  useEffect(() => {
    setTableRows(formikSlice);
  }, [formikSlice]);

  const onAdd = useCallback(() => {
    const newState = [...tableRows];
    const item = {
      id_act: id_act,
      id: parseInt(Math.floor(Math.random() * Math.floor(10000)), 10),
      quantity: 0.0,
      price: 0.0,
      sum: 0.0,
      measure: "hour",
      description: "",
      operation: "add"
    };

    newState.push(item);
    setTableRows(newState);
    handleAdd(item);
     }, [handleAdd, tableRows, id_act]);

  const onRemove = useCallback(
    index => {
      const newState = [...tableRows];
    
      const deletedElement = newState[index];

     //it needes to handle new row deleting
      ActDataService.deleteActTable(deletedElement.id);
      
     
      newState.splice(index, 1);
      setTableRows(newState);
      handleRemove(index);
      
    },
    [handleRemove, tableRows]
  );

  const columns = useMemo(
    () => [
     
      {
        Header: "quantity",
        id: "quantity",
        Cell: ({ row: { index } }) => (
          <Input 
          name={`${name}[${index}].quantity`} 
          autoFocus={true}/>
        )
      },
      {
        Header: "measure",
        id: "measure",
        Cell: ({ row: { index } }) => (
          <Input name={`${name}[${index}].measure` } />
        )
      },
      {
        Header: "price",
        id: "price",
        Cell: ({ row: { index } }) => (
          <Input name={`${name}[${index}].price`}/>
        )
      },
      {
        Header: "sum",
        id: "sum",
        Cell: ({ row: { index } }) => (
        <div style={{ align: "left"}}><b>{tableRows[index].price * tableRows[index].quantity}</b></div>
        )
      },
      {
        Header: "description",
        id: "description",
        Cell: ({ row: { index } }) => (
          <Input name={`${name}[${index}].description`} />
        )
      },      
       
      {
        Header: "",
        id: "actions",
        Cell: ({ row: { index } }) => (
            <Glyphicon glyph="trash" onClick={() => onRemove(index)} />
        )
      },
    ],
     [name, tableRows, onRemove]
  );

  return (
  
    <div className="field" >
      
      <div >
      <span className="headline1">Job details:{" "} 
        <Glyphicon glyph="plus" onClick={onAdd} />
        </span>
      </div>
      <div style={{ display: "flex", justifyContent: "flex-start", padding: "10px" }}>
      </div> 
   
      <div className="documentsTable">
        <Table data={tableRows} columns={columns} rowKey="id" />
      </div>
       
    </div>
  
  );
}

export default React.memo(TableAct);
