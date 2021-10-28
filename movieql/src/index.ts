import { GraphQLServer } from 'graphql-yoga'
import { movies, getById, addMovie, deleteMovie } from '../graphql/db'

const typeDefs = `
type Movie{
    id: Int!
    name: String!
    score: Int!
}

type Query {
    movies: [Movie]!
    movie(id: Int!): Movie
}

type Mutation {
    addMovie(name: String!, score: Int!): Movie!
    deleteMovie(id: Int!): Boolean!
}
`

const resolvers = {
    Query: {
        movies: () => movies,
        movie: (_, {id}) => getById(id),
    },
    Mutation: {
        addMovie: (_, {name, score}) => addMovie(name, score),
        deleteMovie: (_, {id}) => deleteMovie(id)
    }
}

const server = new GraphQLServer({
    typeDefs, resolvers })
server.start(() => console.log('Server is running on localhost:4000'))