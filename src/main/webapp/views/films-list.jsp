<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="./base.jsp" %>
</head>
<body class="pb-3 no-select">
    <%-- Navigation and page heading --%>
    <header class="text-center">
        <h1 class="text-[3rem] font-bold"><a href="/cloud/get-all-films">Film App</a></h1>
        <a class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" href="add-film">Add New Film</a>
    </header>

    <%-- Film listing table --%>
    <div class="p-4 m-3">
        <table class="bg-white backdrop-blur-md mx-auto shadow-lg p-4 rounded-lg">
            <thead>
                <tr>
                    <th class="p-2">Id</th>
                    <th class="p-2">Title</th>
                    <th class="p-2">Year</th>
                    <th class="p-2">Director</th>
                    <th class="p-2">Stars</th>
                    <th class="p-2">Review</th>
                    <th class="p-2">Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${films}" var="film">
                    <tr>
                        <td class="p-2">${film.id}</td>
                        <td class="p-2">${film.title}</td>
                        <td class="p-2">${film.year}</td>
                        <td class="p-2">${film.director}</td>
                        <td class="p-2">${film.stars}</td>
                        <td class="p-2">${film.review}</td>
                        <td class="p-2">
                            <a class="bg-blue-800 px-4 py-2 text-white hover:bg-black rounded-lg" href="update-film?id=${film.id}">Edit</a>
                            
                            <form action="./delete-film" method="POST" style="display:inline;">
                                <input type="hidden" name="id" value="${film.id}" />
                                <button type="submit" class="bg-red-800 px-4 py-2 text-white hover:bg-red-900 rounded-lg">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <%-- Optional scripts --%>
    <script src="./js/main.js"></script>
</body>
</html>
