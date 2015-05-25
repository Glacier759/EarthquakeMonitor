$("#toggle").click(function (event) {
    if ( document.getElementById("span-top").getAttribute("key") === "login" ) {
        event.preventDefault(), $("#toggle").find(".top").toggleClass("active"), $("#toggle").find(".middle").toggleClass("active"), $("#toggle").find(".bottom").toggleClass("active"), $("#overlay-form").toggleClass("open")
        document.getElementById("span-top").setAttribute("key", "toggle");
    }
    else {
        event.preventDefault(), $(this).find(".top").toggleClass("active"), $(this).find(".middle").toggleClass("active"), $(this).find(".bottom").toggleClass("active"), $("#overlay").toggleClass("open")
    }
});
$("#login").click(function (event) {
    event.preventDefault(), $("#toggle").find(".top").toggleClass("active"), $("#toggle").find(".middle").toggleClass("active"), $("#toggle").find(".bottom").toggleClass("active"), $("#overlay-form").toggleClass("open")
    document.getElementById("span-top").setAttribute("key","login");
});