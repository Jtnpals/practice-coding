import axios from 'axios'
import useAsync from './useAsync'

async function getUser(id) {
  const response = await axios.get(
    `http://jsonplaceholder.typicode.com/users/${id}`
  )
  return response.data
}

const User = ({ id }) => {
  const [state] = useAsync(() => getUser(id), [id])
  const { loading, data: user, error } = state
  if (loading) return <div>loading...</div>
  if (error) return <div>error...</div>
  if (!user) return null

  return (
    <div>
      <h2>{user.username}</h2>
      <p>
        <b>Email:</b> {user.email}
      </p>
    </div>
  )
}

export default User
