import * as React from 'react';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import GroupAddIcon from '@mui/icons-material/GroupAdd';
import StackedLineChartIcon from '@mui/icons-material/StackedLineChart';
import {UserModel} from "../../shared/models/User";
import {FC} from "react";

export const UserProfile: FC<UserModel> = (props) => {
    const { handle, email } = props;
    return (
        <Card sx={{ display: 'flex' }}>
            <Box sx={{ display: 'flex', flexDirection: 'column' }}>
                <CardContent sx={{ flex: '1 0 auto' }}>
                    <Typography component="div" variant="h5">
                        {handle}
                    </Typography>
                    <Typography variant="subtitle1" color="text.secondary" component="div">
                        {email}
                    </Typography>
                </CardContent>
                <Box sx={{ display: 'flex', alignItems: 'center', pl: 1, pb: 1 }}>
                    <IconButton aria-label="add friend">
                         <GroupAddIcon/>
                    </IconButton>
                    <IconButton aria-label="view chart">
                        <StackedLineChartIcon/>
                    </IconButton>
                </Box>
            </Box>
            <CardMedia
                component="img"
                sx={{ width: 151 }}
                image="https://mui.com/static/images/cards/live-from-space.jpg"
                alt={handle}
            />
        </Card>
    )
}