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
class ManageUser extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      Authentication: this.props.Authentication,
      user: null,
      dataSource: [],
    };
  }

  async componentDidMount() {
    console.log("start.................");
    console.log(this.state.Authentication);
    const url = "http://localhost:8085/api/account/getAll";
    //    const postAuthen = await axios({method: "POST", url, headers:{authorization: this.state.Authentication}})
    const getData = await axios({
      method: "GET",
      url,
      headers: { authorization: this.state.Authentication },
    });
    this.setState({
      dataSource: getData.data.content,
    });
    console.log(getData.data.content);

    console.log("end.................");
  }
  render() {
    const { dataSource } = this.state;
    // const classes = useStyles();
    return (
      <div>
        {dataSource.length > 1 ? (
          <div>
            <TableContainer component={Paper}>
              <Table style={{minWidth: 650}} aria-label="simple table">
                <TableHead>
                  <TableRow>
                    <TableCell>Id</TableCell>
                    <TableCell align="right">Name</TableCell>
                    <TableCell align="right">UserName</TableCell>
                    <TableCell align="right">Address</TableCell>
                    <TableCell align="right">PhoneNumber</TableCell>
                    <TableCell align="right">Action</TableCell>

                  </TableRow>
                </TableHead>
                <TableBody>
                  {dataSource.map((user) => (
                    <TableRow key={user.name}>
                      <TableCell component="th" scope="row">
                        {user.id}
                      </TableCell>
                      <TableCell align="right">{user.name}</TableCell>
                      <TableCell align="right">{user.username}</TableCell>
                      <TableCell align="right">{user.address}</TableCell>
                      <TableCell align="right">{user.phoneNumber}</TableCell>
                      <TableCell align="right"><Link style= {{color: "blue"}} to = {`/admin/manageuser/${user.id}`}>Edit</Link></TableCell>
                      
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

export default withRouter(ManageUser);
