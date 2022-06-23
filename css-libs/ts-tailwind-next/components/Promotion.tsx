import Marquee from 'react-fast-marquee'

const Promotion = () => {
  return (
    <div className="h-10 flex items-center">
      <Marquee gradient={false} speed={80}>
        LAST DAY 50% Off Invitaion
      </Marquee>
    </div>
  )
}

export default Promotion
