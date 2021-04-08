import React from "react";
import { Button } from "antd";
import { Link, withRouter } from "react-router-dom";
import { WhatsAppOutlined,HomeOutlined } from "@ant-design/icons";
import "./signup.css";
class SignUp extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      status: "",
      name: "",
      username: "",
      phoneNumber: "",
      address: "",
      password: "",
    };

    this.fetRegister = this.fetRegister.bind(this);
    this.setUserName = this.setUserName.bind(this);
    this.setAddress = this.setAddress.bind(this);
    this.setPhoneNumber = this.setPhoneNumber.bind(this);
    this.setPassword = this.setPassword.bind(this);
    this.setName = this.setName.bind(this);

    this.checkPassword = this.checkPassword.bind(this);
  }
  checkName(name) {
    if (name.length < 0) {
      return 0;
    } else if (name.length < 2) {
      return 1;
    } else if (name.length > 40) {
      return 2;
    }
  }
  checkUsername(username) {
    if (username.length < 0) {
      return 0;
    } else if (username.length < 3) {
      return 1;
    } else if (username.length > 15) {
      return 2;
    }
  }
  checkPassword(password) {
    let strength = 0;
    if (password.length < 6) {
      return "Minimum of password length is 6";
    }
    if (password.length > 50) {
      return "Maximum of password length is 50";
    }
    if (password.match(/[a-z]+/)) {
      strength += 1;
    }
    if (password.match(/[A-Z]+/)) {
      strength += 1;
    }
    if (password.match(/[0-9]+/)) {
      strength += 1;
    }
    if (password.match(/[$@#&!]+/)) {
      strength += 1;
    }
    console.log("passtrength: ", strength);
    return strength;
  }
  checkPhoneNumber(phoneNumber) {
    if (phoneNumber.length < 0) {
      return 0;
    }

    if (typeof phoneNumber !== "undefined") {
      var pattern = new RegExp(/^[0-9\b]+$/);

      if (!pattern.test(phoneNumber)) {
        return 1;
      } else if (phoneNumber.length != 10) {
        return 2;
      } else {
        return 3;
      }
    }

    return 69;
  }
  setPassword() {
    const passwordInput = document.querySelector("#password");
    const checkPasswordValid = document.querySelector("#checkPasswordValid");

    this.setState({
      password: passwordInput.value,
    });
    if (typeof this.checkPassword(passwordInput.value) == "string") {
      checkPasswordValid.innerHTML = `<span style="color:red">${this.checkPassword(
        passwordInput.value
      )}</span>`;
    } else {
      switch (this.checkPassword(passwordInput.value)) {
        case 0:
          checkPasswordValid.innerHTML =
            '<span style="color:red">Your password is very weak</span>';
          break;
        case 1:
          checkPasswordValid.innerHTML =
            '<span style="color:orange">Your password is weak</span>';
          break;
        case 3:
          checkPasswordValid.innerHTML =
            '<span style="color:yellow">Your password is ok</span>';
          break;
        case 4:
          checkPasswordValid.innerHTML =
            '<span style="color:green">Your password is strong</span>';
          break;
        default:
          checkPasswordValid.innerHTML = "";
          break;
      }
    }
    if (passwordInput.value === "") {
      checkPasswordValid.innerHTML = "";
    }
  }

  setName() {
    const nameInput = document.querySelector("#name");
    this.setState({
      name: nameInput.value,
    });
    const checkNameValid = document.querySelector("#checkNameValid");
    switch (this.checkName(nameInput.value)) {
      case 0:
        checkNameValid.innerHTML =
          '<span style="color:red">Please enter your name</span>';
        break;
      case 1:
        checkNameValid.innerHTML =
          '<span style="color:red">Your name is too short</span>';
        break;
      case 2:
        checkNameValid.innerHTML =
          '<span style="color:red">Your name is too long</span>';
        break;
      default:
        checkNameValid.innerHTML = "";
        break;
    }
  }

  setUserName() {
    const usernameInput = document.querySelector("#username");
    this.setState({
      username: usernameInput.value,
    });
    const checkUsernameValid = document.querySelector("#checkUsernameValid");
    switch (this.checkUsername(usernameInput.value)) {
      case 0:
        checkUsernameValid.innerHTML =
          '<span style="color:red">Please enter username</span>';
        break;
      case 1:
        checkUsernameValid.innerHTML =
          '<span style="color:red">Your username is too short</span>';
        break;
      case 2:
        checkUsernameValid.innerHTML =
          '<span style="color:red">Your username is too long</span>';
        break;
      default:
        checkUsernameValid.innerHTML = "";
        break;
    }
  }
  setAddress() {
    const addressInput = document.querySelector("#address");
    this.setState({
      address: addressInput.value,
    });
  }
  setPhoneNumber() {
    const phoneNumberInput = document.querySelector("#phoneNumber");
    const checkPhoneNumberValid = document.querySelector(
      "#checkPhoneNumberValid"
    );
    this.setState({
      phoneNumber: phoneNumberInput.value,
    });

    switch (this.checkPhoneNumber(phoneNumberInput.value)) {
      case 0:
        checkPhoneNumberValid.innerHTML =
          '<span style="color:red">Please enter your phone number</span>';
        break;
      case 1:
        checkPhoneNumberValid.innerHTML =
          '<span style="color:red">Please enter only number</span>';
        break;
      case 2:
        checkPhoneNumberValid.innerHTML =
          '<span style="color:red">Please enter valid phone number</span>';
        break;
      default:
        checkPhoneNumberValid.innerHTML = "";
        break;
    }
  }

  async fetRegister(e) {
    e.preventDefault();
    const { name, username, phoneNumber, address, password } = this.state;
    const url = "http://localhost:8085/auth/signup";
    const body = {
      name: name,
      username: username,
      phoneNumber: phoneNumber,
      address: address,
      password: password,
    };
    const response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
    });
    const returnMessage = await response.json();
    console.log(returnMessage);
    if (returnMessage.message) {
      this.props.history.push("/signin");

    } else {
      this.setState({
        status: "Wrong Input !",
      });
    }
    // document.querySelector("#error").textContent = `${returnMessage.message}`;
  }

  render() {
    return (
      <div className="wrapper">
        <div className="title">
        <Link className="home_icon" to="/">
            <HomeOutlined />
          </Link>
          <br />
          <div> Sign Up</div>
        </div>
        <div className="social_media">
          <div className="item">
            <i  id="icon" className="fab fa-facebook-f"></i>
          </div>
          <div className="item">
            <i className="fab fa-twitter"></i>
          </div>
          <div className="item">
            <i  id="icon" className="fab fa-google-plus-g"></i>
          </div>
        </div>

        <form
          className="form"
          action="localhost:8085/auth/signup"
          method="POST"
        >
          <div className="input_field">
            <input
              id="name"
              name="name"
              type="text"
              placeholder="Name"
              className="input"
              onInput={this.setName}
            />
            <i  id="icon" className="fas fa-user"></i>
          </div>
          <div id="checkNameValid"></div>

          <div className="input_field">
            <input
              id="username"
              name="username"
              type="text"
              placeholder="Username"
              className="input"
              onInput={this.setUserName}
            />
            <i  id="icon" className="fas fa-user"></i>
          </div>
          <div id="checkUsernameValid"></div>

          <div className="input_field">
            <input
              id="password"
              name="password"
              type="password"
              placeholder="Password"
              className="input"
              onInput={this.setPassword}
            />
            <i  id="icon" className="fas fa-lock"></i>
          </div>
          <div id="checkPasswordValid"></div>

          <div className="input_field">
            <input
              id="address"
              name="address"
              type="text"
              placeholder="Address"
              className="input"
              onInput={this.setAddress}
            />
            <i  id="icon1" ><HomeOutlined /></i>
          </div>
          <div className="input_field">
            <input
              id="phoneNumber"
              name="phoneNumber"
              type="text"
              placeholder="Phone number"
              className="input"
              onInput={this.setPhoneNumber}
            />
            <div id="checkPhoneNumberValid"></div>
            <i  id="icon1">
              <WhatsAppOutlined />
            </i>
          </div>
          <h6 id = "error"></h6>
          <Link className="linksignin" to="/signin">
            Already have an account? Sign in
          </Link>
          <button className="btn" type="submit" onClick={this.fetRegister}>
            Register
          </button>
        </form>
      </div>
    );
  }
}

export default withRouter(SignUp);
