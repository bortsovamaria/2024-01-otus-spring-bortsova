import {Autocomplete, Button, Dialog, DialogContent, DialogTitle, Stack, TextField, Typography} from "@mui/material";
import {useAuthorListQuery, useGenreListQuery, useUpdateBookMutation} from "../api";
import {BookProps} from "../interface";
import {useSnackbar} from "notistack";
import {useState} from "react";
import {Form, FormikProvider, useFormik} from "formik";
import * as Yup from "yup";


export const UpdateBook = (props: { book: BookProps }) => {
    const {enqueueSnackbar} = useSnackbar();

    const [openForm, setOpenForm] = useState(false);

    const [update] = useUpdateBookMutation();

    const {data: authors} = useAuthorListQuery();
    const {data: genres} = useGenreListQuery();

    const FormValidation = Yup.object().shape({
        title: Yup.string().required(""),
        author: Yup.string().required(""),
        genre: Yup.string().required(""),
    });

    const formik = useFormik({
        initialValues: {
            title: props.book.title,
            author: props.book.author,
            genre: props.book.genre,
        },
        validationSchema: FormValidation,
        onSubmit: () => console.log("submit")
    });


    const handleEdit = () => {
        update({
            id: props.book.id,
            title: formik.values.title,
            author: formik.values.author,
            genre: formik.values.genre,
        })
            .unwrap()
            .then(() => {
                enqueueSnackbar("Success edited!")
            })
            .catch((error: any) => {
                enqueueSnackbar("ERROR!")
            });
        setOpenForm(false);
    }
    return (
        <>
            <Button onClick={() => setOpenForm(true)} variant={"contained"}>Update</Button>
            <Dialog open={openForm} onClose={() => setOpenForm(false)}>
                <DialogTitle>Update book form</DialogTitle>
                <DialogContent>
                    <FormikProvider value={formik}>
                        <Form>
                            <Typography marginY={3}>Complete fields</Typography>
                            <Stack marginY={4} spacing={2}>
                                <TextField
                                    defaultValue={props.book.title}
                                    label={"Title"}
                                    {...formik.getFieldProps("title")}
                                />
                                <Autocomplete
                                    disablePortal
                                    id="authors"
                                    options={authors || []}
                                    sx={{width: 300}}
                                    getOptionLabel={(option) => option.fullName}
                                    renderInput={(params) => <TextField {...params} label="Author"/>}
                                    onChange={(event, newValue) =>
                                        formik.setFieldValue("author", newValue)}
                                />
                                <Autocomplete
                                    disablePortal
                                    id="genres"
                                    options={genres || []}
                                    sx={{width: 300}}
                                    getOptionLabel={(option) => option.name}
                                    renderInput={(params) => <TextField {...params} label="Genre"/>}
                                    onChange={(event, newValue) =>
                                        formik.setFieldValue("genre", newValue)}
                                />
                            </Stack>
                            <Button variant={"contained"} onClick={handleEdit}>Update</Button>
                        </Form>
                    </FormikProvider>
                </DialogContent>
            </Dialog>
        </>
    )
}