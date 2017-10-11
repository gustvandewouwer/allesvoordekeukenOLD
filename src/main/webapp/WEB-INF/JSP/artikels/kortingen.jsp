<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Artikelkortingen' />
<style>
td {
	text-align: right:
}
</style>
</head>
<body>
	<v:menu />
	<h1>Artikelkortingen</h1>
	<ul class='zonderbolletjes'>
		<c:forEach items='${artikels}' var='artikel'>
			<c:url value='' var='url'>
				<c:param name='id' value='${artikel.id}' />
			</c:url>
			<li><a href='${url}'>${artikel.naam}</a></li>
		</c:forEach>
	</ul>
	<c:if test='${not empty artikel}'>
		<h2>${artikel.naam}</h2>
		<table>
			<thead>
				<tr>
					<th>Vanaf aantal</th>
					<th>% korting</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items='${artikel.kortingen}' var='korting'>
					<tr>
						<td>${korting.vanafAantal }</td>
						<td><fmt:formatNumber value='${korting.kortingsPercentage}'
								minFractionDigits='2' maxFractionDigits='2' /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>