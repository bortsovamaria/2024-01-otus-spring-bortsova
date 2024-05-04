export interface BookProps {
    id: number;
    title: string;
    author: {
        id: number;
        fullName: string;
    };
    genre: {
        id: number;
        name: string;
    }
}

export interface AuthorProps {
    id: number;
    fullName: string;
}

export interface GenreProps {
    id: number;
    name: string;
}