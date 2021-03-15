import React from "react";

import axios from "axios";

import "./detailProduct.css";
import Colors from "./color";
import { withRouter } from "react-router";
class DetailProduct extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: this.props.match.params.id,
      isSaved: false,
      product_id: {},
      product: [],
      colors: ["red", "black", "crimson", "teal"],
      index: 0,
      cart: {
        user: {},
        cartItem: [],
        createAt: null,
        updateAt: null
      }
    };
  }


  myRef = React.createRef();
  handleTab = (index) => {
    this.setState({ index: index });
    const images = this.myRef.current.children;
    for (let i = 0; i < images.length; i++) {
      images[i].className = images[i].className.replace("active", "");
    }
    images[index].className = "active";
  };

  async componentDidMount() {
    // console.log("okeyyyyyy");
    const urlProduct = "http://localhost:8085/api/product/getAll";
    const getDataProduct = await axios.get(urlProduct);
    const product = getDataProduct.data;
    this.setState({
      product: product,
    });
    const product_Id = this.state.product.find(
      (item) => item.id === Number(this.state.id)
    );
    this.setState({
      product_id: product_Id,
      isSaved: true
    })
    console.log(this.state.product_id);
  }

  render() {
    const { colors } = this.state;
    const { product_id, isSaved } = this.state;
    return (
      <div className="details">
        {(isSaved) ? (
                <div className="details" >
                  <div className="big-img">
                    <img
                      src="https://tutimviec.com/wp-content/uploads/2018/12/vinmart-amp.jpg"
                      alt=""
                    />
                  </div>

                  <div className="box">
                    <div className="row">
                      <h2>{product_id.name}</h2>
                      <span>${product_id.price}</span>
                    </div>
                    {/* <Colors colors={colors} /> */}

                    <p>quantity: {product_id.quantity}</p>
                    <p>expireDate: {product_id.expireDate}</p>

                    {/* <DetailsThumb
                    images={item.src}
                    tab={this.handleTab}
                    myRef={this.myRef}
                  /> */}
                    <button
                      className="cart"
                      // disabled={inCart}
                      // onClick={() => addToCart(id)}
                    >
                      {/* {inCart === true ? <span>InCart</span> :  */}
                      <span>Add To Cart</span>
                    </button>
                  </div>
                </div>

        ) : (
          ""
        )}
      </div>
    );
  }
}

export default withRouter(DetailProduct);

// <div className="big-img">
//           <img
//             src="https://vincom.com.vn/sites/default/files/2016-10/VinMart_1.jpg"
//             alt=""
//           />
//         </div>

//         <div className="box">
//           <div className="row">
//             <h2>{name}</h2>
//             <span>${price}</span>
//           </div>
//           <Colors colors={colors} />

//           {/* <p>{company}</p> */}
//           <p>{quantity}</p>

//           {/* <DetailsThumb
//                     images={item.src}
//                     tab={this.handleTab}
//                     myRef={this.myRef}
//                   /> */}
//           <button
//             className="cart"
//             // disabled={inCart}
//             // onClick={() => addToCart(id)}
//           >
//             {/* {inCart === true ? <span>InCart</span> :  */}
//             <span>Add To Cart</span>
//           </button>
//         </div>
