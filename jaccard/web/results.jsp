<%@ page import="java.util.Collection" %>
<%@ page import="ie.gmit.sw.domain.Result" %>
<%@ include file="includes/header.jsp" %>

<div class="animated bounceInDown" style="font-size:32pt; font-family:arial; color:#990000; font-weight:bold">Document Comparison Service</div>

</p>&nbsp;</p>&nbsp;</p>

<h2>Results...</h2>

<%
    Collection<Result> results = (Collection<Result>) request.getAttribute("results");

    results.forEach(result -> System.out.println(result.toString()));


%>

<%@ include file="includes/footer.jsp" %>
