import Link from "next/link";
import logo from "../public/logo.png";
import Image from "next/image";
import React from "react";
import {
  AiOutlineUser,
  AiOutlineShoppingCart,
  AiOutlineQuestionCircle,
} from "react-icons/ai";

const Navbar = () => {
  return (
    <nav className="xl:w-320 m-auto bg-gray-400 flex text-xl items-center h-104 font-light mb-10">
      <div className="flex items-center h-14 ml-4 mr-5 text-4xl">amu:amu</div>
      <li className="flex gap-5">
        <ul>
          <Link href="/">
            <a>홈</a>
          </Link>
        </ul>
        <ul>
          <Link href="/about">
            <a>상품</a>
          </Link>
        </ul>
        <ul>
          <Link href="/list">
            <a>이벤트</a>
          </Link>
        </ul>
      </li>
      <li className="flex ml-auto gap-5 mr-4 text-3xl items-center">
        <ul>
          <Link href="/list">
            <a>
              <AiOutlineUser />
            </a>
          </Link>
        </ul>
        <ul>
          <Link href="/list">
            <a>
              <AiOutlineShoppingCart />
            </a>
          </Link>
        </ul>
        <ul>
          <Link href="/list">
            <a>
              <AiOutlineQuestionCircle />
            </a>
          </Link>
        </ul>
      </li>
    </nav>
  );
};

export default Navbar;
