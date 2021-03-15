import React from "react";
import { Button } from "antd";
import { Link, withRouter } from "react-router-dom";
import "./signup.css";
class SignUp extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      name: "",
      username: "",
      phoneNumber:"",
      address: "",
      password: "",
    };

    this.fetRegister = this.fetRegister.bind(this);
    this.setUserName = this.setUserName.bind(this);
    this.setAddress = this.setAddress.bind(this);
    this.setPhoneNumber = this.setPhoneNumber.bind(this);
    this.setPassword = this.setPassword.bind(this);
    this.setName = this.setName.bind(this);
  }
  setPassword(){
    const passwordInput = document.querySelector('#password');
    this.setState({
      password: passwordInput.value
    })

  }

  setName(){
    const nameInput = document.querySelector('#name');
    this.setState({
      name: nameInput.value
    })
  }

  setUserName(){
    const userNameInput = document.querySelector('#username');
    this.setState({
      username: userNameInput.value
    })
  }
  setAddress(){
    const addressInput = document.querySelector('#address');
    this.setState({
      address: addressInput.value
    })
  }
  setPhoneNumber(){
    const phoneNumberInput = document.querySelector('#phoneNumber');
    this.setState({
      phoneNumber: phoneNumberInput.value
    })
  }

 
  async fetRegister(e) {
    e.preventDefault();
    const { name,username, phoneNumber, address, password } = this.state;
    const url = "http://localhost:8085/auth/signup";
    const body = {
      name: name,
      username: username,
      phoneNumber: phoneNumber,
      address: address,
      password: password,
    };
    const response = await fetch(url, 
      {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
    });
    const returnMessage = await response.json();
    document.querySelector('#error').textContent =`${returnMessage.message}`
  }

  render() {
    return (
      <div className="wrapper">


        <div className="title">Register Here</div>
        <div className="social_media">
          <div className="item">
            <i className="fab fa-facebook-f"></i>
          </div>
          <div className="item">
            <i className="fab fa-twitter"></i>
          </div>
          <div className="item">
            <i className="fab fa-google-plus-g"></i>
          </div>
        </div>

        <form
          className="form"
          action="localhost:8085/auth/signup"
          method="POST"
        >
          <div className="input_field">
            <input
            id ="name"
              name="name"
              type="text"
              placeholder="Name"
              className="input"
              onInput= {this.setName}
            />
            <i className="fas fa-user"></i>
          </div>
          <div className="input_field">
            <input
            id = "username"
              name="username"
              type="text"
              placeholder="UserName"
              className="input"
              onInput= {this.setUserName}
            />
            <i className="fas fa-user"></i>
          </div>

          <div className="input_field">
            <input
            id = "password"
              name="password"
              type="password"
              placeholder="Password"
              className="input"
              onInput = {this.setPassword}
            />
            <i className="fas fa-lock"></i>
          </div>
          <div className="input_field">
            <input
            id = 'address'
              name="address"
              type="text"
              placeholder="Address"
              className="input"
              onInput = {this.setAddress}
            />
          </div>
          <div className="input_field">
            <input
            id = 'phoneNumber'
              name="phoneNumber"
              type="number"
              placeholder="PhoneNumber"
              className="input"
              onInput= {this.setPhoneNumber}
            />
            <i class="fas fa-phone-square-alt"></i>{" "}
          </div>
          <h6 id="error"></h6>
          <Link className="linksignin" to = "/signin"> You had an account, go to login page </Link>
           <button className= "btn" type="submit"  onClick ={this.fetRegister}>Register</button>

        </form>
      </div>
    );
  }
}

export default  withRouter(SignUp);
