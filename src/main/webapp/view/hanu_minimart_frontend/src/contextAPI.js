import React from "react";

import PRODUCT_DATA from "./appData";

const ProductContext = React.createContext();
const DETAIL_DATA = [
  //   {
  //     id: "1",
  //     title: "Clothes",
  //     src: [
  //       "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.0-9/152787807_2650726785217827_4255271476529596038_o.jpg?_nc_cat=102&ccb=3&_nc_sid=730e14&_nc_ohc=Rb3nZaD8q8UAX8uEc54&_nc_ht=scontent.fhph1-1.fna&oh=2fdd120bebb8adad50f3bc292d52551e&oe=60669A95",
  //       "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.0-9/152787807_2650726785217827_4255271476529596038_o.jpg?_nc_cat=102&ccb=3&_nc_sid=730e14&_nc_ohc=Rb3nZaD8q8UAX8uEc54&_nc_ht=scontent.fhph1-1.fna&oh=2fdd120bebb8adad50f3bc292d52551e&oe=60669A95",
  //     ],
  //     description: "this is a good clothes",
  //     content:
  //       "Enjoys 2021's latest tech wear street jackets, with a premium quality, design, aesthetic and fit.... you can't go wrong! These street tech jackets are the newest staple piece in streetwear with zipper closure and aesthetic sleeve pocket!",
  //     price: 23,
  //     colors: ["red", "black", "crimson", "teal"],
  //     count: 1,
  //     quantity: 400,
  //   },
  //   {
  //     id: "2",
  //     title: "Clothes",
  //     src: [
  //       "https://scontent.fhph1-2.fna.fbcdn.net/v/t1.0-9/154783611_2654922508131588_6843075652432792323_o.jpg?_nc_cat=103&ccb=3&_nc_sid=730e14&_nc_ohc=vBmgVzsosOUAX_lqHkQ&_nc_ht=scontent.fhph1-2.fna&oh=a96db9b5e0d2fbc4bdb5f19e0180e000&oe=60675702",
  //       "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.0-9/152787807_2650726785217827_4255271476529596038_o.jpg?_nc_cat=102&ccb=3&_nc_sid=730e14&_nc_ohc=Rb3nZaD8q8UAX8uEc54&_nc_ht=scontent.fhph1-1.fna&oh=2fdd120bebb8adad50f3bc292d52551e&oe=60669A95",
  //     ],
  //     description: "this is a good clothes",
  //     content:
  //       "Enjoys 2021's latest tech wear street jackets, with a premium quality, design, aesthetic and fit.... you can't go wrong! These street tech jackets are the newest staple piece in streetwear with zipper closure and aesthetic sleeve pocket!",
  //     price: 23,
  //     colors: ["red", "black", "crimson", "teal"],
  //     count: 1,
  //     quantity: 400,
  //   },
  //   {
  //     id: "3",
  //     title: "Clothes",
  //     src: [
  //       "https://scontent.fhph1-2.fna.fbcdn.net/v/t1.0-9/154783611_2654922508131588_6843075652432792323_o.jpg?_nc_cat=103&ccb=3&_nc_sid=730e14&_nc_ohc=vBmgVzsosOUAX_lqHkQ&_nc_ht=scontent.fhph1-2.fna&oh=a96db9b5e0d2fbc4bdb5f19e0180e000&oe=60675702",
  //       "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.0-9/152787807_2650726785217827_4255271476529596038_o.jpg?_nc_cat=102&ccb=3&_nc_sid=730e14&_nc_ohc=Rb3nZaD8q8UAX8uEc54&_nc_ht=scontent.fhph1-1.fna&oh=2fdd120bebb8adad50f3bc292d52551e&oe=60669A95",
  //     ],
  //     description: "this is a good clothes",
  //     content:
  //       "Enjoys 2021's latest tech wear street jackets, with a premium quality, design, aesthetic and fit.... you can't go wrong! These street tech jackets are the newest staple piece in streetwear with zipper closure and aesthetic sleeve pocket!",
  //     price: 23,
  //     colors: ["red", "black", "crimson", "teal"],
  //     count: 1,
  //     quantity: 400,
  //   }

  {
    id: 1,
    title: "CASSETTE SHIRT",
    img:
      "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.0-9/152787807_2650726785217827_4255271476529596038_o.jpg?_nc_cat=102&ccb=3&_nc_sid=730e14&_nc_ohc=Rb3nZaD8q8UAX8uEc54&_nc_ht=scontent.fhph1-1.fna&oh=2fdd120bebb8adad50f3bc292d52551e&oe=60669A95",
    price: 10,
    company: "Mende",
    info:
      "Enjoys 2021's latest tech wear street jackets, with a premium quality, design, aesthetic and fit.... ",
    inCart: false,
    count: 1,
    index: 0,
  },
  {
    id: 2,
    title: "CASSETTE SHIRT",
    img:
      "https://scontent.fhph1-2.fna.fbcdn.net/v/t1.0-9/154783611_2654922508131588_6843075652432792323_o.jpg?_nc_cat=103&ccb=3&_nc_sid=730e14&_nc_ohc=vBmgVzsosOUAX_lqHkQ&_nc_ht=scontent.fhph1-2.fna&oh=a96db9b5e0d2fbc4bdb5f19e0180e000&oe=60675702",
    price: 10,
    company: "Honda",
    info: "This í the good cả for the middle class people",
    inCart: false,
    count: 1,
  },
];

class ProductProvider extends React.Component {
  constructor(){
    super();
    this.state = {
      products: PRODUCT_DATA,
      detailProduct: DETAIL_DATA,
      Cart: [],
      cartSubTotal: 0,
      isLogin: false,
      
    };
    }
     

    IsLogin= (element)=>{
      this.setState(
        {isLogin: element}
      )
    }
    isLogout= (element)=>{
      console.log("hduduhduhfuhdf")
      if(element === true){
        this.setState(
          {isLogin: false}
        )
      }
     
    }
   

  getItem = (id) => {
    const product = this.state.products.find((item) => item.id === id);
    return product;
  };
  handleDetails = (id) => {
    const product = this.getItem(id);
    this.setState(() => {
      return {
        detailProduct: product,
      };
    });
  };
  addToCart = (id) => {
    let tempProduct = [...this.state.products];
    const index = tempProduct.indexOf(this.getItem(id));
    const product = tempProduct[index];
    product.inCart = true;
    product.count = 1;
    const price = product.price;
    product.total = price;
    this.setState(() => {
      return { products: tempProduct, Cart: [...this.state.Cart, product] };
    }, () =>
    {
      this.makeTotal();
    }
    );
  };

  increment = (id) => {
    let tempCart = [...this.state.Cart];
    const selectedProduct = tempCart.find((item) => item.id === id);
    const index = tempCart.indexOf(selectedProduct);
    const product = tempCart[index];

    product.count = product.count + 1;
    product.total = product.count * product.price;
    this.setState(() => {
      return {
        Cart: [...tempCart],
      };
     }, ()=>{
       this.makeTotal();
     }
    );
  };
  
  decrement = (id) => {
    let tempCart = [...this.state.Cart];
    const selectedProduct = tempCart.find((item) => item.id === id);
    const index = tempCart.indexOf(selectedProduct);
    const product = tempCart[index];

    product.count = product.count - 1;
    product.total = product.count * product.price;
    this.setState(() => {
      return {
        Cart: [...tempCart],
      };
     }, ()=>{
       this.makeTotal();
     }
    );
  };

  removeItem = (id) => {
    let tempProduct = [...this.state.products];
    let tempCart = [...this.state.Cart];
    tempCart = tempCart.filter(item => item.id !== id);
    const index = tempProduct.indexOf(this.getItem(id));

    let removeProduct = tempProduct[index];
    removeProduct.inCart = false;
    removeProduct.total = 0;
    removeProduct.count = 0;
    this.setState(() =>{
      return{
        Cart: [...tempCart],
        product:[...tempProduct]
      }
    }, ()=>{
      this.makeTotal();
    }
    )
  }
  makeTotal=() =>{
    let subTotal = 0;
    this.state.Cart.map(item => (subTotal += item.total));

    this.setState(() =>
    {
      return{
        cartSubTotal: subTotal
      }
    })
    
  }

  render() {
    return (
      <ProductContext.Provider
        value={{
          ...this.state,
          handleDetails: this.handleDetails,
          addToCart: this.addToCart,
          increment: this.increment,
          decrement: this.decrement,
          removeItem: this.removeItem,

          IsLogin: this.IsLogin,
          isLogout: this.isLogout
        }}
      >
        {this.props.children}
      </ProductContext.Provider>
    );
  }
}
const ProductConsumer = ProductContext.Consumer;

export { ProductProvider, ProductConsumer };
