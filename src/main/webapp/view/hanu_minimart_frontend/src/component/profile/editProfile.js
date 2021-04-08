import React from "react";
import { Link, withRouter } from "react-router-dom";
import axios from "axios";

import "../../admin/Manageuser/userDetail.css";
import user from "../../admin/Manageuser/user";
class EditProfile extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: "",
      Authentication: this.props.Authentication,
      user: {},
      name: "",
      username: "",
      phoneNumber: "",
      address: "",
      roles: []
    };

    this.handleClick = this.handleClick.bind(this);
    this.fetchDataUpdate = this.fetchDataUpdate.bind(this);
    this.handleChangeInput= this.handleChangeInput.bind(this);
  }
  handleClick() {
    this.props.history.push("/profile");
  }

handleChangeInput(e) {
    const { name, value } = e.target;
    this.setState({
      [name]: value,
    });
  }
 

  async fetchDataUpdate(event) {
    event.preventDefault();
    console.log("update.......................");
    const { name, username, phoneNumber, address,id } = this.state;


    const body = {
      name: name,
      username: username,
      phoneNumber: phoneNumber,
      address: address,
      role: "ROLE_CUSTOMER"
    }
    console.log(body);
    console.log("role",typeof this.state.roles[0].name);
    const urlUpdate = `http://localhost:8085/api/account/update?id=${id}`;
    const postDataUser = await axios.post(urlUpdate, body);
    const userAfterUpdate = postDataUser.data;
    console.log("data sau update ne", postDataUser.data);

    if (userAfterUpdate) {
      this.props.history.push("/profile");
    }
    console.log(name);
  }

  getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

  async componentDidMount() {
    const userId = this.getCookie("uid");
    if(userId){
      this.setState({
        // isLogin: true,
        id: Number(userId)
      })
    }
    const url = "http://localhost:8085/api/account/getAll";
    const getData = await axios({
      method: "GET",
      url,
      headers: { authorization: this.state.Authentication },
    });

    const user = getData.data.content.find(item => 
      (item.id === Number(userId))
      );
  

    this.setState({
      name: user.name,
      phoneNumber: user.phoneNumber,
      username: user.username,
      address: user.address,
      roles: user.roles,
      user: user
    });
  }

  render() {
    const {user}  = this.state;
    console.log(this.props.isLogin);
    return (
      <div>
        {user !== null ? (
          <div className="container">
            <div className="row gutters">
              <div className="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
                <div className="card h-100">
                  <div className="card-body">
                    <div className="account-settings">
                      <div className="user-profile">
                        <div className="user-avatar">
                          <img
                            src="https://i0.wp.com/sheenhousing.org/wp-content/uploads/2015/04/GenericProfilePhoto-Blue-Round.png?fit=1500%2C1500&ssl=1"
                            alt="Maxwell Admin"
                          />
                        </div>
                        <h5 className="user-name">{user.username}</h5>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div className="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
                <div className="card h-100">
                  <div className="card-body">
                    <div className="row gutters">
                      <div className="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        <h6 className="mb-2 text-primary">Personal Details</h6>
                      </div>
                      <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div className="form-group">
                          <label for="fullName">Full Name</label>
                          <input
                          name="name"
                            type="text"
                            className="form-control"
                            id="fullName"
                            defaultValue={user.name}
                            // onInput={this.setName}
                            onChange={this.handleChangeInput}
                          />
                        </div>
                      </div>
                      <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div className="form-group">
                          <label for="eMail">Username</label>
                          <input
                          name ="username"
                            type="text"
                            className="form-control"
                            id="eMail"
                            defaultValue={user.username}
                            // onInput={this.setUserName}
                            onChange={this.handleChangeInput}

                          />
                        </div>
                      </div>
                      <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                          <label for="phone">PhoneNumber</label>
                          <input
                          name="phoneNumber"
                            type="text"
                            className="form-control"
                            id="phone"
                            defaultValue={user.phoneNumber}
                            // onInput={this.setPhoneNumber}
                            onChange={this.handleChangeInput}

                          />
                        </div>
                      </div>
                      <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                          <label for="website">Address</label>
                          <input
                          name="address"
                            type="url"
                            className="form-control"
                            id="website"
                            defaultValue={user.address}
                            // onInput={this.setAddress}
                            onChange={this.handleChangeInput}

                          />
                        </div>
                      </div>
                    </div>
                    <div className="row gutters">
                      <div className="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        <div className="text-right">
                          <button
                            style={{marginLeft: 10}}
                            type="button"
                            id="submit"
                            name="submit"
                            className="btn btn-secondary"
                            onClick={this.handleClick}
                          >
                            Cancel
                          </button>
                          <button
                            type="button"
                            id="submit"
                            name="submit"
                            className="btn btn-primary"
                            onClick={this.fetchDataUpdate}
                          >
                            Update
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        ) : (
          <div> </div>
        )}
      </div>
    );
  }
}

export default withRouter(EditProfile);
