import {Books} from "./Books";
import {AddBook} from "./AddBook";
import {Stack} from "@mui/material";

const App = () => {

    return (
        <Stack spacing={5}>
            <AddBook/>
            <Books/>
        </Stack>
    );
}

export default App;
