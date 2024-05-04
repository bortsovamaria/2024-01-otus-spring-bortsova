import {createApi, fetchBaseQuery} from '@reduxjs/toolkit/query/react'
import {AuthorProps, BookProps, GenreProps} from "./interface";

export const bookApi = createApi({
    reducerPath: 'bookApi',
    tagTypes: ["books", "authors", "genres"],
    baseQuery: fetchBaseQuery({baseUrl: 'http://localhost:8080/api'}),
    endpoints: (builder) => ({
        bookList: builder.query<BookProps[], void>({
            query: (name) => `/books`,
            providesTags: ["books"]
        }),
        addBook: builder.mutation<void, BookProps>({
            query(body) {
                return {
                    url: `/books`,
                    method: 'POST',
                    body,
                }
            },
            invalidatesTags: ["books"],
        }),
        updateBook: builder.mutation<void, BookProps>({
            query(body) {
                return {
                    url: `/books`,
                    method: 'PUT',
                    body,
                }
            },
            invalidatesTags: ["books"],
        }),
        deleteBook: builder.mutation<{ success: boolean; id: number }, number>({
            query(id) {
                return {
                    url: `/books/${id}`,
                    method: 'DELETE',
                }
            },
            invalidatesTags: ["books"],
        }),

        authorList: builder.query<AuthorProps[], void>({
            query: (name) => `/authors`,
            providesTags: ["authors"]
        }),
        genreList: builder.query<GenreProps[], void>({
            query: (name) => `/genres`,
            providesTags: ["genres"]
        }),
    }),
})

export const {
    useBookListQuery,
    useAddBookMutation,
    useUpdateBookMutation,
    useDeleteBookMutation,
    useAuthorListQuery,
    useGenreListQuery
} = bookApi