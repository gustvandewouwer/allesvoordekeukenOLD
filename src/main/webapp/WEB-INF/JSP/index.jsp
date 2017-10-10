<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="v" uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<v:head title='Allesvoordekeuken' />
<body>
	<header>
		<v:menu />
		<h1>Alles voor de keuken</h1>
		<img src='<c:url value="/images/logo${random}.jpg"/>' alt='keuken' id='logo' />
	</header>
</body>
</html>
