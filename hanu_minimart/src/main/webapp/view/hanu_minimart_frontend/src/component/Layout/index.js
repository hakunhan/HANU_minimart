import React from "react";
import "./index.css";
import "antd/dist/antd.css";
import { Layout, Avatar, Menu, Breadcrumb, Input } from "antd";
import Title from "antd/lib/typography/Title";
import { Link } from "react-router-dom";
import SiderMenu from "./SiderMenu";
import Navbar from './navbar';
// import Product from '../product/productList';

const { Header, Footer, Content } = Layout;

//
class AppRoute extends React.Component{
  constructor(){
    super()

  }
  render(){
    const {children, isLogin } = this.props
    return (
      <Layout  style={{backgroundColor: "red"}}>
  <Header style={{ padding: 10 }}>
    <Navbar isLogin ={isLogin}/>
    <Title style={{ color: "white" }} level={3}>
      <Link to="/"> HANU_mimimart</Link>
    </Title>
  </Header>
  <Layout>
    <SiderMenu />
    <Layout>
      <Content style={{ padding: "0 50px" }}>
        {/* <Breadcrumb style={{ margin: "16px 0" }}>
          <Breadcrumb.Item>Dashboard</Breadcrumb.Item>
        </Breadcrumb> */}
        <div style={{ background: "#fff", padding: 24, minHeight: 580 }}>
          {children}
        </div>
      </Content>
      <Footer>Footer</Footer>
    </Layout>
  </Layout>
</Layout>
    
  


);
  }
}
export default AppRoute;