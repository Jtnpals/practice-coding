import { GraphQLServer } from 'graphql-yoga'

const typeDefs = `
type Person{
    id: Int!
    name: String!
    age: Int!
    gender: String!
}

type Query {
    people: [Person]!
    person(id: Int!): Person
}
`

const people = [
    {
        id: 0,
        name:'Tom',
        age:17,
        gender:'male'
    },
    {
        id: 1,
        name:'Amy',
        age:17,
        gender:'Female'
    },
    {
        id: 2,
        name:'Emily',
        age:17,
        gender:'Female'
    }

]

const getById = id => {
    const filteredPeople = people.filter(person => id === person.id)
    return filteredPeople[0]
}

const resolvers = {
    Query: {
        people: () => people,
        person: (_, {id}) => getById(id),
    }
}

const server = new GraphQLServer({
    typeDefs, resolvers })
server.start(() => console.log('Server is running on localhost:4000'))