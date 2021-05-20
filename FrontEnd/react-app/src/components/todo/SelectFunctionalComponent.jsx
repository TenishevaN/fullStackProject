import React, { useState } from "react";
import Select from "react-select";

const SelectFunctionalComponent = props => {
  const [text, setText] = useState(props.options[2]);

  const onChange = selectedOption => {
    setText(selectedOption);
  };

  return <Select options={props.options} onChange={onChange} value={text} />;
};

export default SelectFunctionalComponent;