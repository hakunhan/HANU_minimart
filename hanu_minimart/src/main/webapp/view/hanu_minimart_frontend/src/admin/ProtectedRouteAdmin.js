import React from "react";
import { BrowserRouter as Router, withRouter, Link, Route, Redirect } from 'react-router-dom';



class ProtectedRouteAdmin extends React.Component{

    render(){
        const {isLogin,children, path, exact} = this.props;
        

        return <Route {...this.props} exact ={exact} to = {path} >
            {isLogin ? children : <Redirect push to ="/admin/signin"/>}
        </Route>
    }
}

export default withRouter(ProtectedRouteAdmin);