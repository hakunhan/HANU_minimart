import axios from "axios";
import React from "react";
import { Link, withRouter } from "react-router-dom";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Paper from "@material-ui/core/Paper";
import { Button } from "react-bootstrap";
import ScollToTop from '../../component/ScrollTotop/ScrollToTop'
class OrderHistory extends React.Component{
    constructor(props){
        super(props);
        this.state={
            orderHistory: {}
        }
    }

    getCookie(name) {
        const value = `; ${document.cookie}`;
        const parts = value.split(`; ${name}=`);
        if (parts.length === 2) return parts.pop().split(";").shift();
      }
    async componentDidMount(){
        console.log("order ....................")
        const id = this.getCookie('uid');

        const urlOrder = `http://localhost:8085/api/order/getByUserId?userId=${id}`;
        const fetchOrder = await axios.get(urlOrder);

        console.log(fetchOrder.data)
        this.setState({
            orderHistory: fetchOrder.data
        })
        console.log(this.state.orderHistory);
        
    }
    render(){
        const {orderHistory} = this.state;
        return (
            <div>
            <div>
            <ScollToTop/>
              <form>
                <input
                  style={{ border: "1px solid blue", width: 300, height: 37 }}
                  placeholder="Search "
                  onInput={this.handleChange}
                />
                <Button type="submit" onClick={this.fetch_Search_User}>
                  Search
                </Button>
              </form>
            </div>
            <br />
            {orderHistory.length >= 1 ? (
              <div>
                <TableContainer component={Paper}>
                  <Table style={{ minWidth: 650 }} aria-label="simple table">
                    <TableHead>
                      <TableRow>
                        <TableCell align="right">Id</TableCell>
                        <TableCell align="right">Address</TableCell>
                        <TableCell align="right">Delivery Date</TableCell>
                        <TableCell align="right">Status</TableCell>
                        <TableCell align="right">Total</TableCell>
                        <TableCell align="right">Action</TableCell>
                      </TableRow>
                    </TableHead>
                    <TableBody>
                      {orderHistory.map((order,index) => (
                        <TableRow key={index}>
                          <TableCell component="th" scope="row">
                            {order.id}
                          </TableCell>
                          <TableCell align="right">{order.user.address}</TableCell>
                          <TableCell align="right">{order.deliveryTime}</TableCell>
                          <TableCell align="right">{order.status}</TableCell>
                          <TableCell align="right">{order.total}</TableCell>
                          <TableCell align="right">
                          <Link
                              style={{ color: "#ffff" }}
                              to={`/orderhistory/${order.id}`}
                            >
                             <Button>View</Button> 
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
          );
    }
}

export default withRouter(OrderHistory);