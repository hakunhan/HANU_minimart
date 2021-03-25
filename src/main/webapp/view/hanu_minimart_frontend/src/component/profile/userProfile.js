import React from "react";
import { Link, withRouter } from "react-router-dom";
import "./userProfile.css";
import axios from 'axios'
class UserProfile extends React.Component {
  constructor(props) {
    super(props);
    this.state={
      user: {},
      isLogin: false,
      Authentication: this.props.Authentication,

    }
  //  this.handleClick = this.handleClick.bind(this);
  }
  getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}
  async componentDidMount(){
    const userId = this.getCookie("uid");
    if(userId){
      this.setState({
        isLogin: true,
      })
    }
    const url = "http://localhost:8085/api/account/getAll";
    const getData = await axios({
      method: "GET",
      url,
      headers: { authorization: this.state.Authentication },
    });

    const findUser = getData.data.content.find(item => 
      (item.id === Number(userId))

      
      );
      this.setState({
        user: findUser
      })
    console.log(findUser);

  }
  render() {
    const { user, isLogin } = this.state;
    return (
      <div class="">
      {
        (isLogin) ? ( <div class="main-body">
          <nav aria-label="breadcrumb" class="main-breadcrumb">
            <ol class="breadcrumb"></ol>
          </nav>

          <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex flex-column align-items-center text-center">
                    <img
                      src="https://i0.wp.com/sheenhousing.org/wp-content/uploads/2015/04/GenericProfilePhoto-Blue-Round.png?fit=1500%2C1500&ssl=1"
                      alt="Admin"
                      class="rounded-circle"
                      width="150"
                    />
                    <div class="mt-3">
                      <h4>{user.username}</h4>
                      <p class="text-muted font-size-sm">{user.address}</p>
                      <button class="btn btn-primary"> <Link to ={`/profile/${user.id}`}>Edit</Link> </button>
                      {/* <button class="btn btn-outline-primary"></button> */}
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-8">
              <div class="card mb-3">
                <div class="card-body">
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Full Name</h6>
                    </div>
                    <div class="col-sm-9 text-secondary"> {user.name}</div>
                  </div>
                  <hr />
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">ID</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">{user.id}</div>
                  </div>
                  <hr />
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Phone</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">{user.phoneNumber}</div>
                  </div>
                  <hr />
                 
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Address</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      {user.address}
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>) : "sdsdsd"
      }
       
      </div>
    );
  }
}

export default withRouter(UserProfile);
