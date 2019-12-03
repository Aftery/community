function post() {
    let questionId = $("#question_id").val();
    let contene = $("#comment_content").val();
    console.log(questionId + "<><>" + contene);
    $.ajax({
        type: "post",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": questionId,
            "content": contene,
            "type": 1
        }),
        dataType: "json",
        success: function (data) {
            console.log(data);
            if (data.code == "200") {
                console.log("进来了---------");
                $("#comment_section").hide();
            } else {
                if (data.code == "2003") {
                    var isAccepted = confirm("lll");
                    if(isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=f88879001d2eec8aa7d6&redirect_uri=http://localhost:8080/callback&scope=user&state=90");
                        localStorage.setItem("closable","true");
                    }

                } else {
                    alert(data.message);
                }
            }
        }
    })

}