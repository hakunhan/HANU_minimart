import React from "react";
import axios from "axios";

import { Link, withRouter } from "react-router-dom";
import { Card, Row, Col, Button } from "react-bootstrap";
import { InfoCircleOutlined } from "@ant-design/icons";
// import { Card } from 'antd';
import "bootstrap/dist/css/bootstrap.min.css";
import "./product.css";
class Product extends React.Component {
  render() {
    const { id, name, price, expiredDate } = this.props.product;
    return (
      <div className="card_product">
        <Card
          onClick={() => {
            this.props.handleDetails(id);
          }}
          style={{ width: "19rem", heigh: "19rem", backgroundColor: "" }}
        >
          <Link to={`product/${id}`}>
            <Card.Img
              variant="top"
              src={
                "https://vincom.com.vn/sites/default/files/2016-10/VinMart_1.jpg"
              }
            />
          </Link>
          <Card.Body>
            <Card.Title> {name}</Card.Title>
            <p className="Deal">
              shock deal: <i className="sale">SaleOff 50%</i>
            </p>
          </Card.Body>
          <Card.Footer className="footer">
            <Row>
              <Col>
                <Link to={`product/${id}`}>
                  <Button
                    variant="danger"
                    size="sm"
                    // disabled={inCart}
                    // onClick={ () =>{value.addToCart(id)} }
                  >
                    {/* {inCart === true ? (
                      <span>InCart</span>
                    ) : (
                      <span>
                        {" "} */}
                    <InfoCircleOutlined />
                    View details
                    {/* </span>
                    )} */}
                  </Button>
                </Link>
              </Col>
              <Col>
                <strong className="text-muted text-right">
                  Price: {price} <em>$</em>
                </strong>
              </Col>
            </Row>
          </Card.Footer>
        </Card>
      </div>
    );
  }
}
export default withRouter(Product);
