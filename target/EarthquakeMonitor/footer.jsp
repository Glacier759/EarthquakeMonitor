<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
  $(function() {
    $(".unlogin").click(function(){
      document.getElementById("menu").click();
      document.getElementById("login").click();
    });
  });
  $(function() {
    $("#system").click(function() {
      document.getElementById("menu").click();
      $("#confirm").modal("toggle");
    });
  });
</script>
