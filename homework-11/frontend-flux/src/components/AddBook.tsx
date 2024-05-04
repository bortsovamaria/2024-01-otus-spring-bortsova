import {Autocomplete, Button, Dialog, DialogContent, DialogTitle, Stack, TextField, Typography} from "@mui/material";
import {useAddBookMutation, useAuthorListQuery, useGenreListQuery} from "../api";
import {useSnackbar} from "notistack";
import {useState} from "react";
import {Form, FormikProvider, useFormik} from "formik";
import * as Yup from "yup";


export const AddBook = () => {
    const {enqueueSnackbar} = useSnackbar();

    const [openForm, setOpenForm] = useState(false);

    const [add] = useAddBookMutation();

    const {data: authors} = useAuthorListQuery();
    const {data: genres} = useGenreListQuery();

    const FormValidation = Yup.object().shape({
        title: Yup.string().required(""),
        author: Yup.string().required(""),
        genre: Yup.string().required(""),
    });

    const formik = useFormik({
        initialValues: {
            title: "",
            author: {
                id: 0,
                fullName: ""
            },
            genre: {
                id: 0,
                name: ""
            }
        },
        validationSchema: FormValidation,
        onSubmit: () => console.log("submit")
    });


    const handleAdd = () => {
        add({
            id: 0,
            title: formik.values.title,
            author: {
                id: formik.values.author.id,
                fullName: formik.values.author.fullName
            },
            genre: {
                id: formik.values.genre.id,
                name: formik.values.genre.name
            },
        })
            .unwrap()
            .then(() => {
                enqueueSnackbar("Success added!")
            })
            .catch((error: any) => {
                enqueueSnackbar("ERROR!")
            });
        setOpenForm(false);
    }
    return (
        <>
            <Button onClick={() => setOpenForm(true)} variant={"contained"}>Add book</Button>
            <Dialog open={openForm} onClose={() => setOpenForm(false)}>
                <DialogTitle>Add book form</DialogTitle>
                <DialogContent>
                    <FormikProvider value={formik}>
                        <Form>
                            <Typography marginY={3}>Complete fields</Typography>
                            <Stack marginY={4} spacing={2}>
                                <TextField
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
                            <Button variant={"contained"} onClick={handleAdd}>Add</Button>
                        </Form>
                    </FormikProvider>
                </DialogContent>
            </Dialog>
        </>
    )
}