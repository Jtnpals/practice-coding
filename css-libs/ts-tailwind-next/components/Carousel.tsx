import hero_1 from "../public/hero_1.jpg";
import hero_2 from "../public/hero_2.jpg";
import hero_3 from "../public/hero_3.jpg";
import hero_4 from "../public/hero_4.jpg";

import Image from "next/image";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";
import "swiper/css/pagination";
import "swiper/css/navigation";

import SwiperCore, { Autoplay, Pagination, Navigation } from "swiper";

SwiperCore.use([Autoplay, Pagination, Navigation]);

const Carousel = () => {
  return (
    <>
      <Swiper
        slidesPerView={2}
        spaceBetween={0}
        autoplay={{
          delay: 2500,
          disableOnInteraction: true,
        }}
        loop={true}
        pagination={{
          clickable: true,
        }}
      >
        <SwiperSlide>
          <Image src={hero_1} />
        </SwiperSlide>
        <SwiperSlide>
          <Image src={hero_2} />
        </SwiperSlide>
        <SwiperSlide>
          <Image src={hero_3} />
        </SwiperSlide>
        <SwiperSlide>
          <Image src={hero_4} />
        </SwiperSlide>
      </Swiper>
    </>
  );
};

export default Carousel;
