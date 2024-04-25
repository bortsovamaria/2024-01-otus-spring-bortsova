import {useDeleteBookMutation} from "../api";
import {Button} from "@mui/material";

export const DeleteBook = (props: {
    id: number;
}) => {
    const [deleteBook] = useDeleteBookMutation();

    const handleDelete = () => {
        deleteBook(props.id)
    }

    return (
        <Button variant={"contained"} onClick={handleDelete}>
            Delete
        </Button>
    );
}