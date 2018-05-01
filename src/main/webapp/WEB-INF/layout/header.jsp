    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <style type="text/css">

        div#logo {
        position: absolute;
        top: 0;
        left: 0;
        }

        div#logo img {
        width: 80px;
        }

        div#titre {
        position: absolute;
        top: 0;
        left: 100px;
        font-size: 24px;
        }

        div#menu {
        position: absolute;
        top: 30px;
        left: 100px;
        }


        </style>

        <div id="titre">Oseille</div>
        <div id="logo">
        <img src="<c:url value='/images/oseille.png'/>" />
        </div>
        <div id="menu">
        Menu 1 Menu 2
        <hr style="margin-left: -10px; margin-right: -10px;"/>
        </div>
