import {FC} from "react";
import {Card, CardContent, Typography} from "@mui/material";
import {UserModel} from "../../shared/models/User";

export const UserProfile: FC<UserModel> = (props) => {
    const { handle } = props;
    return (
        <Card>
            <CardContent>
                <Typography>
                    {handle}
                </Typography>
            </CardContent>
        </Card>
    )
}