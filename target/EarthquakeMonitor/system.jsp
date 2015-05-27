<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="confirm" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4>系统</h4>
      </div>
      <div id="system-body" class="modal-body"></div>
      <div class="modal-footer">
        <button id="system-start" type="button" class="btn btn-success">启动</button>
        <button id="system-stop" type="button" class="btn btn-danger">停止</button>
        <button type="button" class="btn btn-warning" data-dismiss="modal">退出</button>
      </div>
    </div>
  </div>
</div>