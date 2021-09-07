import { FC } from 'react'
import Footer from './Footer'
import Navbar from './Navbar'
import Promotion from './Promotion'

const Layout: FC = ({ children }) => {
  return (
    <div className="bg-gray-100 flex-col font-gogle-noto">
      <Promotion />
      <Navbar />
      {children}
      <Footer />
    </div>
  )
}

export default Layout
