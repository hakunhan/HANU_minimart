import React from "react";
import axios from 'axios';

import Title from "antd/lib/typography/Title";
import "./homepage.page.css";
import Slide from "../../component/Slide/slide.component";
import {
  CarOutlined,
  RotateLeftOutlined,
  SafetyCertificateOutlined,
} from "@ant-design/icons";
import Product from '../showProduct/productList'
import { Card } from "antd";

const { Meta } = Card;
class Home extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      img: [
        "https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png",
        "https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png",
        "https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png",
        "https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png",
      ],
    };
  }

    // axios.get("http:/localhost:8085/api/product/all")
    //   .then(res => {
    //     console.log("hhhahahah")
    //     const persons = res.data;
    //   })
    //   .catch(error => console.log(" iedjiej" + error));
  
  
  render() {
    return (
      <div className="container1">

        <Slide />
        {/* <div className="Flash_sale1">
          <h3>
            <i>Flash Sale</i>
          </h3>
          <div className="Flash_sale">
            {this.state.img.map((url) => {
              return (
                <Card
                  hoverable
                  style={{ width: 200 }}
                  cover={<img alt="example" src={url} />}
                >
                </Card>
              );
            })}
          </div>


        </div> */}
        <h3>Bread / Candy</h3>
          <Product/>
          <hr />
        <div className="content">
          <div className="title1">
            <Title level={4}>
              <RotateLeftOutlined
                style={{ background: "red", color: "#fff" }}
              />{" "}
              Free 7-day return
            </Title>
            <h4>Delivery free</h4>
          </div>
          <div className="title2">
            <Title level={4}>
              <SafetyCertificateOutlined
                style={{ background: "red", color: "#fff" }}
              />
              Genuine product 100%
            </Title>
            <h4>
              {" "}
              <i>Genuine goods guarantee </i>
            </h4>
            <h4>
              {" "}
              <i>or double refund</i>
            </h4>
          </div>
          <div className="title3">
            <Title level={4}>
              <CarOutlined style={{ background: "red", color: "#fff" }} />
              Free ship
            </Title>
            <h4>
              <i>Free delivery nationwide</i>
            </h4>
          </div>
        </div>
      </div>
    );
  }
}

export default Home;
