import { useEffect, useState, useContext } from "react";
import ForumPagination from '../components/ForumPagination';
import TopicsTable from '../components/TopicsTable';
import { Box } from "@mui/material";
import { useNavigate, useSearchParams } from "react-router-dom";
import { ApiContext } from '../context/Context'

export default function Topics() {
    const [searchParams] = useSearchParams();
    const categoryId = searchParams.get('categoryId');
    const page = searchParams.get('page');
    const navigate = useNavigate();

    const [topics, setTopics] = useState([]);
    const [count, setCount] = useState(0);
    const [currentPage, setCurrentPage] = useState(1);
    const [loading, setLoading] = useState(true);
    const apiService = useContext(ApiContext);

    useEffect(() => {
        setLoading(true)
        apiService
            .getTopics(categoryId, page)
            .then(resp => {
                setTopics(resp.data.content);
                setCount(resp.data.totalPages);
                setCurrentPage(resp.data.number + 1);
            })
            .finally(() => setLoading(false));
    }, [page, categoryId]);

    return (
        <>
            <Box sx={{ margin: '10px 0', display: 'flex', flexDirection: 'row-reverse' }}>
                <ForumPagination
                    totalPages={count}
                    currentPage={currentPage}
                    changeCallback={(event, page) => { navigate(`/topics?categoryId=${categoryId}&page=${page - 1}`); }}
                />
            </Box>
            <TopicsTable topics={topics} isLoading={loading} />
        </>
    )
}