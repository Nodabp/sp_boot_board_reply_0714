// async function getOne(bno) { // 비동기 처리를 위한 함수 선언 ~~
//     console.log(bno);
//     // const result = await axios.get(`/api/replies/list/{${bno}}`); // axios 방식으로
//
//     axios.defaults.baseURL = 'http://localhost:8080';
//     const result = await axios.get(`/api/replies/list/${bno}?page=1`);
//
//     console.log(result);
//     return result.data;
// }

async function getList(bno, page, size, goLast) {
    axios.defaults.baseURL = 'http://localhost:8080';
    const result = await axios.get(`/api/replies/list/${bno}`, {
        params: {page, size}
    });

    if (goLast) { // 마지막 페이지로 이동
        const total = result.data.total; // 전체 리플 수
        const lastPage = Math.ceil(total / size) // 마지막 페이지

        return getList(bno, lastPage, size);
    }

    return result.data;
}

// 등록
async function addReply(obj) {
    axios.defaults.baseURL = 'http://localhost:8080';
    const response = await axios.post('/api/replies/', obj);
    return response;
}

// 조회
async function getReply(rno) {
    axios.defaults.baseURL = 'http://localhost:8080';
    const response = await axios.get(`/api/replies/${rno}`);
    return response.data;
}


// 수정

async function modifyReply(obj) {
    axios.defaults.baseURL = 'http://localhost:8080';
    const response = await axios.put(`/api/replies/${obj.rno}`,obj);
    return response.data;
}