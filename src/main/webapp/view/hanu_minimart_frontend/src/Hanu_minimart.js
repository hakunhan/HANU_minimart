import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter, Switch, Route, withRouter } from "react-router-dom";
import UnAuthenRoute from "./unAuthenRoute";

import Header from "./component/header/header.component";
import SignIn from "./component/signIn/signIn";
import SignUp from "./component/signUp/signUp.component";
import Home from "./page/homepage/homepage.page";
import TestProduct from "./page/showProduct/productList";
import DetailProduct from "./page/detailProduct/showProduct";
import "bootstrap/dist/css/bootstrap.min.css";
import Cart from "./page/cart/Cart";
import AppRoute from "./component/Layout/index";
import ProtectedRoute from './ProtectedRoute';
import Profile from './component/profile/userProfile'
//Admin
// import AppRoute2 from "./App.Admin";
// import HeaderAdmin from "./admin/product/header/header.product";
// import ProductAdmin from "./admin/product/showProduct/productList";
import HomeAdmin from "./admin/home.admin.js/home.admin";
// import NewProduct from "./admin/product/NewProduct/newProduct";
import ProtectedRouteAdmin from './admin/ProtectedRouteAdmin';
import AdminSignin from './admin/signin/AdminsignIn';
import AppRouteAdmin from './admin/Layout/index';
import ManageUser from './admin/Manageuser/user';
import UserDetails from "./admin/Manageuser/userDetail";
import ManageProduct from "./admin/product/showProduct/manageProduct";
import NewProduct from  './admin/product/NewProduct/newProduct';
import EditProduct from "./admin/product/editProduct/editProduct";


import TestTable from "./admin/home.admin.js/testTable";
class Hanu_minimart extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      Authentication: "",
      isLoginAdmin: false,
      isLogin: false,
      uid: "",
      completed: false,

    };

    this.setStateLogin = this.setStateLogin.bind(this);
    this.saveAuthentication = this.saveAuthentication.bind(this);
    this.setStateAdminLogin =this.setStateAdminLogin.bind(this);
  }
  //save data token and Bearer
  saveAuthentication(Authentication, user_inform ){
    this.setState({
      Authentication: Authentication,
      user: user_inform
    })
  }
  //authentication for user
  setStateLogin(data, callback_function) {
    this.setState(
      {
        isLogin: data,
      },
      callback_function
    );
  }
  //authentication for admin
  setStateAdminLogin(data, callback_function) {
    this.setState(
      {
        isLoginAdmin: data,
      },
      callback_function
    );
  }
  //get all user from Admin
  
 

  componentDidMount() {
    //xử lí cookies
    const userid = this.getCookie("uid");
    const username = this.getCookie("username");
    const admin = this.getCookie("admin");
    if (userid && admin !== "true") {
      this.setState({
          uid: userid,
          isLogin: true,
          completed: true,
      })
  } else if (!userid && admin !== "true") {
      console.log("login false")
      this.setState({
          isLogin: false,
          completed: true,
      })
  }

  if (userid && admin == "true") {
      this.setState({
          uid: userid,
          isLoginAdmin: true,
          completed: true,
      })
  } else if (!userid && admin == "true") {
      this.setState({
          isLoginAdmin: false,
          completed: true,
      })
  }
  }

  getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}
  render() {
    const { isLogin, isLoginAdmin, completed, user, Authentication,Admin_manage_user } = this.state;

    return (
        <BrowserRouter>
        
          <Switch>
            <Route isLogin={isLogin} exact path="/">
             
              <AppRoute isLogin={isLogin}>
                <Home isLogin={isLogin} />
                {/* <TestTable/> */}

              </AppRoute>
            </Route>
            <UnAuthenRoute isLogin={isLogin} exact path="/signup">
              <SignUp />
            </UnAuthenRoute>
            <UnAuthenRoute isLogin={isLogin} exact path="/signin">
              <SignIn setStateLogin={this.setStateLogin} saveAuthentication= {this.saveAuthentication} />
            </UnAuthenRoute>
            <Route isLogin={isLogin} exact path="/product">
            <AppRoute isLogin={isLogin}>
            <TestProduct/>
              </AppRoute>
            </Route>
            <Route isLogin={isLogin} exact path="/product/:id">
              <AppRoute isLogin={isLogin}>
                <DetailProduct/>
              </AppRoute>
            </Route>
            <ProtectedRoute user ={user} path ='/profile' isLogin={isLogin}>
            <AppRoute isLogin={isLogin} user= {user}>
            <Profile user= {user} />
              </AppRoute>
            </ProtectedRoute>
          </Switch>

          <Switch>
          <UnAuthenRoute isLogin ={isLoginAdmin} exact path="/admin/signin">
            <AdminSignin setStateAdminLogin= {this.setStateAdminLogin} saveAuthentication = {this.saveAuthentication}></AdminSignin>
          </UnAuthenRoute>
         <ProtectedRouteAdmin  isLogin ={isLoginAdmin} exact path="/admin">
         <AppRouteAdmin isLogin ={isLoginAdmin}>
         <HomeAdmin />
         </AppRouteAdmin>
         </ProtectedRouteAdmin>

         <ProtectedRouteAdmin isLogin= {isLoginAdmin} exact path = "/admin/manageuser">
          <AppRouteAdmin  isLogin= {isLoginAdmin}>
            <ManageUser Authentication = {Authentication} getAllUser= {this.getAllUser}/>
          </AppRouteAdmin>
         </ProtectedRouteAdmin>
         <ProtectedRouteAdmin isLogin= {isLoginAdmin} exact path = "/admin/manageuser/:id">
          <AppRouteAdmin  isLogin= {isLoginAdmin} >
            <UserDetails Authentication = {Authentication}  />
          </AppRouteAdmin>
         </ProtectedRouteAdmin> 
         {/* <ProtectedRouteAdmin isLogin= {isLoginAdmin} exact path = "/admin/manageuser/:id">
          <AppRouteAdmin  isLogin= {isLoginAdmin} >
            <UserDetails Authentication = {Authentication}  />
          </AppRouteAdmin>
         </ProtectedRouteAdmin> */}

         <ProtectedRouteAdmin isLogin= {isLoginAdmin} exact path = "/admin/manageproduct">
         <AppRouteAdmin  isLogin= {isLoginAdmin}>
           <ManageProduct Authentication = {Authentication}/>
         </AppRouteAdmin>
         </ProtectedRouteAdmin>

         <ProtectedRouteAdmin isLogin= {isLoginAdmin} exact path = "/admin/manageproduct/newproduct">
         <AppRouteAdmin  isLogin= {isLoginAdmin}>
           <NewProduct Authentication = {Authentication}/>
         </AppRouteAdmin>
         </ProtectedRouteAdmin>

         <ProtectedRouteAdmin isLogin= {isLoginAdmin} exact path = "/admin/manageproduct/edit/:id">
         <AppRouteAdmin  isLogin= {isLoginAdmin}>
           <EditProduct Authentication = {Authentication}/>
         </AppRouteAdmin>
         </ProtectedRouteAdmin>

        

          </Switch>
        </BrowserRouter>
    );
  }
}

export default Hanu_minimart;
