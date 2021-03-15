import React, { useDebugValue } from "react";

import {
  AppstoreOutlined,
  MailOutlined,
  SearchOutlined,
  ShoppingCartOutlined,
  ProfileOutlined,
} from "@ant-design/icons";

import { Layout, Menu, Input } from "antd";
import SubMenu from "antd/lib/menu/SubMenu";
import { Link } from "react-router-dom";
import { ProductConsumer, ProductProvider } from "../../contextAPI";
import './index.css'
// import Product from '../product/productList';

const { Sider } = Layout;

const SiderMenu = () => {
  return (
    <Sider className= "sider">
      <Menu defaultSelectedKeys={["Dashboard"]} mode="inline">
        <Menu.Item key="dashboard">DASHBOARD</Menu.Item>

        <Menu.Item key="location1">
          <AppstoreOutlined /> Order
        </Menu.Item>

        <Menu.Item
          key="search"
          icon={<SearchOutlined />}
          style={{ width: 180 }}
        >
          <Input key="search" />
        </Menu.Item>
        <Menu.Item key="cart" icon={<ShoppingCartOutlined />} title="Cart">
          {/* <ProductConsumer>
                  {
                    (value) => {
                      return (<Link eventKey={2} to ="/cart">
                      My Cart({value.Cart.length})
                      </Link>
                      )
                    }
                  }
                </ProductConsumer> */}
        </Menu.Item>
        <Menu.Item key="Profile" icon={<ProfileOutlined />} title="Profile">
         <Link to = "/profile"> Profile</Link>
        </Menu.Item>
      </Menu>
    </Sider>
  );
};

export default SiderMenu;
