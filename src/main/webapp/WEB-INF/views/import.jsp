<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Import</h1>

<form enctype="multipart/form-data" method="post" action="<c:url value='/import/step1' />">
<table>
    <tr>
        <td>File : </td>
        <td> <input type="file" name="file" /> </td>
    </tr>
</table>
<input type="submit" />
</form>