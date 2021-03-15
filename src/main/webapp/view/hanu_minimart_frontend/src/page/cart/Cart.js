import React from "react";

import { Button, Col, Row, Container } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "./Cart.css";
import { ProductConsumer } from "../../contextAPI";

export default class Cart extends React.Component {
  render() {
    return (
      <section>
        <ProductConsumer>
          {(value) => {
            if (value.Cart.length > 0) {
              return (
                <div>
                  <div>
                    <h2 className ="header">Your Cart</h2>
                  </div>

                  <div className="container-fluid text-center">
                    <div className="row">
                      <div className="col-10 max-auto col-lg-2">
                        <strong> Product</strong>
                      </div>
                      <div className="col-10 max-auto col-lg-2">
                        <strong>Name of Product</strong>
                      </div>
                      <div className="col-10 max-auto col-lg-2">
                        <strong>Price</strong>
                      </div>
                      <div className="col-10 max-auto col-lg-2">
                        <strong>Quantity</strong>
                      </div>
                      <div className="col-10 max-auto col-lg-2">
                        <strong> Remove</strong>
                      </div>
                      <div className="col-10 max-auto col-lg-2">
                        <strong> Total</strong>
                      </div>
                    </div>
                  </div>
                  {value.Cart.map((cartData) => {
                    return (
                      <div className="container-fluid text-center">
                        <div className="row">
                          <div className="col-10 max-auto col-lg-2">
                            <img
                              style={{ width: "6rem", heigh: "4rem" }}
                              src={cartData.img}
                              className="img-fluid"
                            />
                          </div>
                          <div className="col-10 max-auto col-lg-2">
                            {cartData.title}
                          </div>
                          <div className="col-10 max-auto col-lg-2">
                            {cartData.price}
                          </div>
                          <div className="col-10 max-auto col-lg-2">
                            <Button
                            
                              size="sm"
                              className="qtyminus"
                              variant="outline-primary"
                              onClick={() => value.decrement(cartData.id)}
                            >-</Button>
                            {cartData.count}
                            <Button
                              size="sm"
                              className="qtyplus"
                              variant="outline-primary"
                              onClick={() => value.increment(cartData.id)}
                            >+</Button>
                          </div>
                          <div className="col-10 max-auto col-lg-2">
                            <Button
                              variant="primary"
                              onClick={() => {
                                value.removeItem(cartData.id);
                              }}
                              size="sm"
                            >
                              Remove
                            </Button>
                          </div>
                          <div className="col-10 max-auto col-lg-2">
                            {cartData.total}
                          </div>
                        </div>
                      </div>
                    );
                  })}

                  <hr></hr>
                    <Container>
                      <Row>
                        <Col>
                          <strong>Total</strong> {value.cartSubTotal}
                        </Col>
                      </Row>
                    </Container>
                </div>
              );
            } else {
              return (
                <div>
                  <h3>
                    CurrentLy your cart is{" "}
                    <span style={{ color: "red" }}>Empty</span>
                  </h3>
                </div>
              );
            }
          }}
        </ProductConsumer>
      </section>
    );
  }
}
