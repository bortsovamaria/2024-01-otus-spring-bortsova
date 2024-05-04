import styles from "../mystyle.module.css";
import {DeleteBook} from "./DeleteBook";
import {Stack} from "@mui/material";
import {UpdateBook} from "./UpdateBook";
import {useBookListQuery} from "../api";

export const Books = () => {

    const {data: books} = useBookListQuery();
    console.log("books", books)

    return (
        <table className={styles.table}>
            <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Genre</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            {
                books && books.map((book, i) => (
                    <tr key={book.id}>
                        <td>{book.id}</td>
                        <td>{book.title}</td>
                        <td>{book.author.fullName}</td>
                        <td>{book.genre.name}</td>
                        <td>
                            <Stack spacing={1}>
                                <UpdateBook book={book}/>
                                <DeleteBook id={book.id}/>
                            </Stack></td>
                    </tr>
                ))
            }
            </tbody>
        </table>
    );
}