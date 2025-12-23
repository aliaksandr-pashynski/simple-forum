import Pagination from '@mui/material/Pagination';


export default function ForumPagination({ totalPages, currentPage, changeCallback }) {
    if (totalPages > 1) {
        return (
            <Pagination
                count={totalPages}
                page={currentPage}
                variant="outlined"
                shape="rounded"
                onChange={changeCallback}
                sx={{
                    '& .MuiPaginationItem-root': {
                        color: '#ccccccff',
                        backgroundColor: '#404040ff'
                    },
                    '& .Mui-selected': {
                        color: '#ff9f1cff',
                        borderColor: '#ff9f1cff'
                    }
                }} />
        )
    }
}