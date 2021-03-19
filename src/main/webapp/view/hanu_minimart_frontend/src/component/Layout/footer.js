import React from 'react';

import './footer.css'
class Footer1 extends React.Component{
    render(){
        return(
            <div>
            <footer class="bg-white">
    <div className="container py-5">
      <div className="row py-4">
        <div className="col-lg-4 col-md-6 mb-4 mb-lg-0"><img src="img/logo.png" alt="" width="180" className="mb-3"/>
          <p className="font-italic" style={{color: "#fff"}}>Freshness you can taste. Values you can trust.</p>
          {/* <ul class="list-inline mt-4">
            <li class="list-inline-item"><a href="#" target="_blank" title="twitter"><i class="fa fa-twitter"></i></a></li>
            <li class="list-inline-item"><a href="#" target="_blank" title="facebook"><i class="fa fa-facebook"></i></a></li>
            <li class="list-inline-item"><a href="#" target="_blank" title="instagram"><i class="fa fa-instagram"></i></a></li>
            <li class="list-inline-item"><a href="#" target="_blank" title="pinterest"><i class="fa fa-pinterest"></i></a></li>
            <li class="list-inline-item"><a href="#" target="_blank" title="vimeo"><i class="fa fa-vimeo"></i></a></li>
          </ul> */}
        </div>
        {/* <div class="col-lg-2 col-md-6 mb-4 mb-lg-0">
          <h6 class="text-uppercase font-weight-bold mb-4">Shop</h6>
          <ul class="list-unstyled mb-0">
            <li class="mb-2"><a href="#" class="text-muted">For Women</a></li>
            <li class="mb-2"><a href="#" class="text-muted">For Men</a></li>
            <li class="mb-2"><a href="#" class="text-muted">Stores</a></li>
            <li class="mb-2"><a href="#" class="text-muted">Our Blog</a></li>
          </ul>
        </div> */}
        <div className="col-lg-2 col-md-6 mb-4 mb-lg-0">
          <h6 className="text-uppercase font-weight-bold mb-4" style={{color: "#fff"}}>Company</h6>
          <ul className="list-unstyled mb-0">
            <li className="mb-2"><a href="#" >Login</a></li>
            <li className="mb-2"><a href="#">Register</a></li>
            <li className="mb-2"><a href="#" >Wishlist</a></li>
            <li className="mb-2"><a href="#" >Our Products</a></li>
          </ul>
        </div>
        <div className="col-lg-4 col-md-6 mb-lg-0">
          <h6 className="text-uppercase font-weight-bold mb-4" style={{color: "#fff"}}>Newsletter</h6>
          <p className=" mb-4" style={{color: "#fff"}}>Groceries and more, delivered straight to your door!</p>
          <div className="p-1 rounded border">
            <div className="input-group">
              <input type="email" placeholder="Enter your email address" aria-describedby="button-addon1" className="form-control border-0 shadow-0"/>
              <div className="input-group-append">
                <button id="button-addon1" type="submit" className="btn btn-link"><i className="fa fa-paper-plane"></i></button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div className="bg-light py-4">
      <div className="container text-center">
        <p className="mb-0 py-2">© 2020 SQA_HANU_MINIMART</p>
      </div>
    </div>
  </footer>
  </div>
        )

    }
}
export default Footer1;