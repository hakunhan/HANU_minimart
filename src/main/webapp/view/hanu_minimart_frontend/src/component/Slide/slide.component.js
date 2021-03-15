import React from 'react';
import './slide.component.css'
import { Slide } from 'react-slideshow-image';

import 'react-slideshow-image/dist/styles.css'

// update nào 
const proprietes = {
  duration: 5000,
  transitionDuration: 500,
  infinite: true,
  indicators: true,
  arrows: true,
};
const Slideshow = () => {
  return (
    <div className="containerSlide">
      <Slide {...proprietes}>
        <div className="each-slide">
          <div>
            <img src= 'https://lh3.googleusercontent.com/ZbbTi42WeEM716yNHBpuDFlkEslfh2Q0FwUEa3hPvh7XtAwI7_iVxyeqR_OT9NORl9lkkMdLlCgpy-Bi46RYtDvygs2PtVHn=w1180' alt="img1" />
          </div>
        </div>
        <div className="each-slide">
          <div>
            <img src='https://lh3.googleusercontent.com/J9pdTAh_HHDJSDDUGKJJDCtZ7xq6P3Ppdr-gU5zxp7xhtxs-OW06qpirYhDTDPaw7-jAWOmHzhAUat9h9r5M019gFQwqiSQ=w1180' alt="img2" />
          </div>
        </div>
        <div className="each-slide">
          <div>
            <img src='https://lh3.googleusercontent.com/Tc28yWWbpG8uVdz5xqyfEGo4dZf_VEdv6B_V6aU1lyM2KIPXhk7YKlwlJ_SNTk9xRF3psb0hYWzH1o6PPb1FMsWDJKVy8XU=w1180' alt="img3" />
          </div>
        </div>
      </Slide>
    </div>
  );
};
export default Slideshow;