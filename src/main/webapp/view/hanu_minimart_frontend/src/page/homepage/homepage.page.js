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
  
  
  render() {
    return (
      <div className="container1">

        <Slide />
        
          <Product/>
          <hr />
       
      </div>
    );
  }
}

export default Home;
