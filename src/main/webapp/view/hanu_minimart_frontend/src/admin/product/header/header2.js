import React from 'react';
import {Input } from "antd";
import { Button } from "react-bootstrap";
import './header.css';
import {PlusCircleOutlined,SearchOutlined,EditOutlined,CloseSquareOutlined} from "@ant-design/icons"
export default class Header2AdminProduct extends React.Component{

    render() {
        return (
             <div className = "Header">
             <div className = 'newProduct'>
               <Button variant="primary"
                    size="sm"> <PlusCircleOutlined /> New Product</Button>
             </div>
             <div className= "Search">
             <SearchOutlined /><Input key ="search" value ="Search"/>
             </div>

             <div className ="ViewAll">
             <Button variant="primary"
                    size="sm">View All</Button>
             </div>
             <div className ="ViewProductNear">
             <Button variant="primary"
                    size="sm" >View Product Near Expiration</Button>
             </div>

             <div className= "crud">
                 <div className ="Edit">
                     <Button variant="primary"> <EditOutlined />Save</Button>
                 </div>
                 <div className ="delete">
                     <Button variant="danger"><CloseSquareOutlined />Reset</Button>
                 </div>

             </div>
             </div>
        );
    }
}