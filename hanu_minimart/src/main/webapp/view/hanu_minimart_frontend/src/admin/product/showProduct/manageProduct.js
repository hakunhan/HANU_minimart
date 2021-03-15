import React from "react";
import axios from "axios";
import { Link, withRouter } from "react-router-dom";

import { makeStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Paper from "@material-ui/core/Paper";
import { Button } from "bootstrap";



//  const useStyles = makeStyles({
//   table: {
//     minWidth: 650,
//   },
// });
class ManageProduct extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      Authentication: this.props.Authentication,
      product: null,
    };
  }

  async componentDidMount() {
    console.log("this_________________")
    const urlProduct = "http://localhost:8085/api/product/getAll";
    const getData = await axios.get(urlProduct)
    const product = getData.data;
    console.log("product_______________-",typeof product);
    this.setState({
      product: product,
    });
    const producttest = this.state.product.map(product => 
      console.log(product))
  }
  render() {
    const { product } = this.state;
    // const classes = useStyles();
    return (
      <div>
        {(product !== null) ? (
          <div>
            <TableContainer component={Paper}>
              <Table style={{minWidth: 650}} aria-label="simple table">
                <TableHead>
                  <TableRow>
                    <TableCell>Id</TableCell>
                    <TableCell align="right">Name</TableCell>
                    <TableCell align="right">Price</TableCell>
                    <TableCell align="right">Quantity</TableCell>
                    <TableCell align="right">Category</TableCell>
                    <TableCell align="right">Expired Date</TableCell>
                    <TableCell align="right">Action</TableCell>

                  </TableRow>
                </TableHead>
                <TableBody>
                  { this.state.product.map((product) => (
                    <TableRow key={product.name}>
                      <TableCell component="th" scope="row">
                        {product.id}
                      </TableCell>
                      <TableCell align="right">{product.name}</TableCell>
                      <TableCell align="right">{product.price}</TableCell>
                      <TableCell align="right">{product.quantity}</TableCell>
                      <TableCell align="right">{product.category}</TableCell>
                      <TableCell align="right">{product.expireDate}</TableCell>

                      <TableCell align="right"><Link style= {{color: "blue"}} to ={`/admin/manageproduct/${product.id}`} >Edit</Link></TableCell>
                      
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
    );
  }
}

export default withRouter(ManageProduct);