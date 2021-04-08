import React from "react";
import "./index.css";
import "antd/dist/antd.css";
import { Layout } from "antd";
import Title from "antd/lib/typography/Title";
import { Link } from "react-router-dom";
import SiderMenu from "./SiderMenu";
import Navbar from "./navbar";
import Footer1 from './footer'

const { Header, Footer, Content } = Layout;

//
class AppRoute extends React.Component {
  constructor() {
    super();
  }
  render() {
    const { children, isLogin, cart } = this.props;
    return (
      <Layout style={{ backgroundColor: "red" }}>
        <Header style={{ padding: 10 }}>
          <Title style={{ color: "white" }} level={3}>
            <Link style ={{textDecorationColor: "none" , color: "white"}} to="/"> HANU_minimart</Link>
          </Title>

          <Navbar isLogin={isLogin} />
        </Header>
        <Layout>
        
          <SiderMenu cart={cart} />
         
          <Layout>
            <Content style={{ padding: "0 50px" }}>
              {/* <Breadcrumb style={{ margin: "16px 0" }}>
          <Breadcrumb.Item>Dashboard</Breadcrumb.Item>
        </Breadcrumb> */}
              <div style={{ background: "#fff", padding: 24, minHeight: 580 }}>
                {children}
              </div>
            </Content>
            <Footer>
            <Footer1/>
            </Footer>
          </Layout>
          
        </Layout>
      
      </Layout>
    );
  }
}
export default AppRoute;
