 const URL = "/listToJson";

        window.onload = function getData(){
            const xhr = new XMLHttpRequest();
            xhr.onreadystatechange = () => {
                if (xhr.readyState == xhr.DONE){
                    if (xhr.status == 200){
                        let members = JSON.parse(xhr.responseText);
                        renderHTML(members);
                    }
                }
            }

            xhr.open("GET", URL);
            xhr.send();
        }

        function renderHTML(members){
            let output = "";
            for (let m of members){
                output += `
                    <ul>
                        <li>
                            아이디 : ${m.id}
                        </li>
                        <li>
                            이름 : ${m.username}
                        </li>
                        <li>
                            이메일 : ${m.email}
                        </li>
                        <li>
                            가입일자 : ${m.regDate}
                        </li>
                    </ul>
                    <a href = "/detail/${m.id}">수정</a>
                    <a href = "/detail?id=${m.id}">수정</a>
                `;
            }

            document.querySelector("#result").innerHTML = output;
 }