export let movies = [
    {
        id: 0,
        name: 'Tom',
        score: 5
    },
    {
        id: 1,
        name: 'Amy',
        score: 1
    },
    {
        id: 2,
        name: 'Emily',
        score: 2
    }
];

export const getMovies = () => movies;

export const getById = id => {
    const filteredMovie = movies.filter(movie => id === movie.id);
    return filteredMovie[0];
};

export const addMovie = (name, score) => {
    const newMovie = {
        id: movies.length + 1,
        name,
        score,
    }
    movies.push(newMovie)
    return newMovie
}

export const deleteMovie = id => {
    const cleanedMovies = movies.filter(movie => id !== movie.id);
    if (movies.length > cleanedMovies.length) {
        movies = cleanedMovies;
        return true;
    } else {
        return false;
    }
}