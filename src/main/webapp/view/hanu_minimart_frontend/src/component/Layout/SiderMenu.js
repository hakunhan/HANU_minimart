import React, { useDebugValue } from "react";
import './index.css'
import {
  AppstoreOutlined,
  HistoryOutlined,
    SearchOutlined,
  ShoppingCartOutlined,
  ProfileOutlined,
} from "@ant-design/icons";

import { Layout, Menu, Input } from "antd";
import { Link } from "react-router-dom";
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

        
        <Menu.Item key="cart" icon={<ShoppingCartOutlined />} title="Cart">	Cart
          {/* <ProductConsumer>
                  {
                    (value) => { */}
                      <Link to ="/cart">
                      My Cart
                      </Link>
                      {/* )
                    }
                  }
                </ProductConsumer> */}
        </Menu.Item>
        <Menu.Item key="Profile" icon={<ProfileOutlined />} title="Profile">
         <Link to = "/profile">Profile</Link>
        </Menu.Item>
        <Menu.Item key="history" icon={<HistoryOutlined />} title="history">
         <Link to = "/history">History</Link>
        </Menu.Item>
      </Menu>
    </Sider>
  );
};

export default SiderMenu;
