import React from 'react';

import { Route } from 'react-router-dom';

import Layout from './admin/Layout';

const AppRouter2 = ({ component: Component, rest }) => {
  return (
    <Route
      {...rest}
      render={(routeProps) => (
        <Layout>
          <Component {...routeProps} />
        </Layout>
      )}
    />
  );
};

export default AppRouter2;