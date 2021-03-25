import React from "react";

import { Button, Col, Row, Container } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "./Cart.css";
import { withRouter } from "react-router";
import axios from "axios";
import ScrollToTop from "../../component/ScrollTotop/ScrollToTop"
class Cart extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: "",
      idCart: "",
      productInCart: [],
      count: 0,
      quantity1: 1,
      totalPrice: '',
    };
    this.handleChangeInput = this.handleChangeInput.bind(this);
    this.handlePlus = this.handlePlus.bind(this);
    this.handleMinus = this.handleMinus.bind(this);
    this.handleOrderNow = this.handleOrderNow.bind(this);
    this.handleDelete = this.handleDelete.bind(this);
  }

  getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(";").shift();
  }
  //Click icon Delete
  async handleDelete(e,id){
    console.log("deleeeeeeeeeeeeeeeeeeeeee");


    const id_Product = id;
    console.log(id)
    const urlDelete = `http://localhost:8085/api/cartItem/delete/${id_Product}`;
    const fetchDelete = await axios.delete(urlDelete);
    if(fetchDelete){
      // this.props.history.push('/');
      // window.location.reload();
      const id = this.getCookie("uid");
      const urlgetDataCart = `http://localhost:8085/api/cart/getByUser?userId=${id}`;
      const getDataCartItem = await axios.get(urlgetDataCart);
  
      const getDataCart = getDataCartItem.data.cartItem;
      console.log("carrrrrrrrrrrrrrrrrrr22222222",getDataCartItem);
      this.props.handleCart(getDataCart.length);
      console.log(getDataCart.length, "numbeeeeeeeeeeeeeee");
      // window.location.reload()

      this.setState({
        productInCart: getDataCart,
        totalPrice: getDataCartItem.data.totalPrice,
        idCart: getDataCartItem.data.id,
      })
    }
  }
  //click button order now
  async handleOrderNow(event){
    event.preventDefault();
    this.setState({
      totalPrice: 0
    })
    const {id, idCart} = this.state;
    const body = {
      cartId: idCart,
      userId: id
    }
   
    const urlOrder ="http://localhost:8085/api/order/add" ;
    const fetchData = await axios.post(urlOrder, body);
    
    
    if(fetchData.data){
      
      this.props.history.push('/orderhistory')
    }
  }

  handlePlus() {
    this.setState({
      count: this.state.quantity1++,
    });
  }

  handleMinus() {
    if (this.state.quantity1 > 1) {
      this.setState({
        count: this.state.quantity1--,
      });
    } else if ((this.state.quantity1 = 1)) {
      this.setState({
        count: 1,
      });
    }
  }

  handleChangeInput(event) {
    this.setState({
      quantity1: event.target.value,
    });
  }

  async componentDidMount() {
    console.log("v///////////////////////");
    console.log(this.props);
    this.setState({
      id: this.getCookie("uid")
    })
    const urlgetDataCart = `http://localhost:8085/api/cart/getByUser?userId=${this.getCookie("uid")}`;
    const getDataCartItem = await axios.get(urlgetDataCart);

    const getDataCart = getDataCartItem.data.cartItem;
    console.log("carrrrrrrrrrrrrrrrrrr",getDataCartItem.data)
    // const dataForUser = getDataCart.map((item) => {
    //   if (item.cart.user.id === userId) {
    //     arrContainer.push(item);
    //     console.log("lllll", typeof item)
    //   }
    // });
    

    this.setState({
      productInCart: getDataCart,
      totalPrice: getDataCartItem.data.totalPrice,
      idCart: getDataCartItem.data.id
    })
    console.log("getcartitem", this.state.productInCart);
  }

  render() {
    const { productInCart,totalPrice } = this.state;
    console.log(productInCart);
    productInCart.map((product) => (console.log("ca",product)))
    return (
      <div className="container_cart">
        <div className="card shopping-cart">
        <ScrollToTop/>
          { (productInCart.map((product,index) => (
              <div className="" key = {index}>
                  <div className="row">
                      <div className="col-12 col-sm-12 col-md-2 text-center">
                        <img
                        style={{width: 120, height: 80}}
                          // className="img-responsive"
                          src="https://dcassetcdn.com/design_img/6165/17990/17990_139608_6165_image.jpg"/>
                      </div>
                      <div className="col-12 text-sm-center col-sm-12 text-md-left col-md-6">
                        <h5 className="product-name">
                          <strong>{product.productName}</strong>
                        </h5>
                        <h5 className="">Id:{product.id}</h5>
                      </div>

                      <div className="col-12 col-sm-12 text-sm-center col-md-4 text-md-right row">
                        <div
                          className="col-3 col-sm-3 col-md-6 text-md-right"
                          style={{ paddingTop: 5 }}
                        >
                          <h6>
                            <strong>
                              {product.price}
                              <span className="text-muted">x</span>
                            </strong>
                          </h6>
                        </div>
                        <div className="col-4 col-sm-4 col-md-4">
                          <div className="quantity">
                            <input
                              type="button"
                              defaultValue="+"
                              className="plus"
                            />
                            <input
                              type="Number"
                              step={1}
                              max={999}
                              min={1}
                              defaultValue={product.quantity}
                              title="Qty"
                              className="qty"
                              size={4}
                              onChange={this.handleChangeInput}
                            />
                            <input
                              type="button"
                              defaultValue="-"
                              className="minus"
                            />
                          </div>
                        </div>
                        <div className="col-2 col-sm-2 col-md-2 text-right">
                          <button
                          
                            type="button"
                            className="btn btn-outline-danger btn-xs"
                            onClick= { (e) => {this.handleDelete(e, product.id)}}
                          >
                            <i className="fa fa-trash" aria-hidden="true" />
                          </button>
                        </div>
                      </div>
                    </div>
                    <hr />
                  </div>
            )
               
                 
                
              )) }
            
          <div className="order">
          {
            (productInCart.length >=1 ) ? (<button className="btn btn-success" onClick ={this.handleOrderNow}>Order Now</button> ) : 
            <button className="btn btn-success" disabled={true} onClick ={this.handleOrderNow}>Order Now</button>
          }
            {
              (productInCart.length >=1 ) ? <div className="" style={{ margin: 17 }}>
              Total price: <b>{totalPrice}</b> 
            </div> : ""
            }
           
          </div>
        </div>
      </div>
    );
  }
}

export default withRouter(Cart);
