import axios from "axios";
import React from "react";
import { withRouter } from "react-router";
import { ImportOutlined } from "@ant-design/icons";
import { Link } from "react-router-dom";
class ViewDetailHistory extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      orderDetail: [],
      totalPrice: "",
    };
  }

  async componentDidMount() {
    console.log("orderId ,,,,,,", this.props);
    console.log(this.props.match.params.id);
    const urlCart = `http://localhost:8085/api/order/${this.props.match.params.id}`;

    const fetchData = await axios.get(urlCart);

    console.log("nnnnnnnnnnnnn", fetchData.data);
    this.setState({
      orderDetail: fetchData.data.orderLine,
      totalPrice: fetchData.data.total,
    });
  }
  render() {
    const { orderDetail, totalPrice } = this.state;
    return (
      <div>
        <div className="container_cart">
          <button className="btn btn-primary">
          <Link to ="/orderhistory"> <ImportOutlined /></Link>
           
          </button>

          <div className="card shopping-cart">
            {orderDetail.map((product, index) => (
              <div className="" key={index}>
                <div className="row">
                  <div className="col-12 col-sm-12 col-md-2 text-center">
                    <img
                      style={{ width: 100, height: 100 }}
                      // className="img-responsive"
                      src="https://pbs.twimg.com/media/EUU6kHiUMAAcOZs.png"
                    />
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
                      >
                        <i className="fa fa-trash" aria-hidden="true" />
                      </button>
                    </div>
                  </div>
                </div>
                <hr />
              </div>
            ))}

            <div className="order">
              {/* <button className="btn btn-primary" onClick ={this.handleOrderNow}><ImportOutlined /></button> */}
              <div className="" style={{ margin: 17 }}>
                Total price: <b>{totalPrice}</b>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default withRouter(ViewDetailHistory);
