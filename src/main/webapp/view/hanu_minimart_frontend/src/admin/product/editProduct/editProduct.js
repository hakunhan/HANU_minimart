import React from "react";
import "../NewProduct/newProduct.css";
import { withRouter } from "react-router-dom";
import axios from "axios";
class EditProduct extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: this.props.match.params.id,
      product: {},
      name: "",
      price: 0,
      quantity: 0,
      description: "",
      category: "",
      sale: "",
      expireDate: "",
      picture_URL:"",
      status:"",
    };
    this.handleChangeInput = this.handleChangeInput.bind(this);
    this.fetDataUpdate = this.fetDataUpdate.bind(this);
    this.handleClick = this.handleClick.bind(this);
    this.fetchDataDelete = this.fetchDataDelete.bind(this);
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

  async fetchDataDelete(){
    console.log("delete_________________");
    const Url = `http://localhost:8085/api/product/delete/${this.state.id}`;

    const DeleteData = await axios.delete(Url);
    this.props.history.push("/admin/manageproduct");

    
  }
  async fetDataUpdate(event){
    event.preventDefault();
    
      console.log("update product //////////////")
      const {id,name, price, quantity, description,expireDate, sale, category, picture_URL, status} = this.state;
      const id1 = Number(id);

    //   const body ={
    //       name: name,
    //       price: price,
    //       quantity: quantity,
    //       status: status,
    //       quantity: quantity,
    //       status: status,
    //       sale: sale,
    //       expireDate: expireDate,
    //       updateAt: updateAt,
    //       category: category,
    //       picture_URL: picture_URL,
    //   }
    //   console.log(body);

      try{
          const url =`http://localhost:8085/api/product/update/${id1}?name=${name}&price=${price}&quantity=${quantity}&description=${description}&category=${category}&picture_URL=${picture_URL}&sale=${sale}&expireDate=${expireDate}&status=${status}`;
          console.log("link kkkkkkkkkkkkkk",url);
          const sendData = await axios.put(url);
          console.log(sendData.data);

            this.props.history.push("/admin/manageproduct");
          
      }catch (e){
          alert("update false")
      }
  }
  

  async componentDidMount() {
    console.log("Propssss đây ..........", typeof this.state.id);

    const url = "http://localhost:8085/api/product/getAll";
    const getDataProduct = await axios.get(url);
    const data = getDataProduct.data.find(
      (item) => item.id === Number(this.state.id)
    );
    console.log("/////////////////", data);
    this.setState({
      product: data,
     
    });
    this.setState({
        name: data.name,
        price: data.price,
        quantity: data.quantity,
        status: data.status,
        quantity: data.quantity,
        status: data.status,
        sale: data.sale,
        expireDate: data.expireDate,
        updateAt: data.updateAt,
        category: data.category,
        description: data.description,
        picture_URL: data.picture_URL,
    })
  }

  render() {
    const { product } = this.state;
    return (
      <div className="newProduct">
        <div className="container">
          <div className="row gutters">
            <div className="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
              <div className="card h-100">
                <div className="card-body">
                  <div className="account-settings">
                    <div className="user-profile">
                      <div className="user-avatar">
                        <img
                          src={product.picture_URL}
                          alt={product.name}
                        />
                      </div>
                      <h5 className="user-name">{product.name}</h5>
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
                      <h6 className="mb-2 text-primary">Product</h6>
                    </div>
                    <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div className="form-group">
                        <label className="new" for="name">
                          Product Name
                        </label>
                        <input
                        name ="name"
                          type="text"
                          className="form-control"
                          id="name"
                          defaultValue={product.name}
                          onChange={this.handleChangeInput}
                          // placeholder={user.name}
                          // onInput = {this.setName}
                        />
                      </div>
                    </div>
                    <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div className="form-group">
                        <label className="new" for="price">
                          Price
                        </label>
                        <input
                        name="price"
                          type="Number"
                          class="form-control"
                          id="price"
                          defaultValue={product.price}
                          onChange={this.handleChangeInput}

                          // placeholder= {user.username}
                          // onInput = {this.setUserName}
                        />
                      </div>
                    </div>
                    <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div className="form-group">
                        <label className="new" for="quantity">
                          Quantity
                        </label>
                        <input
                        name= "quantity"
                          type="Number"
                          className="form-control"
                          id="quantity"
                          defaultValue={product.quantity}
                          onChange={this.handleChangeInput}

                          // placeholder= {user.phoneNumber}
                          // onInput = {this.setPhoneNumber}
                        />
                      </div>
                    </div>
                    <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div className="form-group">
                        <label className="new" for="category">
                          Category
                        </label>
                        <input
                        name= "category"
                          type="text"
                          className="form-control"
                          id="category"
                          defaultValue={product.category}
                          onChange={this.handleChangeInput}

                          // placeholder={user.address}
                          // onInput = {this.setAddress}
                        />
                      </div>
                    </div>
                    <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div className="form-group">
                        <label className="new" for="imgUrl">
                          Picture URL
                        </label>
                        <input
                        name= "picture_URL"
                          type="text"
                          className="form-control"
                          id="imgUrl"
                          defaultValue={product.picture_URL}
                          onChange={this.handleChangeInput}

                          // placeholder={user.address}
                          // onInput = {this.setAddress}
                        />
                      </div>
                    </div>
                    <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div className="form-group">
                        <label className="new" for="description">
                          Description
                        </label>
                        <input
                        name="description"
                          type="text"
                          className="form-control"
                          id="description"
                          defaultValue = {product.description}
                          onChange={this.handleChangeInput}

                          // placeholder={user.address}
                          // onInput = {this.setAddress}
                        />
                      </div>
                    </div>
                    <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div className="form-group">
                        <label className="new" for="expireDate">
                          expireDate
                        </label>
                        <input
                        name ="expireDate"
                          type="text"
                          class="form-control"
                          id="expireDate"
                          type="date"
                          defaultValue={product.expireDate}
                          onChange={this.handleChangeInput}

                          // placeholder={user.address}
                          // onInput = {this.setAddress}
                        />
                      </div>
                    </div>
                    {/* <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div className="form-group">
                        <label className="new" for="updateAt">
                          UpdateAt
                        </label>
                        <input
                        name= "updateAt"
                          type="url"
                          class="form-control"
                          id="updateAt"
                          defaultValue={product.updateAt}
                          onChange={this.handleChangeInput}

                          // placeholder={user.address}
                          // onInput = {this.setAddress}
                        />
                      </div>
                    </div> */}
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
                          defaultValue={product.sale}
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
                          defaultValue={product.status}
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
                          onClick= {this.fetDataUpdate}
                        >
                          Update
                        </button>
                        <button
                          type="button"
                          id="submit"
                          name="submit"
                          className="btn btn-danger"
                          onClick= {this.fetchDataDelete}
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
      </div>
    );
  }
}
export default withRouter(EditProduct);
