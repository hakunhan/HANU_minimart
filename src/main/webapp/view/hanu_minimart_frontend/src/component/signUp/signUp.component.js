import React from "react";
import { Button } from "antd";
import { Link, withRouter } from "react-router-dom";
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
  setPassword() {

    const passwordInput = document.querySelector("#password");
    const checkValid = document.querySelector("#checkvalid");

    this.setState({
      password: passwordInput.value,
    });
    if (typeof this.checkPassword(passwordInput.value) == "string") {
      checkValid.innerHTML = `<span style="color:red">${this.checkPassword(passwordInput.value)}</span>`;
    } else {
      switch (this.checkPassword(passwordInput.value)) {
        case 0:
          checkValid.innerHTML =
            '<span style="color:red">Your password is very weak</span>';
          break;
        case 1:
          checkValid.innerHTML =
            '<span style="color:orange">Your password is weak</span>';
          break;
        case 3:
          checkValid.innerHTML =
            '<span style="color:yellow">Your password is ok</span>';
          break;
        case 4:
          checkValid.innerHTML =
            '<span style="color:green">Your password is strong</span>';
          break;
        default:
          checkValid.innerHTML = "";
          break;
      }
    }
    if (passwordInput.value === "") {
      checkValid.innerHTML = "";
    }
  }

  setName() {
    const nameInput = document.querySelector("#name");
    this.setState({
      name: nameInput.value,
    });
  }

  setUserName() {
    const userNameInput = document.querySelector("#username");
    this.setState({
      username: userNameInput.value,
    });
  }
  setAddress() {
    const addressInput = document.querySelector("#address");
    this.setState({
      address: addressInput.value,
    });
  }
  setPhoneNumber() {
    const phoneNumberInput = document.querySelector("#phoneNumber");
    this.setState({
      phoneNumber: phoneNumberInput.value,
    });
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
    if (returnMessage.success === "false") {
      this.setState({
        status: "Wrong Input !",
      });
    } else if (returnMessage.success === "true") {
      this.props.history.push("/signin");
    }
    document.querySelector("#error").textContent = `${returnMessage.message}`;
  }

  render() {
    return (
      <div className="wrapper">
        <div className="title">Sign up</div>
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
              id="name"
              name="name"
              type="text"
              placeholder="Name"
              className="input"
              onInput={this.setName}
            />
            <i className="fas fa-user"></i>
          </div>
          <div className="input_field">
            <input
              id="username"
              name="username"
              type="text"
              placeholder="Username"
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
          <div id="checkvalid"></div>

          <div className="input_field">
            <input
              id="address"
              name="address"
              type="text"
              placeholder="Address"
              className="input"
              onInput={this.setAddress}
            />
          </div>
          <div className="input_field">
            <input
              id="phoneNumber"
              name="phoneNumber"
              type="number"
              placeholder="Phone number"
              className="input"
              onInput={this.setPhoneNumber}
            />
            <i class="fas fa-phone-square-alt"></i>{" "}
          </div>
          <h6 id="error"></h6>
          <Link className="linksignin" to="/signin">
            {" "}
            You had an account, go to login page{" "}
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

// export default class SignUp extends Component {
//   render() {
//     return (
//       <div>
//         <Divider orientation="center"> SignUp Form</Divider>

//         <Row justify="center" style={{ padding: "5px"}}>
//           <Form>
//           <Form.Item label="Name">
//               <Input />
//             </Form.Item>
//             <Form.Item label="UserName">
//               <Input />
//             </Form.Item>
//             <Form.Item label="Password">
//               <Input.Password />
//             </Form.Item>
//             <Form.Item label="Address">
//             <input type = "text" />
//             </Form.Item>
//             <Form.Item label="PhoneNumber">
//             <input type = "number"/>
//             </Form.Item>
//             <Form.Item>
//               <Button type="primary">SignUp</Button>
//             </Form.Item>

//             <Link to ='/signin' style={{textDecoration: 'none', color: 'blue'}}>I already have an account</Link>
//           </Form>
//         </Row>
//       </div>
//     );
//   }
// }
