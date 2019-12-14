function setTags(e) {
    console.log(e.toString());
    var value = e.getAttribute("data-tag");
    var tag = $("#tag").val();

    if (tag.indexOf(value) == -1) {
        if (tag) {
            $("#tag").val(tag + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }

}

function shouTag() {
$("#selectTag").show();
}