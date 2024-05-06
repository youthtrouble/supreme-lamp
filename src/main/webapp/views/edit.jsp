<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<%@ include file="./base.jsp" %>
<body class="pb-3 no-select">
    <div>
        <h1 class="text-center text-[3rem] font-bold"><a href="/cloud/get-all-films">Film App</a></h1>
        <form action="update-film" method="post" class="p-4 m-3">
            <input type="hidden" name="id" value="${film.id}" />
            <div class="mb-4">
                <label for="title" class="block text-gray-700 text-sm font-bold mb-2">Title:</label>
                <input type="text" name="title" id="title" value="${film.title}" required class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"/>
            </div>
            <div class="mb-4">
                <label for="year" class="block text-gray-700 text-sm font-bold mb-2">Year:</label>
                <input type="number" name="year" id="year" value="${film.year}" required class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"/>
            </div>
            <div class="mb-4">
                <label for="director" class="block text-gray-700 text-sm font-bold mb-2">Director:</label>
                <input type="text" name="director" id="director" value="${film.director}" required class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"/>
            </div>
            <div class="mb-4">
                <label for="stars" class="block text-gray-700 text-sm font-bold mb-2">Stars:</label>
                <input type="text" name="stars" id="stars" value="${film.stars}" required class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"/>
            </div>
            <div class="mb-4">
                <label for="review" class="block text-gray-700 text-sm font-bold mb-2">Review:</label>
                <textarea name="review" id="review" required class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">${film.review}</textarea>
            </div>
            <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Update Film</button>
        </form>
    </div>
</body>
</html>
