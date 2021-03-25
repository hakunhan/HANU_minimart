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
import { Button } from "react-bootstrap";

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
      showList: [],
      search: "",
    };
    this.handleChange = this.handleChange.bind(this);
    this.fetch_Search_User = this.fetch_Search_User.bind(this);
  }

  async fetch_Search_User(e) {
    e.preventDefault();
    console.log("a_______________", typeof this.state.search);

    const url = `http://localhost:8085/api/account/getAll?name=${this.state.search}`;
    const getData = await axios({
      method: "GET",
      url,
      headers: { authorization: this.state.Authentication },
    });
    const user_data = getData.data.content;
    this.setState({
      dataSource: user_data,
    });
    console.log("data search ------", user_data);
  }

  handleChange(event) {
    this.setState({ search: event.target.value });
  }

  async componentDidMount() {
    console.log("start.................");
    const url = "http://localhost:8085/api/account/getAll";
    //    const postAuthen = await axios({method: "POST", url, headers:{authorization: this.state.Authentication}})
    const getData = await axios({
      method: "GET",
      url,
      headers: { authorization: this.state.Authentication },
    });
    // const data_Active = getData.data.content.filter((user) => user.status === "ACTIVATED")

    const filter = getData.data.content.filter((user) => {
      return user.status === "ACTIVATED";
    });
    console.log(filter);
    this.setState({
      dataSource: filter,
    });
    console.log(typeof this.state.dataSource);

    console.log("end.................");
  }
  render() {
    const { dataSource } = this.state;
   
    return (
      <div>
        <div>
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
        {dataSource.length >= 1 ? (
          <div>
            <TableContainer component={Paper}>
              <Table style={{ minWidth: 650 }} aria-label="simple table">
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
                      <TableCell align="right">
                      <Button>
                      <Link
                          style={{ color: "#fff" }}
                          to={`/admin/manageuser/${user.id}`}
                        >
                          Edit
                        </Link>
                      </Button>
                        
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

export default withRouter(ManageUser);
