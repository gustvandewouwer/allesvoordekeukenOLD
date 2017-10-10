<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='${empty artikel ? "artikel zoeken" : artikel.naam}' />
</head>
<body>
	<v:menu />
	<h1>Artikel zoeken</h1>
	<form>
		<label>Nummer:<span>${fouten.id}</span> <input name='id'
			value='${param.id}' required autofocus type='number' min='1'></label>
		<input type='submit' value='Zoeken'>
	</form>
	<c:if test='${not empty param and empty fouten and empty artikel}'>
		Artikel niet gevonden
	</c:if>
	<c:if test='${not empty artikel}'>
		<dl>
			<dt>Naam</dt>
			<dd>${artikel.naam}</dd>
			<dt>Aankoopprijs</dt>
			<dd>
				<fmt:formatNumber value='${artikel.aankoopprijs}' />
			</dd>
			<dt>Verkoopprijs</dt>
			<dd>
				<fmt:formatNumber value='${artikel.verkoopprijs}' />
			</dd>
			<dt>Winst</dt>
			<dd>
				<fmt:formatNumber value='${artikel.winstPercentage}' />
				%
			</dd>
		</dl>
	</c:if>
</body>
</html>