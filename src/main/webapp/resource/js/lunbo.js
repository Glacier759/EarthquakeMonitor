/**
 * Created by Administrator on 2015/3/29.
 */
window.onload=function(){
    var oDiv=document.getElementById("div1");
    var oUl=document.getElementsByName("ul1")[0];
    var aLi=document.getElementsByName("li");
    var speed=2;

    oUl.innerHTML+=oUl.innerHTML;
    var move=function(){
        if(oUl.offsetLeft<-oUl.offsetWidth/2) {
            oUl.style.left ="0";
        }
        if(oUl.offsetLeft>0){
            oUl.style.left=-oUl.offsetWidth/2+"px";
        }
        oUl.style.left=oUl.offsetLeft+speed+"px";
    };
    timer=setInterval(move,30);
    oUl.style.width=aLi[0].offsetWidth*aLi.length+"px";
    oDiv.onmouseover=function(){
        clearInterval(timer);
    }
    oDiv.onmouseout=function(){
        timer=setInterval(move,30);
    }
    document.getElementById("a1").onclick=function(){
        speed=-2;
    }
    document.getElementById("a2").onclick=function(){
        speed=2;
    }
};
