import useGet from "./query";

const BASE_URL = 'http://localhost:8080/api';

export default {
    getUser: (handle: string) => useGet(`${BASE_URL}/user/${handle}`)
}