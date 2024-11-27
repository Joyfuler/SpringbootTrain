const deleteButton = document.getElementById('delete-btn');

if (deleteButton){
    deleteButton.addEventListener('click', e => {
        let id = document.getElementById('article-id').value;
        function success(){
            alert("삭제가 완료되었습니다.");
            location.replace("/articles");
        }

        function fail(){
            alert("삭제하지 못했습니다.");
            location.replace("/articles");
        }

        httpRequest("DELETE", "/api/articles/" + id, null, sucess, fail);
    });
}

const modifyButton = document.getElementById('modify-btn');

if (modifyButton){
    modifyButton.addEventListener('click', event =>{
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        body = JSON.stringify({
            title : document.getElementById("title").value,
            content : document.getElementById("content").value,
        });

        function success(){
            alert("삭제가 완료되었습니다.");
            location.replace("/articles/" + id);
        }

        function fail(){
            alert("삭제하지 못했습니다.");
            location.replace("/articles/" + id);
        }

        httpRequest("PUT", "/api/articles/" + id, body, success, fail);



    });
}

const createButton = document.getElementById("create-btn");
if (createButton){
    createButton.addEventListener('click', e =>{
        body = JSON.stringify({
            title : document.getElementById("title").value,
            content: document.getElementById("content").value,
        });

        function success (){
            alert('등록이 완료되었습니다.');
            location.replace("/articles");
        }

        function fail(){
            alert("등록에 실패했습니다.");
            location.replace("/articles");
        }

        httpRequest("POST", "/api/articles", body, success, fail);

        function getCookie(key){
            var result = null;
            var cookie = document.cookie.split(";");
            // 쿠키는 ; 값으로 분리.
            cookie.some(function (item){
                item = item.replace(" ", "");
                var dic = item.split("=");
                // 띄어쓰기를 제거한 후 = 단위로 분리한다.

                if (key === dic[0]){
                    result = dic[1];
                    return true;
                    // 가장 앞의 쿠키 값을 가져온다.
                }
            });

            return result;
        }


        function httpRequest(method, url, body, success, fail){
            fetch(url, {
                method: method,
                headers: {
                    Authorization: "권한부여: " + localStorage.getItem("access_token"),
                    "Content-Type": "application/json",
                },

                body: body,                
            }).then((response) => {
                // 응답받아서 문제없이 처리된 경우
                if (response.status === 200 || response.status === 201){
                    return success();
                }
                
                const refresh_token = getCookie("refresh_token");
                if (response.status === 401 && refresh_token){
                    // 기존에 이미 값이 있는 경우는 최신화.
                    fetch("api/token", {
                        method : "POST",
                        headers : {
                            Authorization : "권한부여:" + localStorage.getItem("access_token"),
                            "Content-Type": "application/json",
                        },
                        body: JSON.stringify({
                            refreshToken : getCookie("refresh_token"),
                        }),
                    })
                    
                    .then((res) =>{
                        if (res.ok){
                            return res.json();
                        }
                    }).then((result) => {
                        localStorage.setItem("access_token", result.accessToken);
                        httpRequest(method, url, success, fail);
                    })
                    .catch((error) => fail());
                    
                } else {
                    return fail();
                }
            });
        }

    });
}
