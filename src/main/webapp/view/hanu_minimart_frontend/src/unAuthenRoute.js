import React, { Children } from 'react';

import { BrowserRouter as Router, withRouter, Link, Route, Redirect } from 'react-router-dom';


class UnAuthenRoute extends React.Component{

  componentDidMount(){
    console.log("unauthen")
  }
  render(){
    const {isLogin, path,exact,children} = this.props;
    console.log("isLogin: ", isLogin)
    if(isLogin){
      console.log("goback")
      this.props.history.goBack();
      return null;
    }else{
      console.log("not goback")
      return  (
        <Route
          // {...rest}
          // render={(routeProps) => (
          //   <Layout>
          //     <Component {...routeProps} />
          //   </Layout>
          // )}/>
        exact = {exact}
        to ={path}
        > {children}
        </Route>
      );
    }
  }
}

export default withRouter(UnAuthenRoute);