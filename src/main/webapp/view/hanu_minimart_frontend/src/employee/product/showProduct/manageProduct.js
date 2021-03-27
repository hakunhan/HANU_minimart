import React from "react";
import axios from "axios";
import { Link, withRouter } from "react-router-dom";

import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
// import { Button } from "bootstrap";

import { Input } from "antd";
import { Button } from "react-bootstrap";
import "../header/header.css";
import {
  PlusCircleOutlined,
  SearchOutlined,
  EditOutlined,
  DeleteOutlined,
} from "@ant-design/icons";

class ManageProduct extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      Authentication: this.props.Authentication,
      product: null,
      search: ""
     
    };
    this.handleChange = this.handleChange.bind(this);
    // this.handleChangeRowsPerPage = this.handleChangeRowsPerPage.bind(this);
    this.fetchDataSearch = this.fetchDataSearch.bind(this);
  }
  handleChange(e){
    const {name, value} = e.target;
    this.setState({
      [name]: value
    })
  }

  async fetchDataSearch(event){
    event.preventDefault();
    console.log("aaaaaaaaaaaaaaaaaaa")

    const {search} = this.state;
    console.log(search);
    const url = `http://localhost:8085/api/product/getAll?name=${search}`;
    const fetData = await axios.get(url);
    this.setState({
      product: fetData.data
    })
    console.log("")
  }

  
  async componentDidMount() {
    console.log("this_________________");
    const urlProduct = "http://localhost:8085/api/product/getAll";
    const getData = await axios.get(urlProduct);
    const product = getData.data;
    console.log("product_______________-", typeof product);
    this.setState({
      product: product,
    });
  }
  render() {
    const { product } = this.state;
    return (
      <div>
        <div className="Header">
          <div className="newProduct">
            {/* <Link to="" className="linkNewP"> */}
              <Button variant="primary" size="sm">
                <PlusCircleOutlined />
                New Product
              </Button>
            {/* </Link> */}
          </div>
          <form className="Search">
            <SearchOutlined />
            <Input name = "search" key="search" placeholder="Search" onChange={this.handleChange} />
            <Button type= "submit" key ="button" onClick ={this.fetchDataSearch}>Submit</Button>
          </form>

          <div className="ViewAll">
            <Button key ="button" variant="primary" size="sm">
              View All
            </Button>
          </div>
          <div className="ViewProductNear">
            <Button variant="primary" size="sm">
              View Product Near Expiration
            </Button>
          </div>
        </div>
        <div>
          {product !== null ? (
            <div>
                <TableContainer component={Paper}>
                  <Table style={{ minWidth: 650 }} aria-label="simple table">
                    <TableHead>
                      <TableRow>
                        <TableCell>Id</TableCell>
                        <TableCell align="right">Name</TableCell>
                        <TableCell align="right">Price</TableCell>
                        <TableCell align="right">Quantity</TableCell>
                        <TableCell align="right">Category</TableCell>
                        <TableCell align="right">Sale</TableCell>
                        <TableCell align="right">Expired Date</TableCell>
                        <TableCell align="right">Action</TableCell>
                      </TableRow>
                    </TableHead>
                    <TableBody>
                      {this.state.product.map((product, index) => (
                        <TableRow key={index}>
                          <TableCell component="th" scope="row">
                            {product.id}
                          </TableCell>
                          <TableCell align="right">{product.name}</TableCell>
                          <TableCell align="right">{product.price}</TableCell>
                          <TableCell align="right">
                            {product.quantity}
                          </TableCell>
                          <TableCell align="right">
                            {product.category}
                          </TableCell>
                          <TableCell align="right">
                            {product.sale}
                          </TableCell>
                          <TableCell align="right">
                            {product.expireDate}
                          </TableCell>

                          <TableCell align="right">
                          <Link
                              style={{ color: "#fff" }}
                              to={`/employee/manageproduct/edit/${product.id}`}
                            >
                             <Button>Edit</Button> 
                            </Link>
                            
                          </TableCell>
                        </TableRow>
                      ))}
                    </TableBody>
                  </Table>
                </TableContainer>
               
            </div>
          ) : (
            <div> </div>
          )}
        </div>
      </div>
    );
  }
}

export default withRouter(ManageProduct);
