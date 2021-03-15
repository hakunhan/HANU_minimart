import React from "react";
import axios from "axios";
import './product.css'
import { Card } from "antd";
import Product from "./product";
import {Button} from "react-bootstrap";
import { ReloadOutlined } from "@ant-design/icons";

import { ProductConsumer } from "../../contextAPI";
import { withRouter } from "react-router";
 class ProductList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      search: "",
      product: [],
      detailProduct: null,
      steps: 6,
      img: [

      ]
    };
    // this.getProduct = this.getProduct.bind(this);
    // this.getDetailProduct = this.getDetailProduct.bind(this);
    this.handleLoadMore = this.handleLoadMore.bind(this);
    this.fetch_Search_Product = this.fetch_Search_Product.bind(this);
    this.handleChange= this.handleChange.bind(this);
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
    const urlProduct = "http://localhost:8085/api/product/getAll";
    const getDataProduct = await axios.get(urlProduct);
    const product = getDataProduct.data;
    this.setState({
      product: product,
      detailProduct: product,
    });
    
  }
  async fetch_Search_Product(e){
    e.preventDefault();
    console.log(typeof this.state.search)

    const url =  `http://localhost:8085/api/product/getAll?name=${this.state.search}`;
    const data = await axios.get(url);
    const product = data.data;
    this.setState({
      product: product
    })
  }
  handleChange(event) {
    this.setState({ search: event.target.value })
  }
  handleLoadMore() {
    const { product, steps } = this.state;
    if (steps + 6 < product.length) {
      this.setState({
        steps: steps + 6,
      });
    } else {
      this.setState({
        steps: product.length,
      });
    }
  }

  render() {
    const { product, steps } = this.state;
    return (
      
        <div>
        <div class="row justify-content-center">
                        <div class="col-12 col-md-10 col-lg-8">
                            <form class="card card-sm" >
                                <div class="card-body row no-gutters align-items-center">
                                    <div class="col-auto">
                                        <i class="fas fa-search h4 text-body"></i>
                                    </div>
                                    <div class="col">
                                        <input class="form-control form-control-lg form-control-borderless" type="search" placeholder="Search By Name" onChange={this.handleChange}/>
                                    </div>
                                    <div class="col-auto">
                                        <button class="btn btn-lg btn-success" type="submit" onClick = {this.fetch_Search_Product}>Search</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
          {product && product.length > 0 ? (
            <div className ="content_Cart">
            <Card>
              {product.map((product, index) => {
                if (index < steps) {
                  return (
                    <div>
                      <Card.Grid style={{ width: 350 }}>
                        <Product
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


