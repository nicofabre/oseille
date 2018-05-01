<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Transactions in the file</h1>

<form method="post" action="<c:url value='/import/step2' />">

<div>New Solde after import : <strong>${report.newSolde}</strong></div>

<table>
    <tr>
        <th>Date</th>
        <th>Label</th>
        <th>Amount</th>
    </tr>
    <c:forEach items="${report.transactions}" var="t">
        <tr>
            <td>${t.date}</td>
            <td>${t.label}</td>
            <td>${t.amount}</td>
        </tr>
    </c:forEach>
</table>
<input type="submit" value="Continue ?" />
</form>
