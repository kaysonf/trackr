import {useEffect, useState} from "react";

export default function useGet(url: string) {

    const [data, setData] = useState<unknown>();
    const [error, setError] = useState<string | null>(null);
    const [loading, setLoading] = useState<boolean>(true);

    useEffect(() => {
        if (!url) return;

        fetch(url)
            .then(data => data.json())
            .then(setData)
            .catch(setError)
            .finally(() => setLoading(false));

    }, [url]);

    return { data, loading, error };
}