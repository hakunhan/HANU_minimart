import React from "react";
import axios from "axios";
import "./product.css";
import { Card } from "antd";
import Product from "./product";
import { Button } from "react-bootstrap";
import { ReloadOutlined } from "@ant-design/icons";

import { ProductConsumer } from "../../contextAPI";
import { withRouter } from "react-router";

///
// import Button from '@material-ui/core/Button';
import DialogContent from "@material-ui/core/DialogContent";
import Dialog from "@material-ui/core/Dialog";
import ListItemText from "@material-ui/core/ListItemText";
import ListItem from "@material-ui/core/ListItem";
import List from "@material-ui/core/List";
import Divider from "@material-ui/core/Divider";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import IconButton from "@material-ui/core/IconButton";
import Typography from "@material-ui/core/Typography";
// import CloseIcon from '@material-ui/icons/Close';
import Slide from "@material-ui/core/Slide";

const Transition = React.forwardRef(function Transition(props, ref) {
  return <Slide direction="up" ref={ref} {...props} />;
});
class ProductList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      search: "",
      product: [],
      productHot: [],
      detailProduct: null,
      steps: 8,
      img: [],
      productNew: [],
      productSale: [],
      open: false,
    };
    this.handleLoadMore = this.handleLoadMore.bind(this);
    this.fetch_Search_Product = this.fetch_Search_Product.bind(this);
    this.handleChange = this.handleChange.bind(this);

    this.handleClickOpen = this.handleClickOpen.bind(this);
    this.handleClose = this.handleClose.bind(this);
  }

  handleClickOpen() {
    this.setState({
      open: true,
    });
  }

  handleClose() {
    this.setState({
      open: false,
    });
  }

  getItem = (id) => {
    const product = this.state.product.find((item) => item.id === id);
    return product;
  };
  handleDetails = (id) => {
    const product = this.getItem(id);
    this.setState(() => {
      return {
        detailProduct: product,
      };
    });
  };

  async componentDidMount() {
    console.log("okeyyyyyy");
    const urlProduct = "http://localhost:8085/api/product/homepage/getAll";
    const getDataProduct = await axios.get(urlProduct);
    const product = getDataProduct.data;
    this.setState({
      product: product,
      detailProduct: product,
    });
    const productContainerHot = [];
    const productHot = this.state.product.map((item) => {
      if (item.status === "HOT") {
        productContainerHot.push(item);
      }
    });

    const productContainerNew = [];
    const productNew = this.state.product.map((item) => {
      if (item.status === "NEW") {
        productContainerNew.push(item);
      }
    });
    const productContainerSale = [];
    const productSale = this.state.product.map((item) => {
      if (item.sale > 20) {
        productContainerSale.push(item);
      }
    });
    this.setState({
      productHot: productContainerHot,
      productNew: productContainerNew,
      productSale: productContainerSale,
    });
    console.log("salesssssssssssss", this.state.productHot);
  }
  async fetch_Search_Product(e) {
    e.preventDefault();
    console.log(typeof this.state.search);

    const url = `http://localhost:8085/api/product/homepage/getAll?name=${this.state.search}`;
    const data = await axios.get(url);
    const product = data.data;
    this.setState({
      product: product,
    });
  }
  handleChange(event) {
    this.setState({ search: event.target.value });
  }
  handleLoadMore() {
    const { product, steps } = this.state;
    if (steps + 8 < product.length) {
      this.setState({
        steps: steps + 8,
      });
    } else {
      this.setState({
        steps: product.length,
      });
    }
  }

  render() {
    const { product, steps, productHot, productNew, productSale } = this.state;
    return (
      <div>
        <div class="search">
          <div class="col-auto">
        

            <div>
              
              <button
                class="btn btn-md btn-success"
                type="submit"
                onClick={this.handleClickOpen}
              >
                Search Product Here
              </button>
              <Dialog
                fullScreen
                open={this.state.open}
                onClose={this.handleClose}
                TransitionComponent={Transition}
              >
                <AppBar style={{ position: "relative" }}>
                  <Toolbar
                    style={{
                      backgroundColor: "#001133",
                      color: "#fff",
                      display: "flex",
                      justifyContent: "space-between",
                    }}
                  >
                    {/* <IconButton edge="start"  onClick={this.handleClose} aria-label="close">
              <CloseIcon />
              x
            </IconButton> */}
                    <Typography variant="h6" style={{ color: "#fff" }}>
                      Search
                    </Typography>
                    <Button
                      autoFocus
                      color="inherit"
                      onClick={this.handleClose}
                    >
                      Exit
                    </Button>
                  </Toolbar>
                </AppBar>

                <DialogContent>
                  <div className="row justify-content-center">
                    <div className="col-12 col-md-10 col-lg-8">
                      <form className="card card-sm">
                        <div className="card-body row no-gutters align-items-center">
                          <div className="col-auto">
                            <i className="fas fa-search h4 text-body" />
                          </div>
                          <div className="col">
                            <input
                              className="form-control form-control-lg form-control-borderless"
                              type="search"
                              placeholder="Search topics or keywords"
                              onChange={this.handleChange}
                            />
                          </div>
                          <div className="col-auto">
                            <button
                              className="btn btn-lg btn-success"
                              type="submit"
                              onClick={this.fetch_Search_Product}
                            >
                              Search
                            </button>
                          </div>
                          {/*end of col*/}
                        </div>
                      </form>
                    </div>
                    {/*end of col*/}
                  </div>

                  {product && product.length > 0 ? (
                    <div className="content_Cart">
                      <Card>
                        {product.map((product, index) => {
                          if (index < steps) {
                            return (
                              <div>
                                <Card.Grid style={{ width: 350 }} key={index}>
                                  <Product
                                    key={index}
                                    key={product.id}
                                    product={product}
                                    getItem={this.getItem}
                                    handleDetails={this.handleDetails}
                                  />
                                </Card.Grid>
                              </div>
                            );
                          }
                        })}
                      </Card>

                      <div className="load-more-div">
                        <Button
                          variant="success"
                          size="sm"
                          onClick={this.handleLoadMore}
                        >
                          <ReloadOutlined />
                          Load more
                        </Button>
                      </div>
                    </div>
                  ) : (
                    <div> ko có dữ liệu</div>
                  )}
                </DialogContent>
              </Dialog>
            </div>
          </div>
        </div>

        <br />

       

        {productHot ? (
          <div className="content_Cart">
            <Card>
              <div>
                <Button variant="outline-danger">HOT</Button>
              </div>
              {productHot.map((productHot, index) => {
                if (index < 3) {
                  return (
                    <div>
                      <Card.Grid style={{ width: 350 }} key={index}>
                        <Product
                          key={index}
                          product={productHot}
                          getItem={this.getItem}
                          handleDetails={this.handleDetails}
                        />
                      </Card.Grid>
                    </div>
                  );
                }
              })}
            </Card>

            
          </div>
        ) : (
          <div> ko có dữ liệu</div>
        )}
        {productNew ? (
          <div className="content_Cart">
            <Card>
              <div>
                <Button variant="outline-warning">NEW</Button>
              </div>
              {productNew.map((productNew, index) => {
                if (index < 3) {
                  return (
                    <div>
                      <Card.Grid style={{ width: 350 }} key={index}>
                        <Product
                          key={index}
                          product={productNew}
                          getItem={this.getItem}
                          handleDetails={this.handleDetails}
                        />
                      </Card.Grid>
                    </div>
                  );
                }
              })}
            </Card>

            
          </div>
        ) : (
          <div> ko có dữ liệu</div>
        )}

        {productSale ? (
          <div className="content_Cart">
            <Card>
              <div>
                <Button variant="outline-success">SALE</Button>
              </div>
              {productSale.map((productSale, index) => {
                if (index < 3) {
                  return (
                    <div>
                      <Card.Grid style={{ width: 350 }} key={index}>
                        <Product
                          key={index}
                          product={productSale}
                          getItem={this.getItem}
                          handleDetails={this.handleDetails}
                        />
                      </Card.Grid>
                    </div>
                  );
                }
              })}
            </Card>

           
          </div>
        ) : (
          <div> ko có dữ liệu</div>
        )}


        {product && product.length > 0 ? (
                    <div className="content_Cart">
                      <Card>
                      <div>
                      <Button variant="outline-primary">NEW</Button>

                      </div>
                        {product.map((product, index) => {
                          if (index < steps) {
                            return (
                              <div>
                                <Card.Grid style={{ width: 350 }} key={index}>
                                  <Product
                                    key={index}
                                    key={product.id}
                                    product={product}
                                    getItem={this.getItem}
                                    handleDetails={this.handleDetails}
                                  />
                                </Card.Grid>
                              </div>
                            );
                          }
                        })}
                      </Card>

                      <div className="load-more-div">
                        <Button
                          variant="success"
                          size="sm"
                          onClick={this.handleLoadMore}
                        >
                          <ReloadOutlined />
                          Load more
                        </Button>
                      </div>
                    </div>
                  ) : (
                    <div> ko có dữ liệu</div>
                  )}
      </div>


    );
  }
}
export default withRouter(ProductList);
