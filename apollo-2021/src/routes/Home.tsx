import { gql, useQuery } from "@apollo/client";
import Movie from "../components/Movie";

const GET_MOVIES = gql`
  query {
    movies {
      id
      medium_cover_image
      isLiked @client
    }
  }
`;

const Home = () => {
  const { loading, error, data } = useQuery(GET_MOVIES);
  console.log(loading, error, data);

  return (
    <div>
      {loading && <p>Loading...</p>}
      {error && <p>Error :(</p>}
      {data &&
        data.movies.map(
          (movie: {
            id: string;
            medium_cover_image: string | undefined;
            title: string | undefined;
            isLiked: boolean;
          }) => (
            <Movie
              key={movie.id}
              id={movie.id}
              medium_cover_image={movie.medium_cover_image}
              title={movie.title}
              isLiked={movie.isLiked}
            />
          )
        )}
    </div>
  );
};

export default Home;
