<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<%@ include file="./base.jsp" %>
<body class="pb-3 no-select">
    <div>
        <h1 class="text-center text-[3rem] font-bold"><a href="/cloud/get-all-films">Film App</a></h1>
        <h2 class="text-red-600 text-center">Error</h2>
        <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative" role="alert">
            <strong class="font-bold">Error!</strong>
            <span class="block sm:inline">${errorMessage}</span>
        </div>
        <a class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded inline-block mt-4" href="get-all-films">Return to Films List</a>
    </div>
</body>
</html>
