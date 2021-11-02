import { Link } from "react-router-dom";
import { gql, useMutation } from "@apollo/client";

type MovieProps = {
  id: string;
  medium_cover_image: string | undefined;
  title: string | undefined;
  isLiked: boolean;
};

const Like_MOVIE = gql`
  mutation toggleLikeMovie($id: Int!, $isLiked: Boolean!) {
    toggleLikeMovie(id: $id, isLiked: $isLiked) @client
  }
`;

const Movie = ({ id, medium_cover_image, title, isLiked }: MovieProps) => {
  const [toggleLikeMovie] = useMutation<null>(Like_MOVIE, {
    variables: { id: parseInt(id), isLiked },
  });

  const handleClick = () => {
    toggleLikeMovie();
  };

  return (
    <>
      <Link to={`/${id}`}>
        <img key={id} src={medium_cover_image} alt={title} />
      </Link>
      <button onClick={handleClick}>{isLiked ? "Unlike" : "Like"}</button>
    </>
  );
};

export default Movie;
