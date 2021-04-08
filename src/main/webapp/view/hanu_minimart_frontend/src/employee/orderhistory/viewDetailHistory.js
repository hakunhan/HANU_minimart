import axios from "axios";
import React from "react";
import "./orderhistory.css";
import { withRouter, Link } from "react-router-dom";
import { ImportOutlined } from "@ant-design/icons";

import { makeStyles } from "@material-ui/core/styles";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import DialogTitle from "@material-ui/core/DialogTitle";
import FormControl from "@material-ui/core/FormControl";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import Select from "@material-ui/core/Select";
import Switch from "@material-ui/core/Switch";

// const useStyles = makeStyles((theme) => ({
//   form: {
//     display: "flex",
//     flexDirection: "column",
//     margin: "auto",
//     width: "fit-content",
//   },
//   formControl: {
//     marginTop: theme.spacing(2),
//     minWidth: 120,
//   },
//   formControlLabel: {
//     marginTop: theme.spacing(1),
//   },
// }));
class ViewDetailHistory extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      orderDetail: [],
      totalPrice: "",

      open: false,
      fullwidth: true,
      maxWidth: "sm",
      allProduct: [],
      quantity: [],
      status: "",
      orderId: "",
    };
    this.handleClose = this.handleClose.bind(this);
    this.handleClickOpen = this.handleClickOpen.bind(this);
    this.handleMaxWidthChange = this.handleMaxWidthChange.bind(this);
    this.handleFullWidthChange = this.handleFullWidthChange.bind(this);
    this.handleData = this.handleData.bind(this);
    this.checkStorageAccepted = this.checkStorageAccepted.bind(this);
    this.checkStorageCancel = this.checkStorageCancel.bind(this);

    // this.fetchDataCheckStorage = this.fetchDataCheckStorage.bind(this);
  }

  async checkStorageAccepted() {
    console.log("Aceeeeeeeeeeeeeeeeeeee");

    try {
      const urlStatus = `http://localhost:8085/api/order/updateStatus/${this.props.match.params.id}?status=ACCEPTED`;
      const updateStatus = await axios.put(urlStatus);
    } catch (e) {
      alert("the product is not enough");
    }

    this.setState({
      open: false,
    });
    this.props.history.push("/employee/manageorder");
  }

  async checkStorageCancel() {
    console.log("Cancccccccccccccccccccc");

    try {
      const urlStatus = `http://localhost:8085/api/order/updateStatus/${this.props.match.params.id}?status=CANCEL`;
      const updateStatus = await axios.put(urlStatus);
    } catch (e) {
      alert("not done");
    }

    this.setState({
      open: false,
    });

    this.props.history.push("/employee/manageorder");
  }
  handleData(productName) {
    const productValid = this.state.allProduct.filter(
      (item) => item.name === productName
    );
    console.log("dảkkkkkkkkkkk", productValid.quantity);
    return productValid.quantity;
  }

  // async fetchDataCheckStorage(name){
  //   const linkCheck = `http://localhost:8085/api/productcheckStorage/${name}`;
  //     const fetchLink = await axios.get(linkCheck);
  //     console.log(fetchLink.data);
  //     return fetchLink.data;
  // }
  handleClickOpen(name) {
    this.setState({
      open: true,
    });
  }
  handleClose() {
    this.setState({
      open: false,
    });
  }
  handleMaxWidthChange(event) {
    this.setState({
      maxWidth: event.target.value,
    });
  }
  handleFullWidthChange(event) {
    this.setState({
      fullwidth: event.target.value,
    });
  }

  async componentDidMount() {
    console.log("orderId ,,,,,,", this.props);
    console.log(this.props.match.params.id);
    const urlCart = `http://localhost:8085/api/order/${this.props.match.params.id}`;

    const fetchData = await axios.get(urlCart);

    console.log("nnnnnnnnnnnnn", fetchData.data);

    const quantity = fetchData.data.orderLine.map((item) => {
      console.log(
        item,
        `http://localhost:8085/api/product/checkStorage/${item.productName}`
      );
      return axios
        .get(
          `http://localhost:8085/api/product/checkStorage/${item.productName}`
        )
        .then((res) => res.data)
        .catch((e) => console.log(e));
    });
    Promise.all(quantity).then((res) => {
      console.log("res--------", res);

      this.setState({ quantity: res });
    });

    // const handleQuantity = allPro.map( item => {
    //   fetchData.data.orderLine.map( item2 => {
    //     if(item2.productName === item.name ){
    //       this.state.quantity.push[item.quantity]
    //     }
    //   })
    // })
    this.setState({
      // allProduct: allPro,
      orderDetail: fetchData.data.orderLine,
      totalPrice: fetchData.data.total,
    });
  }
  render() {
    const { orderDetail, totalPrice, quantity } = this.state;
    return (
      <div>
        <div className="container_cart">
          <Link to="/employee/manageorder">
            <button className="btn btn-primary">
              <ImportOutlined />
            </button>
          </Link>

          <div className="card shopping-cart">
            {orderDetail.map((product, index) => (
              <div className="" key={index}>
                <div className="row">
                  <div className="col-12 col-sm-12 col-md-2 text-center">
                    <img
                      style={{ width: 120, height: 80 }}
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
                    {/* <div className="col-2 col-sm-2 col-md-2 text-right">
                      <button
                        type="button"
                        className="btn btn-outline-danger btn-xs"
                      >
                        <i className="fa fa-trash" aria-hidden="true" />
                      </button>
                    </div> */}
                  </div>
                </div>
                <hr />
              </div>
            ))}

            <div className="order">
              <React.Fragment>
                <button
                  className="btn btn-primary"
                  onClick={this.handleClickOpen}
                >
                  Check Storage
                </button>
                <Dialog
                  fullWidth={this.state.fullWidth}
                  maxWidth={this.state.maxWidth}
                  open={this.state.open}
                  onClose={this.handleClose}
                  aria-labelledby="max-width-dialog-title"
                >
                  <DialogTitle id="max-width-dialog-title">
                    Check Storage
                  </DialogTitle>
                  <DialogContent>
                    <form>
                      <div className="informOrder">
                        {orderDetail.map((product, index) => (
                          <div key={index}>
                            <input
                            style={{}}
                              className="nameProduct"
                              value={product.productName}
                            />
                            <div className="quantity_container">
                              <input
                                className="quantity"
                                value={product.quantity}
                              />

                              <input
                                className="quantity"
                                defaultValue={quantity[index]}
                              />

                              {product.enough === true ? (
                                <div
                                  style={{
                                    color: "#ffff",
                                    backgroundColor: "green",
                                    borderRadius: 4,
                                    width: 66,
                                    height:27,
                                    display: "flex",
                                    alignItems: "center",
                                    justifyContent: "center"
                                  }}
                                >
                                  Sufficient
                                </div>
                              ) : (
                                <div
                                  style={{
                                    color: "#ffff",
                                    backgroundColor: "red",
                                    borderRadius: 7,
                                    width: 66,
                                    height:27,
                                    display: "flex",
                                    alignItems: "center",
                                    justifyContent: "center"
                                  }}
                                >
                                  Insufficient
                                </div>
                              )}
                            </div>
                          </div>
                        ))}
                      </div>
                    </form>
                  </DialogContent>
                  <DialogActions>

               
                    <button
                      onClick={this.checkStorageAccepted}
                      className="btn btn-primary"
                    >
                      Accept
                    </button>
                    <button
                      className="btn btn-danger"
                      onClick={this.handleClose}
                    >
                      Cancel
                    </button>
                  </DialogActions>
                </Dialog>
              </React.Fragment>
              <button
                className="btn btn-danger"
                style={{marginLeft: 10}}
                onClick={() => {
                  if (
                    window.confirm(
                      "Are you sure you wish to cancel this order?"
                    )
                  )
                    this.checkStorageCancel();
                }}
              >
                Cancel Order
              </button>
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
