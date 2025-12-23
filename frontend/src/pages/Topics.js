import Header from "../components/Header"
import { Fragment } from "react/jsx-runtime"
import { useSearchParams } from 'react-router-dom';
import TopicsTable from '../components/TopicsTable'


const topics = [
    {
        "id": "b3f8a2c1-7d94-4e1b-9f3d-8c6e5a4b2c1d",
        "name": "Quantum React Hooks",
        "postsCount": 42,
        "createdAt": "2025-12-20T14:23:11.882Z",
        "categoryId": "a9f7d3e5-1b8c-4d2f-9e6a-7c5b4a3f2e1d",
        "createdBy": { "id": "c4d5e6f7-8a9b-4c1d-9e2f-3g4h5i6j7k8l", "username": "Dana" }
    },
    {
        "id": "7a9f2b4c-5e8d-4f1a-9b3c-2d4e6f8a1b3c",
        "name": "Serverless State Machines",
        "postsCount": 17,
        "createdAt": "2025-12-19T09:45:33.221Z",
        "categoryId": "b8c7d6e5-4f3a-2b1c-9d8e-7f6a5b4c3d2e",
        "createdBy": { "id": "d5e6f7g8-9a0b-5c2d-0e3f-4g5h6i7j8k9l", "username": "Bob" }
    },
    {
        "id": "4c8e1d3a-6b7f-4a2c-8d9e-1f3a5c7e9b2d",
        "name": "Edge-Runtime GraphQL",
        "postsCount": 63,
        "createdAt": "2025-12-18T22:11:05.445Z",
        "categoryId": "c7d6e5f4-3a2b-1c0d-8e7f-6a5b4c3d2e1f",
        "createdBy": { "id": "e6f7g8h9-0a1b-6c3d-1e4f-5g6h7i8j9k0l", "username": "Charlie" }
    },
    {
        "id": "9d2f5a7b-3c8e-4f1d-9b2a-4c6e8f1a3b5d",
        "name": "WebAssembly Micro-frontends",
        "postsCount": 29,
        "createdAt": "2025-12-21T07:33:28.119Z",
        "categoryId": "d6e5f4g3-2a1b-0c9d-7e6f-5a4b3c2d1e0f",
        "createdBy": { "id": "f7g8h9i0-1a2b-7c4d-2e5f-6g7h8i9j0k1l", "username": "Eve" }
    },
    {
        "id": "2e6a9b4c-5d7f-4a1b-8c2d-3e5f7a9b2c4d",
        "name": "Rust-powered Bundlers",
        "postsCount": 88,
        "createdAt": "2025-12-17T11:54:42.667Z",
        "categoryId": "e5f4g3h2-1a0b-9c8d-6e5f-4a3b2c1d0e9f",
        "createdBy": { "id": "g8h9i0j1-2a3b-8c5d-3e6f-7g8h9i0j1k2l", "username": "Alice" }
    },
    {
        "id": "5b1d4a7e-8c2f-4b3a-9d1e-2f4a6c8e1b3d",
        "name": "AI-Driven Type Generation",
        "postsCount": 51,
        "createdAt": "2025-12-22T03:12:19.334Z",
        "categoryId": "f4g3h2i1-0a9b-8c7d-5e4f-3a2b1c0d9e8f",
        "createdBy": { "id": "h9i0j1k2-3a4b-9c6d-4e7f-8g9h0i1j2k3l", "username": "Dana" }
    },
    {
        "id": "8c3f6a2b-4d7e-4a1c-9b3d-5e7f9a2b4c6d",
        "name": "Real-time CRDT Sync",
        "postsCount": 34,
        "createdAt": "2025-12-16T18:47:55.901Z",
        "categoryId": "g3h2i1j0-9a8b-7c6d-4e3f-2a1b0c9d8e7f",
        "createdBy": { "id": "i0j1k2l3-4a5b-0c7d-5e8f-9g0h1i2j3k4l", "username": "Bob" }
    },
    {
        "id": "1a4d7b3e-6c8f-4b2a-9d1e-3f5a7c9e2b4d",
        "name": "Deno-First Toolchain",
        "postsCount": 11,
        "createdAt": "2025-12-15T13:29:07.558Z",
        "categoryId": "h2i1j0k9-8a7b-6c5d-3e2f-1a0b9c8d7e6f",
        "createdBy": { "id": "j1k2l3m4-5a6b-1c8d-6e9f-0g1h2i3j4k5l", "username": "Charlie" }
    },
    {
        "id": "7b2e5a8c-4d1f-4a3b-9c2d-4e6f8a1b3c5d",
        "name": "JAM-less Architectures",
        "postsCount": 76,
        "createdAt": "2025-12-14T05:03:44.772Z",
        "categoryId": "i1j0k9l8-7a6b-5c4d-2e1f-0a9b8c7d6e5f",
        "createdBy": { "id": "k2l3m4n5-6a7b-2c9d-7e0f-1g2h3i4j5k6l", "username": "Eve" }
    },
    {
        "id": "3d6a9b2c-5e8f-4a1d-8b3c-6e7f9a2b4c5d",
        "name": "Isomorphic Edge Cache",
        "postsCount": 95,
        "createdAt": "2025-12-13T20:15:31.229Z",
        "categoryId": "j0k9l8m7-6a5b-4c3d-1e0f-9a8b7c6d5e4f",
        "createdBy": { "id": "l3m4n5o6-7a8b-3c0d-8e1f-2g3h4i5j6k7l", "username": "Alice" }
    }
];

export default function Topics() {
    // const [searchParams] = useSearchParams();
    // const categoryId = searchParams.get('categoryId');

    return (
        <Fragment>
            <Header />
            <TopicsTable topics={topics} />
        </Fragment>
    )
}