import React, { Component } from "react";
import Select from "react-select";

class SelectClassComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      text: this.props.options[0]
    };
  }

  onChange = selectedOption => {
    this.setState({ text: selectedOption });
  };

  render() {
    return (
      <Select
        options={this.props.options}
        onChange={this.onChange}
        value={this.state.text}
      />
    );
  }
}

export default SelectClassComponent