import { GraphQLServer } from 'graphql-yoga'
import { getMovies } from '../graphql/db'

const typeDefs = `
type Movie{
    id: Int!
    title: String!
    rating: Float!
    summary: String!
    language: String!
    medium_cover_image: String!
}

type Query {
    movies(limit: Int!, rating:Float!): [Movie]!
}

`

const resolvers = {
    Query: {
        movies: (_,{limit, rating}) => getMovies(limit, rating),
    }
}

const server = new GraphQLServer({
    typeDefs, resolvers })
server.start(() => console.log('Server is running on localhost:4000'))