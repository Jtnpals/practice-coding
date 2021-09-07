import Link from 'next/link'

const Navbar = () => {
  return (
    <nav className="container m-auto bg-gray-400 flex text-xl items-center h-14">
      <div className="logo text-4xl ml-4 mr-5">amuamu</div>
      <li className="flex gap-2">
        <ul>
          <Link href="/">
            <a>home</a>
          </Link>
        </ul>
        <ul>
          <Link href="/about">
            <a>about</a>
          </Link>
        </ul>
        <ul>
          <Link href="/list">
            <a>list</a>
          </Link>
        </ul>
      </li>
      <li className="flex ml-auto gap-3 mr-4">
        <ul>
          <a>user</a>
        </ul>
        <ul>
          <a>wish</a>
        </ul>
        <ul>
          <a>some</a>
        </ul>
      </li>
    </nav>
  )
}

export default Navbar
