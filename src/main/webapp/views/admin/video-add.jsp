<form action="${pageContext.request.contextPath}/admin/video/insert" method="post">
    <label for="title">Title:</label>
    <input type="text" name="title" required><br>
    
    <label for="description">Description:</label>
    <textarea name="description" required></textarea><br>
    
    <label for="videoUrl">Video URL:</label>
    <input type="text" name="videoUrl" required><br>
    
    <label for="status">Status:</label>
    <input type="number" name="status" required><br>

    <button type="submit">Add Video</button>
</form>
