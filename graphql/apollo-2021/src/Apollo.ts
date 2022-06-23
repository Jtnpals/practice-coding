import { ApolloClient, InMemoryCache } from "@apollo/client";

const client = new ApolloClient({
  uri: "https://movieql2.vercel.app/",
  resolvers: {
    Movie: {
      isLiked: () => false,
    },
    Mutation: {
      toggleLikeMovie: (_, { id, isLiked }, { cache }) => {
        cache.modify({
          id: `Movie:${id}`,
          fields: {
            isLiked: () => !isLiked
          },
        });
      },
    },
  },
  cache: new InMemoryCache(),
});

export default client;