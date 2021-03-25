import React, { useDebugValue } from "react";
import "./index.css";
import axios from "axios";
import {
  AppstoreOutlined,
  HistoryOutlined,
  SearchOutlined,
  UserOutlined,
  ShoppingCartOutlined,
  ProfileOutlined,
} from "@ant-design/icons";

import { Layout, Menu, Input } from "antd";
import { Link } from "react-router-dom";
import "./index.css";
import { get } from "jquery";
// import Product from '../product/productList';

const { Sider } = Layout;

class SiderMenu extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      itemInCart: this.props.cart,
      username: " ",
    
    };
  }

  async componentDidMount() {
    const username = this.getCookie("username");
    this.setState({
      username: username,
    });
    const id = this.getCookie("uid");
    const urlgetDataCart = `http://localhost:8085/api/cart/getByUser?userId=${id}`;
    const getDataCartItem = await axios.get(urlgetDataCart);

    const getDataCart = getDataCartItem.data.cartItem.length;
    this.setState({
      itemInCart: getDataCart,
    });
  }
  getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(";").shift();
  }

  render() {
    // const { itemInCart } = this.state;
    return (
      <Sider className="sider">
        <Menu defaultSelectedKeys={["Dashboard"]} mode="inline">
          {this.state.username ? (
            <Menu.Item key="username" icon={<UserOutlined />} disabled= {true} style={{color: "red"}} >
              {this.state.username}
            </Menu.Item>
          ) : (
            " "
          )}

          <Menu.Item key="cart" icon={<ShoppingCartOutlined />}>
         
                
            <Link to="/cart">Cart</Link>
                    
                      
                    
          </Menu.Item>
          <Menu.Item key="Profile" icon={<ProfileOutlined />} title="Profile">
            <Link to="/profile">Profile</Link>
          </Menu.Item>
          <Menu.Item key="history" icon={<HistoryOutlined />} title="history">
            <Link to="/orderhistory">History</Link>
          </Menu.Item>
        </Menu>
      </Sider>
    );
  }
}

export default SiderMenu;
