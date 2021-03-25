import React from "react";
import { BrowserRouter as Router, withRouter, Link, Route, Redirect } from 'react-router-dom';



class ProtectedRouteEm extends React.Component{

    render(){
        const {isLogin,children, path, exact} = this.props;
        

        return <Route {...this.props} exact ={exact} to = {path} >
            {(isLogin === true) ? children : <Redirect push to ="/signin"/>}
        </Route>
    }
}

export default withRouter(ProtectedRouteEm);