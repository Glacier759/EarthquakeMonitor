$("#menu").click(function (event) {
    if ( document.getElementById("span-top").getAttribute("key") === "login" ) {
        event.preventDefault(), $("#menu").find(".top").toggleClass("active"), $("#menu").find(".middle").toggleClass("active"), $("#menu").find(".bottom").toggleClass("active"), $("#overlay-form").toggleClass("open")
        document.getElementById("span-top").setAttribute("key", "menu");
    }
    else {
        event.preventDefault(), $(this).find(".top").toggleClass("active"), $(this).find(".middle").toggleClass("active"), $(this).find(".bottom").toggleClass("active"), $("#overlay").toggleClass("open")
    }
});
$("#login").click(function (event) {
    event.preventDefault(), $("#menu").find(".top").toggleClass("active"), $("#menu").find(".middle").toggleClass("active"), $("#menu").find(".bottom").toggleClass("active"), $("#overlay-form").toggleClass("open")
    document.getElementById("span-top").setAttribute("key","login");
});