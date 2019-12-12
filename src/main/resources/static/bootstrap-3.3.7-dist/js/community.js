function comment2target(questionId, type, contene) {
    // let questionId = $("#question_id").val();
    // let contene = $("#comment_content").val();
    if (!contene) {
        alert("回复的类容不能为空");
        return;
    }
    console.log(questionId + "<><>" + contene);
    $.ajax({
        type: "post",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": questionId,
            "content": contene,
            "type": type
        }),
        dataType: "json",
        success: function (data) {
            console.log(data);
            if (data.code == "200") {
                console.log("进来了---------");
                //$("#comment_section").hide();
                window.location.reload();
            } else {
                if (data.code == "2003") {
                    var isAccepted = confirm("你好没登陆，是否先登录?");
                    if (isAccepted) {
                        localStorage.setItem("closable", "true");
                        window.open("https://github.com/login/oauth/authorize?client_id=f88879001d2eec8aa7d6&redirect_uri=http://localhost:8080/callback&scope=user&state=90");
                    }

                } else {
                    alert(data.message);
                }
            }
        }
    })
}

function post() {
    let questionId = $("#question_id").val();
    let contene = $("#comment_content").val();
    comment2target(questionId, 1, contene);
}

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    comment2target(commentId, 2, content)
}


/*
展开二级评论
 */
function collapseComments(e) {

    var id = e.getAttribute("data-id");
    console.log(id);
    var comments = $("#comment-" + id);
    // comments.toggle("in");
    // var hasClass = comments.hasClass("in");
    // console.log(hasClass);

    //获取一下二级评论的展开状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        //折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        // $.getJSON("/comment/"+id,function (data) {
        //     console.log(data);
        // })
        var subCommentContainer = $("#comment-" + id);
        if (subCommentContainer.children().length != 1) {
            //展开二级评论
            comments.addClass("in");
            // 标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment) {
                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
                    })));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });
                //展开二级评论
                comments.addClass("in");
                // 标记二级评论展开状态
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });
        }
    }
}