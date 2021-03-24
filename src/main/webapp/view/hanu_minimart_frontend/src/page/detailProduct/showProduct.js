import React from "react";

import axios from "axios";

import "./detailProduct.css";
// import Colors from "./color";
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
      //cart Item.....
      count: 0,
      quantity1: 1,
      cartItems: {
        product: {},
        cart: {},
        quantity: 0,
        content: "",
      },
      user: this.props
    };
    this.clickAddToCartItem = this.clickAddToCartItem.bind(this);
    this.handleChangeInput = this.handleChangeInput.bind(this);
    this.handlePlus = this.handlePlus.bind(this);
    this.handleMinus = this.handleMinus.bind(this);
  }

  handlePlus() {
    this.setState({
      count: this.state.quantity1++,
    });
  }

  handleMinus() {
    if (this.state.quantity1 > 1) {
      this.setState({
        count: this.state.quantity1--,
      });
    } else if ((this.state.quantity1 = 1)) {
      this.setState({
        count: 1,
      });
    }
  }

  handleChangeInput(event) {
    this.setState({
      quantity1: event.target.value,
    });
  }

  myRef = React.createRef();
  // handleTab = (index) => {
  //   this.setState({ index: index });
  //   const images = this.myRef.current.children;
  //   for (let i = 0; i < images.length; i++) {
  //     images[i].className = images[i].className.replace("active", "");
  //   }
  //   images[index].className = "active";
  // };

 async clickAddToCartItem() {
    console.log("enterrrrrrrrrrrrrrrrrrrr",this.props.user.id);
    
    const {product_id, quantity1} = this.state;
  

    const urlGetCart = `http://localhost:8085/api/carts/getByUser?userId=${this.props.user.id}`;
    const getCartForUser = await axios.get(urlGetCart);
    console.log("lấy cart nè......", getCartForUser.data);
   

    const body = {
      product: product_id,
      cart: getCartForUser.data,
      quantity: quantity1,
      content: ""
    }
    console.log("content cart??????", body);

    const urlSendCardItem = "http://localhost:8085/api/cartItems";
    const postCartItem = await axios.post(urlSendCardItem, body);

    console.log("Sent ...........", postCartItem)
  }

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
      isSaved: true,
    });
    console.log(this.state.product_id);
  }

  render() {
    // const { colors } = this.state;
    const { product_id, isSaved } = this.state;
    return (
      <div className="details1">
        {isSaved ? (
          <div className="details">
            <div className="big-img">
              <img src={product_id.picture_URL} alt="" />
            </div>

            <div className="box">
              <div className="row">
                <h2>{product_id.name}</h2>
                <span>${product_id.price}</span>
              </div>
              {/* <Colors colors={colors} /> */}

              <p>{product_id.description}</p>
              <p style={{ color: "sandybrown" }}>
                ExpireDate: <strong>{product_id.expireDate}</strong>
              </p>
              <div className="quantity">
                <input
                  type="button"
                  defaultValue="+"
                  className="plus"
                  onClick={this.handlePlus}
                />
                <input
                  type="number"
                  step={1}
                  max={99}
                  min={1}
                  value={this.state.quantity1}
                  title="Qty"
                  className="qty"
                  size={4}
                  onChange={this.handleChangeInput}
                />
                <input
                  type="button"
                  defaultValue="-"
                  className="minus"
                  onClick={this.handleMinus}
                />
              </div>

              {/* <DetailsThumb
                    images={item.src}
                    tab={this.handleTab}
                    myRef={this.myRef}
                  /> */}
              <br />
              <br />

              <button
                // variant="dark"
                className="cart"
                // disabled={inCart}
                onClick={this.clickAddToCartItem}
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
