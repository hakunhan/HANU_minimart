import React from "react";
import { Link, withRouter } from "react-router-dom";
import axios from "axios";

import "./userDetail.css";
class UserDetail extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: this.props.match.params.id,
      Authentication: this.props.Authentication,
      user: {},
      name: "",
      username: "",
      phoneNumber: "",
      address: "",
    };
    this.setUserName = this.setUserName.bind(this);
    this.setName = this.setName.bind(this);

    this.setPhoneNumber = this.setPhoneNumber.bind(this);
    this.setAddress = this.setAddress.bind(this);
    // this.showData = this.showData.bind(this);
    this.handleClick = this.handleClick.bind(this);
    this.fetchDataUpdate = this.fetchDataUpdate.bind(this);
    this.fetDataDelete = this.fetDataDelete.bind(this);
  }
  handleClick() {
    this.props.history.push("/admin/manageuser");
  }

  setName(event) {
    this.setState({
      name: event.target.value,
    });
  }

  setUserName(event) {
    this.setState({
      username: event.target.value,
    });
  }
  setPhoneNumber(event) {
    this.setState({
      phoneNumber: event.target.value,
    });
  }
  setAddress(event) {
    this.setState({
      address: event.target.value,
    });
  }
  async fetDataDelete(event) {
    event.preventDefault();

    console.log("delete>>>>>>>>>>>", this.props);
    const urlDelete = `http://localhost:8085/api/account/delete?id=${this.state.user.id}`;
    const deleteUser = await axios.get(urlDelete);

    this.props.history.push("/admin/manageuser");
  }

  async fetchDataUpdate(event) {
    event.preventDefault();
    console.log("update.......................");
    const { name, username, phoneNumber, address } = this.state;

    const body = {
      name: name,
      username: username,
      phoneNumber: phoneNumber,
      address: address,
    };
    const urlUpdate = `http://localhost:8085/api/account/update?id=${this.state.user.id}`;
    const postDataUser = await axios.post(urlUpdate, body);
    const userAfterUpdate = postDataUser.data;
    console.log("data sau update ne", postDataUser.data);

    if (userAfterUpdate) {
      this.props.history.push("/admin/manageuser");
    }
    console.log(name);
  }

  async componentDidMount() {
    console.log(this.props.match);
    const url = "http://localhost:8085/api/account/getAll";
    //    const postAuthen = await axios({method: "POST", url, headers:{authorization: this.state.Authentication}})
    const getData = await axios({
      method: "GET",
      url,
      headers: { authorization: this.state.Authentication },
    });
    const data_user = getData.data.content.find(
      (user) => user.id === Number(this.state.id)
    );
    console.log("---------------hhhhhhhhhh", data_user);
    this.setState({
      name: data_user.name,
      phoneNumber: data_user.phoneNumber,
      username: data_user.username,
      address: data_user.address,
      user: data_user,
    });
  }

  render() {
    const { user } = this.state;
    // const a = this.props.match;
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
                            src="https://www.flaticon.com/premium-icon/icons/svg/924/924915.svg"
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
                            type="text"
                            className="form-control"
                            id="fullName"
                            defaultValue={user.name}
                            onInput={this.setName}
                          />
                        </div>
                      </div>
                      <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div className="form-group">
                          <label for="eMail">Username</label>
                          <input
                            type="email"
                            className="form-control"
                            id="eMail"
                            defaultValue={user.username}
                            onInput={this.setUserName}
                          />
                        </div>
                      </div>
                      <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                          <label for="phone">PhoneNumber</label>
                          <input
                            type="text"
                            class="form-control"
                            id="phone"
                            defaultValue={user.phoneNumber}
                            onInput={this.setPhoneNumber}
                          />
                        </div>
                      </div>
                      <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                          <label for="website">Address</label>
                          <input
                            type="url"
                            class="form-control"
                            id="website"
                            defaultValue={user.address}
                            onInput={this.setAddress}
                          />
                        </div>
                      </div>
                    </div>
                    <div className="row gutters">
                      <div className="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        <div className="text-right">
                          <button
                            type="button"
                            id="submit"
                            name="submit"
                            class="btn btn-secondary"
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
                          <button
                            type="button"
                            id="submit"
                            name="submit"
                            className="btn btn-danger"
                            onClick={this.fetDataDelete}
                          >
                            Delete
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

export default withRouter(UserDetail);
