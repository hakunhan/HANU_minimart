import React from "react";
import "./newProduct.css";
import { withRouter } from "react-router-dom";
import axios from "axios";
class NewProduct extends React.Component {
  constructor(props){
    super(props);

    this.state= {
      name: "",
      price: "",
      quantity: "",
      description: " ",
      category: " ",
      expireDate: " ",
      picture_URL:"",
      status: "",
      sale: 0,
    }
    this.handleClick = this.handleClick.bind(this);
    this.handleChangeInput = this.handleChangeInput.bind(this);
    this.fetchDataAdd = this.fetchDataAdd.bind(this);

  }
  handleClick() {
    this.props.history.push("/admin/manageproduct");
  }
  handleChangeInput(e) {
    const { name, value } = e.target;
    this.setState({
      [name]: value,
    });
  }
  async fetchDataAdd(){

    console.log("Clickkkkkkkkkkkk");
    const {name, price, quantity,description, status, sale, category, picture_URL,expireDate
    } = this.state;
  
        const body ={
          name: name.trim(),
          price: price,
          quantity: quantity,
          description: description,
          expireDate: expireDate,
          category: category,
          picture_URL: picture_URL,
          status: status,
          sale: sale
      }
      
      const url = "http://localhost:8085/api/product/add";
      const postNew = await axios.post(url, body);
      console.log("neww Product>>>>>>>>>>>>");
        this.props.history.push("/admin/manageproduct");
      

    }
  render() {
    return (
      <div className="newProduct">
        <div className="container">
          <div className="row gutters">
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
                        <h5 class="user-name">food</h5>
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
                      <h6 className="mb-2 text-primary">New Product</h6>
                    </div>
                    <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div className="form-group">
                        <label className ="new" for="name">Product Name</label>
                        <input
                          name = "name"
                          type="text"
                          class="form-control"
                          id="name"
                          onChange={this.handleChangeInput}
                          // placeholder={user.name}
                          // onInput = {this.setName}
                        />
                      </div>
                    </div>
                    <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div className="form-group">
                        <label className ="new" for="price">Price</label>
                        <input
                          name= "price"
                          type="Number"
                          class="form-control"
                          id="price"
                          onChange={this.handleChangeInput}

                          // placeholder= {user.username}
                          // onInput = {this.setUserName}
                        />
                      </div>
                    </div>
                    <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div className="form-group">
                        <label className ="new" for="quantity">Quantity</label>
                        <input
                        name="quantity"
                          type="Number"
                          className="form-control"
                          id="quantity"
                          onChange={this.handleChangeInput}

                          // placeholder= {user.phoneNumber}
                          // onInput = {this.setPhoneNumber}
                        />
                      </div>
                    </div>
                    <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div className="form-group">
                        <label className ="new" for="category">Category</label>
                        <input
                        name= "category"
                          type="text"
                          className="form-control"
                          id="category"
                          // placeholder={user.address}
                          // onInput = {this.setAddress}
                          onChange={this.handleChangeInput}
                        />
                      </div>
                    </div>
                    <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div className="form-group">
                        <label className ="new" for="imgUrl">Picture URL</label>
                        <input
                        name = "picture_URL"
                          type="text"
                          className="form-control"
                          id="imgUrl"
                          onChange={this.handleChangeInput}

                          // placeholder={user.address}
                          // onInput = {this.setAddress}
                        />
                      </div>
                    </div>
                    <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div className="form-group">
                        <label className ="new" for="description">Description</label>
                        <input
                         name = "description"
                          type="text"
                          class="form-control"
                          id="description"
                          onChange={this.handleChangeInput}

                          // placeholder={user.address}
                          // onInput = {this.setAddress}
                        />
                      </div>
                    </div>
                    <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div className="form-group">
                        <label className ="new" for="website">expireDate</label>
                        <input
                        name ="expireDate"
                          type="date"
                          class="form-control"
                          id="website"
                          onChange={this.handleChangeInput}

                          // placeholder={user.address}
                          // onInput = {this.setAddress}
                        />
                      </div>
                    </div>
                    <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div className="form-group">
                        <label className="new" for="sale">
                          Sale
                        </label>
                        <input
                          name ="sale"
                          type="Number"
                          className="form-control"
                          id="sale"
                          onChange={this.handleChangeInput}

                          // placeholder={user.address}
                          // onInput = {this.setAddress}
                        />
                      </div>
                    </div>
                    <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div className="form-group">
                        <label className ="new" for="status">Status</label>
                        <input
                        name="status"
                          type="text"
                          class="form-control"
                          id="sale"
                          onChange={this.handleChangeInput}

                          // placeholder={user.address}
                          // onInput = {this.setAddress}
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
                          className="btn btn-secondary"
                          onClick = {this.handleClick}
                        >
                          Cancel
                        </button>
                        <button
                          type="button"
                          id="submit"
                          name="submit"
                          className="btn btn-primary"
                          onClick= {this.fetchDataAdd}
                        >
                          Add New
                        </button>
                     
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
export default  withRouter(NewProduct);
