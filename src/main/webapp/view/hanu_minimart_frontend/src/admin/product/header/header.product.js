import React from 'react';
import {Input } from "antd";
import { Button } from "react-bootstrap";
import './header.css';
import {PlusCircleOutlined,SearchOutlined,EditOutlined,DeleteOutlined} from "@ant-design/icons"
import { Link } from 'react-router-dom';
export default class HeaderAdmin extends React.Component{

    render() {
        return (
             <div className = "Header">
             <div className = 'newProduct'>
             <Link to ="/admin/product/newProduct" className="linkNewP" >
               <Button variant="primary"
                    size="sm"> <PlusCircleOutlined />
                    New Product </Button>
                    </Link>
             </div>
             <div className= "Search">
             <SearchOutlined /><Input key ="search" placeholder="Search"/>
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
                     <Button variant="primary"> <EditOutlined />Edit</Button>
                 </div>
                 <div className ="delete">
                     <Button variant="danger"><DeleteOutlined />Delete</Button>
                 </div>

             </div>
             </div>
        );
    }
}