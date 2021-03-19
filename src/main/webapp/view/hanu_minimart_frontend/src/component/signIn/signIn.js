import Layout from "antd/lib/layout/layout";
import Title from "antd/lib/typography/Title";
import axios from "axios";
import { error } from "jquery";
import React, { Component } from "react";
import { Link } from "react-router-dom";
import { withRouter } from "react-router-dom";

import "./login.css";
const { Header } = Layout;

class SignIn extends Component {
  constructor(props) {
    super(props);
    this.state = {
      usernameOrEmail: "",
      password: "",
      status: "",
      isLogin: false,
    };
    this.setUserName = this.setUserName.bind(this);
    this.setPassword = this.setPassword.bind(this);
    this.fetLogin = this.fetLogin.bind(this);
  }
 
  setUserName() {
    const userNameInput = document.querySelector("#username");
    this.setState({
      usernameOrEmail: userNameInput.value,
    });
  }
  setPassword() {

    const passwordInput = document.querySelector("#password");
    this.setState({
      password: passwordInput.value,
    });
   
  }
  
  
  async fetLogin(e) {
    e.preventDefault();
    console.log("load.............");
    const { usernameOrEmail, password } = this.state;

    const url = "http://localhost:8085/auth/signin";

    const body = {
      usernameOrEmail: usernameOrEmail,
      password: password,
    };
    try{
      const response = await axios.post(url, body);
      const data = await response.data;
      console.log(data);
      if (data.user.status === "ACTIVATED") {
        this.createCookie("username", `${data.user.username}`, 14.4);
        this.createCookie("uid", `${data.user.id}`, 14.4);
        this.props.setStateLogin(true, () => {
          this.props.history.push("/");
        });
        const Authentication = data.tokenType + " " + data.accessToken;
        console.log(Authentication);
        this.props.saveAuthentication(Authentication, data.user);
      } else if (data.status !== "ACTIVATED") {
        alert("this account is not exist");
      }else{
        this.setState({
          status: "password or username is empty"
        })
      }
    }catch(e){
      console.log( "errrrrrrr");
      this.setState({
        status: "ERROR: password or username is not exact"
      })
    }
   
   
  }

  createCookie(name, value, minutes) {
    if (minutes) {
      var date = new Date();
      date.setTime(date.getTime() + minutes * 60 * 1000);
      var expires = "; expires=" + date.toGMTString();
    } else {
      var expires = "";
    }
    document.cookie = name + "=" + value + expires + "; path=/";
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
          action="http://localhost:8085/auth/signin"
          method="POST"
        >
          <div className="input_field">
            <input
              id="username"
              name="username"
              type="text"
              placeholder="UserName"
              className="input"
              onInput={this.setUserName}
            />
            <i className="fas fa-user"></i>
          </div>

          <div className="input_field">
            <input
              id="password"
              name="password"
              type="password"
              placeholder="Password"
              className="input"
              onInput={this.setPassword}
            />
            <i className="fas fa-lock"></i>

          </div>
          <Link className="linksignup" to="/signup">
            You has not an account, go to register page{" "}
          </Link>
          <h6 style={{color: "red"}}> {this.state.status}</h6>

          <button className="btn" type="submit" onClick={this.fetLogin}>
            Register
          </button>
        </form>
      </div>
      
      
    );
  }
}

export default withRouter(SignIn);
