import React from "react";
import Header2 from '../header/header2';
import './newProduct.css';
import { Button } from "antd";
import {Link} from 'react-router-dom';
export default class NewProduct extends React.Component {
  render() {
    return (
      <div className="newProduct">
      <Header2/>
        <form className ="infoProduct">
            <div className= "Product_Name">
            <label>Name: </label>
            <input type= "text"/>
            </div>
            <br/>
            <div className= "Description">
            <label>Description: </label>
            <input type= "text"/>
            </div>
            <br/>

            <div className= "Price">
            <label>Price: </label>
            <input type= "number"/>
            </div>
            <br/>

            <div className= "Quantity">
            <label>Quantity: </label>
            <input type= "number"/>
            </div>
            <br/>

            <div className= "ExpiredDate">
            <label>ExpiredDate: </label>
            <input type= "date"/>
            </div>
            <br/>

            <div className= "ExpiredDate">

            <Button type ="danger">Create</Button>
            </div>
            
        </form>
      </div>
    );
  }
}
