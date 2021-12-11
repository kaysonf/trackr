import {NextPage} from "next";
import useApi from '../shared/api/index';
import {UserProfile} from "../components/user/UserProfile";
import {UserModel} from "../shared/models/User";

const User: NextPage = () => {
    const {data, loading, error} = useApi.getUser('sonkay_handle');

    return (
        <>
            {data ? <UserProfile {...data as UserModel} /> : null}
        </>
    );
}

export default User;