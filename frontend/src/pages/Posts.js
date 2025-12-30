import { useEffect, useState, useContext, Fragment } from "react";
import ForumPagination from "../components/ForumPagination";
import { Box, Grid, Avatar, Card, CardContent } from "@mui/material";
import { useNavigate, useSearchParams } from "react-router-dom";
import { ApiContext } from "../context/Context";
import PersonIcon from "@mui/icons-material/Person";
import CreatePostForm from "../components/CreatePost";

export default function Posts() {
  const [searchParams] = useSearchParams();
  const topicId = searchParams.get("topicId");
  const page = parseInt(searchParams.get("page") ?? '0', 10);
  const navigate = useNavigate();

  const [posts, setPosts] = useState([]);
  const [totalPages, setTotalPages] = useState(0);
  const [pageSize, setPageSize] = useState(10);

  const [currentPage, setCurrentPage] = useState(1);
  const [loading, setLoading] = useState(true);
  const apiService = useContext(ApiContext);

  useEffect(() => {
    setLoading(true);
    apiService
      .getPosts(topicId, page)
      .then((resp) => {
        setPosts(resp.content);
        setTotalPages(resp.page.totalPages);
        setCurrentPage(resp.page.number + 1);
      })
      .finally(() => setLoading(false));
  }, [page, topicId]);

  const handlePostUpdate = (post) => {
    console.log(posts.length);
    if (posts.length >= pageSize) {
      navigate(`/posts?topicId=${topicId}&page=${totalPages}`);
    } else {
      const newArr = [...posts, post];
      setPosts(newArr);
    }
  }

  return (
    <>
      <Box
        sx={{
          margin: "10px 0",
          display: "flex",
          justifyContent: "space-between",
        }}
      >
        <CreatePostForm topicId={topicId} onPostAdded={(p) => handlePostUpdate(p)} />
        <ForumPagination
          totalPages={totalPages}
          currentPage={currentPage}
          changeCallback={(event, page) => {
            navigate(`/posts?topicId=${topicId}&page=${page - 1}`);
          }}
        />
      </Box>
      <Box>
        <Grid container spacing={0}>
          {posts.map((p) => (
            <Card
              key={p.id}
              sx={{
                width: "100%",
                marginBottom: "10px",
                backgroundColor: "rgb(87, 90, 90)",
                color: "rgb(204, 204, 204)",
              }}
            >
              <CardContent>
                <Grid container>
                  <Grid size={10} sx={{ borderRight: '1px solid #777' }}>
                    <Box display="flex" sx={{ flexDirection: 'column' }}>
                      <Box sx={{ marginBottom: '20px' }}>by <span style={{ color: '#FF7E00', fontWeight: 700 }}>{p.createdBy.username}</span> {'>>'} {new Date(p.createdAt).toLocaleString()}</Box>
                      <Box sx={{ color: '#ddd', lineHeight: '1.4em', marginRight: '40px' }}>{p.body}</Box>
                    </Box>
                  </Grid>
                  <Grid size={2} >
                    <Box sx={{ margin: '0 20px' }}>
                      <Avatar variant="rounded" sx={{ width: 80, height: 80, marginBottom: '5px' }}>
                        <PersonIcon sx={{ fontSize: 50 }} />
                      </Avatar>
                      <Box sx={{ color: '#FF7E00', fontWeight: 700 }}>
                        {p.createdBy.username}
                      </Box>
                    </Box>
                  </Grid>
                </Grid>

              </CardContent>
            </Card>
          ))}
        </Grid>
      </Box>
    </>
  );
}