<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<ul>
<c:forEach var="joueur" items="${joueurs}">
	<li>joueur.name</li>
</c:forEach>
</ul>