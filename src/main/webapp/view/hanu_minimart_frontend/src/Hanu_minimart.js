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
import ProtectedRoute from "./ProtectedRoute";
import Profile from "./component/profile/userProfile";
import EditProfile from "./component/profile/editProfile";
import OrderHistory from "./page/orderhistory/orderhistory";
import ViewDetailHistory from "./page/orderhistory/viewDetailHistory";
//Admin
// import AppRoute2 from "./App.Admin";
// import HeaderAdmin from "./admin/product/header/header.product";
// import ProductAdmin from "./admin/product/showProduct/productList";
import HomeAdmin from "./admin/home.admin.js/home.admin";
// import NewProduct from "./admin/product/NewProduct/newProduct";
import ProtectedRouteAdmin from "./admin/ProtectedRouteAdmin";
// import AdminSignin from './admin/signin/AdminsignIn';
import AppRouteAdmin from "./admin/Layout/index";
import ManageUser from "./admin/Manageuser/user";
import UserDetails from "./admin/Manageuser/userDetail";
import ManageProduct from "./admin/product/showProduct/manageProduct";
import NewProduct from "./admin/product/NewProduct/newProduct";
import EditProduct from "./admin/product/editProduct/editProduct";
import OrderHistoryAdmin from "./admin/orderhistory/orderhistory";
import ViewDetailHistoryAdmin from "./admin/orderhistory/viewDetailHistory";
// Employee
import ProtectedRouteEm from "./employee/ProtectedRouteEm";
import AppRoute2 from "./employee/Layout/index";
// import EmSignIn from './employee/signin/EmSignIn';
import ManageProductEm from "./employee/product/showProduct/manageProduct";
import EditProductEm from "./employee/product/editProduct/editProduct";
import NewProductEm from "./employee/product/NewProduct/newProduct";
import OrderHistoryEm from "./employee/orderhistory/orderhistory";
import ViewDetailHistoryEm from "./employee/orderhistory/viewDetailHistory";
import user from "./admin/Manageuser/user";

// import ManageProductEmployee from './employee/product/product';
class Hanu_minimart extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      Authentication: "",
      isLoginAdmin: false,
      isLogin: false,
      uid: "",
      completed: false,
      isLoginEm: false,
      user: {},
      check: false,
      cart: ''
    };

    this.setStateLogin = this.setStateLogin.bind(this);
    this.saveAuthentication = this.saveAuthentication.bind(this);
    this.setStateAdminLogin = this.setStateAdminLogin.bind(this);
    this.setStateEmLogin = this.setStateEmLogin.bind(this);
    this.handleCart = this.handleCart.bind(this);
  }
  //handle số product trong cart
  handleCart(number){
    this.setState({
      cart: number
    })
  }
  //save data token and Bearer
  saveAuthentication(Authentication, user_inform) {
    this.setState({
      Authentication: Authentication,
      user: user_inform,
    });
  }
  setStateEmLogin(data, callback_function) {
    this.setState(
      {
        isLoginEm: data,
      },
      callback_function
    );
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
    const uid = this.getCookie("uid");
    const username = this.getCookie("username");
    const admin = this.getCookie("admin");
    const employee = this.getCookie("employee");
    console.log(uid, username,admin, employee);

    if ( uid &&admin !== "true" && employee !== "true") {
      this.setState({
        uid: uid,
        isLogin: true,
      });
      // console.log("doneeeeeeeee")
    } else{
      console.log("login false");
      // console.log("stillllllllllllllll", this.state.isLogin)

      this.setState({
        isLogin: false,
      });
    }

    if ( uid && employee === 'true') {
      this.setState({
        uid: uid,
        isLoginEm: true,
      });
      console.log("doneeeeeeeee", this.state.isLoginEm)
    } else if (!uid && employee !== 'true'){
      console.log("login false");
      console.log("stillllllllllllllll", this.state.isLogin)

      this.setState({
        isLoginEm: false,
      });
    }
    
    if ( uid && admin === 'true') {
      this.setState({
        uid: uid,
        isLoginAdmin: true,
      });
      console.log("doneeeeeeeee", this.state.isLoginAmin)
    } else if (!uid && admin !== 'true'){
      console.log("login false");
      console.log("stillllllllllllllll", this.state.isLogin)

      this.setState({
        isLoginAdmin: false,
      });
    }

 
  }

  getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}
  render() {
    const {
      isLogin,
      isLoginAdmin,
      isLoginEm,
      user,
      Authentication,
      cart
      // Admin_manage_user,
    } = this.state;
    console.log(isLoginEm, cart,"rendddddddddddđ");
    return (
      <BrowserRouter>
        <Switch>
          <Route isLogin={isLogin} exact path="/">
            <AppRoute isLogin={isLogin}>
              <Home isLogin={isLogin} />
            </AppRoute>
          </Route>
          <UnAuthenRoute isLogin={isLogin} exact path="/signup">
            <SignUp />
          </UnAuthenRoute>
          <UnAuthenRoute isLogin={isLogin} exact path="/signin">
            <SignIn
              setStateAdminLogin={this.setStateAdminLogin}
              setStateEmLogin={this.setStateEmLogin}
              setStateLogin={this.setStateLogin}
              saveAuthentication={this.saveAuthentication}
            />
          </UnAuthenRoute>
          <ProtectedRoute isLogin={isLogin} exact path="/cart">
            <AppRoute isLogin={isLogin} user={user} cart={cart}>
              <Cart user={user} handleCart={this.handleCart} />
            </AppRoute>
          </ProtectedRoute>

          <ProtectedRoute isLogin={isLogin} exact path="/orderhistory">
            <AppRoute isLogin={isLogin} user={user}>
              <OrderHistory user={user} />
            </AppRoute>
          </ProtectedRoute>

          <ProtectedRoute isLogin={isLogin} exact path="/orderhistory/:id">
            <AppRoute isLogin={isLogin} user={user}>
              <ViewDetailHistory user={user} />
            </AppRoute>
          </ProtectedRoute>

          <Route isLogin={isLogin} exact path="/product">
            <AppRoute isLogin={isLogin}>
              <TestProduct />
            </AppRoute>
          </Route>
          <Route isLogin={isLogin} exact path="/product/:id">
            <AppRoute isLogin={isLogin}>
              <DetailProduct user={user} />
            </AppRoute>
          </Route>
          <ProtectedRoute user={user} exact path="/profile" isLogin={isLogin}>
            <AppRoute isLogin={isLogin}>
              <Profile
                user={user}
                isLogin={isLogin}
                Authentication={Authentication}
              />
            </AppRoute>
          </ProtectedRoute>

          <ProtectedRoute exact path="/profile/:id" isLogin={isLogin}>
            <AppRoute isLogin={isLogin}>
              <EditProfile
                isLogin={isLogin}
                Authentication={Authentication}
                user={user}
              />
            </AppRoute>
          </ProtectedRoute>
        </Switch>

        <Switch>
          {/* <UnAuthenRoute isLogin ={isLoginAdmin} exact path="/admin/signin">
            <AdminSignin  setStateAdminLogin= {this.setStateAdminLogin} saveAuthentication = {this.saveAuthentication}></AdminSignin>
          </UnAuthenRoute> */}
          <ProtectedRouteAdmin isLogin={isLoginAdmin} exact path="/admin">
            <AppRouteAdmin isLogin={isLoginAdmin}>
              <ManageUser
                Authentication={Authentication}
                getAllUser={this.getAllUser}
              />
            </AppRouteAdmin>
          </ProtectedRouteAdmin>

          <ProtectedRouteAdmin
            isLogin={isLoginAdmin}
            exact
            path="/admin/manageuser"
          >
            <AppRouteAdmin isLogin={isLoginAdmin}>
              <ManageUser
                Authentication={Authentication}
                getAllUser={this.getAllUser}
              />
            </AppRouteAdmin>
          </ProtectedRouteAdmin>
          <ProtectedRouteAdmin
            isLogin={isLoginAdmin}
            exact
            path="/admin/manageuser/:id"
          >
            <AppRouteAdmin isLogin={isLoginAdmin}>
              <UserDetails Authentication={Authentication} />
            </AppRouteAdmin>
          </ProtectedRouteAdmin>

          <ProtectedRouteAdmin
            isLogin={isLoginAdmin}
            exact
            path="/admin/manageproduct"
          >
            <AppRouteAdmin isLogin={isLoginAdmin}>
              <ManageProduct Authentication={Authentication} />
            </AppRouteAdmin>
          </ProtectedRouteAdmin>

          <ProtectedRouteAdmin
            isLogin={isLoginAdmin}
            exact
            path="/admin/manageproduct/newproduct"
          >
            <AppRouteAdmin isLogin={isLoginAdmin}>
              <NewProduct Authentication={Authentication} />
            </AppRouteAdmin>
          </ProtectedRouteAdmin>

          <ProtectedRouteAdmin
            isLogin={isLoginAdmin}
            exact
            path="/admin/manageproduct/edit/:id"
          >
            <AppRouteAdmin isLogin={isLoginAdmin}>
              <EditProduct Authentication={Authentication} />
            </AppRouteAdmin>
          </ProtectedRouteAdmin>

          <ProtectedRouteAdmin
            isLogin={isLoginAdmin}
            exact
            path="/admin/manageorder"
          >
            <AppRouteAdmin isLogin={isLoginAdmin}>
              <OrderHistoryAdmin Authentication={Authentication} />
            </AppRouteAdmin>
          </ProtectedRouteAdmin>

          <ProtectedRouteAdmin
            isLogin={isLoginAdmin}
            exact
            path="/admin/manageorder/:id"
          >
            <AppRouteAdmin isLogin={isLoginAdmin}>
              <ViewDetailHistoryAdmin Authentication={Authentication} />
            </AppRouteAdmin>
          </ProtectedRouteAdmin>
        </Switch>

        <Switch>
          <ProtectedRouteEm isLogin={isLoginEm} exact path="/employee">
            <AppRoute2 isLogin={isLoginEm}>
              <ManageProductEm Authentication={Authentication} />
            </AppRoute2>
          </ProtectedRouteEm>

          {/* <UnAuthenRoute isLogin ={isLoginEm} exact path ="/signin">
              <EmSignIn setStateEmLogin= {this.setStateEmLogin} saveAuthentication = {this.saveAuthentication}/>
            </UnAuthenRoute> */}

          <ProtectedRouteEm
            isLogin={isLoginEm}
            exact
            path="/employee/manageproduct"
          >
            <AppRoute2 isLogin={isLoginEm}>
              <ManageProductEm Authentication={Authentication} />
            </AppRoute2>
          </ProtectedRouteEm>

          <ProtectedRouteEm
            isLogin={isLoginEm}
            exact
            path="/employee/manageproduct/edit/:id"
          >
            <AppRoute2 isLogin={isLoginEm}>
              <EditProductEm Authentication={Authentication} />
            </AppRoute2>
          </ProtectedRouteEm>

          <ProtectedRouteEm
            isLogin={isLoginEm}
            exact
            path="/employee/manageorder"
          >
            <AppRoute2 isLogin={isLoginEm}>
              <OrderHistoryEm Authentication={Authentication} />
            </AppRoute2>
          </ProtectedRouteEm>

          <ProtectedRouteEm
            isLogin={isLoginEm}
            exact
            path="/employee/manageorder/:id"
          >
            <AppRoute2 isLogin={isLoginEm}>
              <ViewDetailHistoryEm Authentication={Authentication} />
            </AppRoute2>
          </ProtectedRouteEm>
        </Switch>
      </BrowserRouter>
    );
  }
}

export default Hanu_minimart;
