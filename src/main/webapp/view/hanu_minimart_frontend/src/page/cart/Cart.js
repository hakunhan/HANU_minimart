import React from "react";

import { Button, Col, Row, Container } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "./Cart.css";
import { withRouter } from "react-router";
import axios from "axios";

class Cart extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      productInCart: [],
      count: 0,
      quantity1: 1,
    };
    this.handleChangeInput = this.handleChangeInput.bind(this);
    this.handlePlus = this.handlePlus.bind(this);
    this.handleMinus = this.handleMinus.bind(this);
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
    const arrContainer= [];
    const { productInCart } = this.state;
    console.log("v///////////////////////");
    const userId = this.props.user.id;
    console.log(userId);
    const urlgetDataCart = "http://localhost:8085/api/cartItems";
    const getDataCartItem = await axios.get(urlgetDataCart);

    const getDataCart = getDataCartItem.data;
    console.log(getDataCart)
    const dataForUser = getDataCart.map((item) => {
      if (item.cart.user.id === userId) {
        arrContainer.push(item);
        console.log("lllll", typeof item)
      }
    });
    

    this.setState({
      productInCart: arrContainer
    })
    console.log("getcartitem", productInCart);
  }

  render() {
    const { productInCart } = this.state;
    console.log(productInCart);
    productInCart.map((product) => (console.log("ca",product)))
    return (
      <div className="container_cart">
        <div className="card shopping-cart">
          { (productInCart.map((item,index) => (
              <div className="" key = {index}>
                  <div className="row">
                      <div className="col-12 col-sm-12 col-md-2 text-center">
                        <img
                        style={{width: 120, height: 80}}
                          // className="img-responsive"
                          src={item.product.picture_URL}/>
                      </div>
                      <div className="col-12 text-sm-center col-sm-12 text-md-left col-md-6">
                        <h5 className="product-name">
                          <strong>{item.product.name}</strong>
                        </h5>
                        <h5 className="">Id:{item.product.id}</h5>
                      </div>

                      <div className="col-12 col-sm-12 text-sm-center col-md-4 text-md-right row">
                        <div
                          className="col-3 col-sm-3 col-md-6 text-md-right"
                          style={{ paddingTop: 5 }}
                        >
                          <h6>
                            <strong>
                              {item.product.price}
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
                              defaultValue={1}
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
            <button className="btn btn-success">Checkout</button>
            <div className="" style={{ margin: 17 }}>
              Total price: <b>50.00€</b>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default withRouter(Cart);
