import Users from '@components/Users'
import { UsersProvider } from '@components/UsersContext'

export default function Home() {
  return (
    <UsersProvider>
      <Users />
    </UsersProvider>
  )
}
