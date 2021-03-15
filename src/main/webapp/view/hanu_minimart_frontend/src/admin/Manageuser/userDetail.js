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
      phoneNumber: "",
      address: "",


    };
    this.setName = this.setName.bind(this);
    this.setPhoneNumber = this.setPhoneNumber.bind(this);
    this.setAddress = this.setAddress.bind(this);
    // this.showData = this.showData.bind(this);
    this.handleClick = this.handleClick.bind(this);
    
  }
  handleClick(){
    this.props.history.push('/admin/manageuser')
  }


  setName(event){
    this.setState(
      {
        name: event.target.value
      }
    )
  }
  setPhoneNumber(event){
    this.setState(
      {
        phoneNumber: event.target.value
      }
    )
  }
  setAddress(event){
    this.setState(
      {
        address: event.target.value
      }
    )
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
    const data_user = getData.data.content.find((user) =>
      user.id === Number(this.state.id)
    );
    console.log("---------------hhhhhhhhhh" , data_user);
    this.setState({
      user: data_user
    })
  }

  render() {
    const { user } = this.state;
    // const a = this.props.match;
    return (
      <div>
     
        {(user !== null ) ? (
          <div class="container">
            <div class="row gutters">
              <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
                <div class="card h-100">
                  <div class="card-body">
                    <div class="account-settings">
                      <div class="user-profile">
                        <div class="user-avatar">
                          <img
                            src="https://www.flaticon.com/premium-icon/icons/svg/924/924915.svg"
                            alt="Maxwell Admin"
                          />
                        </div>
                        <h5 class="user-name">{user.username}</h5>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
                <div class="card h-100">
                  <div class="card-body">
                    <div class="row gutters">
                      <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        <h6 class="mb-2 text-primary">Personal Details</h6>
                      </div>
                      <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                          <label for="fullName">Full Name</label>
                          <input
                            type="text"
                            class="form-control"
                            id="fullName"
                            placeholder={user.name}
                          />
                        </div>
                      </div>
                      <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                          <label for="eMail">Username</label>
                          <input
                            type="email"
                            class="form-control"
                            id="eMail"
                            placeholder= {user.username}
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
                            placeholder= {user.phoneNumber}
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
                            placeholder={user.address}
                          />
                        </div>
                      </div>
                    </div>
                    <div class="row gutters">
                      <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        <div class="text-right">
                          <button
                            type="button"
                            id="submit"
                            name="submit"
                            class="btn btn-secondary"
                            onClick = {this.handleClick}
                          >
                            Cancel
                          </button>
                          <button
                            type="button"
                            id="submit"
                            name="submit"
                            class="btn btn-primary"
                          >
                            Update
                          </button>
                          <button
                            type="button"
                            id="submit"
                            name="submit"
                            class="btn btn-danger"
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
