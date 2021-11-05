import { useParams } from "react-router-dom";
import { gql, useQuery } from "@apollo/client";
import { Key } from "react";

const GET_MOVIE = gql`
  query getMovie($id: Int!) {
    movie(id: $id) {
      id
      title
      medium_cover_image
      language
      rating
      description_intro
      isLiked @client
    }

    suggestions(id: $id) {
      id
      title
      medium_cover_image
    }
  }
`;

const Detail = () => {
  const { id }: { id?: string | undefined } = useParams();
  const { loading, data } = useQuery(GET_MOVIE, {
    variables: { id: Number(id) },
  });

  return (
    <div>
      {loading && <p>Loading...</p>}
      {data && (
        <div>
          <h1>{data.movie.title}</h1>
          <img src={data.movie.medium_cover_image} alt={data.movie.title} />
          <p>{data.movie.description_intro}</p>
          <p>{data.movie.isLiked ? "❤" : "😒"}</p>
        </div>
      )}

      <div>
        {data &&
          data.suggestions.map(
            (suggestion: {
              id: string | number | undefined;
              title: string | undefined;
              medium_cover_image: string | undefined;
            }) => (
              <div key={suggestion.id}>
                <h2>{suggestion.title}</h2>
                <img
                  src={suggestion.medium_cover_image}
                  alt={suggestion.title}
                />
              </div>
            )
          )}
      </div>
    </div>
  );
};

export default Detail;
